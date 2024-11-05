package top.continew.admin.common.util;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import org.yaml.snakeyaml.Yaml;

import java.util.HashMap;
import java.util.Map;

public class YamlUtils {

    public static JSON toJsonObject(String... yamlStr){
        Yaml yaml = new Yaml();
        Map<String, Object> mergedConfig = new HashMap<>();
        for (String yamlItem : yamlStr) {
            if(StrUtil.isNotEmpty(yamlItem)){
                mergedConfig.putAll(yaml.load(yamlItem));
            }
        }
        JSON jsonObject = JSONUtil.parse(mergedConfig);
        return jsonObject;
    }
}
