package top.continew.admin.wms.model.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import top.continew.starter.extension.crud.model.req.BaseReq;

import java.io.Serial;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 创建或修改仓库入库明细信息
 *
 * @author luoqiz
 * @since 2024/11/11 11:53
 */
@Data
@Schema(description = "创建或修改仓库入库明细信息")
public class WhseStockInDetailReq extends BaseReq {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 入库id编号
     */
    @Schema(description = "入库id编号")
    @NotNull(message = "入库id编号不能为空")
    private Long stockInId;

    /**
     * 库存物料id
     */
    @Schema(description = "库存物料id")
//    @NotNull(message = "库存id不能为空")
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
     * 计划入库数量
     */
    @Schema(description = "计划入库数量")
    @NotNull(message = "计划入库数量不能为空")
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

    /**
     * 状态 (1待核检 2核检通过)
     */
    @Schema(description = "核检状态")
    private Integer status;
}