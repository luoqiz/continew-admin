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

package top.continew.admin.system.model.query;

import java.io.Serial;
import java.io.Serializable;
import java.time.*;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

import top.continew.starter.data.core.annotation.Query;
import top.continew.starter.data.core.enums.QueryType;

/**
 * 语言查询条件
 *
 * @author luoqiz
 * @since 2024/10/21 18:15
 */
@Data
@Schema(description = "语言查询条件")
public class LanguageQuery implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 模块标识
     */
    @Schema(description = "模块标识")
    @Query(type = QueryType.EQ)
    private String moduleId;

    /**
     * 模块名称
     */
    @Schema(description = "模块名称")
    @Query(type = QueryType.LIKE)
    private String moduleName;

    /**
     * 内容
     */
    @Schema(description = "内容")
    @Query(type = QueryType.EQ)
    private String content;

    /**
     * 所属语言类型
     */
    @Schema(description = "所属语言类型")
    @Query(type = QueryType.EQ)
    private String dictItem;

    /**
     * 状态（1：启用；2：禁用）
     */
    @Schema(description = "状态（1：启用；2：禁用）")
    @Query(type = QueryType.EQ)
    private Integer status;
}