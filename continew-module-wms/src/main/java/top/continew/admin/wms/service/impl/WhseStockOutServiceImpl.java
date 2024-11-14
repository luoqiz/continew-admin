package top.continew.admin.wms.service.impl;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import top.continew.starter.extension.crud.service.impl.BaseServiceImpl;
import top.continew.admin.wms.mapper.WhseStockOutMapper;
import top.continew.admin.wms.model.entity.WhseStockOutDO;
import top.continew.admin.wms.model.query.WhseStockOutQuery;
import top.continew.admin.wms.model.req.WhseStockOutReq;
import top.continew.admin.wms.model.resp.WhseStockOutDetailResp;
import top.continew.admin.wms.model.resp.WhseStockOutResp;
import top.continew.admin.wms.service.WhseStockOutService;

/**
 * 仓库出库业务实现
 *
 * @author luoqiz
 * @since 2024/11/14 15:15
 */
@Service
@RequiredArgsConstructor
public class WhseStockOutServiceImpl extends BaseServiceImpl<WhseStockOutMapper, WhseStockOutDO, WhseStockOutResp, WhseStockOutDetailResp, WhseStockOutQuery, WhseStockOutReq> implements WhseStockOutService {}