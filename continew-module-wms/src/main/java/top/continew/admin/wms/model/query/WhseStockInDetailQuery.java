package top.continew.admin.wms.model.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import top.continew.starter.data.core.annotation.Query;
import top.continew.starter.data.core.enums.QueryType;

import java.io.Serial;
import java.io.Serializable;

/**
 * 仓库入库明细查询条件
 *
 * @author luoqiz
 * @since 2024/11/11 11:53
 */
@Data
@Schema(description = "仓库入库明细查询条件")
public class WhseStockInDetailQuery implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 入库id编号
     */
    @Schema(description = "入库id编号")
    @Query(type = QueryType.EQ)
    private Long stockInId;

    /**
     * 商品sku
     */
    @Schema(description = "商品sku")
    @Query(type = QueryType.EQ)
    private String goodsSku;

    /**
     * 商品名称
     */
    @Schema(description = "商品名称")
    @Query(type = QueryType.LIKE_RIGHT)
    private String goodsName;
}