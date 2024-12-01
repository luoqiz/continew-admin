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
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.continew.admin.wms.mapper.WhseStockOutMapper;
import top.continew.admin.wms.model.entity.WhseStockOutDO;
import top.continew.admin.wms.model.entity.WhseStockOutDetailDO;
import top.continew.admin.wms.model.query.WhseStockOutDetailQuery;
import top.continew.admin.wms.model.query.WhseStockOutQuery;
import top.continew.admin.wms.model.req.WhseStockOutDetailReq;
import top.continew.admin.wms.model.req.WhseStockOutReq;
import top.continew.admin.wms.model.resp.WhseStockOutDetailMainResp;
import top.continew.admin.wms.model.resp.WhseStockOutInfoResp;
import top.continew.admin.wms.model.resp.WhseStockOutResp;
import top.continew.admin.wms.service.WhseStockMoveService;
import top.continew.admin.wms.service.WhseStockOutDetailService;
import top.continew.admin.wms.service.WhseStockOutService;
import top.continew.starter.core.exception.BusinessException;
import top.continew.starter.extension.crud.model.query.SortQuery;
import top.continew.starter.extension.crud.service.impl.BaseServiceImpl;
import top.continew.starter.file.excel.converter.ExcelBigNumberConverter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 仓库出库业务实现
 *
 * @author luoqiz
 * @since 2024/11/14 15:15
 */
@Service
@RequiredArgsConstructor
public class WhseStockOutServiceImpl extends BaseServiceImpl<WhseStockOutMapper, WhseStockOutDO, WhseStockOutResp, WhseStockOutInfoResp, WhseStockOutQuery, WhseStockOutReq> implements WhseStockOutService {

    @Resource
    private WhseStockOutDetailService detailService;

    @Resource
    @Lazy
    private WhseStockMoveService stockMoveService;

    @Override
    public WhseStockOutInfoResp detailById(Long id) {
        WhseStockOutInfoResp info = get(id);
        if (info == null) {
            throw new RuntimeException("数据不存在");
        }
        WhseStockOutDetailQuery query = new WhseStockOutDetailQuery();
        query.setStockOutId(id);
        List<WhseStockOutDetailMainResp> list = detailService.list(query, new SortQuery());
        for (WhseStockOutDetailMainResp whseStockOutDetailMainResp : list) {
            if (whseStockOutDetailMainResp.getGoodsUnpacking()) {
                if (info.getWhseType() != 1) {
                    whseStockOutDetailMainResp.setGoodsUnit(whseStockOutDetailMainResp.getGoodsPackUnit());
                }
            }
        }
        info.setGoodsList(list);
        return info;
    }

    @Override
    public void update(WhseStockOutReq req, Long id) {
        WhseStockOutDetailQuery query = new WhseStockOutDetailQuery();
        query.setStockOutId(id);
        List<WhseStockOutDetailMainResp> list = detailService.list(query, new SortQuery());
        if (list.size() > 0) {
            throw new BusinessException("已经录入商品无法修改！");
        }
        super.update(req, id);
    }

    @Override
    public void updateStatus(Long id, int status) {
        WhseStockOutDO entity = this.getById(id);
        if (entity == null) {
            throw new RuntimeException("数据不存在，请检查！");
        }
        WhseStockOutDetailQuery query = new WhseStockOutDetailQuery();
        query.setStockOutId(id);
        List<WhseStockOutDetailMainResp> detailList = detailService.list(query, new SortQuery());

        if (status == 2 && detailList.isEmpty()) {
            throw new BusinessException("尚未添加物料，无法审核！");
        }

        if (status == 3) {
            entity.setOutTime(LocalDateTime.now());

            for (WhseStockOutDetailMainResp whseStockOutDetailMainResp : detailList) {
                if (whseStockOutDetailMainResp.getStatus() != 2) {
                    throw new RuntimeException("有物料尚未核验，请重新检查！");
                }
            }

            if (entity.getStockMoveId() != null) {
                stockMoveService.updateStatus(entity.getStockMoveId(), 3);
            }
        }
        entity.setStatus(status);
        baseMapper.updateById(entity);
    }

    @Transactional
    @Override
    public Long add(WhseStockOutReq stockOutReq, List<WhseStockOutDetailReq> stockOutDetailReqList) {
        Long id = add(stockOutReq);
        List<WhseStockOutDetailDO> list = new ArrayList<>();
        for (WhseStockOutDetailReq whseStockOutDetailReq : stockOutDetailReqList) {
            WhseStockOutDetailDO detailDO = new WhseStockOutDetailDO();
            BeanUtil.copyProperties(whseStockOutDetailReq, detailDO);
            detailDO.setStockOutId(id);
            list.add(detailDO);
        }
        detailService.batchAdd(list);
        return id;
    }

    @Override
    public List<Map<String, Integer>> staticsToday(Long whseId) {
        return baseMapper.staticsToday(whseId);
    }

    @Override
    public void export(Long id, HttpServletResponse response, String lang) {
        if (lang == null) {
            lang = "zh";
        }
        WhseStockOutInfoResp info = detailById(id);
        String fileName = info.getName() + ".xlsx";
        String exportFileName = URLUtil.encode("%s_%s.xlsx".formatted(fileName, DateUtil
            .format(new Date(), "yyyyMMddHHmmss")));
        response.setHeader("Content-disposition", "attachment;filename=" + exportFileName);
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        // 模板文件保存在springboot项目的resources/static下
        ClassPathResource resource = new ClassPathResource("static/stock_out_" + lang + ".xlsx");
        // 方案1
        try {
            ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream())
                .withTemplate(resource.getInputStream())
                .autoCloseStream(false)
                //                .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
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
}