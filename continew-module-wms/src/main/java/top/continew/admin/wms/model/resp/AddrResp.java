package top.continew.admin.wms.model.resp;

import java.io.Serial;
import java.time.*;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

import top.continew.starter.extension.crud.model.resp.BaseResp;

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
    @Schema(description = "所属部门")
    private Long deptId;

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