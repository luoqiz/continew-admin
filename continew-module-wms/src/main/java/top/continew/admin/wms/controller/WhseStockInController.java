package top.continew.admin.wms.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
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
@CrudRequestMapping(value = "/wms/whseStockIn", api = {Api.PAGE, Api.GET, Api.ADD, Api.UPDATE, Api.DELETE, Api.EXPORT})
public class WhseStockInController extends BaseController<WhseStockInService, WhseStockInResp, WhseStockInInfoResp, WhseStockInQuery, WhseStockInReq> {

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
    public WhseStockInInfoResp detail(@PathVariable("id") Long id) {
        this.checkPermission(Api.LIST);
        return baseService.detailById(id);
    }
}