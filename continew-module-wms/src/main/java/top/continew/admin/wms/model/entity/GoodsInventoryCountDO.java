package top.continew.admin.wms.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import top.continew.starter.extension.crud.model.entity.BaseDO;

import java.io.Serial;
import java.time.LocalDateTime;

/**
 * 物料盘点实体
 *
 * @author luoqiz
 * @since 2024/11/26 13:51
 */
@Data
@TableName("wms_goods_inventory_count")
public class GoodsInventoryCountDO extends BaseDO {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    private String name;

    /**
     * 仓库id
     */
    private Long whseId;

    /**
     * 仓库区域id编号
     */
    private String whseAreaId;

    /**
     * 状态 1待盘点 2盘点中 3已结束
     */
    private Integer status;

    /**
     * 开始时间
     */
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    private LocalDateTime endTime;

    /**
     * 备注信息
     */
    private String memo;
}