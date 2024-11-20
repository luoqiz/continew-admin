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

package top.continew.admin.wms.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import top.continew.admin.wms.mapper.GoodsStockMapper;
import top.continew.admin.wms.model.entity.GoodsStockDO;
import top.continew.admin.wms.model.query.GoodsStockQuery;
import top.continew.admin.wms.model.req.GoodsStockReq;
import top.continew.admin.wms.model.resp.GoodsStockDetailResp;
import top.continew.admin.wms.model.resp.GoodsStockResp;
import top.continew.admin.wms.service.GoodsStockService;
import top.continew.starter.extension.crud.service.impl.BaseServiceImpl;

import java.util.List;

/**
 * 商品库存业务实现
 *
 * @author luoqiz
 * @since 2024/11/13 17:57
 */
@Service
@RequiredArgsConstructor
public class GoodsStockServiceImpl extends BaseServiceImpl<GoodsStockMapper, GoodsStockDO, GoodsStockResp, GoodsStockDetailResp, GoodsStockQuery, GoodsStockReq> implements GoodsStockService {
    public boolean batchAdd(List<GoodsStockDO> datas) {
        return baseMapper.insertBatch(datas);
    }

    @Override
    public void updates(LambdaUpdateWrapper<GoodsStockDO> updateQuery) {
        baseMapper.update(updateQuery);
    }
}