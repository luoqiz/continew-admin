package top.continew.admin.wms.model.req;

import java.io.Serial;
import java.time.*;

import jakarta.validation.constraints.*;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

import org.hibernate.validator.constraints.Length;

import top.continew.starter.extension.crud.model.req.BaseReq;

/**
 * 创建或修改仓库地址信息
 *
 * @author luoqiz
 * @since 2024/11/06 19:11
 */
@Data
@Schema(description = "创建或修改仓库地址信息")
public class AddrReq extends BaseReq {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 仓库编号
     */
    @Schema(description = "仓库编号")
    @NotBlank(message = "仓库编号不能为空")
    @Length(max = 100, message = "仓库编号长度不能超过 {max} 个字符")
    private String whseNo;

    /**
     * 仓库名称
     */
    @Schema(description = "仓库名称")
    @NotBlank(message = "仓库名称不能为空")
    @Length(max = 24, message = "仓库名称长度不能超过 {max} 个字符")
    private String name;

    /**
     * 仓库地址
     */
    @Schema(description = "仓库地址")
    @Length(max = 48, message = "仓库地址长度不能超过 {max} 个字符")
    private String addr;

    /**
     * 仓库类型 (1国仓  2地仓  3店仓)
     */
    @Schema(description = "仓库类型 (1国仓  2地仓  3店仓)")
    private Integer whseType;

    /**
     * 状态 (1使用  2停用)
     */
    @Schema(description = "状态 (1使用  2停用)")
    @NotNull(message = "状态 (1使用  2停用)不能为空")
    private Integer status;

    /**
     * 备注信息
     */
    @Schema(description = "备注信息")
    @Length(max = 1000, message = "备注信息长度不能超过 {max} 个字符")
    private String memo;

    /**
     * 所属部门
     */
    @Schema(description = "所属部门")
    private Long deptId;
}