package top.continew.admin.wms.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import top.continew.starter.extension.crud.model.entity.BaseDO;

import java.io.Serial;
import java.time.LocalDate;

/**
 * 物料盘点详情实体
 *
 * @author luoqiz
 * @since 2024/11/26 13:44
 */
@Data
@TableName("wms_goods_inventory_count_item")
public class GoodsInventoryCountItemDO extends BaseDO {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 盘点表id
     */
    private Long inventoryCountId;

    /**
     * 库存id
     */
    private Long stockId;

    /**
     * 物料编号
     */
    private Long goodsId;

    /**
     * 物料sku条码
     */
    private String goodsSku;

    /**
     * 初始库存
     */
    private Integer initNum;

    /**
     * 实际库存
     */
    private Integer realNum;

    /**
     * 状态 1待盘点 2盘点中 3已结束
     */
    private Integer status;

    /**
     * 生产日期
     */
    private LocalDate prodTime;

    /**
     * 过期日期
     */
    private LocalDate expiryTime;

    /**
     * 备注信息
     */
    private String memo;
}