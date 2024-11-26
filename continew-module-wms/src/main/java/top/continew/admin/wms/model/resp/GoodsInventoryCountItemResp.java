package top.continew.admin.wms.model.resp;

import cn.crane4j.annotation.Assemble;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import top.continew.admin.wms.constant.WmsConstants;
import top.continew.starter.extension.crud.model.resp.BaseResp;

import java.io.Serial;
import java.time.LocalDateTime;

/**
 * 物料盘点详情信息
 *
 * @author luoqiz
 * @since 2024/11/26 13:44
 */
@Data
@Schema(description = "物料盘点详情信息")
public class GoodsInventoryCountItemResp extends BaseResp {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 盘点表id
     */
    @Schema(description = "盘点表id")
    private Long inventoryCountId;

    /**
     * 库存id
     */
    @Schema(description = "库存id")
    private Long stockId;

    /**
     * 物料编号
     */
    @Schema(description = "物料编号")
    private Long goodsId;

    /**
     * 物料sku条码
     */
    @Schema(description = "物料sku条码")
    @Assemble(container = WmsConstants.goodsSkuContainer, prop = "name:goodsName")
    private String goodsSku;

    /**
     * 物料sku条码
     */
    @Schema(description = "物料名称")
    private String goodsName;

    /**
     * 初始库存
     */
    @Schema(description = "初始库存")
    private Integer initNum;

    /**
     * 实际库存
     */
    @Schema(description = "实际库存")
    private Integer realNum;

    /**
     * 状态 1待盘点 2盘点中 3已结束
     */
    @Schema(description = "状态 1待盘点 2盘点中 3已结束")
    private Integer status;

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
     * 备注信息
     */
    @Schema(description = "备注信息")
    private String memo;

    /**
     * 修改人
     */
    @Schema(description = "修改人")
    private Long updateUser;

    /**
     * 修改时间
     */
    @Schema(description = "修改时间")
    private LocalDateTime updateTime;
}