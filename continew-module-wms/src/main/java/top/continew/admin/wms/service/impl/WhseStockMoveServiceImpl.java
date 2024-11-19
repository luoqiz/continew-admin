package top.continew.admin.wms.service.impl;

import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import top.continew.admin.wms.mapper.WhseStockMoveMapper;
import top.continew.admin.wms.model.entity.WhseStockMoveDO;
import top.continew.admin.wms.model.query.WhseStockMoveDetialQuery;
import top.continew.admin.wms.model.query.WhseStockMoveQuery;
import top.continew.admin.wms.model.req.*;
import top.continew.admin.wms.model.resp.WhseStockMoveDetailResp;
import top.continew.admin.wms.model.resp.WhseStockMoveDetialResp;
import top.continew.admin.wms.model.resp.WhseStockMoveResp;
import top.continew.admin.wms.service.*;
import top.continew.starter.extension.crud.model.query.SortQuery;
import top.continew.starter.extension.crud.service.impl.BaseServiceImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * 仓库移库业务实现
 *
 * @author luoqiz
 * @since 2024/11/15 14:50
 */
@Service
@RequiredArgsConstructor
public class WhseStockMoveServiceImpl extends BaseServiceImpl<WhseStockMoveMapper, WhseStockMoveDO, WhseStockMoveResp, WhseStockMoveDetailResp, WhseStockMoveQuery, WhseStockMoveReq> implements WhseStockMoveService {

    @Resource
    private WhseStockMoveDetialService detailService;

    @Resource
    private WhseStockOutService stockOutService;
    @Resource
    private WhseStockOutDetailService stockOutDetailService;

    @Resource
    private WhseStockInService stockInService;

    @Override
    public WhseStockMoveDetailResp detail(Long id) {
        WhseStockMoveDetailResp resp = get(id);
        WhseStockMoveDetialQuery query = new WhseStockMoveDetialQuery();
        query.setStockMoveId(id);
        SortQuery sortQuery = new SortQuery();
        sortQuery.setSort(new String[]{"createTime", "asc"});
        List<WhseStockMoveDetialResp> deailList = detailService.list(query, sortQuery);
        resp.setGoodsList(deailList);
        return resp;
    }

    @Override
    public void updateStatus(Long id, int status) {
        WhseStockMoveDO entity = this.getById(id);
        if (entity == null) {
            throw new RuntimeException("数据不存在，请检查！");
        }
        entity.setStatus(status);
        // 审核完成则进入派发单，添加上出库单和入库单
        if (status == 2) {
            //创建出库单
            WhseStockOutReq stockOutReq = new WhseStockOutReq();
            stockOutReq.setName("MOVE-" + entity.getName());
            stockOutReq.setWhseId(entity.getStockOutWhseId());
            stockOutReq.setStockOutNo("MOVE-" + entity.getStockMoveNo());
            stockOutReq.setStatus(2);
            stockOutReq.setStockMoveId(entity.getId());
            List<WhseStockOutDetailReq> stockOutDetailReqList = new ArrayList<>();


            // 创建入库单
            WhseStockInReq stockInReq = new WhseStockInReq();
            stockInReq.setName("MOVE-" + entity.getName());
            stockInReq.setWhseId(entity.getStockInWhseId());
            stockInReq.setStockInNo("MOVE-" + entity.getStockMoveNo());
            stockInReq.setStatus(2);
            stockInReq.setStockMoveId(entity.getId());
            List<WhseStockInDetailReq> stockInDetailReqList = new ArrayList<>();

            WhseStockMoveDetialQuery detailQuery = new WhseStockMoveDetialQuery();
            detailQuery.setStockMoveId(id);
            List<WhseStockMoveDetialResp> detailList = detailService.list(detailQuery, new SortQuery());
            for (WhseStockMoveDetialResp whseStockMoveDetialResp : detailList) {
                WhseStockOutDetailReq stockOutDetailReq = new WhseStockOutDetailReq();
                stockOutDetailReq.setGoodsStockId(whseStockMoveDetialResp.getGoodsStockId());
                stockOutDetailReq.setGoodsSku(whseStockMoveDetialResp.getGoodsSku());
                stockOutDetailReq.setGoodsName(whseStockMoveDetialResp.getGoodsName());
                stockOutDetailReq.setProdTime(whseStockMoveDetialResp.getProdTime());
                stockOutDetailReq.setExpiryTime(whseStockMoveDetialResp.getExpiryTime());
                stockOutDetailReq.setPlanNum(whseStockMoveDetialResp.getPlanNum());
                stockOutDetailReq.setRealNum(whseStockMoveDetialResp.getPlanNum());
                stockOutDetailReq.setStatus(1);
                stockOutDetailReqList.add(stockOutDetailReq);

                WhseStockInDetailReq stockInDetailReq = new WhseStockInDetailReq();
                stockInDetailReq.setGoodsStockId(whseStockMoveDetialResp.getGoodsStockId());
                stockInDetailReq.setGoodsSku(whseStockMoveDetialResp.getGoodsSku());
                stockInDetailReq.setGoodsName(whseStockMoveDetialResp.getGoodsName());
                stockInDetailReq.setProdTime(whseStockMoveDetialResp.getProdTime());
                stockInDetailReq.setExpiryTime(whseStockMoveDetialResp.getExpiryTime());
                stockInDetailReq.setPlanNum(whseStockMoveDetialResp.getPlanNum());
                stockInDetailReq.setRealNum(whseStockMoveDetialResp.getPlanNum());
                stockInDetailReq.setStatus(1);
                stockInDetailReqList.add(stockInDetailReq);
            }

            Long stockOutId = stockOutService.add(stockOutReq, stockOutDetailReqList);
            Long stockInId = stockInService.add(stockInReq, stockInDetailReqList);
            entity.setStockInId(stockInId);
            entity.setStockOutId(stockOutId);
        }

        baseMapper.updateById(entity);
    }
}