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
import java.time.LocalDateTime;

/**
 * 创建或修改物料盘点详情参数
 *
 * @author luoqiz
 * @since 2024/11/26 13:44
 */
@Data
@Schema(description = "创建或修改物料盘点详情参数")
public class GoodsInventoryCountItemReq extends BaseReq {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 盘点表id
     */
    @Schema(description = "盘点表id")
    @NotNull(message = "盘点表id不能为空")
    private Long inventoryCountId;

    /**
     * 库存id
     */
    @Schema(description = "库存id")
    @NotNull(message = "库存id不能为空")
    private Long stockId;

    /**
     * 物料编号
     */
    @Schema(description = "物料编号")
    //    @NotNull(message = "物料编号不能为空")
    private Long goodsId;

    /**
     * 物料sku条码
     */
    @Schema(description = "物料sku条码")
    @NotBlank(message = "物料sku条码不能为空")
    @Length(max = 100, message = "物料sku条码长度不能超过 {max} 个字符")
    private String goodsSku;

    /**
     * 初始库存
     */
    @Schema(description = "初始库存")
    @NotNull(message = "初始库存不能为空")
    private Integer initNum;

    /**
     * 初始库存
     */
    @Schema(description = "实际库存")
    private Integer realNum;

    /**
     * 状态 1待盘点 2盘点中 3已结束
     */
    @Schema(description = "状态 1待盘点 2盘点中 3已结束")
    @NotNull(message = "状态 1待盘点 2盘点中 3已结束不能为空")
    private Integer status;

    /**
     * 生产日期
     */
    @Schema(description = "生产日期")
    @NotNull(message = "生产日期不能为空")
    private LocalDateTime prodTime;

    /**
     * 过期日期
     */
    @Schema(description = "过期日期")
    @NotNull(message = "过期日期不能为空")
    private LocalDateTime expiryTime;

    /**
     * 备注信息
     */
    @Schema(description = "备注信息")
    @Length(max = 1000, message = "备注信息长度不能超过 {max} 个字符")
    private String memo;
}