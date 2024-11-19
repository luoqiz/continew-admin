package top.continew.admin.wms.model.resp;

import java.io.Serial;
import java.time.*;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

import top.continew.starter.extension.crud.model.resp.BaseResp;

/**
 * 仓库出库明细信息
 *
 * @author luoqiz
 * @since 2024/11/14 16:21
 */
@Data
@Schema(description = "仓库出库明细信息")
public class WhseStockOutDetailMainResp extends BaseResp {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 出库单号
     */
    @Schema(description = "出库单号")
    private Long stockOutId;

    /**
     * 库存物料id
     */
    @Schema(description = "库存物料id")
    private Long goodsStockId;

    /**
     * 商品sku
     */
    @Schema(description = "商品sku")
    private String goodsSku;

    /**
     * 商品名称
     */
    @Schema(description = "商品名称")
    private String goodsName;

    /**
     * 生产日期
     */
    @Schema(description = "生产日期")
    private LocalDate prodTime;

    /**
     * 过期日期
     */
    @Schema(description = "过期日期")
    private LocalDate expiryTime;

    /**
     * 计划出库数量
     */
    @Schema(description = "计划出库数量")
    private Integer planNum;

    /**
     * 实际出库数量
     */
    @Schema(description = "实际出库数量")
    private Integer realNum;

    /**
     * 备注信息
     */
    @Schema(description = "备注信息")
    private String memo;

    /**
     * 状态 (1待核检 2核检通过)
     */
    @Schema(description = "状态 (1待核检 2核检通过)")
    private Integer status;
}