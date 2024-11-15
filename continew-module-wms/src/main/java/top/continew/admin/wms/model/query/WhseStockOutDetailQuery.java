package top.continew.admin.wms.model.query;

import java.io.Serial;
import java.io.Serializable;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

import top.continew.starter.data.core.annotation.Query;
import top.continew.starter.data.core.enums.QueryType;

/**
 * 仓库出库明细查询条件
 *
 * @author luoqiz
 * @since 2024/11/14 16:21
 */
@Data
@Schema(description = "仓库出库明细查询条件")
public class WhseStockOutDetailQuery implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 出库单号
     */
    @Schema(description = "出库单号")
    @Query(type = QueryType.EQ)
    private Long stockOutId;

    /**
     * 商品sku
     */
    @Schema(description = "商品sku")
    @Query(type = QueryType.LIKE_RIGHT)
    private String goodsSku;

    /**
     * 商品名称
     */
    @Schema(description = "商品名称")
    @Query(type = QueryType.EQ)
    private String goodsName;
}