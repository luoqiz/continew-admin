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

package top.continew.admin.tenant.config;

import cn.hutool.core.util.StrUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import top.continew.admin.common.config.TenantExtensionProperties;
import top.continew.admin.tenant.service.TenantService;
import top.continew.admin.tenant.util.TenantDomainUtils;
import top.continew.starter.core.util.ServletUtils;
import top.continew.starter.extension.tenant.autoconfigure.TenantProperties;
import top.continew.starter.json.jackson.util.JSONUtils;
import top.continew.starter.web.model.R;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * 租户认证入口模式过滤器
 *
 * <p>平台域名和已配置的租户域名由服务端注入租户 ID；兼容入口保留 Starter 原有的请求头解析。</p>
 *
 * @author Codex
 */
@Slf4j
public class TenantAuthModeFilter extends OncePerRequestFilter {

    private static final String OPTIONS = "OPTIONS";

    private final TenantAuthProperties properties;
    private final TenantExtensionProperties tenantExtensionProperties;
    private final TenantProperties tenantProperties;
    private final TenantService tenantService;
    private final AntPathMatcher pathMatcher = new AntPathMatcher();

    public TenantAuthModeFilter(TenantAuthProperties properties,
        TenantExtensionProperties tenantExtensionProperties,
        TenantProperties tenantProperties,
        TenantService tenantService) {
        this.properties = properties;
        this.tenantExtensionProperties = tenantExtensionProperties;
        this.tenantProperties = tenantProperties;
        this.tenantService = tenantService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain) throws ServletException, IOException {
        // 预检请求不参与租户解析，否则浏览器的 CORS 预检会因没有租户头而被提前拒绝。
        if (!properties.isEnabled() || OPTIONS.equalsIgnoreCase(request.getMethod())) {
            filterChain.doFilter(request, response);
            return;
        }

        TenantAuthContext context;
        try {
            // 先根据 Host 确定认证入口，再交给租户 Starter 使用统一的租户上下文。
            context = this.resolve(request);
        } catch (TenantAuthException e) {
            this.writeError(response, e.getStatus(), e.getMessage());
            return;
        } catch (Exception e) {
            log.error("租户认证入口解析失败，URI：{}", request.getRequestURI(), e);
            this.writeError(response, HttpStatus.INTERNAL_SERVER_ERROR, "租户解析失败，请联系管理员");
            return;
        }

        request.setAttribute(TenantAuthContext.REQUEST_ATTRIBUTE, context);
        if (TenantAuthMode.LEGACY.equals(context.mode())) {
            // 兼容入口完全沿用客户端租户头；WebSocket 则额外兼容 query 中的 tenantId。
            filterChain.doFilter(this.wrapLegacyWebSocketRequest(request), response);
            return;
        }

        // 平台域名和普通租户域名以服务端解析结果为准，覆盖客户端可能伪造的租户头。
        HttpServletRequest wrappedRequest = new TenantHeaderRequestWrapper(request,
            tenantProperties.getTenantIdHeader(),
            tenantExtensionProperties.getTenantCodeHeader(),
            String.valueOf(context.tenantId()), null);
        filterChain.doFilter(wrappedRequest, response);
    }

    private TenantAuthContext resolve(HttpServletRequest request) {
        String host = this.normalizeHost(request.getServerName());
        // 解析优先级固定为：平台配置域名 > 数据库租户域名 > 兼容入口域名 > 拒绝访问。
        if (this.containsDomain(properties.getPlatformDomains(), host)) {
            // 平台入口固定使用默认租户（通常为 0），不允许用请求头切换到其他租户。
            return new TenantAuthContext(TenantAuthMode.PLATFORM, host,
                tenantExtensionProperties.getDefaultTenantId());
        }

        if (StrUtil.isNotBlank(host)) {
            // 普通租户域名由租户管理功能写入数据库，因此无需在 yml 中逐一配置。
            Long tenantId = tenantService.getIdByDomain(host);
            if (tenantId != null) {
                return new TenantAuthContext(TenantAuthMode.DOMAIN, host, tenantId);
            }
        }

        if (this.containsDomain(properties.getLegacyDomains(), host)) {
            // 未配置域名的租户通过旧的 X-Tenant-Id / X-Tenant-Code 方式访问。
            this.checkLegacyHeader(request);
            return new TenantAuthContext(TenantAuthMode.LEGACY, host, null);
        }

        if (!properties.isRejectUnknownHost()) {
            // 仅在显式关闭拒绝策略时，才允许未知 Host 回退到兼容模式。
            this.checkLegacyHeader(request);
            return new TenantAuthContext(TenantAuthMode.LEGACY, host, null);
        }
        throw new TenantAuthException(HttpStatus.BAD_REQUEST,
            "当前访问域名未配置，请使用正确的系统入口");
    }

