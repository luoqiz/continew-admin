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

package top.continew.admin.wms.mapper;

import org.apache.ibatis.annotations.Select;
import top.continew.admin.wms.model.entity.GoodsStockDO;
import top.continew.admin.wms.model.resp.GoodsStockResp;
import top.continew.starter.data.mp.base.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * 商品库存 Mapper
 *
 * @author luoqiz
 * @since 2024/11/13 17:57
 */
public interface GoodsStockMapper extends BaseMapper<GoodsStockDO> {

    @Select("SELECT wgs.goods_sku goodsSku,goodsSkuTable.`name` as goodsName, SUM(wgs.real_num) as realNum FROM wms_goods_stock wgs LEFT JOIN wms_goods_sku goodsSkuTable ON wgs.goods_sku = goodsSkuTable.barcode WHERE TO_DAYS(wgs.create_time) = TO_DAYS(NOW()) AND whse_id = #{whseId} GROUP BY wgs.goods_sku,goodsSkuTable.`name`")
    List<Map<String, Integer>> staticsToday(Long whseId);

    @Select(" SELECT wgs.goods_sku goodsSku,goodsSkuTable.`name` as goodsName, SUM(wgs.real_num) as realNum FROM wms_goods_stock wgs LEFT JOIN wms_goods_sku goodsSkuTable ON wgs.goods_sku = goodsSkuTable.barcode WHERE TO_DAYS(wgs.expiry_time) >= TO_DAYS(NOW()) AND whse_id = #{whseId} GROUP BY wgs.goods_sku,goodsSkuTable.`name`;")
    List<Map<String, Integer>> statisticsStock(Long whseId);

//    @Select("SELECT * FROM wms_goods_stock WHERE expiry_time <= CURDATE() + INTERVAL #{day} DAY AND expiry_time >= CURDATE() and whse_id = #{whseId}")
    @Select("SELECT wgs.goods_sku, wgsku.`name` as goods_name ,SUM(wgs.real_num) as real_num  FROM wms_goods_stock wgs LEFT JOIN wms_goods_sku wgsku on wgsku.barcode = wgs.goods_sku WHERE expiry_time <= CURDATE() + INTERVAL #{day} DAY AND expiry_time >= CURDATE() AND whse_id=#{whseId} GROUP BY goods_sku,`name`")
    List<GoodsStockResp> expiredStock(Long whseId, int day);
}