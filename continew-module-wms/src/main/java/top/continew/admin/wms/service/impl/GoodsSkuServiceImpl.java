package top.continew.admin.wms.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import top.continew.admin.wms.mapper.GoodsSkuMapper;
import top.continew.admin.wms.model.entity.GoodsSkuDO;
import top.continew.admin.wms.model.query.GoodsSkuQuery;
import top.continew.admin.wms.model.req.GoodsSkuReq;
import top.continew.admin.wms.model.resp.GoodsSkuDetailResp;
import top.continew.admin.wms.model.resp.GoodsSkuResp;
import top.continew.admin.wms.service.GoodsSkuService;
import top.continew.starter.extension.crud.service.impl.BaseServiceImpl;

import java.util.List;

/**
 * 商品规格(sku)业务实现
 *
 * @author luoqiz
 * @since 2024/11/07 11:10
 */
@Service
@RequiredArgsConstructor
public class GoodsSkuServiceImpl extends BaseServiceImpl<GoodsSkuMapper, GoodsSkuDO, GoodsSkuResp, GoodsSkuDetailResp, GoodsSkuQuery, GoodsSkuReq> implements GoodsSkuService {
    @Override
    public GoodsSkuDetailResp getBySkuNo(String skuNo) {
        LambdaQueryWrapper<GoodsSkuDO> query = new LambdaQueryWrapper<>();
        query.eq(GoodsSkuDO::getBarcode, skuNo);
        GoodsSkuDO goodsSkuDo = baseMapper.selectOne(query);
        GoodsSkuDetailResp resp = new GoodsSkuDetailResp();
        BeanUtil.copyProperties(goodsSkuDo, resp);
        return resp;
    }

    @Override
    public List<GoodsSkuDO> getBySkuNoList(List<String> barcodes) {
        LambdaQueryWrapper<GoodsSkuDO> query = new LambdaQueryWrapper<>();
        query.in(GoodsSkuDO::getBarcode, barcodes);
        return baseMapper.selectList(query);
    }
}