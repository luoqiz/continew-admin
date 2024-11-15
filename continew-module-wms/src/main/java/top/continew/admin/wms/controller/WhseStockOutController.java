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
    @GetMapping("/detail/{id}")
    public WhseStockOutInfoResp detail(@PathVariable("id") Long id) {
        this.checkPermission(Api.LIST);
        return baseService.detailById(id);
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
        this.checkPermission(Api.LIST);
        baseService.updateStatus(id,status);
    }
}