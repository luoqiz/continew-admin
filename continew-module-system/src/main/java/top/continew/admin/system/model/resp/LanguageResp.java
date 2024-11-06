package top.continew.admin.system.model.resp;

import java.io.Serial;
import java.time.*;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

import top.continew.starter.extension.crud.model.resp.BaseResp;

/**
 * 语言信息
 *
 * @author luoqiz
 * @since 2024/10/21 18:15
 */
@Data
@Schema(description = "语言信息")
public class LanguageResp extends BaseResp {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 模块标识
     */
    @Schema(description = "模块标识")
    private String moduleId;

    /**
     * 模块名称
     */
    @Schema(description = "模块名称")
    private String moduleName;

    /**
     * 内容
     */
    @Schema(description = "内容")
    private String content;

    /**
     * 所属语言类型
     */
    @Schema(description = "所属语言类型")
    private String dictItem;

    /**
     * 状态（1：启用；2：禁用）
     */
    @Schema(description = "状态（1：启用；2：禁用）")
    private Integer status;

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