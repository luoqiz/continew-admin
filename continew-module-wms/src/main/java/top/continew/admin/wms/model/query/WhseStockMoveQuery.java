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
 * 仓库移库查询条件
 *
 * @author luoqiz
 * @since 2024/11/15 14:50
 */
@Data
@Schema(description = "仓库移库查询条件")
public class WhseStockMoveQuery implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 移库名称
     */
    @Schema(description = "移库名称")
    @Query(type = QueryType.LIKE_RIGHT)
    private String name;

    /**
     * 移库单号
     */
    @Schema(description = "移库单号")
    @Query(type = QueryType.EQ)
    private String stockMoveNo;

    /**
     * 出库类型(1国-国 2国-地 3地-地 4地-店 5店-店)
     */
    @Schema(description = "出库类型(1国-国 2国-地 3地-地 4地-店 5店-店)")
    @Query(type = QueryType.EQ)
    private Integer stockMoveType;

    /**
     * 入仓id编号
     */
    @Schema(description = "入仓id编号")
    @Query(type = QueryType.EQ)
    private Long stockInWhseId;

    /**
     * 出仓id编号
     */
    @Schema(description = "出仓id编号")
    @Query(type = QueryType.EQ)
    private Long stockOutWhseId;

    /**
     * 状态 (1审核中 2操作中 2已完成)
     */
    @Schema(description = "状态 (1审核中 2操作中 2已完成)")
    @Query(type = QueryType.EQ)
    private Integer status;
}