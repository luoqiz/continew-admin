package top.continew.admin.wms.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import top.continew.starter.extension.crud.model.entity.BaseDO;

import java.io.Serial;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 仓库入库明细实体
 *
 * @author luoqiz
 * @since 2024/11/11 11:53
 */
@Data
@TableName("wms_whse_stock_in_detail")
public class WhseStockInDetailDO extends BaseDO {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 入库id编号
     */
    private String stockInId;

    /**
     * 商品sku
     */
    private String goodsSku;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 生产日期
     */
    private LocalDate prodTime;

    /**
     * 过期日期
     */
    private LocalDate expiryTime;

    /**
     * 计划入库数量
     */
    private Integer planNum;

    /**
     * 实际入库数量
     */
    private Integer realNum;

    /**
     * 备注信息
     */
    private String memo;

    /**
     * 状态 (1待核检 2核检通过)
     */
    private Integer status;
}