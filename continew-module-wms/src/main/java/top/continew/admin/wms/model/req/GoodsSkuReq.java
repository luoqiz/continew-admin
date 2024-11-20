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
import java.math.BigDecimal;

import jakarta.validation.constraints.*;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

import org.hibernate.validator.constraints.Length;

import top.continew.starter.extension.crud.model.req.BaseReq;

/**
 * 创建或修改商品规格(sku)信息
 *
 * @author luoqiz
 * @since 2024/11/07 11:10
 */
@Data
@Schema(description = "创建或修改商品规格(sku)信息")
public class GoodsSkuReq extends BaseReq {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 条形码
     */
    @Schema(description = "条形码")
    @NotBlank(message = "条形码不能为空")
    @Length(max = 120, message = "条形码长度不能超过 {max} 个字符")
    private String barcode;

    /**
     * 规格名称
     */
    @Schema(description = "规格名称")
    @NotBlank(message = "规格名称不能为空")
    @Length(max = 24, message = "规格名称长度不能超过 {max} 个字符")
    private String name;

    /**
     * 单位
     */
    @Schema(description = "单位")
    @NotBlank(message = "单位不能为空")
    @Length(max = 48, message = "单位长度不能超过 {max} 个字符")
    private String unit;

    /**
     * 数量
     */
    @Schema(description = "数量")
    @NotNull(message = "数量不能为空")
    private Integer amount;

    /**
     * 拆箱
     */
    @Schema(description = "拆箱")
    @NotNull(message = "拆箱不能为空")
    private Boolean unpacking;

    /**
     * 拆箱单位
     */
    @Schema(description = "拆箱单位")
    private String packUnit;

    /**
     * 拆箱数量
     */
    @Schema(description = "拆箱数量")
    private Integer packAmount;

    /**
     * 售价
     */
    @Schema(description = "售价")
    @NotNull(message = "售价不能为空")
    private BigDecimal price;

    /**
     * 状态 1上架 2下架
     */
    //    @Schema(description = "状态 1上架  2下架")
    //    @NotNull(message = "状态 1上架  2下架不能为空")
    //    private Integer status;
}