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
 * 商品库存实体
 *
 * @author luoqiz
 * @since 2024/11/13 17:57
 */
@Data
@TableName("wms_goods_stock")
public class GoodsStockDO extends BaseDO {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 入库单编号
     */
    private Long stockInId;

    /**
     * 入库单号
     */
    private String stockInNo;

    /**
     * 入库单明细编号
     */
    private Long stockInDetailId;

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
     * 仓库类型（0 国仓 1地仓 2店仓）
     */
    private Integer whseType;

    /**
     * 仓库id
     */
    private Long whseId;

    /**
     * 仓库区域id编号
     */
    private String whseAreaId;

    /**
     * 状态 1待出库 2已出库 3部分出库
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
    private String info;
}