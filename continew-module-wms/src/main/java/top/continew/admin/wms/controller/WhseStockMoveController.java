package top.continew.admin.wms.controller;

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
public class WhseStockMoveController extends BaseController<WhseStockMoveService, WhseStockMoveResp, WhseStockMoveDetailResp, WhseStockMoveQuery, WhseStockMoveReq> {}