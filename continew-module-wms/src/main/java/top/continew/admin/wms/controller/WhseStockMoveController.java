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
import top.continew.admin.wms.model.query.WhseStockMoveQuery;
import top.continew.admin.wms.model.req.WhseStockMoveReq;
import top.continew.admin.wms.model.resp.WhseStockMoveDetailResp;
import top.continew.admin.wms.model.resp.WhseStockMoveResp;
import top.continew.admin.wms.service.WhseStockMoveService;
import top.continew.starter.extension.crud.annotation.CrudRequestMapping;
import top.continew.starter.extension.crud.controller.BaseController;
import top.continew.starter.extension.crud.enums.Api;

/**
 * 仓库移库管理 API
 *
 * @author luoqiz
 * @since 2024/11/15 14:50
 */
@Tag(name = "仓库移库管理 API")
@RestController
@CrudRequestMapping(value = "/wms/whseStockMove", api = {Api.PAGE, Api.ADD, Api.UPDATE, Api.DELETE, Api.EXPORT})
public class WhseStockMoveController extends BaseController<WhseStockMoveService, WhseStockMoveResp, WhseStockMoveDetailResp, WhseStockMoveQuery, WhseStockMoveReq> {

    @Operation(summary = "查询详情", description = "查询详情")
    @Parameter(name = "id", description = "ID", example = "1", in = ParameterIn.PATH)
    @ResponseBody
    @GetMapping({"/{id}"})
    public WhseStockMoveDetailResp get(@PathVariable("id") Long id) {
        this.checkPermission(Api.LIST);
        return baseService.detail(id);
    }

    @Operation(summary = "修改出库状态", description = "修改出库状态")
    @Parameter(name = "id", description = "ID", example = "1", in = ParameterIn.PATH)
    @ResponseBody
    @PutMapping("/status/{id}/{status}")
    public void updateStatus(@PathVariable("id") Long id, @PathVariable("status") int status) {
        this.checkPermission(Api.UPDATE);
        baseService.updateStatus(id, status);
    }

    @ExcludeFromGracefulResponse
    @Operation(
            summary = "导出数据",
            description = "导出数据"
    )
    @GetMapping({"/export/{id}"})
    public void export(@PathVariable("id") Long id, HttpServletResponse response) {
        this.checkPermission(Api.EXPORT);
        this.baseService.export(id, response);
    }
}