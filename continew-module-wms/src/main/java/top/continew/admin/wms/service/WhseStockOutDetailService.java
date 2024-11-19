package top.continew.admin.wms.service;

import top.continew.admin.wms.model.entity.WhseStockOutDetailDO;
import top.continew.starter.extension.crud.service.BaseService;
import top.continew.admin.wms.model.query.WhseStockOutDetailQuery;
import top.continew.admin.wms.model.req.WhseStockOutDetailReq;
import top.continew.admin.wms.model.resp.WhseStockOutDetailDetailResp;
import top.continew.admin.wms.model.resp.WhseStockOutDetailMainResp;

import java.util.List;

/**
 * 仓库出库明细业务接口
 *
 * @author luoqiz
 * @since 2024/11/14 16:21
 */
public interface WhseStockOutDetailService extends BaseService<WhseStockOutDetailMainResp, WhseStockOutDetailDetailResp, WhseStockOutDetailQuery, WhseStockOutDetailReq> {
    void batchAdd(List<WhseStockOutDetailDO> stockOutDetailReqList);
}