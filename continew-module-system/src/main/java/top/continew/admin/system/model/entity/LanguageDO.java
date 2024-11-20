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

package top.continew.admin.system.model.entity;

import java.io.Serial;

import lombok.Data;

import com.baomidou.mybatisplus.annotation.TableName;

import top.continew.starter.extension.crud.model.entity.BaseDO;

/**
 * 语言实体
 *
 * @author luoqiz
 * @since 2024/10/21 18:15
 */
@Data
@TableName("sys_language")
public class LanguageDO extends BaseDO {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 模块标识
     */
    private String moduleId;

    /**
     * 模块名称
     */
    private String moduleName;

    /**
     * 内容
     */
    private String content;

    /**
     * 所属语言类型
     */
    private String dictItem;

    /**
     * 状态（1：启用；2：禁用）
     */
    private Integer status;
}