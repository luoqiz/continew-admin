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

package top.continew.admin.tenant.util;

import cn.hutool.core.util.StrUtil;

import java.net.IDN;
import java.util.Locale;

/**
 * 租户域名规范化工具
 *
 * @author Codex
 */
public final class TenantDomainUtils {

    private TenantDomainUtils() {
    }

    /**
     * 规范化域名。域名只保存主机名，不保存协议、路径或端口。
     *
     * @param domain 域名
     * @return 规范化后的域名；空值返回 {@code null}
     */
    public static String normalize(String domain) {
        if (StrUtil.isBlank(domain)) {
            return null;
        }
        // 数据库和请求 Host 统一只使用主机名，协议、路径、查询参数和片段均不属于租户域名。
        String value = domain.trim();
        if (value.contains("://") || value.contains("/") || value.contains("?")
            || value.contains("#")) {
            throw new IllegalArgumentException("域名只能填写主机名");
        }
        if (value.startsWith("[") && value.contains("]")) {
            value = value.substring(1, value.indexOf(']'));
        } else if (value.indexOf(':') == value.lastIndexOf(':') && value.contains(":")) {
            value = value.substring(0, value.indexOf(':'));
        }
        while (value.endsWith(".")) {
            value = value.substring(0, value.length() - 1);
        }
        if (value.isEmpty()) {
            return null;
        }
        // 转为 ASCII（兼容国际化域名）并统一小写，保证缓存键和唯一索引使用同一种表示。
        return IDN.toASCII(value, IDN.USE_STD3_ASCII_RULES).toLowerCase(Locale.ROOT);
    }
}
