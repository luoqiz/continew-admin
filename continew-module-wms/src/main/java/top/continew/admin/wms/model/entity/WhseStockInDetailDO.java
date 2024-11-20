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
 * 仓库入库明细实体
 *
 * @author luoqiz
 * @since 2024/11/11 11:53
 */
@Data
@TableName("wms_whse_stock_in_detail")
public class WhseStockInDetailDO extends BaseDO {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 入库id编号
     */
    private String stockInId;

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
     * 计划入库数量
     */
    private Integer planNum;

    /**
     * 实际入库数量
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