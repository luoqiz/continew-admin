/*
 * Copyright (c) 2022-present Charles7c Authors. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package top.continew.admin.wms.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.continew.admin.wms.enums.WhseStockInStatusEnum;
import top.continew.admin.wms.mapper.WhseStockInMapper;
import top.continew.admin.wms.model.entity.GoodsStockDO;
import top.continew.admin.wms.model.entity.WhseStockInDO;
import top.continew.admin.wms.model.entity.WhseStockInDetailDO;
import top.continew.admin.wms.model.query.WhseStockInDetailQuery;
import top.continew.admin.wms.model.query.WhseStockInQuery;
import top.continew.admin.wms.model.req.WhseStockInDetailReq;
import top.continew.admin.wms.model.req.WhseStockInReq;
import top.continew.admin.wms.model.resp.AddrDetailResp;
import top.continew.admin.wms.model.resp.WhseStockInDetailResp;
import top.continew.admin.wms.model.resp.WhseStockInInfoResp;
import top.continew.admin.wms.model.resp.WhseStockInResp;
import top.continew.admin.wms.service.*;
import top.continew.starter.extension.crud.model.query.SortQuery;
import top.continew.starter.extension.crud.service.impl.BaseServiceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    @Resource
    private GoodsStockService goodsStockService;

    @Resource
    private AddrService addrService;

    @Resource
    @Lazy
    private WhseStockMoveService moveService;

    @Override
    public Long add(WhseStockInReq req) {
        this.beforeAdd(req);
        WhseStockInDO entity = BeanUtil.copyProperties(req, super.getEntityClass());
        if (entity.getStatus() == null) {
            entity.setStatus(WhseStockInStatusEnum.AWAIT_APPROVAL.getValue());
        }
        baseMapper.insert(entity);
        this.afterAdd(req, entity);
        return entity.getId();
    }

    @Override
    public WhseStockInInfoResp detailById(Long id) {
        WhseStockInInfoResp stockInInfo = get(id);
        if (stockInInfo == null) {
            throw new RuntimeException("数据不存在");
        }
        LambdaQueryWrapper<WhseStockInDetailDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(WhseStockInDetailDO::getStockInId, id);
        SortQuery sortquery = new SortQuery();
        WhseStockInDetailQuery query = new WhseStockInDetailQuery();
        query.setStockInId(id);
        sortquery.setSort(null);
        List<WhseStockInDetailResp> list = detailService.list(query, sortquery);
        stockInInfo.setGoodsList(list);
        return stockInInfo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateStatus(Long id, int status) {
        WhseStockInDO entity = this.getById(id);
        if (entity == null) {
            throw new RuntimeException("数据不存在，请检查！");
        }
        // 如果是完成入库，则补充上入库时间
        if (status == 3) {
            entity.setInTime(LocalDateTime.now());
            WhseStockInDetailQuery query = new WhseStockInDetailQuery();
            query.setStockInId(id);
            List<WhseStockInDetailResp> stockInDetail = detailService.list(query, new SortQuery());
            List<GoodsStockDO> datas = new ArrayList<>();
            AddrDetailResp whseInfo = addrService.get(entity.getWhseId());
            for (WhseStockInDetailResp whseStockInDetailResp : stockInDetail) {
                if (!whseStockInDetailResp.getStatus().equals(2)) {
                    throw new RuntimeException("有物料尚未核验，请重新检查！");
                }
                GoodsStockDO temp = new GoodsStockDO();
                temp.setStockInId(id);
                temp.setStockInNo(entity.getStockInNo());
                temp.setStockInDetailId(whseStockInDetailResp.getId());
                temp.setGoodsSku(whseStockInDetailResp.getGoodsSku());
                temp.setInitNum(whseStockInDetailResp.getRealNum());
                temp.setRealNum(whseStockInDetailResp.getRealNum());
                temp.setWhseId(entity.getWhseId());
                temp.setWhseType(whseInfo.getWhseType());
                temp.setStatus(1);
                temp.setProdTime(whseStockInDetailResp.getProdTime());
                temp.setExpiryTime(whseStockInDetailResp.getExpiryTime());
                temp.setInfo("");
                datas.add(temp);
            }
            goodsStockService.batchAdd(datas);
            // 更新移库单完成
            if (entity.getStockMoveId() != null) {
                moveService.updateStatus(entity.getStockMoveId(), 3);
            }
        }
        entity.setStatus(status);

        baseMapper.updateById(entity);
    }

    @Transactional
    @Override
    public Long add(WhseStockInReq stockInReq, List<WhseStockInDetailReq> stockInDetailReqList) {
        Long id = add(stockInReq);
        for (WhseStockInDetailReq whseStockInDetailReq : stockInDetailReqList) {
            whseStockInDetailReq.setStockInId(id);
        }
        detailService.batchAdd(stockInDetailReqList);
        return id;
    }
}