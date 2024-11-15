package top.continew.admin.wms.service.impl;

import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import top.continew.admin.wms.mapper.WhseStockOutMapper;
import top.continew.admin.wms.model.entity.WhseStockOutDO;
import top.continew.admin.wms.model.query.WhseStockOutDetailQuery;
import top.continew.admin.wms.model.query.WhseStockOutQuery;
import top.continew.admin.wms.model.req.WhseStockOutReq;
import top.continew.admin.wms.model.resp.WhseStockOutDetailMainResp;
import top.continew.admin.wms.model.resp.WhseStockOutInfoResp;
import top.continew.admin.wms.model.resp.WhseStockOutResp;
import top.continew.admin.wms.service.WhseStockOutDetailService;
import top.continew.admin.wms.service.WhseStockOutService;
import top.continew.starter.extension.crud.model.query.SortQuery;
import top.continew.starter.extension.crud.service.impl.BaseServiceImpl;

import java.util.List;

/**
 * 仓库出库业务实现
 *
 * @author luoqiz
 * @since 2024/11/14 15:15
 */
@Service
@RequiredArgsConstructor
public class WhseStockOutServiceImpl extends BaseServiceImpl<WhseStockOutMapper, WhseStockOutDO, WhseStockOutResp, WhseStockOutInfoResp, WhseStockOutQuery, WhseStockOutReq> implements WhseStockOutService {

    @Resource
    private WhseStockOutDetailService detailService;

    @Override
    public WhseStockOutInfoResp detailById(Long id) {
        WhseStockOutInfoResp info = get(id);
        if (info == null) {
            throw new RuntimeException("数据不存在");
        }
        WhseStockOutDetailQuery query = new WhseStockOutDetailQuery();
        query.setStockOutId(id);
        List<WhseStockOutDetailMainResp> list = detailService.list(query, new SortQuery());
        info.setGoodsList(list);
        return info;
    }

    @Override
    public void updateStatus(Long id, int status) {
        WhseStockOutDO entity = this.getById(id);
        if (entity == null) {
            throw new RuntimeException("数据不存在，请检查！");
        }
        entity.setStatus(status);
        baseMapper.updateById(entity);
    }
}