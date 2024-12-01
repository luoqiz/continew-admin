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
import top.continew.admin.wms.model.query.WhseStockOutQuery;
import top.continew.admin.wms.model.req.WhseStockOutDetailReq;
import top.continew.admin.wms.model.req.WhseStockOutReq;
import top.continew.admin.wms.model.resp.WhseStockOutInfoResp;
import top.continew.admin.wms.model.resp.WhseStockOutResp;
import top.continew.starter.extension.crud.service.BaseService;

import java.util.List;
import java.util.Map;

/**
 * 仓库出库业务接口
 *
 * @author luoqiz
 * @since 2024/11/14 15:15
 */
public interface WhseStockOutService extends BaseService<WhseStockOutResp, WhseStockOutInfoResp, WhseStockOutQuery, WhseStockOutReq> {
    WhseStockOutInfoResp detailById(Long id);

    void updateStatus(Long id, int status);

    Long add(WhseStockOutReq stockOutReq, List<WhseStockOutDetailReq> stockOutDetailReqList);

    /**
     * 统计仓库内今日物料出库信息
     *
     * @param whseId 仓库id
     * @return
     */
    List<Map<String, Integer>> staticsToday(Long whseId);

    void export(Long id, HttpServletResponse response, String lang);
}