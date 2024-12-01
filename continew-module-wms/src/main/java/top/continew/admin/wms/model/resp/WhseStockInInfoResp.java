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

import cn.crane4j.annotation.Assemble;
import cn.crane4j.annotation.Mapping;
import cn.crane4j.annotation.condition.ConditionOnPropertyNotNull;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import top.continew.admin.wms.constant.WmsConstants;
import top.continew.starter.extension.crud.model.resp.BaseDetailResp;

import java.io.Serial;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 仓库入库详情信息
 *
 * @author luoqiz
 * @since 2024/11/11 17:06
 */
@Data
@ExcelIgnoreUnannotated
@Schema(description = "仓库入库详情信息")
public class WhseStockInInfoResp extends BaseDetailResp {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 入库名称
     */
    @Schema(description = "入库名称")
    @ExcelProperty(value = "入库名称")
    private String name;

    /**
     * 入库单号
     */
    @Schema(description = "入库单号")
    @ExcelProperty(value = "入库单号")
    private String stockInNo;

    /**
     * 入库类型(1 采购入库)
     */
    @Schema(description = "入库类型(1 采购入库)")
    @ExcelProperty(value = "入库类型(1 采购入库)")
    private Integer stockInType;

    /**
     * 仓库id编号
     */
    @ConditionOnPropertyNotNull
    @Assemble(container = WmsConstants.addrContainer, props = {@Mapping(src = "name", ref = "whseName"),
        @Mapping(src = "whseType", ref = "whseType")})
    @Schema(description = "仓库id编号")
    @ExcelProperty(value = "仓库id编号")
    private Long whseId;

    @Schema(description = "仓库类型")
    @ExcelProperty(value = "仓库类型")
    private Integer whseType;

    @Schema(description = "仓库名称")
    @ExcelProperty(value = "仓库名称")
    private String whseName;
    /**
     * 仓库区域id编号
     */
    @Schema(description = "仓库区域id编号")
    @ExcelProperty(value = "仓库区域id编号")
    private String whseAreaId;

    /**
     * 关联移库单号
     */
    @ConditionOnPropertyNotNull
    @Schema(description = "关联移库单号ID")
    @ExcelProperty(value = "关联移库单号ID")
    @Assemble(container = WmsConstants.whseStockMoveIdContainer, prop = "stockMoveNo:stockMoveNo")
    private Long stockMoveId;

    /**
     * 关联移库单号
     */
    @Schema(description = "关联移库单号")
    @ExcelProperty(value = "关联移库单号")
    private String stockMoveNo;

    /**
     * 入库时间
     */
    @Schema(description = "入库时间")
    @ExcelProperty(value = "入库时间")
    private LocalDateTime inTime;

    /**
     * 状态 (1审核中 2待入库 3已完成)
     */
    @Schema(description = "状态 (1审核中 2待入库 3已完成)")
    @ExcelProperty(value = "状态 (1审核中 2待入库 3已完成)")
    private Integer status;

    /**
     * 备注信息
     */
    @Schema(description = "备注信息")
    @ExcelProperty(value = "备注信息")
    private String memo;

    @Schema(description = "物料列表")
    @ExcelProperty(value = "物料列表")
    public List<WhseStockInDetailResp> goodsList;
}