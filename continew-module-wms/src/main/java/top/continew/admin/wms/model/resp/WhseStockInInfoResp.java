package top.continew.admin.wms.model.resp;

import cn.crane4j.annotation.Assemble;
import cn.crane4j.annotation.Mapping;
import cn.crane4j.annotation.condition.ConditionOnPropertyNotNull;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import top.continew.admin.wms.constant.WmsConstants;
import top.continew.starter.extension.crud.model.resp.BaseDetailResp;

import java.io.Serial;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 仓库入库详情信息
 *
 * @author luoqiz
 * @since 2024/11/11 17:06
 */
@Data
@ExcelIgnoreUnannotated
@Schema(description = "仓库入库详情信息")
public class WhseStockInInfoResp extends BaseDetailResp {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 入库名称
     */
    @Schema(description = "入库名称")
    @ExcelProperty(value = "入库名称")
    private String name;

    /**
     * 入库单号
     */
    @Schema(description = "入库单号")
    @ExcelProperty(value = "入库单号")
    private String stockInNo;

    /**
     * 入库类型(1 采购入库)
     */
    @Schema(description = "入库类型(1 采购入库)")
    @ExcelProperty(value = "入库类型(1 采购入库)")
    private Integer stockInType;

    /**
     * 仓库id编号
     */
    @ConditionOnPropertyNotNull
    @Assemble(
            container = WmsConstants.addrContainer,
            prop = "name:whseName"
    )
    @Schema(description = "仓库id编号")
    @ExcelProperty(value = "仓库id编号")
    private Long whseId;

    @Schema(description = "仓库名称")
    @ExcelProperty(value = "仓库名称")
    private String whseName;
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
    private String stockMoveId;

    /**
     * 入库时间
     */
    @Schema(description = "入库时间")
    @ExcelProperty(value = "入库时间")
    private LocalDateTime inTime;

    /**
     * 状态 (1审核中 2待入库 3已完成)
     */
    @Schema(description = "状态 (1审核中 2待入库 3已完成)")
    @ExcelProperty(value = "状态 (1审核中 2待入库 3已完成)")
    private Integer status;

    /**
     * 备注信息
     */
    @Schema(description = "备注信息")
    @ExcelProperty(value = "备注信息")
    private String memo;

    @Schema(description = "物料列表")
    @ExcelProperty(value = "物料列表")
    public List<WhseStockInDetailResp> goodsList;
}