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

/**
 * 物料盘点查询条件
 *
 * @author luoqiz
 * @since 2024/11/26 13:51
 */
@Data
@Schema(description = "物料盘点查询条件")
public class GoodsInventoryCountQuery implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    @Schema(description = "名称")
    @Query(type = QueryType.LIKE_RIGHT)
    private String name;

    /**
     * 仓库id
     */
    @Schema(description = "仓库id")
    @Query(type = QueryType.EQ)
    private Long whseId;

    /**
     * 状态 1待盘点 2盘点中 3已结束
     */
    @Schema(description = "状态 1待盘点 2盘点中 3已结束")
    @Query(type = QueryType.EQ)
    private Integer status;
}