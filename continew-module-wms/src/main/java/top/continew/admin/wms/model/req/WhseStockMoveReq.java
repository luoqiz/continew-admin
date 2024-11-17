package top.continew.admin.wms.model.req;

import java.io.Serial;
import java.time.*;

import jakarta.validation.constraints.*;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

import org.hibernate.validator.constraints.Length;

import top.continew.starter.extension.crud.model.req.BaseReq;

/**
 * 创建或修改仓库移库信息
 *
 * @author luoqiz
 * @since 2024/11/15 14:50
 */
@Data
@Schema(description = "创建或修改仓库移库信息")
public class WhseStockMoveReq extends BaseReq {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 移库名称
     */
    @Schema(description = "移库名称")
    @NotBlank(message = "移库名称不能为空")
    @Length(max = 100, message = "移库名称长度不能超过 {max} 个字符")
    private String name;

    /**
     * 移库单号
     */
    @Schema(description = "移库单号")
    @NotBlank(message = "移库单号不能为空")
    @Length(max = 100, message = "移库单号长度不能超过 {max} 个字符")
    private String stockMoveNo;

    /**
     * 出库类型(1国-国 2国-地 3地-地 4地-店 5店-店)
     */
//    @Schema(description = "出库类型(1国-国 2国-地 3地-地 4地-店 5店-店)")
//    @NotNull(message = "出库类型(1国-国 2国-地 3地-地 4地-店 5店-店)不能为空")
//    private Integer stockMoveType;

    /**
     * 入仓id编号
     */
    @Schema(description = "入仓id编号")
    @NotNull(message = "入仓id编号不能为空")
    private Long stockInWhseId;

    /**
     * 出仓id编号
     */
    @Schema(description = "出仓id编号")
    @NotNull(message = "出仓id编号不能为空")
    private Long stockOutWhseId;

    /**
     * 状态 (1审核中 2操作中 2已完成)
     */
//    @Schema(description = "状态 (1审核中 2操作中 2已完成)")
//    @NotNull(message = "状态 (1审核中 2操作中 2已完成)不能为空")
//    private Integer status;
}