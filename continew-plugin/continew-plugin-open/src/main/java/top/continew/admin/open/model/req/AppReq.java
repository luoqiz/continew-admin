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

package top.continew.admin.open.model.req;

import java.io.Serial;
import java.time.*;

import jakarta.validation.constraints.*;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

import org.hibernate.validator.constraints.Length;

import top.continew.starter.extension.crud.model.req.BaseReq;

/**
 * 创建或修改应用信息
 *
 * @author chengzi
 * @since 2024/10/17 16:03
 */
@Data
@Schema(description = "创建或修改应用信息")
public class AppReq extends BaseReq {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 应用名称
     */
    @Schema(description = "应用名称")
    @NotBlank(message = "应用名称不能为空")
    @Length(max = 255, message = "应用名称长度不能超过 {max} 个字符")
    private String name;

    /**
     * APPKEY
     */
    @Schema(description = "应用密钥")
    @NotBlank(message = "应用密钥不能为空")
    @Length(max = 255, message = "应用密钥长度不能超过 {max} 个字符")
    private String appKey;

    /**
     * 应用状态
     */
    @Schema(description = "应用状态")
    @NotBlank(message = "应用状态不能为空")
    @Length(max = 255, message = "应用状态长度不能超过 {max} 个字符")
    private String status;

    /**
     * 失效时间
     */
    @Schema(description = "失效时间")
    @NotNull(message = "失效时间不能为空")
    private LocalDateTime expirationTime;

    /**
     * 应用描述
     */
    @Schema(description = "应用描述")
    @Length(max = 255, message = "应用描述长度不能超过 {max} 个字符")
    private String appDesc;
}