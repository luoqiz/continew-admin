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

}