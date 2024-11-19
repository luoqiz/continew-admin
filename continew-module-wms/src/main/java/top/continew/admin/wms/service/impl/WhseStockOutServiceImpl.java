package top.continew.admin.wms.service.impl;

import cn.hutool.core.bean.BeanUtil;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.continew.admin.wms.mapper.WhseStockOutMapper;
import top.continew.admin.wms.model.entity.WhseStockOutDO;
import top.continew.admin.wms.model.entity.WhseStockOutDetailDO;
import top.continew.admin.wms.model.query.WhseStockOutDetailQuery;
import top.continew.admin.wms.model.query.WhseStockOutQuery;
import top.continew.admin.wms.model.req.WhseStockOutDetailReq;
import top.continew.admin.wms.model.req.WhseStockOutReq;
import top.continew.admin.wms.model.resp.WhseStockOutDetailMainResp;
import top.continew.admin.wms.model.resp.WhseStockOutInfoResp;
import top.continew.admin.wms.model.resp.WhseStockOutResp;
import top.continew.admin.wms.service.WhseStockOutDetailService;
import top.continew.admin.wms.service.WhseStockOutService;
import top.continew.starter.core.exception.BusinessException;
import top.continew.starter.extension.crud.model.query.SortQuery;
import top.continew.starter.extension.crud.service.impl.BaseServiceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
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
    public void update(WhseStockOutReq req, Long id) {
        WhseStockOutDetailQuery query = new WhseStockOutDetailQuery();
        query.setStockOutId(id);
        List<WhseStockOutDetailMainResp> list = detailService.list(query, new SortQuery());
        if (list.size() > 0) {
            throw new BusinessException("已经录入商品无法修改！");
        }
        super.update(req, id);
    }

    @Override
    public void updateStatus(Long id, int status) {
        WhseStockOutDO entity = this.getById(id);
        if (entity == null) {
            throw new RuntimeException("数据不存在，请检查！");
        }
        if (status == 3) {
            entity.setOutTime(LocalDate.now());

            WhseStockOutDetailQuery query = new WhseStockOutDetailQuery();
            query.setStockOutId(id);
            List<WhseStockOutDetailMainResp> detailList = detailService.list(query, new SortQuery());
            for (WhseStockOutDetailMainResp whseStockOutDetailMainResp : detailList) {
                if(whseStockOutDetailMainResp.getStatus()!=2){
                    throw new RuntimeException("有物料尚未核验，请重新检查！");
                }
            }

        }
        entity.setStatus(status);
        baseMapper.updateById(entity);
    }

    @Transactional
    @Override
    public Long add(WhseStockOutReq stockOutReq, List<WhseStockOutDetailReq> stockOutDetailReqList) {
        Long id = add(stockOutReq);
        List<WhseStockOutDetailDO> list = new ArrayList<>();
        for (WhseStockOutDetailReq whseStockOutDetailReq : stockOutDetailReqList) {
            WhseStockOutDetailDO detailDO = new WhseStockOutDetailDO();
            BeanUtil.copyProperties(whseStockOutDetailReq, detailDO);
            detailDO.setStockOutId(id);
            list.add(detailDO);
        }
        detailService.batchAdd(list);
        return id;
    }
}