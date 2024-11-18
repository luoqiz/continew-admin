package top.continew.admin.wms.model.resp;

import cn.crane4j.annotation.AssembleMethod;
import cn.crane4j.annotation.ContainerMethod;
import cn.crane4j.annotation.Mapping;
import cn.crane4j.annotation.condition.ConditionOnExpression;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import top.continew.admin.system.model.resp.DeptResp;
import top.continew.admin.system.service.DeptService;
import top.continew.starter.extension.crud.model.resp.BaseDetailResp;

import java.io.Serial;

/**
 * 仓库地址详情信息
 *
 * @author luoqiz
 * @since 2024/11/06 19:11
 */
@Data
@ExcelIgnoreUnannotated
@Schema(description = "仓库地址详情信息")
public class AddrDetailResp extends BaseDetailResp {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 仓库编号
     */
    @Schema(description = "仓库编号")
    @ExcelProperty(value = "仓库编号")
    private String whseNo;

    /**
     * 仓库名称
     */
    @Schema(description = "仓库名称")
    @ExcelProperty(value = "仓库名称")
    private String name;

    /**
     * 仓库地址
     */
    @Schema(description = "仓库地址")
    @ExcelProperty(value = "仓库地址")
    private String addr;

    /**
     * 仓库类型 (1国仓  2地仓  3店仓)
     */
    @Schema(description = "仓库类型 (1国仓  2地仓  3店仓)")
    @ExcelProperty(value = "仓库类型 (1国仓  2地仓  3店仓)")
    private Integer whseType;

    /**
     * 状态 (1使用  2停用)
     */
    @Schema(description = "状态 (1使用  2停用)")
    @ExcelProperty(value = "状态 (1使用  2停用)")
    private Integer status;

    /**
     * 备注信息
     */
    @Schema(description = "备注信息")
    @ExcelProperty(value = "备注信息")
    private String memo;

    /**
     * 所属部门
     */
    @Schema(description = "所属部门")
    @ConditionOnExpression("#target.deptName == null")
    @AssembleMethod(props = @Mapping(src = "name", ref = "deptName"), targetType = DeptService.class, method = @ContainerMethod(bindMethod = "get", resultType = DeptResp.class))
    @ExcelProperty(value = "所属部门")
    private Long deptId;

    /**
     * 所属部门
     */
    @Schema(description = "所属部门", example = "测试部")
    @ExcelProperty(value = "所属部门", order = 7)
    private String deptName;
}