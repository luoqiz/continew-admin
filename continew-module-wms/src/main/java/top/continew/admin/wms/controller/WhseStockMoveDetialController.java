package top.continew.admin.wms.controller;

import top.continew.starter.extension.crud.enums.Api;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.*;

import top.continew.starter.extension.crud.annotation.CrudRequestMapping;
import top.continew.starter.extension.crud.controller.BaseController;
import top.continew.admin.wms.model.query.WhseStockMoveDetialQuery;
import top.continew.admin.wms.model.req.WhseStockMoveDetialReq;
import top.continew.admin.wms.model.resp.WhseStockMoveDetialDetailResp;
import top.continew.admin.wms.model.resp.WhseStockMoveDetialResp;
import top.continew.admin.wms.service.WhseStockMoveDetialService;

/**
 * 仓库移库明细管理 API
 *
 * @author luoqiz
 * @since 2024/11/15 17:17
 */
@Tag(name = "仓库移库明细管理 API")
@RestController
@CrudRequestMapping(value = "/wms/whseStockMoveDetial", api = {Api.PAGE, Api.GET, Api.ADD, Api.UPDATE, Api.DELETE, Api.EXPORT})
public class WhseStockMoveDetialController extends BaseController<WhseStockMoveDetialService, WhseStockMoveDetialResp, WhseStockMoveDetialDetailResp, WhseStockMoveDetialQuery, WhseStockMoveDetialReq> {}