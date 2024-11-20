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

package top.continew.admin.wms.service;

import top.continew.admin.wms.model.entity.AddrDO;
import top.continew.admin.wms.model.query.AddrQuery;
import top.continew.admin.wms.model.req.AddrReq;
import top.continew.admin.wms.model.resp.AddrDetailResp;
import top.continew.admin.wms.model.resp.AddrResp;
import top.continew.starter.extension.crud.service.BaseService;

import java.util.List;

/**
 * 仓库地址业务接口
 *
 * @author luoqiz
 * @since 2024/11/06 18:05
 */
public interface AddrService extends BaseService<AddrResp, AddrDetailResp, AddrQuery, AddrReq> {
    List<AddrDO> listByIds(List<Long> ids);
}