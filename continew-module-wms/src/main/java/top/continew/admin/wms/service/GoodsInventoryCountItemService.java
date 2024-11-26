package top.continew.admin.wms.service;

import top.continew.admin.wms.model.query.GoodsInventoryCountItemQuery;
import top.continew.admin.wms.model.req.GoodsInventoryCountItemReq;
import top.continew.admin.wms.model.resp.GoodsInventoryCountItemDetailResp;
import top.continew.admin.wms.model.resp.GoodsInventoryCountItemResp;
import top.continew.starter.extension.crud.service.BaseService;

/**
 * 物料盘点详情业务接口
 *
 * @author luoqiz
 * @since 2024/11/26 13:44
 */
public interface GoodsInventoryCountItemService extends BaseService<GoodsInventoryCountItemResp, GoodsInventoryCountItemDetailResp, GoodsInventoryCountItemQuery, GoodsInventoryCountItemReq> {


}