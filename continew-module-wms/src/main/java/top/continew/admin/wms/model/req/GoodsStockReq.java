package top.continew.admin.wms.model.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import top.continew.starter.extension.crud.model.req.BaseReq;

import java.io.Serial;
import java.time.LocalDateTime;

/**
 * 创建或修改商品库存信息
 *
 * @author luoqiz
 * @since 2024/11/13 17:57
 */
@Data
@Schema(description = "创建或修改商品库存信息")
public class GoodsStockReq extends BaseReq {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 入库单编号
     */
    @Schema(description = "入库单编号")
    @NotNull(message = "入库单编号不能为空")
    private Long stockInId;

    /**
     * 入库单号
     */
    @Schema(description = "入库单号")
    @NotBlank(message = "入库单号不能为空")
    @Length(max = 100, message = "入库单号长度不能超过 {max} 个字符")
    private String stockInNo;

    /**
     * 入库单明细编号
     */
    @Schema(description = "入库单明细编号")
    @NotNull(message = "入库单明细编号不能为空")
    private Long stockInDetailId;

    /**
     * 物料编号
     */
    @Schema(description = "物料编号")
    @NotNull(message = "物料编号不能为空")
    private Long goodsId;

    /**
     * 物料sku条码
     */
    @Schema(description = "物料sku条码")
    @NotBlank(message = "物料sku条码不能为空")
    @Length(max = 100, message = "物料sku条码长度不能超过 {max} 个字符")
    private String goodsSku;

    /**
     * 初始库存
     */
    @Schema(description = "初始库存")
    @NotNull(message = "初始库存不能为空")
    private Integer initNum;

    /**
     * 实际库存
     */
    @Schema(description = "实际库存")
    @NotNull(message = "实际库存不能为空")
    private Integer realNum;

    /**
     * 仓库类型（0 国仓  1地仓  2店仓）
     */
    @Schema(description = "仓库类型（0 国仓  1地仓  2店仓）")
    @NotNull(message = "仓库类型（0 国仓  1地仓  2店仓）不能为空")
    private Integer whseType;

    /**
     * 仓库id
     */
    @Schema(description = "仓库id")
    @NotNull(message = "仓库id不能为空")
    private Long whseId;

    /**
     * 生产日期
     */
    @Schema(description = "生产日期")
    @NotNull(message = "生产日期不能为空")
    private LocalDateTime prodTime;

    /**
     * 过期日期
     */
    @Schema(description = "过期日期")
    @NotNull(message = "过期日期不能为空")
    private LocalDateTime expiryTime;
}