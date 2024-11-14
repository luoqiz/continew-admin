package top.continew.admin.wms.controller;

import top.continew.starter.extension.crud.enums.Api;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.*;

import top.continew.starter.extension.crud.annotation.CrudRequestMapping;
import top.continew.starter.extension.crud.controller.BaseController;
import top.continew.admin.wms.model.query.WhseStockOutQuery;
import top.continew.admin.wms.model.req.WhseStockOutReq;
import top.continew.admin.wms.model.resp.WhseStockOutDetailResp;
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
public class WhseStockOutController extends BaseController<WhseStockOutService, WhseStockOutResp, WhseStockOutDetailResp, WhseStockOutQuery, WhseStockOutReq> {}