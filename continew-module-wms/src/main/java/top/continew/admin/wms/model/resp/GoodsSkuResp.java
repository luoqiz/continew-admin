package top.continew.admin.wms.model.resp;

import java.io.Serial;
import java.time.*;
import java.math.BigDecimal;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

import top.continew.starter.extension.crud.model.resp.BaseResp;

/**
 * 商品规格(sku)信息
 *
 * @author luoqiz
 * @since 2024/11/07 11:10
 */
@Data
@Schema(description = "商品规格(sku)信息")
public class GoodsSkuResp extends BaseResp {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 条形码
     */
    @Schema(description = "条形码")
    private String barcode;

    /**
     * 商品编号
     */
    @Schema(description = "商品编号")
    private Long spuId;

    /**
     * 规格名称
     */
    @Schema(description = "规格名称")
    private String name;

    /**
     * 单位
     */
    @Schema(description = "单位")
    private String unit;

    /**
     * 数量
     */
    @Schema(description = "数量")
    private Integer amount;

    /**
     * 拆箱
     */
    @Schema(description = "拆箱")
    private Boolean unpacking;

    /**
     * 拆箱单位
     */
    @Schema(description = "拆箱单位")
    private String packUnit;

    /**
     * 拆箱数量
     */
    @Schema(description = "拆箱数量")
    private Integer packAmount;

    /**
     * 卖点
     */
    @Schema(description = "卖点")
    private String sellPoint;

    /**
     * 规格列表
     */
    @Schema(description = "规格列表")
    private String specs;

    /**
     * 售价
     */
    @Schema(description = "售价")
    private BigDecimal price;

    /**
     * 首图
     */
    @Schema(description = "首图")
    private String img;

    /**
     * 图片列表
     */
    @Schema(description = "图片列表")
    private String pics;

    /**
     * 状态 1上架  2下架
     */
    @Schema(description = "状态 1上架  2下架")
    private Integer status;

    /**
     * 备注信息
     */
    @Schema(description = "备注信息")
    private String memo;

    /**
     * 修改人
     */
    @Schema(description = "修改人")
    private Long updateUser;

    /**
     * 修改时间
     */
    @Schema(description = "修改时间")
    private LocalDateTime updateTime;
}