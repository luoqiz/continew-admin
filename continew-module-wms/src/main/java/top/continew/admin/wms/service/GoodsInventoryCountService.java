package top.continew.admin.wms.service;

import top.continew.admin.wms.model.query.GoodsInventoryCountQuery;
import top.continew.admin.wms.model.req.GoodsInventoryCountReq;
import top.continew.admin.wms.model.resp.GoodsInventoryCountDetailResp;
import top.continew.admin.wms.model.resp.GoodsInventoryCountResp;
import top.continew.starter.extension.crud.service.BaseService;

/**
 * 物料盘点业务接口
 *
 * @author luoqiz
 * @since 2024/11/26 13:51
 */
public interface GoodsInventoryCountService extends BaseService<GoodsInventoryCountResp, GoodsInventoryCountDetailResp, GoodsInventoryCountQuery, GoodsInventoryCountReq> {
    boolean updateStatus(Long id, int status);
}