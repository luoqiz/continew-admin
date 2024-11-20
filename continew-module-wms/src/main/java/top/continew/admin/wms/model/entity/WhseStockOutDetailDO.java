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

import java.io.Serial;
import java.time.*;

import lombok.Data;

import com.baomidou.mybatisplus.annotation.TableName;

import top.continew.starter.extension.crud.model.entity.BaseDO;

/**
 * 仓库出库明细实体
 *
 * @author luoqiz
 * @since 2024/11/14 16:21
 */
@Data
@TableName("wms_whse_stock_out_detail")
public class WhseStockOutDetailDO extends BaseDO {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 出库单号
     */
    private Long stockOutId;

    /**
     * 库存id
     */
    private Long goodsStockId;

    /**
     * 商品sku
     */
    private String goodsSku;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 生产日期
     */
    private LocalDate prodTime;

    /**
     * 过期日期
     */
    private LocalDate expiryTime;

    /**
     * 计划出库数量
     */
    private Integer planNum;

    /**
     * 实际出库数量
     */
    private Integer realNum;

    /**
     * 备注信息
     */
    private String memo;

    /**
     * 状态 (1待核检 2核检通过)
     */
    private Integer status;
}