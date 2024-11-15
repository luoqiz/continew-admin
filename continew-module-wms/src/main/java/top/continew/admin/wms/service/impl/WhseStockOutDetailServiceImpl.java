package top.continew.admin.wms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.continew.admin.wms.mapper.WhseStockOutDetailMapper;
import top.continew.admin.wms.model.entity.GoodsStockDO;
import top.continew.admin.wms.model.entity.WhseStockOutDetailDO;
import top.continew.admin.wms.model.query.GoodsStockQuery;
import top.continew.admin.wms.model.query.WhseStockOutDetailQuery;
import top.continew.admin.wms.model.req.GoodsStockReq;
import top.continew.admin.wms.model.req.WhseStockOutDetailReq;
import top.continew.admin.wms.model.resp.*;
import top.continew.admin.wms.service.GoodsStockService;
import top.continew.admin.wms.service.WhseStockOutDetailService;
import top.continew.admin.wms.service.WhseStockOutService;
import top.continew.starter.core.exception.BusinessException;
import top.continew.starter.extension.crud.model.query.SortQuery;
import top.continew.starter.extension.crud.service.impl.BaseServiceImpl;

import java.util.List;

/**
 * 仓库出库明细业务实现
 *
 * @author luoqiz
 * @since 2024/11/14 16:21
 */
@Service
@RequiredArgsConstructor
public class WhseStockOutDetailServiceImpl extends BaseServiceImpl<WhseStockOutDetailMapper, WhseStockOutDetailDO, WhseStockOutDetailMainResp, WhseStockOutDetailDetailResp, WhseStockOutDetailQuery, WhseStockOutDetailReq> implements WhseStockOutDetailService {

    @Resource
    private GoodsStockService stockService;

    @Resource
    @Lazy
    private WhseStockOutService stockOutService;

    @Override
    @Transactional
    public Long add(WhseStockOutDetailReq req) {
        // 获取入库单
        WhseStockOutInfoResp stockOutInfo = stockOutService.get(req.getStockOutId());

        // 获取当前仓库有相同物料的所有批次
        GoodsStockQuery stockQuery = new GoodsStockQuery();
        // 仓库id
        stockQuery.setWhseId(stockOutInfo.getWhseId());
        stockQuery.setGoodsSku(req.getGoodsSku());
        SortQuery stockSortQuery = new SortQuery();
        stockSortQuery.setSort(new String[]{"createTime", "asc"});
        List<GoodsStockResp> goodsStock = stockService.list(stockQuery, stockSortQuery);
        // 若是按照入库时间排序后，第一条不是现在提交的数据，则不允许提交。必须先入库的先出库
        if (goodsStock.get(0) == null) {
            throw new BusinessException("提交数据错误！");
        }
        if (!goodsStock.get(0).getId().equals(req.getGoodsStockId())) {
            throw new BusinessException("您出库的不是最早入库的物料！");
        }

        synchronized (this) {
            GoodsStockDetailResp goodsInfo = stockService.get(req.getGoodsStockId());
            if (goodsInfo.getRealNum() < req.getPlanNum()) {
                throw new BusinessException("库存不足！");
            }
            GoodsStockReq updateQuery = new GoodsStockReq();
            updateQuery.setRealNum(goodsInfo.getRealNum() - req.getPlanNum());
            stockService.update(updateQuery, req.getGoodsStockId());
            return super.add(req);
        }
    }

    @Override
    @Transactional
    public void delete(List<Long> ids) {
        LambdaQueryWrapper<WhseStockOutDetailDO> query = new LambdaQueryWrapper<>();
        query.in(WhseStockOutDetailDO::getId, ids);
        List<WhseStockOutDetailDO> res = list(query);
        for (WhseStockOutDetailDO re : res) {
            GoodsStockDO temp = new GoodsStockDO();
            temp.setId(re.getGoodsStockId());
            LambdaUpdateWrapper<GoodsStockDO> updateQuery = Wrappers.update(temp).setSql("`real_num` = `real_num` + " + re.getPlanNum()).lambda();
            updateQuery.eq(GoodsStockDO::getId, re.getGoodsStockId());
            stockService.updates(updateQuery);
        }
        super.delete(ids);
    }
}