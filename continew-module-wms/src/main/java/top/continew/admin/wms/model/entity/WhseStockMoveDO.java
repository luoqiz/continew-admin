package top.continew.admin.wms.model.entity;

import java.io.Serial;
import java.time.*;

import lombok.Data;

import com.baomidou.mybatisplus.annotation.TableName;

import top.continew.starter.extension.crud.model.entity.BaseDO;

/**
 * 仓库移库实体
 *
 * @author luoqiz
 * @since 2024/11/15 14:50
 */
@Data
@TableName("wms_whse_stock_move")
public class WhseStockMoveDO extends BaseDO {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 移库名称
     */
    private String name;

    /**
     * 移库单号
     */
    private String stockMoveNo;

    /**
     * 出库类型(1国-国 2国-地 3地-地 4地-店 5店-店)
     */
    private Integer stockMoveType;

    /**
     * 入仓id编号
     */
    private Long stockInWhseId;

    /**
     * 入仓区域id编号
     */
    private String stockInWhseAreaId;

    /**
     * 出仓id编号
     */
    private Long stockOutWhseId;

    /**
     * 出库区域id编号
     */
    private String stockOutWhseAreaId;

    /**
     * 移库时间
     */
    private LocalDateTime moveTime;

    /**
     * 状态 (1审核中 2操作中 2已完成)
     */
    private Integer status;

    /**
     * 关联入库单号
     */
    private Long stockInId;

    /**
     * 关联出库单号
     */
    private Long stockOutId;

    /**
     * 备注信息
     */
    private String memo;
}