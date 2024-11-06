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

package top.continew.admin.open.model.entity;

import java.io.Serial;
import java.time.*;

import lombok.Data;

import com.baomidou.mybatisplus.annotation.TableName;

import top.continew.starter.extension.crud.model.entity.BaseDO;

/**
 * 应用实体
 *
 * @author chengzi
 * @since 2024/10/17 16:03
 */
@Data
@TableName("sys_app")
public class AppDO extends BaseDO {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Long id;

    /**
     * 应用名称
     */
    private String name;

    /**
     * APPKEY
     */
    private String appKey;

    /**
     * APPSECRET
     */
    private String appSecret;

    /**
     * 应用状态
     */
    private String status;

    /**
     * 失效时间
     */
    private LocalDateTime expirationTime;

    /**
     * 应用描述
     */
    private String appDesc;

    /**
     * secret查看状态
     */
    private String secretStatus;
}