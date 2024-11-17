package top.continew.admin.wms.model.req;

import java.io.Serial;
import java.time.*;

import jakarta.validation.constraints.*;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

import org.hibernate.validator.constraints.Length;

import top.continew.starter.data.core.annotation.Query;
import top.continew.starter.data.core.enums.QueryType;
import top.continew.starter.extension.crud.model.req.BaseReq;

/**
 * 创建或修改仓库移库明细信息
 *
 * @author luoqiz
 * @since 2024/11/15 17:17
 */
@Data
@Schema(description = "创建或修改仓库移库明细信息")
public class WhseStockMoveDetialReq extends BaseReq {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 移库id
     */
    @Schema(description = "移库id")
    @NotNull(message = "移库单号不能为空")
    private Long stockMoveId;

    /**
     * 移库单号
     */
    @Schema(description = "移库单号")
//    @NotBlank(message = "移库单号不能为空")
//    @Length(max = 100, message = "移库单号长度不能超过 {max} 个字符")
    private String stockMoveNo;

    /**
     * 库存id
     */
    @Schema(description = "库存id")
    @NotNull(message = "库存id不能为空")
    private Long goodsStockId;

    /**
     * 商品sku
     */
    @Schema(description = "商品sku")
    @NotBlank(message = "商品sku不能为空")
    @Length(max = 100, message = "商品sku长度不能超过 {max} 个字符")
    private String goodsSku;

    /**
     * 商品名称
     */
    @Schema(description = "商品名称")
    @NotBlank(message = "商品名称不能为空")
    @Length(max = 100, message = "商品名称长度不能超过 {max} 个字符")
    private String goodsName;

    /**
     * 移库时间
     */
    @Schema(description = "移库时间")
//    @NotNull(message = "移库时间不能为空")
    private LocalDateTime moveTime;

    /**
     * 生产日期
     */
    @Schema(description = "生产日期")
    @NotNull(message = "生产日期不能为空")
    private LocalDate prodTime;

    /**
     * 过期日期
     */
    @Schema(description = "过期日期")
    @NotNull(message = "过期日期不能为空")
    private LocalDate expiryTime;

    /**
     * 计划数量
     */
    @Schema(description = "计划数量")
    @NotNull(message = "计划数量不能为空")
    private Integer planNum;
}