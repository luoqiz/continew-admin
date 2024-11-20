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
import java.time.LocalDateTime;

/**
 * 仓库入库实体
 *
 * @author luoqiz
 * @since 2024/11/07 17:49
 */
@Data
@TableName("wms_whse_stock_in")
public class WhseStockInDO extends BaseDO {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 入库名称
     */
    private String name;

    /**
     * 入库单号
     */
    private String stockInNo;

    /**
     * 入库类型(1 采购入库)
     */
    private Integer stockInType;

    /**
     * 仓库id编号
     */
    private Long whseId;

    /**
     * 仓库区域id编号
     */
    private Long whseAreaId;

    /**
     * 关联移库单号
     */
    private Long stockMoveId;

    /**
     * 入库时间
     */
    private LocalDateTime inTime;

    /**
     * 状态 (1审核中 2待入库 3已完成)
     */
    private Integer status;

    /**
     * 备注信息
     */
    private String memo;
}