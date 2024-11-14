package top.continew.admin.wms.service;

import top.continew.admin.wms.model.entity.GoodsStockDO;
import top.continew.admin.wms.model.query.GoodsStockQuery;
import top.continew.admin.wms.model.req.GoodsStockReq;
import top.continew.admin.wms.model.resp.GoodsStockDetailResp;
import top.continew.admin.wms.model.resp.GoodsStockResp;
import top.continew.starter.extension.crud.service.BaseService;

import java.util.List;

/**
 * 商品库存业务接口
 *
 * @author luoqiz
 * @since 2024/11/13 17:57
 */
public interface GoodsStockService extends BaseService<GoodsStockResp, GoodsStockDetailResp, GoodsStockQuery, GoodsStockReq> {
    boolean batchAdd(List<GoodsStockDO> datas);
}