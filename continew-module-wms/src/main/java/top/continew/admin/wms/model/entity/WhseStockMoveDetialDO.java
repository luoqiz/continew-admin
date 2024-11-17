package top.continew.admin.wms.model.entity;

import java.io.Serial;
import java.time.*;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import com.baomidou.mybatisplus.annotation.TableName;

import top.continew.starter.data.core.annotation.Query;
import top.continew.starter.data.core.enums.QueryType;
import top.continew.starter.extension.crud.model.entity.BaseDO;

/**
 * 仓库移库明细实体
 *
 * @author luoqiz
 * @since 2024/11/15 17:17
 */
@Data
@TableName("wms_whse_stock_move_detial")
public class WhseStockMoveDetialDO extends BaseDO {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 移库id
     */
    private Long stockMoveId;

    /**
     * 移库单号
     */
    private String stockMoveNo;

    /**
     * 库存id
     */
    private Long goodsStockId;

    /**
     * 商品sku
     */
    private String goodsSku;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 移库时间
     */
    private LocalDateTime moveTime;

    /**
     * 生产日期
     */
    private LocalDate prodTime;

    /**
     * 过期日期
     */
    private LocalDate expiryTime;

    /**
     * 计划数量
     */
    private Integer planNum;

    /**
     * 实际数量
     */
    private Integer realNum;

    /**
     * 备注信息
     */
    private String memo;
}