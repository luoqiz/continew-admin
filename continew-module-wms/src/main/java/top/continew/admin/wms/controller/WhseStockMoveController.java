package top.continew.admin.wms.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import top.continew.starter.extension.crud.enums.Api;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.*;

import top.continew.starter.extension.crud.annotation.CrudRequestMapping;
import top.continew.starter.extension.crud.controller.BaseController;
import top.continew.admin.wms.model.query.WhseStockMoveQuery;
import top.continew.admin.wms.model.req.WhseStockMoveReq;
import top.continew.admin.wms.model.resp.WhseStockMoveDetailResp;
import top.continew.admin.wms.model.resp.WhseStockMoveResp;
import top.continew.admin.wms.service.WhseStockMoveService;

/**
 * 仓库移库管理 API
 *
 * @author luoqiz
 * @since 2024/11/15 14:50
 */
@Tag(name = "仓库移库管理 API")
@RestController
@CrudRequestMapping(value = "/wms/whseStockMove", api = {Api.PAGE, Api.GET, Api.ADD, Api.UPDATE, Api.DELETE, Api.EXPORT})
public class WhseStockMoveController extends BaseController<WhseStockMoveService, WhseStockMoveResp, WhseStockMoveDetailResp, WhseStockMoveQuery, WhseStockMoveReq> {

    @Operation(
            summary = "查询详情",
            description = "查询详情"
    )
    @Parameter(
            name = "id",
            description = "ID",
            example = "1",
            in = ParameterIn.PATH
    )
    @ResponseBody
    @GetMapping({"/{id}"})
    public WhseStockMoveDetailResp get(@PathVariable("id") Long id) {
        this.checkPermission(Api.LIST);
        return baseService.detail(id);
    }


    @Operation(
            summary = "修改出库状态",
            description = "修改出库状态"
    )
    @Parameter(
            name = "id",
            description = "ID",
            example = "1",
            in = ParameterIn.PATH
    )
    @ResponseBody
    @PutMapping("/status/{id}/{status}")
    public void updateStatus(@PathVariable("id") Long id,@PathVariable("status") int status) {
        this.checkPermission(Api.UPDATE);
        baseService.updateStatus(id,status);
    }
}