package top.continew.admin.wms.model.entity;

import java.io.Serial;

import lombok.Data;

import com.baomidou.mybatisplus.annotation.TableName;

import top.continew.starter.extension.crud.model.entity.BaseDO;

/**
 * 仓库地址实体
 *
 * @author luoqiz
 * @since 2024/11/06 19:11
 */
@Data
@TableName("whse_addr")
public class AddrDO extends BaseDO {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 仓库编号
     */
    private String whseNo;

    /**
     * 仓库名称
     */
    private String name;

    /**
     * 仓库地址
     */
    private String addr;

    /**
     * 仓库类型 (1国仓  2地仓  3店仓)
     */
    private Integer whseType;

    /**
     * 状态 (1使用  2停用)
     */
    private Integer status;

    /**
     * 备注信息
     */
    private String memo;

    /**
     * 所属部门
     */
    private Long deptId;
}