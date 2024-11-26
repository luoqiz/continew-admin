package top.continew.admin.wms.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RestController;
import top.continew.admin.wms.model.query.GoodsInventoryCountItemQuery;
import top.continew.admin.wms.model.req.GoodsInventoryCountItemReq;
import top.continew.admin.wms.model.resp.GoodsInventoryCountItemDetailResp;
import top.continew.admin.wms.model.resp.GoodsInventoryCountItemResp;
import top.continew.admin.wms.service.GoodsInventoryCountItemService;
import top.continew.starter.extension.crud.annotation.CrudRequestMapping;
import top.continew.starter.extension.crud.controller.BaseController;
import top.continew.starter.extension.crud.enums.Api;

/**
 * 物料盘点详情管理 API
 *
 * @author luoqiz
 * @since 2024/11/26 13:44
 */
@Tag(name = "物料盘点详情管理 API")
@RestController
@CrudRequestMapping(value = "/wms/goodsInventoryCountItem", api = {Api.PAGE, Api.DETAIL, Api.ADD, Api.UPDATE, Api.DELETE, Api.EXPORT})
public class GoodsInventoryCountItemController extends BaseController<GoodsInventoryCountItemService, GoodsInventoryCountItemResp, GoodsInventoryCountItemDetailResp, GoodsInventoryCountItemQuery, GoodsInventoryCountItemReq> {
}