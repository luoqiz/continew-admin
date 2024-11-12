package top.continew.admin.wms.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import top.continew.admin.wms.mapper.WhseStockInDetailMapper;
import top.continew.admin.wms.model.entity.WhseStockInDetailDO;
import top.continew.admin.wms.model.query.WhseStockInDetailQuery;
import top.continew.admin.wms.model.req.WhseStockInDetailReq;
import top.continew.admin.wms.model.resp.WhseStockInDetailDetailResp;
import top.continew.admin.wms.model.resp.WhseStockInDetailResp;
import top.continew.admin.wms.service.WhseStockInDetailService;
import top.continew.starter.extension.crud.service.impl.BaseServiceImpl;

/**
 * 仓库入库明细业务实现
 *
 * @author luoqiz
 * @since 2024/11/11 11:53
 */
@Service
@RequiredArgsConstructor
public class WhseStockInDetailServiceImpl extends BaseServiceImpl<WhseStockInDetailMapper, WhseStockInDetailDO, WhseStockInDetailResp, WhseStockInDetailDetailResp, WhseStockInDetailQuery, WhseStockInDetailReq> implements WhseStockInDetailService {
}