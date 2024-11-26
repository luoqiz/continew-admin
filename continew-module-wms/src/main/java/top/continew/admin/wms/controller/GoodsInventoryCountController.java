package top.continew.admin.wms.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import top.continew.admin.wms.model.query.GoodsInventoryCountQuery;
import top.continew.admin.wms.model.req.GoodsInventoryCountReq;
import top.continew.admin.wms.model.resp.GoodsInventoryCountDetailResp;
import top.continew.admin.wms.model.resp.GoodsInventoryCountResp;
import top.continew.admin.wms.service.GoodsInventoryCountService;
import top.continew.starter.extension.crud.annotation.CrudRequestMapping;
import top.continew.starter.extension.crud.controller.BaseController;
import top.continew.starter.extension.crud.enums.Api;

/**
 * 物料盘点管理 API
 *
 * @author luoqiz
 * @since 2024/11/26 13:51
 */
@Tag(name = "物料盘点管理 API")
@RestController
@CrudRequestMapping(value = "/wms/goodsInventoryCount", api = {Api.PAGE, Api.DETAIL, Api.ADD, Api.UPDATE, Api.DELETE, Api.EXPORT})
public class GoodsInventoryCountController extends BaseController<GoodsInventoryCountService, GoodsInventoryCountResp, GoodsInventoryCountDetailResp, GoodsInventoryCountQuery, GoodsInventoryCountReq> {

    @Operation(summary = "修改盘点状态", description = "修改盘点状态")
    @Parameter(name = "id", description = "ID", example = "1", in = ParameterIn.PATH)
    @ResponseBody
    @PutMapping("/status/{id}/{status}")
    public boolean updateStatus(@PathVariable("id") Long id, @PathVariable("status") int status) {
        this.checkPermission(Api.UPDATE);
        return baseService.updateStatus(id, status);
    }
}