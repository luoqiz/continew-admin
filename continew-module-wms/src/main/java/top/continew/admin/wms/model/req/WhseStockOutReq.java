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

import jakarta.validation.constraints.*;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

import org.hibernate.validator.constraints.Length;

import top.continew.starter.extension.crud.model.req.BaseReq;

/**
 * 创建或修改仓库出库信息
 *
 * @author luoqiz
 * @since 2024/11/14 15:15
 */
@Data
@Schema(description = "创建或修改仓库出库信息")
public class WhseStockOutReq extends BaseReq {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 出库名称
     */
    @Schema(description = "出库名称")
    @NotBlank(message = "出库名称不能为空")
    @Length(max = 100, message = "出库名称长度不能超过 {max} 个字符")
    private String name;

    /**
     * 出库单号
     */
    @Schema(description = "出库单号")
    @NotBlank(message = "出库单号不能为空")
    @Length(max = 100, message = "出库单号长度不能超过 {max} 个字符")
    private String stockOutNo;

    /**
     * 仓库id编号
     */
    @Schema(description = "仓库id编号")
    @NotBlank(message = "仓库id编号不能为空")
    @Length(max = 100, message = "仓库id编号长度不能超过 {max} 个字符")
    private Long whseId;

    /**
     * 状态 (1审核中 2操作中 3已完成)
     */
    @Schema(description = "状态 (1审核中 2操作中 3已完成)")
    private Integer status;

    /**
     * 关联移库单号
     */
    private Long stockMoveId;
}