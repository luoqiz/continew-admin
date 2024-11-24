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

package top.continew.admin.wms.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import top.continew.admin.wms.model.query.GoodsStockQuery;
import top.continew.admin.wms.model.req.GoodsStockReq;
import top.continew.admin.wms.model.resp.GoodsStockDetailResp;
import top.continew.admin.wms.model.resp.GoodsStockResp;
import top.continew.admin.wms.service.GoodsStockService;
import top.continew.starter.extension.crud.annotation.CrudRequestMapping;
import top.continew.starter.extension.crud.controller.BaseController;
import top.continew.starter.extension.crud.enums.Api;

import java.util.List;
import java.util.Map;

/**
 * 商品库存管理 API
 *
 * @author luoqiz
 * @since 2024/11/13 17:57
 */
@Tag(name = "商品库存管理 API")
@RestController
@CrudRequestMapping(value = "/wms/goodsStock", api = {Api.PAGE, Api.DETAIL, Api.ADD, Api.UPDATE, Api.DELETE,
    Api.EXPORT})
public class GoodsStockController extends BaseController<GoodsStockService, GoodsStockResp, GoodsStockDetailResp, GoodsStockQuery, GoodsStockReq> {

    @Operation(summary = "获取指定库的今日入库信息", description = "获取指定库的今日入库信息")
    @Parameter(name = "whseId", description = "仓库id", example = "1", in = ParameterIn.PATH)
    @ResponseBody
    @GetMapping("/statics/{whseId}")
    public List<Map<String, Integer>> statistics(@PathVariable Long whseId) {
        return baseService.staticsToday(whseId);
    }

    @Operation(summary = "获取指定库的库存信息", description = "获取指定库的库存信息")
    @Parameter(name = "whseId", description = "仓库id", example = "1", in = ParameterIn.PATH)
    @ResponseBody
    @GetMapping("/statics/stock/{whseId}")
    public List<Map<String, Integer>> statisticsStock(@PathVariable Long whseId) {
        return baseService.statisticsStock(whseId);
    }

    @Operation(summary = "获取指定库的临期库存", description = "获取指定库的临期库存")
    @Parameter(name = "whseId", description = "仓库id", example = "1", in = ParameterIn.PATH)
    @ResponseBody
    @GetMapping("/statics/expired/{whseId}")
    public List<GoodsStockResp> expiredStock(@PathVariable Long whseId, int day) {
        return baseService.expiredStock(whseId, day);
    }
}