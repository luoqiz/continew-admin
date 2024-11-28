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
import top.continew.admin.wms.model.entity.WhseStockMoveDO;
import top.continew.admin.wms.service.impl.WhseStockMoveServiceImpl;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class WhseStockMoveIdContainer implements Container<Long> {

    private final WhseStockMoveServiceImpl whseStockMoveService;

    public String getNamespace() {
        return WmsConstants.whseStockMoveIdContainer;
    }

    public Map<Long, WhseStockMoveDO> get(Collection<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return null;
        }
        List<WhseStockMoveDO> datas = whseStockMoveService.listByIds(ids);
        return datas.stream().collect(Collectors.toMap(WhseStockMoveDO::getId, Function.identity()));
    }
}