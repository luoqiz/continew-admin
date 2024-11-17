package top.continew.admin.wms.service;

import top.continew.starter.extension.crud.service.BaseService;
import top.continew.admin.wms.model.query.WhseStockMoveDetialQuery;
import top.continew.admin.wms.model.req.WhseStockMoveDetialReq;
import top.continew.admin.wms.model.resp.WhseStockMoveDetialDetailResp;
import top.continew.admin.wms.model.resp.WhseStockMoveDetialResp;

/**
 * 仓库移库明细业务接口
 *
 * @author luoqiz
 * @since 2024/11/15 17:17
 */
public interface WhseStockMoveDetialService extends BaseService<WhseStockMoveDetialResp, WhseStockMoveDetialDetailResp, WhseStockMoveDetialQuery, WhseStockMoveDetialReq> {}