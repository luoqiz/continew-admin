package top.continew.admin.wms.service.impl;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import top.continew.starter.extension.crud.service.impl.BaseServiceImpl;
import top.continew.admin.wms.mapper.WhseStockMoveMapper;
import top.continew.admin.wms.model.entity.WhseStockMoveDO;
import top.continew.admin.wms.model.query.WhseStockMoveQuery;
import top.continew.admin.wms.model.req.WhseStockMoveReq;
import top.continew.admin.wms.model.resp.WhseStockMoveDetailResp;
import top.continew.admin.wms.model.resp.WhseStockMoveResp;
import top.continew.admin.wms.service.WhseStockMoveService;

/**
 * 仓库移库业务实现
 *
 * @author luoqiz
 * @since 2024/11/15 14:50
 */
@Service
@RequiredArgsConstructor
public class WhseStockMoveServiceImpl extends BaseServiceImpl<WhseStockMoveMapper, WhseStockMoveDO, WhseStockMoveResp, WhseStockMoveDetailResp, WhseStockMoveQuery, WhseStockMoveReq> implements WhseStockMoveService {}