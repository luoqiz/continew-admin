package top.continew.admin.wms.model.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import top.continew.starter.extension.crud.model.req.BaseReq;

import java.io.Serial;

/**
 * 创建或修改仓库入库信息
 *
 * @author luoqiz
 * @since 2024/11/07 17:49
 */
@Data
@Schema(description = "创建或修改仓库入库信息")
public class WhseStockInReq extends BaseReq {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 入库名称
     */
    @Schema(description = "入库名称")
    @NotBlank(message = "入库名称不能为空")
    @Length(max = 100, message = "入库名称长度不能超过 {max} 个字符")
    private String name;

    /**
     * 入库单号
     */
    @Schema(description = "入库单号")
    @NotBlank(message = "入库单号不能为空")
    @Length(max = 100, message = "入库单号长度不能超过 {max} 个字符")
    private String stockInNo;

    /**
     * 仓库id编号
     */
    @Schema(description = "仓库id编号")
    private Long whseId;
}