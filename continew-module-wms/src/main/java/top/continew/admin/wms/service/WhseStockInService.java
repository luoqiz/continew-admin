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

import jakarta.servlet.http.HttpServletResponse;
import top.continew.admin.wms.model.entity.WhseStockInDO;
import top.continew.admin.wms.model.query.WhseStockInQuery;
import top.continew.admin.wms.model.req.WhseStockInDetailReq;
import top.continew.admin.wms.model.req.WhseStockInReq;
import top.continew.admin.wms.model.resp.WhseStockInInfoResp;
import top.continew.admin.wms.model.resp.WhseStockInResp;
import top.continew.starter.extension.crud.service.BaseService;

import java.util.Collection;
import java.util.List;

/**
 * 仓库入库业务接口
 *
 * @author luoqiz
 * @since 2024/11/07 17:49
 */
public interface WhseStockInService extends BaseService<WhseStockInResp, WhseStockInInfoResp, WhseStockInQuery, WhseStockInReq> {
    /**
     * 获取入库单详情（包括物料信息）
     *
     * @param id
     * @return
     */
    WhseStockInInfoResp detailById(Long id);

    /**
     * 更新状态
     *
     * @param id
     * @param status
     * @return
     */
    void updateStatus(Long id, int status);

    /**
     * 添加完整的入库记录
     *
     * @param stockInReq
     * @param stockInDetailReqList
     */
    Long add(WhseStockInReq stockInReq, List<WhseStockInDetailReq> stockInDetailReqList);

    void export(Long id, HttpServletResponse response);

    List<WhseStockInDO> getByIds(Collection<Long> ids);
}