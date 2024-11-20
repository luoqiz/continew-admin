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

package top.continew.admin.wms.model.resp;

import java.io.Serial;
import java.time.*;
import java.util.List;

import cn.crane4j.annotation.Assemble;
import cn.crane4j.annotation.condition.ConditionOnPropertyNotNull;
import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;

import top.continew.admin.wms.constant.WmsConstants;
import top.continew.starter.extension.crud.model.resp.BaseDetailResp;

/**
 * 仓库移库详情信息
 *
 * @author luoqiz
 * @since 2024/11/15 14:50
 */
@Data
@ExcelIgnoreUnannotated
@Schema(description = "仓库移库详情信息")
public class WhseStockMoveDetailResp extends BaseDetailResp {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 移库名称
     */
    @Schema(description = "移库名称")
    @ExcelProperty(value = "移库名称")
    private String name;

    /**
     * 移库单号
     */
    @Schema(description = "移库单号")
    @ExcelProperty(value = "移库单号")
    private String stockMoveNo;

    /**
     * 出库类型(1国-国 2国-地 3地-地 4地-店 5店-店)
     */
    @Schema(description = "出库类型(1国-国 2国-地 3地-地 4地-店 5店-店)")
    @ExcelProperty(value = "出库类型(1国-国 2国-地 3地-地 4地-店 5店-店)")
    private Integer stockMoveType;

    /**
     * 入仓id编号
     */
    @ConditionOnPropertyNotNull
    @Assemble(container = WmsConstants.addrContainer, prop = "name:stockInWhseName")
    @Schema(description = "入仓id编号")
    @ExcelProperty(value = "入仓id编号")
    private Long stockInWhseId;

    /**
     * 入仓名称
     */
    @Schema(description = "入仓名称")
    @ExcelProperty(value = "入仓名称")
    private String stockInWhseName;

    /**
     * 入仓区域id编号
     */
    @Schema(description = "入仓区域id编号")
    @ExcelProperty(value = "入仓区域id编号")
    private String stockInWhseAreaId;

    /**
     * 出仓id编号
     */
    @ConditionOnPropertyNotNull
    @Assemble(container = WmsConstants.addrContainer, prop = "name:stockOutWhseName")
    @Schema(description = "出仓id编号")
    @ExcelProperty(value = "出仓id编号")
    private Long stockOutWhseId;

    @Schema(description = "出仓名称")
    @ExcelProperty(value = "出仓名称")
    private String stockOutWhseName;

    /**
     * 出库区域id编号
     */
    @Schema(description = "出库区域id编号")
    @ExcelProperty(value = "出库区域id编号")
    private String stockOutWhseAreaId;

    /**
     * 移库时间
     */
    @Schema(description = "移库时间")
    @ExcelProperty(value = "移库时间")
    private LocalDateTime moveTime;

    /**
     * 状态 (1审核中 2操作中 2已完成)
     */
    @Schema(description = "状态 (1审核中 2操作中 2已完成)")
    @ExcelProperty(value = "状态 (1审核中 2操作中 2已完成)")
    private Integer status;

    /**
     * 关联入库单号
     */
    @Schema(description = "关联入库单号")
    @ExcelProperty(value = "关联入库单号")
    private Long stockInId;

    /**
     * 关联出库单号
     */
    @Schema(description = "关联出库单号")
    @ExcelProperty(value = "关联出库单号")
    private Long stockOutId;

    /**
     * 备注信息
     */
    @Schema(description = "备注信息")
    @ExcelProperty(value = "备注信息")
    private String memo;

    /**
     * 物品信息
     */
    List<WhseStockMoveDetialResp> goodsList;
}