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

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import top.continew.starter.data.mp.base.BaseMapper;
import top.continew.admin.wms.model.entity.WhseStockOutDO;

import java.util.List;
import java.util.Map;

/**
 * 仓库出库 Mapper
 *
 * @author luoqiz
 * @since 2024/11/14 15:15
 */
public interface WhseStockOutMapper extends BaseMapper<WhseStockOutDO> {

    @Select("select goods_sku,SUM(real_num) as realNum from wms_whse_stock_out_detail where stock_out_id IN (select id FROM wms_whse_stock_out WHERE TO_DAYS(out_time) = TO_DAYS(NOW()) AND whse_id = #{whseId}) GROUP BY goods_sku;")
    List<Map<String, Integer>> staticsToday(@Param("whseId") Long whseId);
}