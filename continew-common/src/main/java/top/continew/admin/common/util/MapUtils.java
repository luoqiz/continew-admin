package top.continew.admin.common.util;

import cn.hutool.core.map.MapUtil;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MapUtils {
    /**
     * 深度合并两个map
     */
    public static Map<String, Object> mergeMap(Map<String, Object> to, Map<String, Object> from) {
        if (MapUtil.isEmpty(to)) {
            return from;
        }
        if (MapUtil.isEmpty(from)) {
            return to;
        }
        if (MapUtil.isEmpty(to) && MapUtil.isEmpty(from)) {
            return new HashMap<>();
        }
        Set<Map.Entry<String, Object>> entries = to.entrySet();
        Iterator<Map.Entry<String, Object>> iterator = entries.iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Object> kv = iterator.next();
            String toKey = kv.getKey();
            Object toValue = kv.getValue();
            Object fromValue = from.get(toKey);
            if (fromValue != null) {
                if (toValue instanceof Map) {
                    Map<String, Object> childTo = (Map<String, Object>) toValue;
                    mergeMap(childTo, (Map<String, Object>) fromValue);
                } else {
                    to.put(toKey, fromValue);
                }
            }
        }

        Set<String> keys = from.keySet();
        for (String key : keys) {
            if (!to.containsKey(key)) {
                to.put(key, from.get(key));
            }
        }
        return to;
    }
}
