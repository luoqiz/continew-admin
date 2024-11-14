package top.continew.admin.wms.model.entity;

import java.io.Serial;
import java.time.*;

import lombok.Data;

import com.baomidou.mybatisplus.annotation.TableName;

import top.continew.starter.extension.crud.model.entity.BaseDO;

/**
 * 仓库出库实体
 *
 * @author luoqiz
 * @since 2024/11/14 15:15
 */
@Data
@TableName("wms_whse_stock_out")
public class WhseStockOutDO extends BaseDO {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 出库名称
     */
    private String name;

    /**
     * 出库单号
     */
    private String stockOutNo;

    /**
     * 出库类型(1 销售出库 2移库出库)
     */
    private Integer stockOutType;

    /**
     * 仓库id编号
     */
    private String whseId;

    /**
     * 仓库区域id编号
     */
    private String whseAreaId;

    /**
     * 关联移库单号
     */
    private Long stockMoveId;

    /**
     * 出库时间
     */
    private LocalDate outTime;

    /**
     * 状态 (1审核中 2操作中 3已完成)
     */
    private Integer status;
}