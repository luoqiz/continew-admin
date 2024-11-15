package top.continew.admin.common.util;

import cn.hutool.core.util.StrUtil;
import pl.jalokim.propertiestojson.util.PropertiesToJsonConverter;

import java.util.*;

public class PropsUtils {

    public static String props2json(String... props) {
        Map<String, String> properties = new HashMap<>();
        List<String> lines = new ArrayList<>();
        for (String prop : props) {
            lines.addAll(Arrays.stream(prop.split("\n")).filter(item -> StrUtil.isNotEmpty(item)).toList());
        }
        for (String item : lines) {
            String[] list = item.split(": ");
            if(list.length==2){
                System.out.println(list[0]+"---" + list[1]);
                properties.put(list[0], list[1]);
            }
        }
        return new PropertiesToJsonConverter().convertToJson(properties);
    }
}
