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
import cn.crane4j.annotation.Mapping;
import cn.crane4j.annotation.condition.ConditionOnPropertyNotNull;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import top.continew.admin.wms.constant.WmsConstants;
import top.continew.starter.extension.crud.model.resp.BaseDetailResp;

import java.io.Serial;
import java.time.LocalDateTime;

/**
 * 物料盘点详情信息
 *
 * @author luoqiz
 * @since 2024/11/26 13:51
 */
@Data
@ExcelIgnoreUnannotated
@Schema(description = "物料盘点详情信息")
public class GoodsInventoryCountDetailResp extends BaseDetailResp {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    @Schema(description = "名称")
    @ExcelProperty(value = "名称")
    private String name;

    /**
     * 仓库id
     */
    @ConditionOnPropertyNotNull
    @Schema(description = "仓库id")
    @ExcelProperty(value = "仓库id")
    @Assemble(container = WmsConstants.addrContainer, props = {@Mapping(src = "name", ref = "whseName"),
        @Mapping(src = "whseType", ref = "whseType"),})
    private Long whseId;

    /**
     * 仓库名称
     */
    @Schema(description = "仓库名称")
    private String whseName;

    /**
     * 仓库类型
     */
    @Schema(description = "仓库类型")
    private Integer whseType;

    /**
     * 仓库区域id编号
     */
    @Schema(description = "仓库区域id编号")
    @ExcelProperty(value = "仓库区域id编号")
    private String whseAreaId;

    /**
     * 状态 1待盘点 2盘点中 3已结束
     */
    @Schema(description = "状态 1待盘点 2盘点中 3已结束")
    @ExcelProperty(value = "状态 1待盘点 2盘点中 3已结束")
    private Integer status;

    /**
     * 开始时间
     */
    @Schema(description = "开始时间")
    @ExcelProperty(value = "开始时间")
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    @Schema(description = "结束时间")
    @ExcelProperty(value = "结束时间")
    private LocalDateTime endTime;

    /**
     * 备注信息
     */
    @Schema(description = "备注信息")
    @ExcelProperty(value = "备注信息")
    private String memo;
}