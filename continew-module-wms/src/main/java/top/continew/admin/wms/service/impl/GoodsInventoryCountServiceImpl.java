package top.continew.admin.wms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.continew.admin.wms.mapper.GoodsInventoryCountMapper;
import top.continew.admin.wms.model.entity.GoodsInventoryCountDO;
import top.continew.admin.wms.model.entity.GoodsInventoryCountItemDO;
import top.continew.admin.wms.model.entity.GoodsStockDO;
import top.continew.admin.wms.model.query.GoodsInventoryCountQuery;
import top.continew.admin.wms.model.query.GoodsStockQuery;
import top.continew.admin.wms.model.req.AddrReq;
import top.continew.admin.wms.model.req.GoodsInventoryCountReq;
import top.continew.admin.wms.model.resp.GoodsInventoryCountDetailResp;
import top.continew.admin.wms.model.resp.GoodsInventoryCountResp;
import top.continew.admin.wms.model.resp.GoodsStockResp;
import top.continew.admin.wms.service.GoodsInventoryCountService;
import top.continew.admin.wms.service.WhseAddrService;
import top.continew.starter.extension.crud.model.query.SortQuery;
import top.continew.starter.extension.crud.service.impl.BaseServiceImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * 物料盘点业务实现
 *
 * @author luoqiz
 * @since 2024/11/26 13:51
 */
@Service
@RequiredArgsConstructor
public class GoodsInventoryCountServiceImpl extends BaseServiceImpl<GoodsInventoryCountMapper, GoodsInventoryCountDO, GoodsInventoryCountResp, GoodsInventoryCountDetailResp, GoodsInventoryCountQuery, GoodsInventoryCountReq> implements GoodsInventoryCountService {

    @Resource
    private WhseAddrService whseAddrService;

    @Resource
    private GoodsInventoryCountItemServiceImpl itemService;

    @Resource
    private GoodsStockServiceImpl goodsStockService;

    @Transactional
    @Override
    public synchronized boolean updateStatus(Long id, int status) {
        GoodsInventoryCountDO info = getById(id);
        AddrReq addrReq = new AddrReq();
        // 如果是2，则开始盘点
        if (status == 2) {
            // 更新库状态
            addrReq.setStatus(3);
            whseAddrService.update(addrReq, info.getWhseId());

            // 将库存保存到盘点细节中
            GoodsStockQuery goodsStockQuery = new GoodsStockQuery();
            goodsStockQuery.setRealNum(0);
            goodsStockQuery.setWhseId(info.getWhseId());
            List<GoodsStockResp> stockList = goodsStockService.list(goodsStockQuery, new SortQuery());

            Collection<GoodsInventoryCountItemDO> entityList = new ArrayList<>();
            for (GoodsStockResp goodsStockResp : stockList) {
                GoodsInventoryCountItemDO domain = new GoodsInventoryCountItemDO();
                domain.setInventoryCountId(info.getId());
                domain.setStockId(goodsStockResp.getId());
                domain.setGoodsId(goodsStockResp.getGoodsId());
                domain.setGoodsSku(goodsStockResp.getGoodsSku());
                domain.setInitNum(goodsStockResp.getRealNum());
                domain.setStatus(1);
                domain.setProdTime(goodsStockResp.getProdTime());
                domain.setExpiryTime(goodsStockResp.getExpiryTime());
                entityList.add(domain);
            }
            itemService.saveBatch(entityList);
        }
        if (status == 3) {
            addrReq.setStatus(1);
            whseAddrService.update(addrReq, info.getWhseId());
            // 将盘点后的数据更新回库存表中
            LambdaQueryWrapper<GoodsInventoryCountItemDO> query = new LambdaQueryWrapper<>();
            query.eq(GoodsInventoryCountItemDO::getInventoryCountId, info.getId());
            List<GoodsInventoryCountItemDO> items = itemService.list(query);

            Collection<GoodsStockDO> entityList = new ArrayList<>();
            for (GoodsInventoryCountItemDO item : items) {
                if (!Objects.equals(item.getInitNum(), item.getRealNum())) {
                    GoodsStockDO goodsStockDO = new GoodsStockDO();
                    goodsStockDO.setId(item.getStockId());
                    goodsStockDO.setRealNum(item.getRealNum());
                    entityList.add(goodsStockDO);
                }
            }
            goodsStockService.updateBatchById(entityList);
        }
        GoodsInventoryCountDO domain = new GoodsInventoryCountDO();
        domain.setId(id);
        domain.setStatus(status);
        updateById(domain);
        return true;
    }
}