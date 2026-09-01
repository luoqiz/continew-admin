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

import jakarta.servlet.http.HttpServletRequest;

/**
 * 当前请求的租户认证入口上下文
 *
 * @author Codex
 */
public record TenantAuthContext(TenantAuthMode mode, String host, Long tenantId) {

    /**
     * 请求属性名称；由入口过滤器写入，供平台权限拦截器识别当前访问入口。
     */
    public static final String REQUEST_ATTRIBUTE = TenantAuthContext.class.getName();

    /**
     * 获取当前请求上下文
     *
     * @param request 请求
     * @return 上下文；入口模式解析未启用时按兼容模式处理
     */
    public static TenantAuthContext get(HttpServletRequest request) {
        Object context = request.getAttribute(REQUEST_ATTRIBUTE);
        if (context instanceof TenantAuthContext tenantAuthContext) {
            return tenantAuthContext;
        }
        return new TenantAuthContext(TenantAuthMode.LEGACY, null, null);
    }

    /**
     * 是否为平台管理入口
     *
     * @param request 请求
     * @return 是否为平台管理入口
     */
    public static boolean isPlatform(HttpServletRequest request) {
        return TenantAuthMode.PLATFORM.equals(get(request).mode());
    }
}
