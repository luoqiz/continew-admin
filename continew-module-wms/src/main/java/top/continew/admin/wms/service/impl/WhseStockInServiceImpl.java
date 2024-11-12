package top.continew.admin.wms.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import top.continew.admin.wms.enums.WhseStockInStatusEnum;
import top.continew.admin.wms.mapper.WhseStockInMapper;
import top.continew.admin.wms.model.entity.WhseStockInDO;
import top.continew.admin.wms.model.entity.WhseStockInDetailDO;
import top.continew.admin.wms.model.query.WhseStockInDetailQuery;
import top.continew.admin.wms.model.query.WhseStockInQuery;
import top.continew.admin.wms.model.req.WhseStockInReq;
import top.continew.admin.wms.model.resp.WhseStockInDetailResp;
import top.continew.admin.wms.model.resp.WhseStockInInfoResp;
import top.continew.admin.wms.model.resp.WhseStockInResp;
import top.continew.admin.wms.service.WhseStockInDetailService;
import top.continew.admin.wms.service.WhseStockInService;
import top.continew.starter.extension.crud.model.query.SortQuery;
import top.continew.starter.extension.crud.service.impl.BaseServiceImpl;

import java.util.List;

/**
 * 仓库入库业务实现
 *
 * @author luoqiz
 * @since 2024/11/07 17:49
 */
@Service
@RequiredArgsConstructor
public class WhseStockInServiceImpl extends BaseServiceImpl<WhseStockInMapper, WhseStockInDO, WhseStockInResp, WhseStockInInfoResp, WhseStockInQuery, WhseStockInReq> implements WhseStockInService {

    @Resource
    private WhseStockInDetailService detailService;

    @Override
    public Long add(WhseStockInReq req) {
        this.beforeAdd(req);
        WhseStockInDO entity = BeanUtil.copyProperties(req, super.getEntityClass());
        entity.setStatus(WhseStockInStatusEnum.AWAIT_APPROVAL.getValue());
        baseMapper.insert(entity);
        this.afterAdd(req, entity);
        return entity.getId();
    }

    @Override
    public WhseStockInInfoResp detailById(Long id) {
        WhseStockInInfoResp stockInInfo = get(id);

        LambdaQueryWrapper<WhseStockInDetailDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(WhseStockInDetailDO::getStockInId, id);
        SortQuery sortquery = new SortQuery();
        WhseStockInDetailQuery query = new WhseStockInDetailQuery();
        query.setStockInId(id);
        sortquery.setSort(null);
        List<WhseStockInDetailResp> list = detailService.list(query, sortquery);
        stockInInfo.setGoodsList(list);
        return null;
    }
}