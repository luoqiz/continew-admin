package top.continew.admin.wms.model.resp;

import java.io.Serial;
import java.time.*;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;

import top.continew.starter.extension.crud.model.resp.BaseDetailResp;

/**
 * 仓库出库详情信息
 *
 * @author luoqiz
 * @since 2024/11/14 15:15
 */
@Data
@ExcelIgnoreUnannotated
@Schema(description = "仓库出库详情信息")
public class WhseStockOutDetailResp extends BaseDetailResp {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 出库名称
     */
    @Schema(description = "出库名称")
    @ExcelProperty(value = "出库名称")
    private String name;

    /**
     * 出库单号
     */
    @Schema(description = "出库单号")
    @ExcelProperty(value = "出库单号")
    private String stockOutNo;

    /**
     * 出库类型(1 销售出库 2移库出库)
     */
    @Schema(description = "出库类型(1 销售出库 2移库出库)")
    @ExcelProperty(value = "出库类型(1 销售出库 2移库出库)")
    private Integer stockOutType;

    /**
     * 仓库id编号
     */
    @Schema(description = "仓库id编号")
    @ExcelProperty(value = "仓库id编号")
    private String whseId;

    /**
     * 仓库区域id编号
     */
    @Schema(description = "仓库区域id编号")
    @ExcelProperty(value = "仓库区域id编号")
    private String whseAreaId;

    /**
     * 关联移库单号
     */
    @Schema(description = "关联移库单号")
    @ExcelProperty(value = "关联移库单号")
    private Long stockMoveId;

    /**
     * 出库时间
     */
    @Schema(description = "出库时间")
    @ExcelProperty(value = "出库时间")
    private LocalDate outTime;

    /**
     * 状态 (1审核中 2操作中 3已完成)
     */
    @Schema(description = "状态 (1审核中 2操作中 3已完成)")
    @ExcelProperty(value = "状态 (1审核中 2操作中 3已完成)")
    private Integer status;
}