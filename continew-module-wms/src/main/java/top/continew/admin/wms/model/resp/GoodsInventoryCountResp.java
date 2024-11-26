package top.continew.admin.wms.model.resp;

import cn.crane4j.annotation.Assemble;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import top.continew.admin.wms.constant.WmsConstants;
import top.continew.starter.extension.crud.model.resp.BaseResp;

import java.io.Serial;
import java.time.LocalDateTime;

/**
 * 物料盘点信息
 *
 * @author luoqiz
 * @since 2024/11/26 13:51
 */
@Data
@Schema(description = "物料盘点信息")
public class GoodsInventoryCountResp extends BaseResp {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    @Schema(description = "名称")
    private String name;

    /**
     * 仓库id
     */
    @Schema(description = "仓库id")
    @Assemble(container = WmsConstants.addrContainer, prop = "name:whseName")
    private Long whseId;

    /**
     * 仓库名称
     */
    @Schema(description = "仓库名称")
    private String whseName;
    /**
     * 状态 1待盘点 2盘点中 3已结束
     */
    @Schema(description = "状态 1待盘点 2盘点中 3已结束")
    private Integer status;

    /**
     * 开始时间
     */
    @Schema(description = "开始时间")
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    @Schema(description = "结束时间")
    private LocalDateTime endTime;

    /**
     * 备注信息
     */
    @Schema(description = "备注信息")
    private String memo;

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