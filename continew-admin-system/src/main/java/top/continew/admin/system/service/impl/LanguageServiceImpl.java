package top.continew.admin.system.service.impl;

import cn.hutool.json.JSON;
import cn.hutool.setting.yaml.YamlUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import top.continew.admin.common.util.YamlUtils;
import top.continew.admin.system.model.entity.DictDO;
import top.continew.admin.system.model.entity.DictItemDO;
import top.continew.admin.system.model.resp.DictResp;
import top.continew.admin.system.service.DictItemService;
import top.continew.admin.system.service.DictService;
import top.continew.starter.extension.crud.model.resp.LabelValueResp;
import top.continew.starter.extension.crud.service.impl.BaseServiceImpl;
import top.continew.admin.system.mapper.LanguageMapper;
import top.continew.admin.system.model.entity.LanguageDO;
import top.continew.admin.system.model.query.LanguageQuery;
import top.continew.admin.system.model.req.LanguageReq;
import top.continew.admin.system.model.resp.LanguageDetailResp;
import top.continew.admin.system.model.resp.LanguageResp;
import top.continew.admin.system.service.LanguageService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 语言业务实现
 *
 * @author luoqiz
 * @since 2024/10/21 18:15
 */
@Service
@RequiredArgsConstructor
public class LanguageServiceImpl extends BaseServiceImpl<LanguageMapper, LanguageDO, LanguageResp, LanguageDetailResp, LanguageQuery, LanguageReq> implements LanguageService {

    @Resource
    private DictService dictService;
    @Resource
    private DictItemService dictItemService;

    private String  language_dict_key = "language_type";

    @Transactional
    @Override
    public Long add(LanguageReq req) {
        DictDO dictDO = dictService.getByCode(language_dict_key);
        List<DictItemDO> items = dictItemService.list(new LambdaQueryWrapper<DictItemDO>().eq(DictItemDO::getDictId, dictDO.getId()));
        Collection<LanguageDO> batch = new ArrayList<>();
        for (DictItemDO item : items) {
            LanguageDO temp = new LanguageDO();
            temp.setModuleId(req.getModuleId());
            temp.setModuleName(req.getModuleName());
            temp.setStatus(req.getStatus());
            temp.setDictItem(item.getValue());
            batch.add(temp);
        }
        super.saveBatch(batch);
        return (long) batch.size();
    }

    @Override
    public JSON getLanguageByType(String type) {
        LambdaQueryWrapper<LanguageDO> query = new LambdaQueryWrapper<>();
        query.eq(LanguageDO::getDictItem, type);
        List<LanguageDO> res = list(query);
        String[] str_array = new String[res.size()];
        for (int i = 0; i < res.size(); i++) {
            str_array[i] = res.get(i).getContent();
        }
        return YamlUtils.toJsonObject(str_array);
    }
}