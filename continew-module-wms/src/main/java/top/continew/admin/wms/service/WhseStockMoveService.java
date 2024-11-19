package top.continew.admin.wms.service;

import top.continew.starter.extension.crud.service.BaseService;
import top.continew.admin.wms.model.query.WhseStockMoveQuery;
import top.continew.admin.wms.model.req.WhseStockMoveReq;
import top.continew.admin.wms.model.resp.WhseStockMoveDetailResp;
import top.continew.admin.wms.model.resp.WhseStockMoveResp;

/**
 * 仓库移库业务接口
 *
 * @author luoqiz
 * @since 2024/11/15 14:50
 */
public interface WhseStockMoveService extends BaseService<WhseStockMoveResp, WhseStockMoveDetailResp, WhseStockMoveQuery, WhseStockMoveReq> {

    WhseStockMoveDetailResp detail(Long id);

    void updateStatus(Long id, int status);
}