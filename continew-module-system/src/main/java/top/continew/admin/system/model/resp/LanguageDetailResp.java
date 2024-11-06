package top.continew.admin.system.model.resp;

import java.io.Serial;
import java.time.*;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;

import top.continew.starter.extension.crud.model.resp.BaseDetailResp;

/**
 * 语言详情信息
 *
 * @author luoqiz
 * @since 2024/10/21 18:15
 */
@Data
@ExcelIgnoreUnannotated
@Schema(description = "语言详情信息")
public class LanguageDetailResp extends BaseDetailResp {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 模块标识
     */
    @Schema(description = "模块标识")
    @ExcelProperty(value = "模块标识")
    private String moduleId;

    /**
     * 模块名称
     */
    @Schema(description = "模块名称")
    @ExcelProperty(value = "模块名称")
    private String moduleName;

    /**
     * 内容
     */
    @Schema(description = "内容")
    @ExcelProperty(value = "内容")
    private String content;

    /**
     * 所属语言类型
     */
    @Schema(description = "所属语言类型")
    @ExcelProperty(value = "所属语言类型")
    private String dictItem;

    /**
     * 状态（1：启用；2：禁用）
     */
    @Schema(description = "状态（1：启用；2：禁用）")
    @ExcelProperty(value = "状态（1：启用；2：禁用）")
    private Integer status;
}