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

package top.continew.admin.wms.config;

import cn.crane4j.core.container.Container;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import top.continew.admin.wms.constant.WmsConstants;
import top.continew.admin.wms.model.entity.AddrDO;
import top.continew.admin.wms.service.WhseAddrService;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class WhseAddrContainer implements Container<Long> {

    private final WhseAddrService whseAddrService;

    public String getNamespace() {
        return WmsConstants.addrContainer;
    }

    public Map<Long, AddrDO> get(Collection<Long> ids) {
        List<AddrDO> addrs = whseAddrService.listByIds(ids.stream().toList());
        return addrs.stream().collect(Collectors.toMap(AddrDO::getId, Function.identity()));
    }
}