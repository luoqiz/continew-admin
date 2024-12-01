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
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import top.continew.admin.wms.constant.WmsConstants;
import top.continew.starter.extension.crud.model.resp.BaseResp;

import java.io.Serial;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 仓库移库明细信息
 *
 * @author luoqiz
 * @since 2024/11/15 17:17
 */
@Data
@Schema(description = "仓库移库明细信息")
public class WhseStockMoveDetialResp extends BaseResp {

    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 移库单号
     */
    @Schema(description = "移库单id")
    private Long stockMoveId;

    /**
     * 移库单号
     */
    @Schema(description = "移库单号")
    private String stockMoveNo;

    /**
     * 库存id
     */
    @Schema(description = "库存id")
    private Long goodsStockId;

    /**
     * 商品sku
     */
    @ConditionOnPropertyNotNull
    @Schema(description = "商品sku")
    @Assemble(container = WmsConstants.goodsSkuContainer, props = {@Mapping(src = "name", ref = "goodsName"),
        @Mapping(src = "unit", ref = "goodsUnit"), @Mapping(src = "packUnit", ref = "goodsPackUnit"),
        @Mapping(src = "unpacking", ref = "goodsUnpacking"),})
    private String goodsSku;

    @Schema(description = "是否需要拆箱")
    private Boolean goodsUnpacking;

    @Schema(description = "物料单位")
    private String goodsUnit;

    @Schema(description = "物料拆箱单位")
    private String goodsPackUnit;
    /**
     * 商品名称
     */
    @Schema(description = "商品名称")
    private String goodsName;

    /**
     * 移库时间
     */
    @Schema(description = "移库时间")
    private LocalDateTime moveTime;

    /**
     * 生产日期
     */
    @Schema(description = "生产日期")
    @ColumnWidth(30)
    private LocalDate prodTime;

    /**
     * 过期日期
     */
    @Schema(description = "过期日期")
    @ColumnWidth(50)
    private LocalDate expiryTime;

    /**
     * 计划数量
     */
    @Schema(description = "计划数量")
    @ColumnWidth(50)
    private Integer planNum;

    /**
     * 备注信息
     */
    @Schema(description = "备注信息")
    private String memo;

}