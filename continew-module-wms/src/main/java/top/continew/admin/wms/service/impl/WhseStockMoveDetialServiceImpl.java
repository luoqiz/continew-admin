package top.continew.admin.wms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.continew.admin.wms.mapper.WhseStockMoveDetialMapper;
import top.continew.admin.wms.model.entity.GoodsStockDO;
import top.continew.admin.wms.model.entity.WhseStockMoveDetialDO;
import top.continew.admin.wms.model.query.GoodsStockQuery;
import top.continew.admin.wms.model.query.WhseStockMoveDetialQuery;
import top.continew.admin.wms.model.req.GoodsStockReq;
import top.continew.admin.wms.model.req.WhseStockMoveDetialReq;
import top.continew.admin.wms.model.resp.*;
import top.continew.admin.wms.service.GoodsStockService;
import top.continew.admin.wms.service.WhseStockMoveDetialService;
import top.continew.admin.wms.service.WhseStockMoveService;
import top.continew.starter.core.exception.BusinessException;
import top.continew.starter.extension.crud.model.query.SortQuery;
import top.continew.starter.extension.crud.service.impl.BaseServiceImpl;

import java.util.List;

/**
 * 仓库移库明细业务实现
 *
 * @author luoqiz
 * @since 2024/11/15 17:17
 */
@Service
@RequiredArgsConstructor
public class WhseStockMoveDetialServiceImpl extends BaseServiceImpl<WhseStockMoveDetialMapper, WhseStockMoveDetialDO, WhseStockMoveDetialResp, WhseStockMoveDetialDetailResp, WhseStockMoveDetialQuery, WhseStockMoveDetialReq> implements WhseStockMoveDetialService {

    @Resource
    private GoodsStockService stockService;
    @Resource
    private WhseStockMoveService stockMoveService;

    @Override
    public Long add(WhseStockMoveDetialReq req) {
        // 获取移库单
        WhseStockMoveDetailResp stockMoveInfo = stockMoveService.get(req.getStockMoveId());

        // 获取当前仓库有相同物料的所有批次
        GoodsStockQuery stockQuery = new GoodsStockQuery();
        stockQuery.setWhseId(stockMoveInfo.getStockOutWhseId());
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
            req.setStockMoveNo(stockMoveInfo.getStockMoveNo());
            return super.add(req);
        }
    }

    @Override
    @Transactional
    public void delete(List<Long> ids) {
        LambdaQueryWrapper<WhseStockMoveDetialDO> query = new LambdaQueryWrapper<>();
        query.in(WhseStockMoveDetialDO::getId, ids);
        List<WhseStockMoveDetialDO> res = list(query);
        for (WhseStockMoveDetialDO re : res) {
            GoodsStockDO temp = new GoodsStockDO();
            temp.setId(re.getGoodsStockId());
            LambdaUpdateWrapper<GoodsStockDO> updateQuery = Wrappers.update(temp).setSql("`real_num` = `real_num` + " + re.getPlanNum()).lambda();
            updateQuery.eq(GoodsStockDO::getId, re.getGoodsStockId());
            stockService.updates(updateQuery);
        }
        super.delete(ids);
    }
}