package top.continew.admin.wms.model.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import top.continew.starter.data.core.annotation.Query;
import top.continew.starter.data.core.enums.QueryType;

import java.io.Serial;
import java.io.Serializable;

/**
 * 仓库入库查询条件
 *
 * @author luoqiz
 * @since 2024/11/07 17:49
 */
@Data
@Schema(description = "仓库入库查询条件")
public class WhseStockInQuery implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 入库名称
     */
    @Schema(description = "入库名称")
    @Query(type = QueryType.LIKE)
    private String name;

    /**
     * 入库单号
     */
    @Schema(description = "入库单号")
    @Query(type = QueryType.LIKE_RIGHT)
    private String stockInNo;

    /**
     * 仓库id编号
     */
    @Schema(description = "仓库id编号")
    @Query(type = QueryType.EQ)
    private Long whseId;

    /**
     * 状态 (1审核中 2待入库 3已完成)
     */
    @Schema(description = "状态 (1审核中 2待入库 3已完成)")
    @Query(type = QueryType.EQ)
    private Integer status;
}