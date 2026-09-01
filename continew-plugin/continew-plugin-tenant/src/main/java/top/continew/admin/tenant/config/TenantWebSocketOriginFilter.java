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

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;
import top.continew.admin.tenant.util.TenantDomainUtils;

import java.io.IOException;
import java.net.URI;

/**
 * WebSocket 来源校验过滤器
 *
 * <p>租户域名来自数据库，Starter 的 allowed-origins 无法在启动时完整枚举。
 * Starter 继续使用通配配置以支持动态域名，本过滤器强制浏览器 Origin 与当前 Host 一致，
 * 防止跨站 WebSocket 握手。</p>
 *
 * @author Codex
 */
public class TenantWebSocketOriginFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain) throws ServletException, IOException {
        String origin = request.getHeader("Origin");
        if (origin == null || origin.isBlank()) {
            // 非浏览器客户端可能不发送 Origin，保留其 WebSocket 接入能力。
            filterChain.doFilter(request, response);
            return;
        }
        // 浏览器携带 Origin 时必须与当前访问 Host 和协议一致，防止跨租户站点发起握手。
        if (!this.isSameHost(request, origin)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "WebSocket 来源不受信任");
            return;
        }
        filterChain.doFilter(request, response);
    }

    private boolean isSameHost(HttpServletRequest request, String origin) {
        try {
            // Origin 只允许是合法的 http(s) 源，不接受路径、查询参数、片段或用户信息。
            URI originUri = URI.create(origin);
            if (!("http".equalsIgnoreCase(originUri.getScheme())
                || "https".equalsIgnoreCase(originUri.getScheme()))
                || originUri.getHost() == null || originUri.getRawUserInfo() != null
                || originUri.getRawPath() != null && !originUri.getRawPath().isEmpty()
                || originUri.getRawQuery() != null
                || originUri.getRawFragment() != null) {
                return false;
            }
            String requestHost = TenantDomainUtils.normalize(request.getServerName());
            String originHost = TenantDomainUtils.normalize(originUri.getHost());
            return requestHost != null && requestHost.equals(originHost)
                && this.isSameScheme(request, originUri.getScheme());
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    private boolean isSameScheme(HttpServletRequest request, String originScheme) {
        String forwardedProto = request.getHeader("X-Forwarded-Proto");
        String requestScheme = forwardedProto == null || forwardedProto.isBlank()
            ? request.getScheme()
            : forwardedProto.split(",", 2)[0].trim();
        return originScheme.equalsIgnoreCase(requestScheme);
    }
}
