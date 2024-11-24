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

package top.continew.admin.controller.system;

import cn.dev33.satoken.annotation.SaIgnore;
import cn.hutool.json.JSON;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import top.continew.admin.system.model.query.LanguageQuery;
import top.continew.admin.system.model.req.LanguageReq;
import top.continew.admin.system.model.resp.LanguageDetailResp;
import top.continew.admin.system.model.resp.LanguageResp;
import top.continew.admin.system.service.DictItemService;
import top.continew.admin.system.service.LanguageService;
import top.continew.starter.extension.crud.annotation.CrudRequestMapping;
import top.continew.starter.extension.crud.controller.BaseController;
import top.continew.starter.extension.crud.enums.Api;
import top.continew.starter.extension.crud.model.resp.LabelValueResp;

import java.util.List;

/**
 * 语言管理 API
 *
 * @author luoqiz
 * @since 2024/10/21 18:15
 */
@Tag(name = "语言管理 API")
@RestController
@CrudRequestMapping(value = "/system/language", api = {Api.PAGE, Api.DETAIL, Api.ADD, Api.UPDATE, Api.DELETE, Api.EXPORT})
public class LanguageController extends BaseController<LanguageService, LanguageResp, LanguageDetailResp, LanguageQuery, LanguageReq> {

    @Resource
    private DictItemService dictItemService;

    private String code = "language_type";

    @SaIgnore
    @Operation(summary = "查询语言", description = "查询字典列表")
    @GetMapping("/dict")
    public List<LabelValueResp> listDict() {
        return dictItemService.listByDictCode(code);
    }

    @SaIgnore
    @Operation(summary = "查询语言", description = "查询字典列表")
    @GetMapping("/type/{type}")
    public JSON getLanguageByType(@PathVariable String type) {
        return baseService.getLanguageByType(type);
    }
}