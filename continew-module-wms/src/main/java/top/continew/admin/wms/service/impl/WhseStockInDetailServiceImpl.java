package top.continew.admin.wms.service.impl;

import cn.hutool.core.bean.BeanUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.continew.admin.wms.mapper.WhseStockInDetailMapper;
import top.continew.admin.wms.model.entity.WhseStockInDetailDO;
import top.continew.admin.wms.model.query.WhseStockInDetailQuery;
import top.continew.admin.wms.model.req.WhseStockInDetailReq;
import top.continew.admin.wms.model.resp.WhseStockInDetailDetailResp;
import top.continew.admin.wms.model.resp.WhseStockInDetailResp;
import top.continew.admin.wms.service.WhseStockInDetailService;
import top.continew.starter.extension.crud.service.impl.BaseServiceImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 仓库入库明细业务实现
 *
 * @author luoqiz
 * @since 2024/11/11 11:53
 */
@Service
@RequiredArgsConstructor
public class WhseStockInDetailServiceImpl extends BaseServiceImpl<WhseStockInDetailMapper, WhseStockInDetailDO, WhseStockInDetailResp, WhseStockInDetailDetailResp, WhseStockInDetailQuery, WhseStockInDetailReq> implements WhseStockInDetailService {

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void batchAdd(List<WhseStockInDetailReq> reqs) {
        Collection<WhseStockInDetailDO> entityList = new ArrayList<>();
        for (WhseStockInDetailReq req : reqs) {
            WhseStockInDetailDO entity = new WhseStockInDetailDO();
            BeanUtil.copyProperties(req, entity);
            entityList.add(entity);
        }
        baseMapper.insertBatch(entityList);
    }
}