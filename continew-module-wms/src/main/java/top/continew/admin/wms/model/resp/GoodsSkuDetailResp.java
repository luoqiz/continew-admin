package top.continew.admin.wms.model.resp;

import java.io.Serial;
import java.time.*;
import java.math.BigDecimal;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;

import top.continew.starter.extension.crud.model.resp.BaseDetailResp;

/**
 * 商品规格(sku)详情信息
 *
 * @author luoqiz
 * @since 2024/11/07 11:10
 */
@Data
@ExcelIgnoreUnannotated
@Schema(description = "商品规格(sku)详情信息")
public class GoodsSkuDetailResp extends BaseDetailResp {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 条形码
     */
    @Schema(description = "条形码")
    @ExcelProperty(value = "条形码")
    private String barcode;

    /**
     * 商品编号
     */
    @Schema(description = "商品编号")
    @ExcelProperty(value = "商品编号")
    private Long spuId;

    /**
     * 规格名称
     */
    @Schema(description = "规格名称")
    @ExcelProperty(value = "规格名称")
    private String name;

    /**
     * 单位
     */
    @Schema(description = "单位")
    @ExcelProperty(value = "单位")
    private String unit;

    /**
     * 数量
     */
    @Schema(description = "数量")
    @ExcelProperty(value = "数量")
    private Integer amount;

    /**
     * 拆箱
     */
    @Schema(description = "拆箱")
    @ExcelProperty(value = "拆箱")
    private String unpacking;

    /**
     * 拆箱单位
     */
    @Schema(description = "拆箱单位")
    @ExcelProperty(value = "拆箱单位")
    private String packUnit;

    /**
     * 拆箱数量
     */
    @Schema(description = "拆箱数量")
    @ExcelProperty(value = "拆箱数量")
    private Integer packAmount;

    /**
     * 卖点
     */
    @Schema(description = "卖点")
    @ExcelProperty(value = "卖点")
    private String sellPoint;

    /**
     * 规格列表
     */
    @Schema(description = "规格列表")
    @ExcelProperty(value = "规格列表")
    private String specs;

    /**
     * 售价
     */
    @Schema(description = "售价")
    @ExcelProperty(value = "售价")
    private BigDecimal price;

    /**
     * 首图
     */
    @Schema(description = "首图")
    @ExcelProperty(value = "首图")
    private String img;

    /**
     * 图片列表
     */
    @Schema(description = "图片列表")
    @ExcelProperty(value = "图片列表")
    private String pics;

    /**
     * 状态 1上架  2下架
     */
    @Schema(description = "状态 1上架  2下架")
    @ExcelProperty(value = "状态 1上架  2下架")
    private Integer status;

    /**
     * 备注信息
     */
    @Schema(description = "备注信息")
    @ExcelProperty(value = "备注信息")
    private String memo;
}