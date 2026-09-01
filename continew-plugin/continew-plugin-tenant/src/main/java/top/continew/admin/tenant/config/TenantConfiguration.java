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

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.continew.admin.common.config.TenantExtensionProperties;
import top.continew.admin.tenant.service.TenantService;
import top.continew.starter.core.constant.OrderedConstants;
import top.continew.starter.extension.tenant.annotation.ConditionalOnEnabledTenant;
import top.continew.starter.extension.tenant.autoconfigure.TenantProperties;
import top.continew.starter.extension.tenant.config.TenantProvider;

/**
 * 租户配置
 *
 * @author Charles7c
 * @since 2025/7/12 13:30
 */
@Configuration
public class TenantConfiguration implements WebMvcConfigurer {

    private final TenantPlatformAdminInterceptor tenantPlatformAdminInterceptor =
        new TenantPlatformAdminInterceptor();

    /**
     * 租户扩展配置属性
     */
    @Bean
    public TenantExtensionProperties tenantExtensionProperties() {
        return new TenantExtensionProperties();
    }

    /**
     * 租户认证入口配置
     */
    @Bean
    @ConfigurationProperties(prefix = "tenant-auth")
    public TenantAuthProperties tenantAuthProperties() {
        return new TenantAuthProperties();
    }

    /**
     * 租户认证入口过滤器
     */
    @Bean
    @ConditionalOnEnabledTenant
    public FilterRegistrationBean<TenantAuthModeFilter> tenantAuthModeFilter(
        TenantAuthProperties properties,
        TenantExtensionProperties tenantExtensionProperties,
        TenantProperties tenantProperties,
        TenantService tenantService) {
        FilterRegistrationBean<TenantAuthModeFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new TenantAuthModeFilter(properties, tenantExtensionProperties,
            tenantProperties, tenantService));
        registration.addUrlPatterns("/*");
        registration.setName("tenantAuthModeFilter");
        registration.setOrder(OrderedConstants.Filter.TRACE_FILTER - 50);
        registration.setAsyncSupported(true);
        return registration;
    }

    /**
     * WebSocket 来源校验过滤器
     */
    @Bean
    @ConditionalOnEnabledTenant
    public FilterRegistrationBean<TenantWebSocketOriginFilter> tenantWebSocketOriginFilter() {
        FilterRegistrationBean<TenantWebSocketOriginFilter> registration =
            new FilterRegistrationBean<>();
        registration.setFilter(new TenantWebSocketOriginFilter());
        registration.addUrlPatterns("/websocket", "/websocket/*");
        registration.setName("tenantWebSocketOriginFilter");
        registration.setOrder(OrderedConstants.Filter.TRACE_FILTER - 40);
        registration.setAsyncSupported(true);
        return registration;
    }

    /**
     * 租户提供者
     */
    @Bean
    @ConditionalOnEnabledTenant
    public TenantProvider tenantProvider(TenantExtensionProperties tenantExtensionProperties,
        TenantService tenantService) {
        return new DefaultTenantProvider(tenantExtensionProperties, tenantService);
    }

    /**
     * API 文档分组配置
     */
    @Bean
    public GroupedOpenApi tenantModuleApi() {
        return GroupedOpenApi.builder().group("tenant").displayName("租户管理")
            .pathsToMatch("/tenant/**").build();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 租户管理接口必须同时满足“平台域名”和“超级管理员”两个条件。
        registry.addInterceptor(this.tenantPlatformAdminInterceptor)
            .addPathPatterns("/tenant/management/**", "/tenant/package/**")
            .order(OrderedConstants.Interceptor.AUTH_INTERCEPTOR + 100);
    }
}
