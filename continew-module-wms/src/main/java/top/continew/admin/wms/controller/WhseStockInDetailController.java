package top.continew.admin.wms.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RestController;
import top.continew.admin.wms.model.query.WhseStockInDetailQuery;
import top.continew.admin.wms.model.req.WhseStockInDetailReq;
import top.continew.admin.wms.model.resp.WhseStockInDetailDetailResp;
import top.continew.admin.wms.model.resp.WhseStockInDetailResp;
import top.continew.admin.wms.service.WhseStockInDetailService;
import top.continew.starter.extension.crud.annotation.CrudRequestMapping;
import top.continew.starter.extension.crud.controller.BaseController;
import top.continew.starter.extension.crud.enums.Api;

/**
 * 仓库入库明细管理 API
 *
 * @author luoqiz
 * @since 2024/11/11 11:53
 */
@Tag(name = "仓库入库明细管理 API")
@RestController
@CrudRequestMapping(value = "/wms/whseStockInDetail", api = {Api.PAGE, Api.GET, Api.ADD, Api.UPDATE, Api.DELETE, Api.EXPORT})
public class WhseStockInDetailController extends BaseController<WhseStockInDetailService, WhseStockInDetailResp, WhseStockInDetailDetailResp, WhseStockInDetailQuery, WhseStockInDetailReq> {
}