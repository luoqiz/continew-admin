package top.continew.admin.wms.model.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import top.continew.starter.extension.crud.model.resp.BaseResp;

import java.io.Serial;
import java.time.LocalDateTime;

/**
 * 仓库入库明细信息
 *
 * @author luoqiz
 * @since 2024/11/11 11:53
 */
@Data
@Schema(description = "仓库入库明细信息")
public class WhseStockInDetailResp extends BaseResp {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 入库id编号
     */
    @Schema(description = "入库id编号")
    private String stockInId;

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
    private LocalDateTime prodTime;

    /**
     * 过期日期
     */
    @Schema(description = "过期日期")
    private LocalDateTime expiryTime;

    /**
     * 计划入库数量
     */
    @Schema(description = "计划入库数量")
    private Integer planNum;

    /**
     * 实际入库数量
     */
    @Schema(description = "实际入库数量")
    private Integer realNum;

    /**
     * 备注信息
     */
    @Schema(description = "备注信息")
    private String memo;
}