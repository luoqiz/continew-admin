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

import com.feiniaojin.gracefulresponse.api.ExcludeFromGracefulResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;
import top.continew.admin.wms.model.query.WhseStockInQuery;
import top.continew.admin.wms.model.req.WhseStockInReq;
import top.continew.admin.wms.model.resp.WhseStockInInfoResp;
import top.continew.admin.wms.model.resp.WhseStockInResp;
import top.continew.admin.wms.service.WhseStockInService;
import top.continew.starter.extension.crud.annotation.CrudRequestMapping;
import top.continew.starter.extension.crud.controller.BaseController;
import top.continew.starter.extension.crud.enums.Api;

/**
 * 仓库入库管理 API
 *
 * @author luoqiz
 * @since 2024/11/07 17:49
 */
@Tag(name = "仓库入库管理 API")
@RestController
@CrudRequestMapping(value = "/wms/whseStockIn", api = {Api.PAGE, Api.DETAIL, Api.ADD, Api.UPDATE, Api.DELETE,
    Api.EXPORT})
public class WhseStockInController extends BaseController<WhseStockInService, WhseStockInResp, WhseStockInInfoResp, WhseStockInQuery, WhseStockInReq> {

    @Operation(summary = "查询详情", description = "查询详情")
    @Parameter(name = "id", description = "ID", example = "1", in = ParameterIn.PATH)
    @ResponseBody
    @GetMapping("/detail/{id}")
    public WhseStockInInfoResp detail(@PathVariable("id") Long id) {
        this.checkPermission(Api.LIST);
        return baseService.detailById(id);
    }

    @Operation(summary = "修改入库状态", description = "修改入库状态")
    @Parameter(name = "id", description = "ID", example = "1", in = ParameterIn.PATH)
    @ResponseBody
    @PutMapping("/status/{id}/{status}")
    public void updateStatus(@PathVariable("id") Long id, @PathVariable("status") int status) {
        this.checkPermission(Api.UPDATE);
        baseService.updateStatus(id, status);
    }

    @ExcludeFromGracefulResponse
    @Operation(summary = "导出数据", description = "导出数据")
    @GetMapping({"/export/{id}"})
    public void export(@PathVariable("id") Long id, HttpServletResponse response) {
        this.checkPermission(Api.EXPORT);
        this.baseService.export(id, response);
    }
}