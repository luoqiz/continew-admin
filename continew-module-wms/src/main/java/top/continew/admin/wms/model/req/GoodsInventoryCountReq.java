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
 * 创建或修改物料盘点参数
 *
 * @author luoqiz
 * @since 2024/11/26 13:51
 */
@Data
@Schema(description = "创建或修改物料盘点参数")
public class GoodsInventoryCountReq extends BaseReq {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    @Schema(description = "名称")
    @NotBlank(message = "名称不能为空")
    @Length(max = 100, message = "名称长度不能超过 {max} 个字符")
    private String name;

    /**
     * 仓库id
     */
    @Schema(description = "仓库id")
    @NotNull(message = "仓库id不能为空")
    private Long whseId;

    /**
     * 状态 1待盘点 2盘点中 3已结束
     */
    @Schema(description = "状态 1待盘点 2盘点中 3已结束")
    @NotNull(message = "状态 1待盘点 2盘点中 3已结束不能为空")
    private Integer status;

    /**
     * 开始时间
     */
    @Schema(description = "开始时间")
    @NotNull(message = "开始时间不能为空")
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    @Schema(description = "结束时间")
    @NotNull(message = "结束时间不能为空")
    private LocalDateTime endTime;
}