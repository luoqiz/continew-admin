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
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.URLUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.fill.FillWrapper;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.continew.admin.wms.enums.WhseStockInStatusEnum;
import top.continew.admin.wms.mapper.WhseStockInMapper;
import top.continew.admin.wms.model.entity.GoodsSkuDO;
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
import top.continew.starter.core.exception.BusinessException;
import top.continew.starter.extension.crud.model.query.SortQuery;
import top.continew.starter.extension.crud.service.impl.BaseServiceImpl;
import top.continew.starter.file.excel.converter.ExcelBigNumberConverter;

import java.time.LocalDateTime;
import java.util.*;

/**
 * 仓库入库业务实现
 *
 * @author luoqiz
 * @since 2024/11/07 17:49
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class WhseStockInServiceImpl extends BaseServiceImpl<WhseStockInMapper, WhseStockInDO, WhseStockInResp, WhseStockInInfoResp, WhseStockInQuery, WhseStockInReq> implements WhseStockInService {

    @Resource
    private WhseStockInDetailService detailService;

    @Resource
    private GoodsStockService goodsStockService;

    @Resource
    private WhseAddrService whseAddrService;

    @Resource
    @Lazy
    private WhseStockMoveService moveService;

    @Resource
    private GoodsSkuService goodsSkuService;

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
            throw new BusinessException("数据不存在，请检查！");
        }

        // 如果是完成入库，则补充上入库时间
        if (status == 3) {
            entity.setInTime(LocalDateTime.now());
            AddrDetailResp whseInfo = whseAddrService.get(entity.getWhseId());
            if (whseInfo.getStatus() != 1) {
                throw new BusinessException("当前仓库状态不可用");
            }

            // 入库单详情
            WhseStockInDetailQuery query = new WhseStockInDetailQuery();
            query.setStockInId(id);
            List<WhseStockInDetailResp> stockInDetail = detailService.list(query, new SortQuery());

            // 如果是地区入库转为小件存储
            boolean isArea = false;
            List<GoodsSkuDO> skuInfoList = null;

            if (whseInfo.getWhseType() == 2) {
                isArea = true;
                List<String> skuList = stockInDetail.stream().map(WhseStockInDetailResp::getGoodsSku).toList();
                skuInfoList = goodsSkuService.getBySkuNoList(skuList);
            }

            List<GoodsStockDO> datas = new ArrayList<>();
            for (WhseStockInDetailResp whseStockInDetailResp : stockInDetail) {
                if (!whseStockInDetailResp.getStatus().equals(2)) {
                    throw new RuntimeException("有物料尚未核验，请重新检查！");
                }
                GoodsStockDO temp = new GoodsStockDO();
                temp.setStockInId(id);
                temp.setGoodsId(whseStockInDetailResp.getGoodsId());
                temp.setStockInNo(entity.getStockInNo());
                temp.setStockInDetailId(whseStockInDetailResp.getId());
                temp.setGoodsSku(whseStockInDetailResp.getGoodsSku());
                if (isArea) {
                    Optional<GoodsSkuDO> skuInfo = skuInfoList.stream()
                            .filter(domain -> domain.getBarcode().equals(whseStockInDetailResp.getGoodsSku()))
                            .findFirst();
                    if (skuInfo.isPresent()) {
                        GoodsSkuDO sku = skuInfo.get();
                        if (sku.getUnpacking()) {
                            temp.setInitNum(whseStockInDetailResp.getRealNum() * skuInfo.get().getPackAmount());
                            temp.setRealNum(whseStockInDetailResp.getRealNum() * skuInfo.get().getPackAmount());
                        } else {
                            temp.setInitNum(whseStockInDetailResp.getRealNum());
                            temp.setRealNum(whseStockInDetailResp.getRealNum());
                        }
                    }
                } else {
                    temp.setInitNum(whseStockInDetailResp.getRealNum());
                    temp.setRealNum(whseStockInDetailResp.getRealNum());
                }
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

    @Override
    public void export(Long id, HttpServletResponse response) {
        WhseStockInInfoResp info = detailById(id);
        String fileName = info.getName() + ".xlsx";
        String exportFileName = URLUtil.encode("%s_%s.xlsx".formatted(fileName, DateUtil
                .format(new Date(), "yyyyMMddHHmmss")));
        response.setHeader("Content-disposition", "attachment;filename=" + exportFileName);
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        ClassPathResource resource = new ClassPathResource("static/stock_in.xlsx");
        try {
            ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream())
                    .withTemplate(resource.getInputStream())
                    .autoCloseStream(false)
                    .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
                    .registerConverter(new ExcelBigNumberConverter())
                    .build();
            WriteSheet writeSheet = EasyExcel.writerSheet().build();
            // 如果有多个list 模板上必须有{前缀.} 这里的前缀就是 data1，然后多个list必须用 FillWrapper包裹
            excelWriter.fill(new FillWrapper("goodsList", info.goodsList), writeSheet);
            excelWriter.fill(info, writeSheet);
            excelWriter.finish();
            excelWriter.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<WhseStockInDO> getByIds(Collection<Long> ids) {
        return listByIds(ids);
    }
}