package top.continew.admin.wms.model.resp;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import top.continew.starter.extension.crud.model.resp.BaseDetailResp;

import java.io.Serial;
import java.time.LocalDateTime;

/**
 * 物料盘点详情详情信息
 *
 * @author luoqiz
 * @since 2024/11/26 13:44
 */
@Data
@ExcelIgnoreUnannotated
@Schema(description = "物料盘点详情详情信息")
public class GoodsInventoryCountItemDetailResp extends BaseDetailResp {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 盘点表id
     */
    @Schema(description = "盘点表id")
    @ExcelProperty(value = "盘点表id")
    private Long inventoryCountId;

    /**
     * 库存id
     */
    @Schema(description = "库存id")
    @ExcelProperty(value = "库存id")
    private Long stockId;

    /**
     * 物料编号
     */
    @Schema(description = "物料编号")
    @ExcelProperty(value = "物料编号")
    private Long goodsId;

    /**
     * 物料sku条码
     */
    @Schema(description = "物料sku条码")
    @ExcelProperty(value = "物料sku条码")
    private String goodsSku;

    /**
     * 初始库存
     */
    @Schema(description = "初始库存")
    @ExcelProperty(value = "初始库存")
    private Integer initNum;

    /**
     * 实际库存
     */
    @Schema(description = "实际库存")
    @ExcelProperty(value = "实际库存")
    private Integer realNum;

    /**
     * 状态 1待盘点 2盘点中 3已结束
     */
    @Schema(description = "状态 1待盘点 2盘点中 3已结束")
    @ExcelProperty(value = "状态 1待盘点 2盘点中 3已结束")
    private Integer status;

    /**
     * 生产日期
     */
    @Schema(description = "生产日期")
    @ExcelProperty(value = "生产日期")
    private LocalDateTime prodTime;

    /**
     * 过期日期
     */
    @Schema(description = "过期日期")
    @ExcelProperty(value = "过期日期")
    private LocalDateTime expiryTime;

    /**
     * 备注信息
     */
    @Schema(description = "备注信息")
    @ExcelProperty(value = "备注信息")
    private String memo;
}