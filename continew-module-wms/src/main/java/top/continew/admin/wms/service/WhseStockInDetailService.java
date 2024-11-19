package top.continew.admin.wms.service;

import top.continew.admin.wms.model.query.WhseStockInDetailQuery;
import top.continew.admin.wms.model.req.WhseStockInDetailReq;
import top.continew.admin.wms.model.resp.WhseStockInDetailDetailResp;
import top.continew.admin.wms.model.resp.WhseStockInDetailResp;
import top.continew.starter.extension.crud.model.query.SortQuery;
import top.continew.starter.extension.crud.service.BaseService;

import java.util.List;

/**
 * 仓库入库明细业务接口
 *
 * @author luoqiz
 * @since 2024/11/11 11:53
 */
public interface WhseStockInDetailService extends BaseService<WhseStockInDetailResp, WhseStockInDetailDetailResp, WhseStockInDetailQuery, WhseStockInDetailReq> {

    @Override
    List<WhseStockInDetailResp> list(WhseStockInDetailQuery query, SortQuery sortQuery);

    /**
     * 批量插入数据
     *
     * @param reqs
     */
    void batchAdd(List<WhseStockInDetailReq> reqs);
}

