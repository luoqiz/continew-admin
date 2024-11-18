package top.continew.admin.wms.model.resp;

import cn.crane4j.annotation.AssembleMethod;
import cn.crane4j.annotation.ContainerMethod;
import cn.crane4j.annotation.Mapping;
import cn.crane4j.annotation.condition.ConditionOnExpression;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import top.continew.admin.system.model.resp.DeptResp;
import top.continew.admin.system.service.DeptService;
import top.continew.starter.extension.crud.model.resp.BaseResp;

import java.io.Serial;
import java.time.LocalDateTime;

/**
 * 仓库地址信息
 *
 * @author luoqiz
 * @since 2024/11/06 19:11
 */
@Data
@Schema(description = "仓库地址信息")
public class AddrResp extends BaseResp {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 仓库编号
     */
    @Schema(description = "仓库编号")
    private String whseNo;

    /**
     * 仓库名称
     */
    @Schema(description = "仓库名称")
    private String name;

    /**
     * 仓库地址
     */
    @Schema(description = "仓库地址")
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
    private Integer status;

    /**
     * 备注信息
     */
    @Schema(description = "备注信息")
    private String memo;

    /**
     * 所属部门
     */
    @ConditionOnExpression("#target.deptName == null")
    @AssembleMethod(props = @Mapping(src = "name", ref = "deptName"), targetType = DeptService.class, method = @ContainerMethod(bindMethod = "get", resultType = DeptResp.class))
    @Schema(description = "所属部门")
    private Long deptId;

    /**
     * 所属部门
     */
    @Schema(description = "所属部门", example = "测试部")
    private String deptName;
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