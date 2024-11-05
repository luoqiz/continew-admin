package top.continew.admin.system.model.entity;

import java.io.Serial;

import lombok.Data;

import com.baomidou.mybatisplus.annotation.TableName;

import top.continew.starter.extension.crud.model.entity.BaseDO;

/**
 * 语言实体
 *
 * @author luoqiz
 * @since 2024/10/21 18:15
 */
@Data
@TableName("sys_language")
public class LanguageDO extends BaseDO {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 模块标识
     */
    private String moduleId;

    /**
     * 模块名称
     */
    private String moduleName;

    /**
     * 内容
     */
    private String content;

    /**
     * 所属语言类型
     */
    private String dictItem;

    /**
     * 状态（1：启用；2：禁用）
     */
    private Integer status;
}