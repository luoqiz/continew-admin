package top.continew.admin.wms.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import top.continew.admin.wms.mapper.GoodsInventoryCountItemMapper;
import top.continew.admin.wms.model.entity.GoodsInventoryCountItemDO;
import top.continew.admin.wms.model.query.GoodsInventoryCountItemQuery;
import top.continew.admin.wms.model.req.GoodsInventoryCountItemReq;
import top.continew.admin.wms.model.resp.GoodsInventoryCountItemDetailResp;
import top.continew.admin.wms.model.resp.GoodsInventoryCountItemResp;
import top.continew.admin.wms.service.GoodsInventoryCountItemService;
import top.continew.starter.extension.crud.service.impl.BaseServiceImpl;

import java.util.Collection;

/**
 * 物料盘点详情业务实现
 *
 * @author luoqiz
 * @since 2024/11/26 13:44
 */
@Service
@RequiredArgsConstructor
public class GoodsInventoryCountItemServiceImpl extends BaseServiceImpl<GoodsInventoryCountItemMapper, GoodsInventoryCountItemDO, GoodsInventoryCountItemResp, GoodsInventoryCountItemDetailResp, GoodsInventoryCountItemQuery, GoodsInventoryCountItemReq> implements GoodsInventoryCountItemService {

    @Override
    public boolean saveBatch(Collection<GoodsInventoryCountItemDO> entityList) {
        return super.saveBatch(entityList);
    }
}