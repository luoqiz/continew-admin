package top.continew.admin.wms.model.resp;

import java.io.Serial;
import java.time.*;

import cn.crane4j.annotation.Assemble;
import cn.crane4j.annotation.condition.ConditionOnPropertyNotNull;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

import top.continew.admin.wms.constant.WmsConstants;
import top.continew.starter.extension.crud.model.resp.BaseResp;

/**
 * 仓库出库信息
 *
 * @author luoqiz
 * @since 2024/11/14 15:15
 */
@Data
@Schema(description = "仓库出库信息")
public class WhseStockOutResp extends BaseResp {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 出库名称
     */
    @Schema(description = "出库名称")
    private String name;

    /**
     * 出库单号
     */
    @Schema(description = "出库单号")
    private String stockOutNo;

    /**
     * 出库类型(1 销售出库 2移库出库)
     */
    @Schema(description = "出库类型(1 销售出库 2移库出库)")
    private Integer stockOutType;

    /**
     * 仓库id编号
     */
    @ConditionOnPropertyNotNull
    @Assemble(
            container = WmsConstants.addrContainer,
            prop = "name:whseName"
    )
    @Schema(description = "仓库id编号")
    private Long whseId;

    @ExcelProperty(value = "仓库名称")
    private String whseName;

    /**
     * 仓库区域id编号
     */
    @Schema(description = "仓库区域id编号")
    private String whseAreaId;

    /**
     * 关联移库单号
     */
    @Schema(description = "关联移库单号")
    private Long stockMoveId;

    /**
     * 出库时间
     */
    @Schema(description = "出库时间")
    private LocalDate outTime;

    /**
     * 状态 (1审核中 2操作中 3已完成)
     */
    @Schema(description = "状态 (1审核中 2操作中 3已完成)")
    private Integer status;
}