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

/**
 * 仓库地址实体
 *
 * @author luoqiz
 * @since 2024/11/06 19:11
 */
@Data
@TableName("wms_whse_addr")
public class AddrDO extends BaseDO {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 仓库编号
     */
    private String whseNo;

    /**
     * 仓库名称
     */
    private String name;

    /**
     * 仓库地址
     */
    private String addr;

    /**
     * 仓库类型 (1国仓 2地仓 3店仓)
     */
    private Integer whseType;

    /**
     * 状态 (1使用 2停用)
     */
    private Integer status;

    /**
     * 备注信息
     */
    private String memo;

    /**
     * 所属部门
     */
    private Long deptId;
}