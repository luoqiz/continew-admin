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

package top.continew.admin.system.service;

import cn.hutool.json.JSON;
import top.continew.starter.extension.crud.service.BaseService;
import top.continew.admin.system.model.query.LanguageQuery;
import top.continew.admin.system.model.req.LanguageReq;
import top.continew.admin.system.model.resp.LanguageDetailResp;
import top.continew.admin.system.model.resp.LanguageResp;

/**
 * 语言业务接口
 *
 * @author luoqiz
 * @since 2024/10/21 18:15
 */
public interface LanguageService extends BaseService<LanguageResp, LanguageDetailResp, LanguageQuery, LanguageReq> {
    JSON getLanguageByType(String type);
}