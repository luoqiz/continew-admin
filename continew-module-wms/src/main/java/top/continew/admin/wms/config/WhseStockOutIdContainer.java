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
import top.continew.admin.wms.model.entity.WhseStockOutDO;
import top.continew.admin.wms.service.impl.WhseStockOutServiceImpl;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class WhseStockOutIdContainer implements Container<Long> {

    private final WhseStockOutServiceImpl whseStockOutService;

    public String getNamespace() {
        return WmsConstants.whseStockOutIdContainer;
    }

    public Map<Long, WhseStockOutDO> get(Collection<Long> ids) {
        List<WhseStockOutDO> datas = whseStockOutService.listByIds(ids);
        return datas.stream().collect(Collectors.toMap(WhseStockOutDO::getId, Function.identity()));
    }
}