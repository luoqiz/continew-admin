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

package top.continew.admin.system.model.req;

import java.io.Serial;
import java.time.*;

import jakarta.validation.constraints.*;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

import org.hibernate.validator.constraints.Length;

import top.continew.starter.extension.crud.model.req.BaseReq;

/**
 * 创建或修改语言信息
 *
 * @author luoqiz
 * @since 2024/10/21 18:15
 */
@Data
@Schema(description = "创建或修改语言信息")
public class LanguageReq extends BaseReq {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 模块标识
     */
    @Schema(description = "模块标识")
    @NotBlank(message = "模块标识不能为空")
    @Length(max = 50, message = "模块标识长度不能超过 {max} 个字符")
    private String moduleId;

    /**
     * 模块名称
     */
    @Schema(description = "模块名称")
    @NotBlank(message = "模块名称不能为空")
    @Length(max = 50, message = "模块名称长度不能超过 {max} 个字符")
    private String moduleName;

    /**
     * 内容
     */
    @Schema(description = "内容")
    //    @NotBlank(message = "内容不能为空")
    //    @Length(max = 65535, message = "内容长度不能超过 {max} 个字符")
    private String content;

    /**
     * 所属语言类型
     */
    @Schema(description = "所属语言类型")
    //    @NotNull(message = "所属语言类型不能为空")
    private String dictItem;

    /**
     * 状态（1：启用；2：禁用）
     */
    @Schema(description = "状态（1：启用；2：禁用）")
    //    @NotNull(message = "状态（1：启用；2：禁用）不能为空")
    private Integer status;
}