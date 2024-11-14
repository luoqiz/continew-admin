package top.continew.admin.wms.model.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import top.continew.starter.data.core.annotation.Query;
import top.continew.starter.data.core.enums.QueryType;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 商品库存查询条件
 *
 * @author luoqiz
 * @since 2024/11/13 17:57
 */
@Data
@Schema(description = "商品库存查询条件")
public class GoodsStockQuery implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 入库单编号
     */
    @Schema(description = "入库单编号")
    @Query(type = QueryType.EQ)
    private Long stockInId;

    /**
     * 入库单号
     */
    @Schema(description = "入库单号")
    @Query(type = QueryType.LIKE_RIGHT)
    private String stockInNo;

    /**
     * 入库单明细编号
     */
    @Schema(description = "入库单明细编号")
    @Query(type = QueryType.EQ)
    private Long stockInDetailId;

    /**
     * 物料编号
     */
    @Schema(description = "物料编号")
    @Query(type = QueryType.EQ)
    private Long goodsId;

    /**
     * 物料sku条码
     */
    @Schema(description = "物料sku条码")
    @Query(type = QueryType.LIKE_RIGHT)
    private String goodsSku;

    /**
     * 仓库类型（0 国仓  1地仓  2店仓）
     */
    @Schema(description = "仓库类型（0 国仓  1地仓  2店仓）")
    @Query(type = QueryType.EQ)
    private Integer whseType;

    /**
     * 仓库id
     */
    @Schema(description = "仓库id")
    @Query(type = QueryType.EQ)
    private Long whseId;

    /**
     * 状态 1待出库 2已出库 3部分出库
     */
    @Schema(description = "状态 1待出库 2已出库 3部分出库")
    @Query(type = QueryType.EQ)
    private Integer status;

    /**
     * 生产日期
     */
    @Schema(description = "生产日期")
    @Query(type = QueryType.BETWEEN)
    private LocalDateTime[] prodTime;

    /**
     * 过期日期
     */
    @Schema(description = "过期日期")
    @Query(type = QueryType.BETWEEN)
    private LocalDateTime[] expiryTime;
}