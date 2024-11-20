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

package top.continew.admin.wms.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import top.continew.starter.extension.crud.enums.Api;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.*;

import top.continew.starter.extension.crud.annotation.CrudRequestMapping;
import top.continew.starter.extension.crud.controller.BaseController;
import top.continew.admin.wms.model.query.WhseStockOutQuery;
import top.continew.admin.wms.model.req.WhseStockOutReq;
import top.continew.admin.wms.model.resp.WhseStockOutInfoResp;
import top.continew.admin.wms.model.resp.WhseStockOutResp;
import top.continew.admin.wms.service.WhseStockOutService;

import java.util.List;
import java.util.Map;

/**
 * 仓库出库管理 API
 *
 * @author luoqiz
 * @since 2024/11/14 15:15
 */
@Tag(name = "仓库出库管理 API")
@RestController
@CrudRequestMapping(value = "/wms/whseStockOut", api = {Api.PAGE, Api.GET, Api.ADD, Api.UPDATE, Api.DELETE, Api.EXPORT})
public class WhseStockOutController extends BaseController<WhseStockOutService, WhseStockOutResp, WhseStockOutInfoResp, WhseStockOutQuery, WhseStockOutReq> {

    @Operation(summary = "查询详情", description = "查询详情")
    @Parameter(name = "id", description = "ID", example = "1", in = ParameterIn.PATH)
    @ResponseBody
    @GetMapping("/detail/{id}")
    public WhseStockOutInfoResp detail(@PathVariable("id") Long id) {
        this.checkPermission(Api.LIST);
        return baseService.detailById(id);
    }

    @Operation(summary = "修改出库状态", description = "修改出库状态")
    @Parameter(name = "id", description = "ID", example = "1", in = ParameterIn.PATH)
    @ResponseBody
    @PutMapping("/status/{id}/{status}")
    public void updateStatus(@PathVariable("id") Long id, @PathVariable("status") int status) {
        this.checkPermission(Api.UPDATE);
        baseService.updateStatus(id, status);
    }

    @Operation(summary = "获取指定库的今日出库信息", description = "获取指定库的今日出库信息")
    @Parameter(name = "id", description = "ID", example = "1", in = ParameterIn.PATH)
    @ResponseBody
    @GetMapping("/statics/{whseId}")
    public List<Map<String, Integer>> statistics(@PathVariable Long whseId) {
        return baseService.staticsToday(whseId);
    }
}