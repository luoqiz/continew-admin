package top.continew.admin.wms.model.query;

import java.io.Serial;
import java.io.Serializable;
import java.time.*;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

import top.continew.starter.data.core.annotation.Query;
import top.continew.starter.data.core.enums.QueryType;

/**
 * 仓库出库查询条件
 *
 * @author luoqiz
 * @since 2024/11/14 15:15
 */
@Data
@Schema(description = "仓库出库查询条件")
public class WhseStockOutQuery implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 出库名称
     */
    @Schema(description = "出库名称")
    @Query(type = QueryType.LIKE_LEFT)
    private String name;

    /**
     * 出库单号
     */
    @Schema(description = "出库单号")
    @Query(type = QueryType.EQ)
    private String stockOutNo;

    /**
     * 仓库id编号
     */
    @Schema(description = "仓库id编号")
    @Query(type = QueryType.EQ)
    private String whseId;

    /**
     * 状态 (1审核中 2操作中 3已完成)
     */
    @Schema(description = "状态 (1审核中 2操作中 3已完成)")
    @Query(type = QueryType.EQ)
    private Integer status;
}