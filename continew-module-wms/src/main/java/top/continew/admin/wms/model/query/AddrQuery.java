package top.continew.admin.wms.model.query;

import java.io.Serial;
import java.io.Serializable;
import java.time.*;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

import top.continew.starter.data.core.annotation.Query;
import top.continew.starter.data.core.enums.QueryType;

/**
 * 仓库地址查询条件
 *
 * @author luoqiz
 * @since 2024/11/06 19:11
 */
@Data
@Schema(description = "仓库地址查询条件")
public class AddrQuery implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 仓库编号
     */
    @Schema(description = "仓库编号")
    @Query(type = QueryType.EQ)
    private String whseNo;

    /**
     * 仓库名称
     */
    @Schema(description = "仓库名称")
    @Query(type = QueryType.LIKE)
    private String name;

    /**
     * 仓库类型 (1国仓  2地仓  3店仓)
     */
    @Schema(description = "仓库类型 (1国仓  2地仓  3店仓)")
    private Integer whseType;

    /**
     * 状态 (1使用  2停用)
     */
    @Schema(description = "状态 (1使用  2停用)")
    @Query(type = QueryType.EQ)
    private Integer status;
}