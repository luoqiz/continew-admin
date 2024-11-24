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

package top.continew.admin.common.util;

import cn.hutool.core.util.StrUtil;
import pl.jalokim.propertiestojson.util.PropertiesToJsonConverter;

import java.util.*;

public class PropsUtils {

    public static String props2json(String... props) {
        Map<String, String> properties = new HashMap<>();
        List<String> lines = new ArrayList<>();
        for (String prop : props) {
            if (StrUtil.isEmpty(prop)) continue;
            lines.addAll(Arrays.stream(prop.split("\n")).filter(item -> StrUtil.isNotEmpty(item)).toList());
        }
        for (String item : lines) {
            String[] list = item.split(": ");
            if (list.length == 2) {
                System.out.println(list[0] + "---" + list[1]);
                properties.put(list[0], list[1]);
            }
        }
        return new PropertiesToJsonConverter().convertToJson(properties);
    }
}
