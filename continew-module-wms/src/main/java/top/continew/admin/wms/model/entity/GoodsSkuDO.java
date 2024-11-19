package top.continew.admin.wms.model.entity;

import java.io.Serial;
import java.math.BigDecimal;

import lombok.Data;

import com.baomidou.mybatisplus.annotation.TableName;

import top.continew.starter.extension.crud.model.entity.BaseDO;

/**
 * 商品规格(sku)实体
 *
 * @author luoqiz
 * @since 2024/11/07 11:10
 */
@Data
@TableName("wms_goods_sku")
public class GoodsSkuDO extends BaseDO {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 条形码
     */
    private String barcode;

    /**
     * 商品编号
     */
    private Long spuId;

    /**
     * 规格名称
     */
    private String name;

    /**
     * 单位
     */
    private String unit;

    /**
     * 数量
     */
    private Integer amount;

    /**
     * 拆箱
     */
    private Boolean unpacking;

    /**
     * 拆箱单位
     */
    private String packUnit;

    /**
     * 拆箱数量
     */
    private Integer packAmount;

    /**
     * 卖点
     */
    private String sellPoint;

    /**
     * 规格列表
     */
    private String specs;

    /**
     * 售价
     */
    private BigDecimal price;

    /**
     * 首图
     */
    private String img;

    /**
     * 图片列表
     */
    private String pics;

    /**
     * 状态 1上架  2下架
     */
    private Integer status;

    /**
     * 备注信息
     */
    private String memo;
}