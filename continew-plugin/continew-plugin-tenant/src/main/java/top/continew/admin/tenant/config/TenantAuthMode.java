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

/**
 * 租户认证模式
 *
 * @author Codex
 */
public enum TenantAuthMode {

    /**
     * 平台管理域名模式，固定使用默认租户
     */
    PLATFORM,

    /**
     * 普通租户域名模式，根据数据库中的域名解析租户
     */
    DOMAIN,

    /**
     * 兼容模式，根据请求头解析租户
     */
    LEGACY
}
