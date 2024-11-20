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
import cn.crane4j.annotation.condition.ConditionOnPropertyNotNull;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import top.continew.admin.wms.constant.WmsConstants;
import top.continew.starter.extension.crud.model.resp.BaseDetailResp;

import java.io.Serial;
import java.time.LocalDate;

/**
 * 商品库存详情信息
 *
 * @author luoqiz
 * @since 2024/11/13 17:57
 */
@Data
@ExcelIgnoreUnannotated
@Schema(description = "商品库存详情信息")
public class GoodsStockDetailResp extends BaseDetailResp {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 入库单编号
     */
    @Schema(description = "入库单编号")
    @ExcelProperty(value = "入库单编号")
    private Long stockInId;

    /**
     * 入库单号
     */
    @Schema(description = "入库单号")
    @ExcelProperty(value = "入库单号")
    private String stockInNo;

    /**
     * 入库单明细编号
     */
    @Schema(description = "入库单明细编号")
    @ExcelProperty(value = "入库单明细编号")
    private Long stockInDetailId;

    /**
     * 物料编号
     */
    @Schema(description = "物料编号")
    @ExcelProperty(value = "物料编号")
    private Long goodsId;

    @Schema(description = "物料名称")
    @ExcelProperty(value = "物料名称")
    private String goodsName;

    /**
     * 物料sku条码
     */
    @ConditionOnPropertyNotNull
    @Assemble(container = WmsConstants.goodsSkuContainer, prop = "name:goodsName")
    @Schema(description = "物料sku条码")
    @ExcelProperty(value = "物料sku条码")
    private String goodsSku;

    /**
     * 初始库存
     */
    @Schema(description = "初始库存")
    @ExcelProperty(value = "初始库存")
    private Integer initNum;

    /**
     * 实际库存
     */
    @Schema(description = "实际库存")
    @ExcelProperty(value = "实际库存")
    private Integer realNum;

    /**
     * 仓库类型
     */
    @Schema(description = "仓库类型")
    @ExcelProperty(value = "仓库类型")
    private Integer whseType;

    /**
     * 仓库id
     */
    @ConditionOnPropertyNotNull
    @Assemble(container = WmsConstants.addrContainer, prop = "name:whseName")
    @Schema(description = "仓库id")
    @ExcelProperty(value = "仓库id")
    private Long whseId;

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
     * 状态 1待出库 2已出库 3部分出库
     */
    @Schema(description = "状态 1待出库 2已出库 3部分出库")
    @ExcelProperty(value = "状态 1待出库 2已出库 3部分出库")
    private Integer status;

    /**
     * 生产日期
     */
    @Schema(description = "生产日期")
    @ExcelProperty(value = "生产日期")
    private LocalDate prodTime;

    /**
     * 过期日期
     */
    @Schema(description = "过期日期")
    @ExcelProperty(value = "过期日期")
    private LocalDate expiryTime;

    /**
     * 备注信息
     */
    @Schema(description = "备注信息")
    @ExcelProperty(value = "备注信息")
    private String info;
}