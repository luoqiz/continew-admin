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

package top.continew.admin.wms.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import top.continew.starter.extension.crud.model.entity.BaseDO;

import java.io.Serial;
import java.time.LocalDate;

/**
 * 物料盘点详情实体
 *
 * @author luoqiz
 * @since 2024/11/26 13:44
 */
@Data
@TableName("wms_goods_inventory_count_item")
public class GoodsInventoryCountItemDO extends BaseDO {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 盘点表id
     */
    private Long inventoryCountId;

    /**
     * 库存id
     */
    private Long stockId;

    /**
     * 物料编号
     */
    private Long goodsId;

    /**
     * 物料sku条码
     */
    private String goodsSku;

    /**
     * 初始库存
     */
    private Integer initNum;

    /**
     * 实际库存
     */
    private Integer realNum;

    /**
     * 状态 1待盘点 2盘点中 3已结束
     */
    private Integer status;

    /**
     * 生产日期
     */
    private LocalDate prodTime;

    /**
     * 过期日期
     */
    private LocalDate expiryTime;

    /**
     * 备注信息
     */
    private String memo;
}