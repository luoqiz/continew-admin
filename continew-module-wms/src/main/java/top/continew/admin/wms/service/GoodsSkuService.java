package top.continew.admin.wms.service;

import top.continew.admin.wms.model.entity.GoodsSkuDO;
import top.continew.admin.wms.model.query.GoodsSkuQuery;
import top.continew.admin.wms.model.req.GoodsSkuReq;
import top.continew.admin.wms.model.resp.GoodsSkuDetailResp;
import top.continew.admin.wms.model.resp.GoodsSkuResp;
import top.continew.starter.extension.crud.service.BaseService;

import java.util.List;

/**
 * 商品规格(sku)业务接口
 *
 * @author luoqiz
 * @since 2024/11/07 11:10
 */
public interface GoodsSkuService extends BaseService<GoodsSkuResp, GoodsSkuDetailResp, GoodsSkuQuery, GoodsSkuReq> {
    GoodsSkuDetailResp getBySkuNo(String skuNo);

    List<GoodsSkuDO> getBySkuNoList(List<String> list);
}