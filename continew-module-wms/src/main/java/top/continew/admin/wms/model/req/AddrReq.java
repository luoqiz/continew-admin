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
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import top.continew.starter.extension.crud.model.req.BaseReq;

import java.io.Serial;

/**
 * 创建或修改仓库地址信息
 *
 * @author luoqiz
 * @since 2024/11/06 19:11
 */
@Data
@Schema(description = "创建或修改仓库地址信息")
public class AddrReq extends BaseReq {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 仓库编号
     */
    @Schema(description = "仓库编号")
    @NotBlank(message = "仓库编号不能为空")
    @Length(max = 100, message = "仓库编号长度不能超过 {max} 个字符")
    private String whseNo;

    /**
     * 仓库名称
     */
    @Schema(description = "仓库名称")
    @NotBlank(message = "仓库名称不能为空")
    @Length(max = 24, message = "仓库名称长度不能超过 {max} 个字符")
    private String name;

    /**
     * 仓库地址
     */
    @Schema(description = "仓库地址")
    @Length(max = 48, message = "仓库地址长度不能超过 {max} 个字符")
    private String addr;

    /**
     * 仓库类型 (1国仓 2地仓 3店仓)
     */
    @Schema(description = "仓库类型 (1国仓  2地仓  3店仓)")
    private Integer whseType;

    /**
     * 状态 (1使用 2停用)
     */
    @Schema(description = "状态 (1使用  2停用  3盘点)")
    @NotNull(message = "状态 (1使用  2停用  3盘点)不能为空")
    private Integer status;

    /**
     * 备注信息
     */
    @Schema(description = "备注信息")
    @Length(max = 1000, message = "备注信息长度不能超过 {max} 个字符")
    private String memo;

    /**
     * 所属部门
     */
    @Schema(description = "所属部门")
    private Long deptId;
}