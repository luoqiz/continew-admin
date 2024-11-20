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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.continew.admin.wms.mapper.WhseStockInDetailMapper;
import top.continew.admin.wms.model.entity.WhseStockInDetailDO;
import top.continew.admin.wms.model.query.WhseStockInDetailQuery;
import top.continew.admin.wms.model.req.WhseStockInDetailReq;
import top.continew.admin.wms.model.resp.WhseStockInDetailDetailResp;
import top.continew.admin.wms.model.resp.WhseStockInDetailResp;
import top.continew.admin.wms.service.WhseStockInDetailService;
import top.continew.starter.extension.crud.service.impl.BaseServiceImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 仓库入库明细业务实现
 *
 * @author luoqiz
 * @since 2024/11/11 11:53
 */
@Service
@RequiredArgsConstructor
public class WhseStockInDetailServiceImpl extends BaseServiceImpl<WhseStockInDetailMapper, WhseStockInDetailDO, WhseStockInDetailResp, WhseStockInDetailDetailResp, WhseStockInDetailQuery, WhseStockInDetailReq> implements WhseStockInDetailService {

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void batchAdd(List<WhseStockInDetailReq> reqs) {
        Collection<WhseStockInDetailDO> entityList = new ArrayList<>();
        for (WhseStockInDetailReq req : reqs) {
            WhseStockInDetailDO entity = new WhseStockInDetailDO();
            BeanUtil.copyProperties(req, entity);
            entityList.add(entity);
        }
        baseMapper.insertBatch(entityList);
    }
}