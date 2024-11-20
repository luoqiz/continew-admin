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

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import top.continew.starter.extension.crud.model.req.BaseReq;

import java.io.Serial;

/**
 * 创建或修改仓库入库信息
 *
 * @author luoqiz
 * @since 2024/11/07 17:49
 */
@Data
@Schema(description = "创建或修改仓库入库信息")
public class WhseStockInReq extends BaseReq {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 入库名称
     */
    @Schema(description = "入库名称")
    @NotBlank(message = "入库名称不能为空")
    @Length(max = 100, message = "入库名称长度不能超过 {max} 个字符")
    private String name;

    /**
     * 入库单号
     */
    @Schema(description = "入库单号")
    @NotBlank(message = "入库单号不能为空")
    @Length(max = 100, message = "入库单号长度不能超过 {max} 个字符")
    private String stockInNo;

    /**
     * 仓库id编号
     */
    @Schema(description = "仓库id编号")
    private Long whseId;

    /**
     * 状态 (1待核检 2核检通过)
     */
    @Schema(description = "核检状态")
    private Integer status;

    /**
     * 关联移库单号
     */
    private Long stockMoveId;
}