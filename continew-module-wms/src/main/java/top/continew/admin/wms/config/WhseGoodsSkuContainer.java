package top.continew.admin.wms.config;

import cn.crane4j.core.container.Container;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import top.continew.admin.wms.constant.WmsConstants;
import top.continew.admin.wms.model.entity.GoodsSkuDO;
import top.continew.admin.wms.service.GoodsSkuService;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class WhseGoodsSkuContainer implements Container<String> {

    private final GoodsSkuService skuService;

    public String getNamespace() {
        return WmsConstants.goodsSkuContainer;
    }

    public Map<String, GoodsSkuDO> get(Collection<String> ids) {
        List<GoodsSkuDO> datas = skuService.getBySkuNoList(ids.stream().toList());
        return datas.stream().collect(Collectors.toMap(GoodsSkuDO::getBarcode, Function.identity()));
    }
}