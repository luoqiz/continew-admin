/*
 * Copyright (c) 2022-present Charles7c Authors. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package top.continew.admin.auth.service;

import cn.hutool.core.util.StrUtil;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import top.continew.admin.auth.constant.AuthConstants;
import top.continew.starter.core.util.validation.ValidationUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.Locale;

/**
 * 社交登录租户上下文状态服务
 *
 * <p>社交登录回调可能从普通租户域名跳转到平台域名，租户上下文需要通过 state
 * 传递。租户 ID 使用 HMAC 签名，避免客户端篡改 state 后切换到其他租户。</p>
 *
 * @author Codex
 */
@Service
public class TenantAuthStateService {

    private static final String HMAC_ALGORITHM = "HmacSHA256";
    private static final String STATE_VERSION = "v1";
    private static final String STATE_SEPARATOR = ".";
    private static final String FIELD_SEPARATOR = "\u0000";

    private final String secret;
    private final boolean enabled;

    public TenantAuthStateService(@Value("${tenant-auth.social-state-secret:}") String secret,
        @Value("${tenant-auth.enabled:false}") boolean enabled) {
        this.secret = secret;
        this.enabled = enabled;
    }

    /**
     * 校验生产入口配置
     */
    @PostConstruct
    public void validateConfiguration() {
        if (enabled && StrUtil.isBlank(secret)) {
            throw new IllegalStateException("请配置 tenant-auth.social-state-secret");
        }
    }

    /**
     * 创建包含租户上下文的 state
     *
     * @param state          原始 state
     * @param tenantId       租户 ID
     * @param defaultTenantId 默认租户 ID
     * @return state
     */
    public String createState(String state, Long tenantId, Long defaultTenantId,
        String targetHost) {
        // 开发环境可以未配置签名密钥，此时保持原有 JustAuth state 行为。
        if (StrUtil.isBlank(secret) || StrUtil.isBlank(targetHost)) {
            return state;
        }
        // 将原始 state、租户 ID 和回调目标 Host 一起签名，防止用户篡改后切换租户。
        String tenantIdValue = tenantId == null ? "" : tenantId.toString();
        String payload = String.join(FIELD_SEPARATOR, state, tenantIdValue,
            targetHost.trim().toLowerCase(Locale.ROOT));
        String encodedPayload = Base64.getUrlEncoder().withoutPadding()
            .encodeToString(payload.getBytes(StandardCharsets.UTF_8));
        String signedPayload = STATE_VERSION + STATE_SEPARATOR + encodedPayload;
        return signedPayload + STATE_SEPARATOR + this.sign(signedPayload);
    }

    /**
     * 校验并解析 state 中的租户 ID
     *
     * @param state state
     * @return 租户 ID；未携带租户上下文时返回 {@code null}
     */
    public Long resolveTenantId(String state) {
        return this.resolveState(state).tenantId();
    }

    /**
     * 校验并解析 state
     *
     * @param state state
     * @return state 上下文
     */
    public TenantAuthState resolveState(String state) {
        if (StrUtil.isBlank(state)) {
            return new TenantAuthState(null, null);
        }
        if (state.startsWith(STATE_VERSION + STATE_SEPARATOR)) {
            // 新格式携带回调目标 Host，用于社交平台回调后返回原租户域名。
            return this.resolveCurrentState(state);
        }

        // 兼容已经发出的旧格式：原始 state.租户 ID.签名，仅恢复租户 ID。
        int signatureSeparator = state.lastIndexOf(AuthConstants.SOCIAL_TENANT_SEPARATOR);
        int tenantSeparator = state.lastIndexOf(AuthConstants.SOCIAL_TENANT_SEPARATOR,
            signatureSeparator - 1);
        if (tenantSeparator < 0 || signatureSeparator == state.length() - 1) {
            return new TenantAuthState(null, null);
        }
        String payload = state.substring(0, signatureSeparator);
        String signature = state.substring(signatureSeparator + 1);
        this.verify(payload, signature);
        try {
            return new TenantAuthState(
                Long.valueOf(state.substring(tenantSeparator + 1, signatureSeparator)), null);
        } catch (NumberFormatException e) {
            ValidationUtils.throwIf(true, "社交登录状态无效，请重新授权");
            return new TenantAuthState(null, null);
        }
    }

    private TenantAuthState resolveCurrentState(String state) {
        int payloadSeparator = state.indexOf(STATE_SEPARATOR);
        int signatureSeparator = state.lastIndexOf(STATE_SEPARATOR);
        if (payloadSeparator < 0 || payloadSeparator == signatureSeparator
            || signatureSeparator == state.length() - 1) {
            ValidationUtils.throwIf(true, "社交登录状态无效，请重新授权");
            return new TenantAuthState(null, null);
        }
        String signedPayload = state.substring(0, signatureSeparator);
        this.verify(signedPayload, state.substring(signatureSeparator + 1));
        try {
            String payload = new String(Base64.getUrlDecoder()
                .decode(state.substring(payloadSeparator + 1, signatureSeparator)),
                StandardCharsets.UTF_8);
            String[] fields = payload.split(FIELD_SEPARATOR, -1);
            if (fields.length != 3 || StrUtil.isBlank(fields[0]) || StrUtil.isBlank(fields[2])) {
                throw new IllegalArgumentException();
            }
            Long tenantId = StrUtil.isBlank(fields[1]) ? null : Long.valueOf(fields[1]);
            return new TenantAuthState(tenantId, fields[2]);
        } catch (Exception e) {
            ValidationUtils.throwIf(true, "社交登录状态无效，请重新授权");
            return new TenantAuthState(null, null);
        }
    }

    private void verify(String payload, String signature) {
        String expectedSignature = this.sign(payload);
        // 使用定时无关的比较方式，避免通过响应时间推测签名内容。
        boolean valid = MessageDigest.isEqual(
            expectedSignature.getBytes(StandardCharsets.US_ASCII),
            signature.getBytes(StandardCharsets.US_ASCII));
        ValidationUtils.throwIf(!valid, "社交登录状态无效，请重新授权");
    }

    /**
     * 社交登录 state 上下文
     *
     * @param tenantId   租户 ID
     * @param targetHost 回调后应返回的前端主机
     */
    public record TenantAuthState(Long tenantId, String targetHost) {
    }

    private String sign(String payload) {
        ValidationUtils.throwIfBlank(secret, "请配置 tenant-auth.social-state-secret");
        try {
            Mac mac = Mac.getInstance(HMAC_ALGORITHM);
            mac.init(new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), HMAC_ALGORITHM));
            return Base64.getUrlEncoder().withoutPadding()
                .encodeToString(mac.doFinal(payload.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            throw new IllegalStateException("社交登录状态签名失败", e);
        }
    }
}
