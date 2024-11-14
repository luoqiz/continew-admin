package top.continew.admin.wms.model.resp;

import cn.crane4j.annotation.Assemble;
import cn.crane4j.annotation.condition.ConditionOnPropertyNotNull;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import top.continew.admin.wms.constant.WmsConstants;
import top.continew.starter.extension.crud.model.resp.BaseResp;

import java.io.Serial;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 商品库存信息
 *
 * @author luoqiz
 * @since 2024/11/13 17:57
 */
@Data
@Schema(description = "商品库存信息")
public class GoodsStockResp extends BaseResp {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 入库单编号
     */
    @Schema(description = "入库单编号")
    private Long stockInId;

    /**
     * 入库单号
     */
    @Schema(description = "入库单号")
    private String stockInNo;

    /**
     * 入库单明细编号
     */
    @Schema(description = "入库单明细编号")
    private Long stockInDetailId;

    /**
     * 物料编号
     */
    @Schema(description = "物料编号")
    private Long goodsId;

    /**
     * 物料sku条码
     */
    @ConditionOnPropertyNotNull
    @Assemble(
            container = WmsConstants.goodsSkuContainer,
            prop = "name:goodsName"
    )
    @Schema(description = "物料sku条码")
    private String goodsSku;

    @Schema(description = "物料名称")
    @ExcelProperty(value = "物料名称")
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
     * 仓库类型
     */
    @Schema(description = "仓库类型")
    private Integer whseType;

    /**
     * 仓库id
     */
    @ConditionOnPropertyNotNull
    @Assemble(
            container = WmsConstants.addrContainer,
            prop = "name:whseName"
    )
    @Schema(description = "仓库id")
    private Long whseId;

    @Schema(description = "仓库名称")
    private String whseName;

    /**
     * 状态 1待出库 2已出库 3部分出库
     */
    @Schema(description = "状态 1待出库 2已出库 3部分出库")
    private Integer status;

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
     * 备注信息
     */
    @Schema(description = "备注信息")
    private String info;

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