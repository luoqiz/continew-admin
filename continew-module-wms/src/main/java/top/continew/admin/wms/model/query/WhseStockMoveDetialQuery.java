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

import java.io.Serial;
import java.io.Serializable;
import java.time.*;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

import top.continew.starter.data.core.annotation.Query;
import top.continew.starter.data.core.enums.QueryType;

/**
 * 仓库移库明细查询条件
 *
 * @author luoqiz
 * @since 2024/11/15 17:17
 */
@Data
@Schema(description = "仓库移库明细查询条件")
public class WhseStockMoveDetialQuery implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 移库id
     */
    @Schema(description = "移库id")
    @Query(type = QueryType.EQ)
    private Long stockMoveId;

    /**
     * 移库单号
     */
    @Schema(description = "移库单号")
    @Query(type = QueryType.EQ)
    private String stockMoveNo;

    /**
     * 库存id
     */
    @Schema(description = "库存id")
    @Query(type = QueryType.EQ)
    private Long goodsStockId;

    /**
     * 商品sku
     */
    @Schema(description = "商品sku")
    @Query(type = QueryType.EQ)
    private String goodsSku;

    /**
     * 商品名称
     */
    @Schema(description = "商品名称")
    @Query(type = QueryType.LIKE_RIGHT)
    private String goodsName;

    /**
     * 移库时间
     */
    @Schema(description = "移库时间")
    @Query(type = QueryType.BETWEEN)
    private LocalDateTime[] moveTime;

    /**
     * 生产日期
     */
    @Schema(description = "生产日期")
    @Query(type = QueryType.BETWEEN)
    private LocalDate[] prodTime;

    /**
     * 过期日期
     */
    @Schema(description = "过期日期")
    @Query(type = QueryType.BETWEEN)
    private LocalDate[] expiryTime;
}