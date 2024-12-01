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

package top.continew.admin.wms.model.resp;

import cn.crane4j.annotation.Assemble;
import cn.crane4j.annotation.condition.ConditionOnPropertyNotNull;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import top.continew.admin.wms.constant.WmsConstants;
import top.continew.starter.extension.crud.model.resp.BaseResp;

import java.io.Serial;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 仓库出库信息
 *
 * @author luoqiz
 * @since 2024/11/14 15:15
 */
@Data
@Schema(description = "仓库出库信息")
public class WhseStockOutResp extends BaseResp {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 出库名称
     */
    @Schema(description = "出库名称")
    private String name;

    /**
     * 出库单号
     */
    @Schema(description = "出库单号")
    private String stockOutNo;

    /**
     * 出库类型(1 销售出库 2移库出库)
     */
    @Schema(description = "出库类型(1 销售出库 2移库出库)")
    private Integer stockOutType;

    /**
     * 仓库id编号
     */
    @ConditionOnPropertyNotNull
    @Assemble(container = WmsConstants.addrContainer, prop = "name:whseName")
    @Schema(description = "仓库id编号")
    private Long whseId;

    @ExcelProperty(value = "仓库名称")
    private String whseName;

    /**
     * 仓库区域id编号
     */
    @Schema(description = "仓库区域id编号")
    private String whseAreaId;

    /**
     * 关联移库单号
     */
    @Schema(description = "关联移库单号")
    @ConditionOnPropertyNotNull
    @Assemble(container = WmsConstants.whseStockMoveIdContainer, prop = "stockMoveNo:stockMoveNo")
    private Long stockMoveId;

    /**
     * 关联移库单号
     */
    @Schema(description = "关联移库单号")
    private String stockMoveNo;

    /**
     * 出库时间
     */
    @Schema(description = "出库时间")
    private LocalDateTime outTime;

    /**
     * 状态 (1审核中 2操作中 3已完成)
     */
    @Schema(description = "状态 (1审核中 2操作中 3已完成)")
    private Integer status;
}