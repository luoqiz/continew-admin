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

package top.continew.admin.wms.service;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import top.continew.admin.wms.model.entity.GoodsStockDO;
import top.continew.admin.wms.model.query.GoodsStockQuery;
import top.continew.admin.wms.model.req.GoodsStockReq;
import top.continew.admin.wms.model.resp.GoodsStockDetailResp;
import top.continew.admin.wms.model.resp.GoodsStockResp;
import top.continew.starter.extension.crud.service.BaseService;

import java.util.List;
import java.util.Map;

/**
 * 商品库存业务接口
 *
 * @author luoqiz
 * @since 2024/11/13 17:57
 */
public interface GoodsStockService extends BaseService<GoodsStockResp, GoodsStockDetailResp, GoodsStockQuery, GoodsStockReq> {
    boolean batchAdd(List<GoodsStockDO> datas);

    void updates(LambdaUpdateWrapper<GoodsStockDO> updateQuery);

    List<Map<String, Integer>> staticsToday(Long whseId);

    /**
     * 库存信息
     *
     * @param whseId 仓库信息
     * @return
     */
    List<Map<String, Integer>> statisticsStock(Long whseId);

    /**
     * 获取临期库存
     *
     * @param whseId 仓库id
     * @return
     */
    List<GoodsStockResp> expiredStock(Long whseId, int day);
}