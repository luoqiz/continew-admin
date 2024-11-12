package top.continew.admin.wms.model.resp;

import cn.crane4j.annotation.Assemble;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import top.continew.starter.extension.crud.model.resp.BaseResp;

import java.io.Serial;
import java.time.LocalDateTime;

/**
 * 仓库入库信息
 *
 * @author luoqiz
 * @since 2024/11/07 17:49
 */
@Data
@Schema(description = "仓库入库信息")
public class WhseStockInResp extends BaseResp {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 入库名称
     */
    @Schema(description = "入库名称")
    private String name;

    /**
     * 入库单号
     */
    @Schema(description = "入库单号")
    private String stockInNo;

    /**
     * 仓库id编号
     */
    @Schema(description = "仓库id编号")
    @Assemble(
            container = "whseAddr",
//            props = {@Mapping(
//                    src = "name",
//                    ref = "whseName"
//            )}
            prop = "name:whseName"
//            props = @Mapping(src = "name", ref = "whseName")
    )
    private Long whseId;

    /**
     * 仓库名称
     */
    @Schema(description = "仓库名称")
    private String whseName;
    /**
     * 关联移库单号
     */
    @Schema(description = "关联移库单号")
    private Long stockMoveId;

    /**
     * 入库时间
     */
    @Schema(description = "入库时间")
    private LocalDateTime inTime;

    /**
     * 状态 (1审核中 2待入库 3已完成)
     */
    @Schema(description = "状态 (1审核中 2待入库 3已完成)")
    private Integer status;

    /**
     * 备注信息
     */
    @Schema(description = "备注信息")
    private String memo;
}