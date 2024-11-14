package top.continew.admin.wms.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RestController;
import top.continew.admin.wms.model.query.GoodsStockQuery;
import top.continew.admin.wms.model.req.GoodsStockReq;
import top.continew.admin.wms.model.resp.GoodsStockDetailResp;
import top.continew.admin.wms.model.resp.GoodsStockResp;
import top.continew.admin.wms.service.GoodsStockService;
import top.continew.starter.extension.crud.annotation.CrudRequestMapping;
import top.continew.starter.extension.crud.controller.BaseController;
import top.continew.starter.extension.crud.enums.Api;

/**
 * 商品库存管理 API
 *
 * @author luoqiz
 * @since 2024/11/13 17:57
 */
@Tag(name = "商品库存管理 API")
@RestController
@CrudRequestMapping(value = "/wms/goodsStock", api = {Api.PAGE, Api.GET, Api.ADD, Api.UPDATE, Api.DELETE, Api.EXPORT})
public class GoodsStockController extends BaseController<GoodsStockService, GoodsStockResp, GoodsStockDetailResp, GoodsStockQuery, GoodsStockReq> {
}