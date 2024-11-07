package top.continew.admin.wms.model.query;

import java.io.Serial;
import java.io.Serializable;
import java.time.*;
import java.math.BigDecimal;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

import top.continew.starter.data.core.annotation.Query;
import top.continew.starter.data.core.enums.QueryType;

/**
 * 商品规格(sku)查询条件
 *
 * @author luoqiz
 * @since 2024/11/07 11:10
 */
@Data
@Schema(description = "商品规格(sku)查询条件")
public class GoodsSkuQuery implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 条形码
     */
    @Schema(description = "条形码")
    @Query(type = QueryType.LIKE_RIGHT)
    private String barcode;

    /**
     * 规格名称
     */
    @Schema(description = "规格名称")
    @Query(type = QueryType.LIKE)
    private String name;

    /**
     * 状态 1上架  2下架
     */
    @Schema(description = "状态 1上架  2下架")
    @Query(type = QueryType.EQ)
    private Integer status;
}