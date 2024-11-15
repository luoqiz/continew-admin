package top.continew.admin.wms.controller;

import top.continew.starter.extension.crud.enums.Api;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.*;

import top.continew.starter.extension.crud.annotation.CrudRequestMapping;
import top.continew.starter.extension.crud.controller.BaseController;
import top.continew.admin.wms.model.query.WhseStockOutDetailQuery;
import top.continew.admin.wms.model.req.WhseStockOutDetailReq;
import top.continew.admin.wms.model.resp.WhseStockOutDetailDetailResp;
import top.continew.admin.wms.model.resp.WhseStockOutDetailMainResp;
import top.continew.admin.wms.service.WhseStockOutDetailService;

/**
 * 仓库出库明细管理 API
 *
 * @author luoqiz
 * @since 2024/11/14 16:21
 */
@Tag(name = "仓库出库明细管理 API")
@RestController
@CrudRequestMapping(value = "/wms/whseStockOutDetail", api = {Api.PAGE, Api.GET, Api.ADD, Api.UPDATE, Api.DELETE, Api.EXPORT})
public class WhseStockOutDetailController extends BaseController<WhseStockOutDetailService, WhseStockOutDetailMainResp, WhseStockOutDetailDetailResp, WhseStockOutDetailQuery, WhseStockOutDetailReq> {

}