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
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
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
import top.continew.starter.core.exception.BusinessException;
import top.continew.starter.extension.crud.model.query.SortQuery;
import top.continew.starter.extension.crud.service.impl.BaseServiceImpl;
import top.continew.starter.file.excel.converter.ExcelBigNumberConverter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
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
        sortQuery.setSort(new String[] {"createTime", "asc"});
        List<WhseStockMoveDetialResp> deailList = detailService.list(query, sortQuery);
        for (WhseStockMoveDetialResp whseStockMoveDetialResp : deailList) {
            if (whseStockMoveDetialResp.getGoodsUnpacking()) {
                if (resp.getStockOutWhseType() != 1) {
                    whseStockMoveDetialResp.setGoodsUnit(whseStockMoveDetialResp.getGoodsPackUnit());
                }
            }
        }
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
            WhseStockMoveDetialQuery detailQuery = new WhseStockMoveDetialQuery();
            detailQuery.setStockMoveId(id);
            List<WhseStockMoveDetialResp> detailList = detailService.list(detailQuery, new SortQuery());
            if (detailList.isEmpty()) {
                throw new BusinessException("尚未添加物料，无法审核！");
            }
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
        if (status == 4) {
            entity.setMoveTime(LocalDateTime.now());
        }
        baseMapper.updateById(entity);
    }

    @Override
    public void export(Long id, HttpServletResponse response, String lang) {
        if (lang == null) {
            lang = "zh";
        }
        WhseStockMoveDetailResp info = detail(id);
        if (lang.equals("zh")) {
            if (info.getStatus().equals(1)) {
                info.setStatusString("审核中");
            }
            if (info.getStatus().equals(2)) {
                info.setStatusString("出库中");
            }
            if (info.getStatus().equals(3)) {
                info.setStatusString("入库中");
            }
            if (info.getStatus().equals(4)) {
                info.setStatusString("已完成");
            }
        }
        String fileName = info.getName() + ".xlsx";
        String exportFileName = URLUtil.encode("%s_%s.xlsx".formatted(fileName, DateUtil
            .format(new Date(), "yyyyMMddHHmmss")));
        response.setHeader("Content-disposition", "attachment;filename=" + exportFileName);
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        ClassPathResource resource = new ClassPathResource("static/stock_move" + "_" + lang + ".xlsx");
        try {
            ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream())
                .withTemplate(resource.getInputStream())
                .autoCloseStream(false)
                //                .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
                .registerConverter(new ExcelBigNumberConverter())
                .build();
            WriteSheet writeSheet = EasyExcel.writerSheet().build();
            // 如果有多个list 模板上必须有{前缀.} 这里的前缀就是 data1，然后多个list必须用 FillWrapper包裹
            excelWriter.fill(new FillWrapper("goodsList", info.getGoodsList()), writeSheet);
            excelWriter.fill(info, writeSheet);
            excelWriter.finish();
            excelWriter.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}