    private void checkLegacyHeader(HttpServletRequest request) {
        if (!properties.isLegacyRequireHeader() || this.isLegacyHeaderExcluded(request)) {
            return;
        }
        // 普通 HTTP 请求使用租户头；WebSocket 握手常通过 query 传递 tenantId，因此两者均需支持。
        boolean hasTenantId =
            StrUtil.isNotBlank(request.getHeader(tenantProperties.getTenantIdHeader()));
        boolean hasTenantCode =
            StrUtil.isNotBlank(request.getHeader(tenantExtensionProperties.getTenantCodeHeader()));
        boolean hasWebSocketTenantId = this.isWebSocketRequest(request)
            && StrUtil.isNotBlank(request.getParameter("tenantId"));
        if (!hasTenantId && !hasTenantCode && !hasWebSocketTenantId) {
            throw new TenantAuthException(HttpStatus.BAD_REQUEST, "请指定租户 ID 或租户编码");
        }
    }

    private HttpServletRequest wrapLegacyWebSocketRequest(HttpServletRequest request) {
        // Starter 统一读取请求头。为兼容旧前端的 WebSocket query 参数，这里将 tenantId 映射为请求头。
        boolean hasTenantHeader =
            StrUtil.isNotBlank(request.getHeader(tenantProperties.getTenantIdHeader()))
                || StrUtil.isNotBlank(
                    request.getHeader(tenantExtensionProperties.getTenantCodeHeader()));
        if (!this.isWebSocketRequest(request) || hasTenantHeader) {
            return request;
        }
        String tenantId = request.getParameter("tenantId");
        if (StrUtil.isBlank(tenantId)) {
            return request;
        }
        return new TenantHeaderRequestWrapper(request,
            tenantProperties.getTenantIdHeader(),
            tenantExtensionProperties.getTenantCodeHeader(),
            tenantId, null);
    }

    private boolean isWebSocketRequest(HttpServletRequest request) {
        return "websocket".equalsIgnoreCase(request.getHeader("Upgrade"));
    }

    private boolean isLegacyHeaderExcluded(HttpServletRequest request) {
        String path = request.getRequestURI().substring(request.getContextPath().length());
        // 白名单用于验证码、认证上下文等登录前公共接口，避免它们被要求先提供租户信息。
        return properties.getLegacyHeaderExcludes().stream()
            .anyMatch(pattern -> pathMatcher.match(pattern, path));
    }

    private boolean containsDomain(List<String> domains, String host) {
        if (StrUtil.isBlank(host)) {
            return false;
        }
        return domains.stream()
            .map(this::normalizeHost)
            .anyMatch(host::equals);
    }

    private String normalizeHost(String host) {
        try {
            return TenantDomainUtils.normalize(host);
        } catch (IllegalArgumentException e) {
            throw new TenantAuthException(HttpStatus.BAD_REQUEST, "访问域名格式不正确");
        }
    }

    private void writeError(HttpServletResponse response, HttpStatus status, String message)
        throws IOException {
        response.setStatus(status.value());
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        ServletUtils.writeJSON(response,
            JSONUtils.toJsonStr(R.fail(String.valueOf(status.value()), message)));
    }

    private static final class TenantAuthException extends RuntimeException {

        private final HttpStatus status;

        private TenantAuthException(HttpStatus status, String message) {
            super(message);
            this.status = status;
        }

        private HttpStatus getStatus() {
            return status;
        }
    }

    private static final class TenantHeaderRequestWrapper extends HttpServletRequestWrapper {

        private final String tenantIdHeader;
        private final String tenantCodeHeader;
        private final String tenantId;
        private final String tenantCode;

        private TenantHeaderRequestWrapper(HttpServletRequest request,
            String tenantIdHeader,
            String tenantCodeHeader,
            String tenantId,
            String tenantCode) {
            super(request);
            this.tenantIdHeader = tenantIdHeader;
            this.tenantCodeHeader = tenantCodeHeader;
            this.tenantId = tenantId;
            this.tenantCode = tenantCode;
        }

        /**
         * 伪造服务端解析出的租户头，使下游既有租户 Starter 无需感知新的域名入口。
         */
        @Override
        public String getHeader(String name) {
            if (this.matches(name, tenantIdHeader)) {
                return tenantId;
            }
            if (this.matches(name, tenantCodeHeader)) {
                return tenantCode;
            }
            return super.getHeader(name);
        }

        @Override
        public Enumeration<String> getHeaders(String name) {
            if (this.matches(name, tenantIdHeader)) {
                return Collections.enumeration(List.of(tenantId));
            }
            if (this.matches(name, tenantCodeHeader)) {
                return tenantCode == null ? Collections.emptyEnumeration()
                    : Collections.enumeration(List.of(tenantCode));
            }
            return super.getHeaders(name);
        }

        @Override
        public Enumeration<String> getHeaderNames() {
            Set<String> names = new LinkedHashSet<>();
            Enumeration<String> headerNames = super.getHeaderNames();
            while (headerNames != null && headerNames.hasMoreElements()) {
                String name = headerNames.nextElement();
                if (!this.matches(name, tenantIdHeader) && !this.matches(name, tenantCodeHeader)) {
                    names.add(name);
                }
            }
            if (tenantId != null) {
                names.add(tenantIdHeader);
            }
            if (tenantCode != null) {
                names.add(tenantCodeHeader);
            }
            return Collections.enumeration(names);
        }

        private boolean matches(String left, String right) {
            return left != null && right != null && left.equalsIgnoreCase(right);
        }
    }
}
