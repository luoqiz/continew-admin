package top.continew.admin.wms.service.impl;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import top.continew.starter.extension.crud.service.impl.BaseServiceImpl;
import top.continew.admin.wms.mapper.GoodsSkuMapper;
import top.continew.admin.wms.model.entity.GoodsSkuDO;
import top.continew.admin.wms.model.query.GoodsSkuQuery;
import top.continew.admin.wms.model.req.GoodsSkuReq;
import top.continew.admin.wms.model.resp.GoodsSkuDetailResp;
import top.continew.admin.wms.model.resp.GoodsSkuResp;
import top.continew.admin.wms.service.GoodsSkuService;

/**
 * 商品规格(sku)业务实现
 *
 * @author luoqiz
 * @since 2024/11/07 11:10
 */
@Service
@RequiredArgsConstructor
public class GoodsSkuServiceImpl extends BaseServiceImpl<GoodsSkuMapper, GoodsSkuDO, GoodsSkuResp, GoodsSkuDetailResp, GoodsSkuQuery, GoodsSkuReq> implements GoodsSkuService {}