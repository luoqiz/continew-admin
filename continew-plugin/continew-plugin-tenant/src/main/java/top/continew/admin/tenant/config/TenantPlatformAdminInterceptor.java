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
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import top.continew.admin.common.context.UserContextHolder;
import top.continew.starter.core.util.ServletUtils;
import top.continew.starter.json.jackson.util.JSONUtils;
import top.continew.starter.web.model.R;

/**
 * 平台租户管理访问控制
 *
 * @author Codex
 */
public class TenantPlatformAdminInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
        HttpServletResponse response,
        Object handler) {
        // 入口模式未启用时保持原有单租户/兼容访问行为
        if (!(request
            .getAttribute(TenantAuthContext.REQUEST_ATTRIBUTE) instanceof TenantAuthContext)) {
            return true;
        }
        // 平台能力必须同时满足平台域名和超级管理员身份，不能仅依赖任一条件。
        if (!TenantAuthContext.isPlatform(request) || !UserContextHolder.isSuperAdmin()) {
            response.setCharacterEncoding("UTF-8");
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            ServletUtils.writeJSON(response, JSONUtils.toJsonStr(
                R.fail(String.valueOf(HttpServletResponse.SC_FORBIDDEN), "仅平台超级管理员可访问")));
            return false;
        }
        return true;
    }
}
