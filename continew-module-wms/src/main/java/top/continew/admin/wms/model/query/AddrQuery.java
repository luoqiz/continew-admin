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

package top.continew.admin.wms.model.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import top.continew.starter.data.core.annotation.Query;
import top.continew.starter.data.core.enums.QueryType;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

/**
 * 仓库地址查询条件
 *
 * @author luoqiz
 * @since 2024/11/06 19:11
 */
@Data
@Schema(description = "仓库地址查询条件")
public class AddrQuery implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 仓库编号
     */
    @Schema(description = "仓库编号")
    @Query(type = QueryType.EQ)
    private String whseNo;

    /**
     * 仓库名称
     */
    @Schema(description = "仓库名称")
    @Query(type = QueryType.LIKE)
    private String name;

    /**
     * 仓库类型 (1国仓 2地仓 3店仓)
     */
    @Schema(description = "仓库类型 (1国仓  2地仓  3店仓)")
    private Integer whseType;

    /**
     * 状态 (1使用 2停用)
     */
    @Schema(description = "状态 (1使用  2停用)")
    @Query(type = QueryType.EQ)
    private Integer status;

    /**
     * 所属部门
     */
    @Schema(description = "所属部门")
    @Query(type = QueryType.IN)
    private Set<Long> deptId;
}