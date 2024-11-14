package top.continew.admin.wms.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import top.continew.admin.wms.mapper.GoodsStockMapper;
import top.continew.admin.wms.model.entity.GoodsStockDO;
import top.continew.admin.wms.model.query.GoodsStockQuery;
import top.continew.admin.wms.model.req.GoodsStockReq;
import top.continew.admin.wms.model.resp.GoodsStockDetailResp;
import top.continew.admin.wms.model.resp.GoodsStockResp;
import top.continew.admin.wms.service.GoodsStockService;
import top.continew.starter.extension.crud.service.impl.BaseServiceImpl;

import java.util.List;

/**
 * 商品库存业务实现
 *
 * @author luoqiz
 * @since 2024/11/13 17:57
 */
@Service
@RequiredArgsConstructor
public class GoodsStockServiceImpl extends BaseServiceImpl<GoodsStockMapper, GoodsStockDO, GoodsStockResp, GoodsStockDetailResp, GoodsStockQuery, GoodsStockReq> implements GoodsStockService {
    public boolean batchAdd(List<GoodsStockDO> datas) {
        return baseMapper.insertBatch(datas);
    }
}