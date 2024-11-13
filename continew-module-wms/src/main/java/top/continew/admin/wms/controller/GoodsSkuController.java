package top.continew.admin.wms.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import top.continew.admin.wms.model.query.GoodsSkuQuery;
import top.continew.admin.wms.model.req.GoodsSkuReq;
import top.continew.admin.wms.model.resp.GoodsSkuDetailResp;
import top.continew.admin.wms.model.resp.GoodsSkuResp;
import top.continew.admin.wms.service.GoodsSkuService;
import top.continew.starter.extension.crud.annotation.CrudRequestMapping;
import top.continew.starter.extension.crud.controller.BaseController;
import top.continew.starter.extension.crud.enums.Api;

/**
 * 商品规格(sku)管理 API
 *
 * @author luoqiz
 * @since 2024/11/07 11:10
 */
@Tag(name = "商品规格(sku)管理 API")
@RestController
@CrudRequestMapping(value = "/wms/goodsSku", api = {Api.PAGE, Api.GET, Api.ADD, Api.UPDATE, Api.DELETE, Api.EXPORT})
public class GoodsSkuController extends BaseController<GoodsSkuService, GoodsSkuResp, GoodsSkuDetailResp, GoodsSkuQuery, GoodsSkuReq> {

    /**
     * 查询详情
     *
     * @param skuNo skuNo
     * @return 详情信息
     */
    @Operation(summary = "查询详情", description = "查询详情")
    @Parameter(name = "skuNo", description = "skuNo", example = "1", in = ParameterIn.PATH)
    @ResponseBody
    @GetMapping("/sku/{skuNo}")
    public GoodsSkuDetailResp get(@PathVariable("skuNo") String skuNo) {
        this.checkPermission(Api.LIST);
        return baseService.getBySkuNo(skuNo);
    }
}