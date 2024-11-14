package top.continew.admin.wms.service;

import top.continew.starter.extension.crud.service.BaseService;
import top.continew.admin.wms.model.query.WhseStockOutQuery;
import top.continew.admin.wms.model.req.WhseStockOutReq;
import top.continew.admin.wms.model.resp.WhseStockOutDetailResp;
import top.continew.admin.wms.model.resp.WhseStockOutResp;

/**
 * 仓库出库业务接口
 *
 * @author luoqiz
 * @since 2024/11/14 15:15
 */
public interface WhseStockOutService extends BaseService<WhseStockOutResp, WhseStockOutDetailResp, WhseStockOutQuery, WhseStockOutReq> {}