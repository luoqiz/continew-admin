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

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;

import top.continew.starter.extension.crud.model.resp.BaseDetailResp;

/**
 * 仓库出库明细详情信息
 *
 * @author luoqiz
 * @since 2024/11/14 16:21
 */
@Data
@ExcelIgnoreUnannotated
@Schema(description = "仓库出库明细详情信息")
public class WhseStockOutDetailDetailResp extends BaseDetailResp {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 出库单号
     */
    @Schema(description = "出库单号")
    @ExcelProperty(value = "出库单号")
    private Long stockOutId;
    /**
     * 库存物料id
     */
    @Schema(description = "库存物料id")
    @ExcelProperty(value = "库存物料id")
    private Long goodsStockId;

    /**
     * 商品sku
     */
    @Schema(description = "商品sku")
    @ExcelProperty(value = "商品sku")
    private String goodsSku;

    /**
     * 商品名称
     */
    @Schema(description = "商品名称")
    @ExcelProperty(value = "商品名称")
    private String goodsName;

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
     * 计划出库数量
     */
    @Schema(description = "计划出库数量")
    @ExcelProperty(value = "计划出库数量")
    private Integer planNum;

    /**
     * 实际出库数量
     */
    @Schema(description = "实际出库数量")
    @ExcelProperty(value = "实际出库数量")
    private Integer realNum;

    /**
     * 备注信息
     */
    @Schema(description = "备注信息")
    @ExcelProperty(value = "备注信息")
    private String memo;

    /**
     * 状态 (1待核检 2核检通过)
     */
    @Schema(description = "状态 (1待核检 2核检通过)")
    @ExcelProperty(value = "状态 (1待核检 2核检通过)")
    private Integer status;
}