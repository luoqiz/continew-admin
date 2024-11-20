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

package top.continew.admin.wms.model.req;

import java.io.Serial;
import java.time.*;

import com.alibaba.excel.annotation.ExcelProperty;
import jakarta.validation.constraints.*;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

import org.hibernate.validator.constraints.Length;

import top.continew.starter.extension.crud.model.req.BaseReq;

/**
 * 创建或修改仓库出库明细信息
 *
 * @author luoqiz
 * @since 2024/11/14 16:21
 */
@Data
@Schema(description = "创建或修改仓库出库明细信息")
public class WhseStockOutDetailReq extends BaseReq {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 出库单号
     */
    @Schema(description = "出库单号")
    @NotNull(message = "出库单号不能为空")
    private Long stockOutId;

    /**
     * 库存物料id
     */
    @Schema(description = "库存物料id")
    @NotNull(message = "库存id不能为空")
    private Long goodsStockId;

    /**
     * 商品sku
     */
    @Schema(description = "商品sku")
    @NotBlank(message = "商品sku不能为空")
    @Length(max = 100, message = "商品sku长度不能超过 {max} 个字符")
    private String goodsSku;

    /**
     * 商品名称
     */
    @Schema(description = "商品名称")
    @NotBlank(message = "商品名称不能为空")
    @Length(max = 100, message = "商品名称长度不能超过 {max} 个字符")
    private String goodsName;

    /**
     * 生产日期
     */
    @Schema(description = "生产日期")
    @NotNull(message = "生产日期不能为空")
    private LocalDate prodTime;

    /**
     * 过期日期
     */
    @Schema(description = "过期日期")
    @NotNull(message = "过期日期不能为空")
    private LocalDate expiryTime;

    /**
     * 计划出库数量
     */
    @Schema(description = "计划出库数量")
    @NotNull(message = "计划出库数量不能为空")
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