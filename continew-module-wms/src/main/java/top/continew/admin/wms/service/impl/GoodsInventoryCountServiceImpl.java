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

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.URLUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.fill.FillWrapper;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.continew.admin.wms.mapper.GoodsInventoryCountMapper;
import top.continew.admin.wms.model.entity.GoodsInventoryCountDO;
import top.continew.admin.wms.model.entity.GoodsInventoryCountItemDO;
import top.continew.admin.wms.model.entity.GoodsStockDO;
import top.continew.admin.wms.model.query.GoodsInventoryCountItemQuery;
import top.continew.admin.wms.model.query.GoodsInventoryCountQuery;
import top.continew.admin.wms.model.query.GoodsStockQuery;
import top.continew.admin.wms.model.req.AddrReq;
import top.continew.admin.wms.model.req.GoodsInventoryCountReq;
import top.continew.admin.wms.model.resp.*;
import top.continew.admin.wms.service.GoodsInventoryCountService;
import top.continew.admin.wms.service.WhseAddrService;
import top.continew.starter.extension.crud.model.query.SortQuery;
import top.continew.starter.extension.crud.service.impl.BaseServiceImpl;
import top.continew.starter.file.excel.converter.ExcelBigNumberConverter;

import java.util.*;
import java.util.stream.Collectors;

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

            // 将盘点详情表的状态全部更新为已完成
            List<Long> itemIdList = items.stream().map(GoodsInventoryCountItemDO::getId).collect(Collectors.toList());
            LambdaUpdateWrapper<GoodsInventoryCountItemDO> updateWrapper = new LambdaUpdateWrapper<>();
            updateWrapper.in(GoodsInventoryCountItemDO::getId, itemIdList);
            updateWrapper.set(GoodsInventoryCountItemDO::getStatus, 3);
            itemService.update(updateWrapper);
        }
        GoodsInventoryCountDO domain = new GoodsInventoryCountDO();
        domain.setId(id);
        domain.setStatus(status);
        updateById(domain);
        return true;
    }

    @Override
    public void export(Long id, HttpServletResponse response) {
        GoodsInventoryCountDetailResp info = get(id);
        // 将盘点后的数据更新回库存表中
        
        GoodsInventoryCountItemQuery query = new GoodsInventoryCountItemQuery();
        query.setInventoryCountId(info.getId());
        List<GoodsInventoryCountItemResp> items = itemService.list(query, new SortQuery());

        String fileName = info.getName() + ".xlsx";
        String exportFileName = URLUtil.encode("%s_%s.xlsx".formatted(fileName, DateUtil
                .format(new Date(), "yyyyMMddHHmmss")));
        response.setHeader("Content-disposition", "attachment;filename=" + exportFileName);
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        ClassPathResource resource = new ClassPathResource("static/inventory_count.xlsx");
        try {
            ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream())
                    .withTemplate(resource.getInputStream())
                    .autoCloseStream(false)
                    .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
                    .registerConverter(new ExcelBigNumberConverter())
                    .build();
            WriteSheet writeSheet = EasyExcel.writerSheet().build();
            // 如果有多个list 模板上必须有{前缀.} 这里的前缀就是 data1，然后多个list必须用 FillWrapper包裹
            excelWriter.fill(new FillWrapper("goodsList", items), writeSheet);
            excelWriter.fill(info, writeSheet);
            excelWriter.finish();
            excelWriter.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}