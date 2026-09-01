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
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import top.continew.admin.common.config.TenantExtensionProperties;
import top.continew.admin.tenant.service.TenantService;
import top.continew.starter.core.util.ServletUtils;
import top.continew.starter.core.util.validation.CheckUtils;
import top.continew.starter.extension.tenant.config.TenantProvider;
import top.continew.starter.extension.tenant.context.TenantContext;

/**
 * 默认租户提供者
 *
 * @author 小熊
 * @author Charles7c
 * @since 2024/12/12 15:35
 */
@RequiredArgsConstructor
public class DefaultTenantProvider implements TenantProvider {

    private final TenantExtensionProperties tenantExtensionProperties;
    private final TenantService tenantService;

    @Override
    public TenantContext getByTenantId(String tenantIdAsString, boolean verify) {
        TenantContext context = new TenantContext();
        Long defaultTenantId = tenantExtensionProperties.getDefaultTenantId();
        context.setTenantId(defaultTenantId);
        // 平台入口注入默认租户 ID，直接返回，确保客户端无法通过请求头切换平台租户。
        if (defaultTenantId.toString().equals(tenantIdAsString)) {
            return context;
        }
        Long tenantId;
        // 域名入口已经由过滤器注入租户 ID；只有兼容登录请求才可能需要从租户编码解析。
        if (StrUtil.isBlank(tenantIdAsString)) {
            // 检查是否指定了租户编码（登录相关接口）
            HttpServletRequest request = ServletUtils.getRequest();
            String tenantCode = request.getHeader(tenantExtensionProperties.getTenantCodeHeader());
            if (StrUtil.isBlank(tenantCode)) {
                return context;
            }
            Long id = tenantService.getIdByCode(tenantCode);
            CheckUtils.throwIfNull(id, "编码为 [%s] 的租户不存在".formatted(tenantCode));
            tenantId = id;
        } else {
            // 兼容入口已提供租户 ID，继续使用 Starter 原有的 ID 解析逻辑。
            tenantId = Long.parseLong(tenantIdAsString);
        }
        // 登录和业务请求均需校验租户状态，防止禁用或过期租户继续获得上下文。
        if (verify) {
            tenantService.checkStatus(tenantId);
        }
        context.setTenantId(tenantId);
        return context;
    }
}
