package top.continew.admin.wms.config;

import cn.crane4j.core.container.Container;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import top.continew.admin.wms.constant.WmsConstants;
import top.continew.admin.wms.model.entity.AddrDO;
import top.continew.admin.wms.service.AddrService;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class WhseAddrContainer implements Container<Long> {

    private final AddrService addrService;

    public String getNamespace() {
        return WmsConstants.addrContainer;
    }

    public Map<Long, AddrDO> get(Collection<Long> ids) {
        List<AddrDO> addrs = addrService.listByIds(ids.stream().toList());
        return addrs.stream().collect(Collectors.toMap(AddrDO::getId, Function.identity()));
    }
}