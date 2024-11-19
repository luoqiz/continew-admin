package top.continew.admin.wms.model.resp;

import java.io.Serial;
import java.time.*;
import java.util.List;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

import top.continew.starter.extension.crud.model.resp.BaseResp;

/**
 * 仓库移库明细信息
 *
 * @author luoqiz
 * @since 2024/11/15 17:17
 */
@Data
@Schema(description = "仓库移库明细信息")
public class WhseStockMoveDetialResp extends BaseResp {

    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 移库单号
     */
    @Schema(description = "移库单id")
    private Long stockMoveId;

    /**
     * 移库单号
     */
    @Schema(description = "移库单号")
    private String stockMoveNo;

    /**
     * 库存id
     */
    @Schema(description = "库存id")
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
     * 移库时间
     */
    @Schema(description = "移库时间")
    private LocalDateTime moveTime;

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
     * 计划数量
     */
    @Schema(description = "计划数量")
    private Integer planNum;

    /**
     * 备注信息
     */
    @Schema(description = "备注信息")
    private String memo;

}