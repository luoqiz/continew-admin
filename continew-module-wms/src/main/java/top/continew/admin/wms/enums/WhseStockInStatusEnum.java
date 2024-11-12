package top.continew.admin.wms.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import top.continew.starter.core.enums.BaseEnum;

@Getter
@RequiredArgsConstructor
public enum WhseStockInStatusEnum implements BaseEnum<Integer> {
    AWAIT_APPROVAL(1, "待审核"),
    PENDING_STOCK_IN(2, "待入库"),
    DONE(3, "已完成"),
    ;
    private final Integer value;
    private final String description;
}
