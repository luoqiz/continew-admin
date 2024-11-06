package top.continew.admin.wms.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.continew.admin.wms.model.query.AddrQuery;
import top.continew.admin.wms.model.req.AddrReq;
import top.continew.admin.wms.model.resp.AddrDetailResp;
import top.continew.admin.wms.model.resp.AddrResp;
import top.continew.admin.wms.service.AddrService;
import top.continew.starter.extension.crud.annotation.CrudRequestMapping;
import top.continew.starter.extension.crud.controller.BaseController;
import top.continew.starter.extension.crud.enums.Api;
import top.continew.starter.extension.crud.model.query.PageQuery;
import top.continew.starter.extension.crud.model.resp.BaseIdResp;
import top.continew.starter.extension.crud.model.resp.PageResp;
import top.continew.starter.extension.crud.util.ValidateGroup;

import java.io.Serializable;

/**
 * 仓库地址管理 API
 *
 * @author luoqiz
 * @since 2024/11/06 18:05
 */
@Tag(name = "仓库地址管理 API")
@RestController
@CrudRequestMapping(value = "/wms/addr", api = {Api.GET, Api.UPDATE, Api.DELETE, Api.EXPORT})
public class AddrController extends BaseController<AddrService, AddrResp, AddrDetailResp, AddrQuery, AddrReq> {

    @Operation(
            summary = "新增门仓",
            description = "新增数据"
    )
    @ResponseBody
    @PostMapping("/store")
    public BaseIdResp<Serializable> addStore(
            @Validated({ValidateGroup.Crud.Add.class})
            @RequestBody AddrReq req) {
        this.checkPermission(Api.ADD);
        req.setWhseType(3);
        return BaseIdResp.builder().id(this.baseService.add(req)).build();
    }

    @Operation(
            summary = "新增地仓",
            description = "新增数据"
    )
    @ResponseBody
    @PostMapping("/region")
    public BaseIdResp<Serializable> addRegion(
            @Validated({ValidateGroup.Crud.Add.class})
            @RequestBody
            AddrReq req) {
        this.checkPermission(Api.ADD);
        req.setWhseType(2);
        return BaseIdResp.builder().id(this.baseService.add(req)).build();
    }

    @Operation(
            summary = "新增国仓",
            description = "新增数据"
    )
    @ResponseBody
    @PostMapping("/country")
    public BaseIdResp<Serializable> addCon(
            @Validated({ValidateGroup.Crud.Add.class})
            @RequestBody
            AddrReq req) {
        this.checkPermission(Api.ADD);
        req.setWhseType(1);
        return BaseIdResp.builder().id(this.baseService.add(req)).build();
    }

    @Operation(
            summary = "分页店仓查询列表",
            description = "分页查询列表"
    )
    @ResponseBody
    @GetMapping("/store")
    public PageResp<AddrResp> pageStore(AddrQuery query, @Validated PageQuery pageQuery) {
        this.checkPermission(Api.LIST);
        query.setWhseType(3);
        return this.baseService.page(query, pageQuery);
    }

    @Operation(
            summary = "分页地仓查询列表",
            description = "分页查询列表"
    )
    @ResponseBody
    @GetMapping("/region")
    public PageResp<AddrResp> pageRegion(AddrQuery query, @Validated PageQuery pageQuery) {
        this.checkPermission(Api.LIST);
        query.setWhseType(2);
        return this.baseService.page(query, pageQuery);
    }

    @Operation(
            summary = "分页国仓查询列表",
            description = "分页查询列表"
    )
    @ResponseBody
    @GetMapping("/country")
    public PageResp<AddrResp> pageCountry(AddrQuery query, @Validated PageQuery pageQuery) {
        this.checkPermission(Api.LIST);
        query.setWhseType(1);
        return this.baseService.page(query, pageQuery);
    }
}