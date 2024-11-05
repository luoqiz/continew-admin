package top.continew.admin.controller.system;

import cn.dev33.satoken.annotation.SaIgnore;
import cn.hutool.json.JSON;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import jakarta.annotation.Resource;
import top.continew.admin.system.service.DictItemService;
import top.continew.starter.extension.crud.enums.Api;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.*;

import top.continew.starter.extension.crud.annotation.CrudRequestMapping;
import top.continew.starter.extension.crud.controller.BaseController;
import top.continew.admin.system.model.query.LanguageQuery;
import top.continew.admin.system.model.req.LanguageReq;
import top.continew.admin.system.model.resp.LanguageDetailResp;
import top.continew.admin.system.model.resp.LanguageResp;
import top.continew.admin.system.service.LanguageService;
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
@CrudRequestMapping(value = "/system/language", api = {Api.PAGE, Api.GET, Api.ADD, Api.UPDATE, Api.DELETE, Api.EXPORT})
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