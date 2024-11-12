package top.continew.admin.wms.service;

import top.continew.admin.wms.model.query.WhseStockInQuery;
import top.continew.admin.wms.model.req.WhseStockInReq;
import top.continew.admin.wms.model.resp.WhseStockInInfoResp;
import top.continew.admin.wms.model.resp.WhseStockInResp;
import top.continew.starter.extension.crud.service.BaseService;

/**
 * 仓库入库业务接口
 *
 * @author luoqiz
 * @since 2024/11/07 17:49
 */
public interface WhseStockInService extends BaseService<WhseStockInResp, WhseStockInInfoResp, WhseStockInQuery, WhseStockInReq> {
    WhseStockInInfoResp detailById(Long id);
}