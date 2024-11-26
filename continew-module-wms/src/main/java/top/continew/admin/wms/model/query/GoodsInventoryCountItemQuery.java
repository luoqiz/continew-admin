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

package top.continew.admin.wms.model.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import top.continew.starter.data.core.annotation.Query;
import top.continew.starter.data.core.enums.QueryType;

import java.io.Serial;
import java.io.Serializable;

/**
 * 物料盘点详情查询条件
 *
 * @author luoqiz
 * @since 2024/11/26 13:44
 */
@Data
@Schema(description = "物料盘点详情查询条件")
public class GoodsInventoryCountItemQuery implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 盘点表id
     */
    @Schema(description = "盘点表id")
    @Query(type = QueryType.EQ)
    private Long inventoryCountId;

    /**
     * 库存id
     */
    @Schema(description = "库存id")
    @Query(type = QueryType.EQ)
    private Long stockId;

    /**
     * 物料编号
     */
    @Schema(description = "物料编号")
    @Query(type = QueryType.EQ)
    private Long goodsId;

    /**
     * 物料sku条码
     */
    @Schema(description = "物料sku条码")
    @Query(type = QueryType.EQ)
    private String goodsSku;

    /**
     * 状态 1待盘点 2盘点中 3已结束
     */
    @Schema(description = "状态 1待盘点 2盘点中 3已结束")
    @Query(type = QueryType.EQ)
    private Integer status;
}