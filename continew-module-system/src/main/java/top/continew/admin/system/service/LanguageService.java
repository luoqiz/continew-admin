package top.continew.admin.system.service;

import cn.hutool.json.JSON;
import top.continew.starter.extension.crud.model.resp.LabelValueResp;
import top.continew.starter.extension.crud.service.BaseService;
import top.continew.admin.system.model.query.LanguageQuery;
import top.continew.admin.system.model.req.LanguageReq;
import top.continew.admin.system.model.resp.LanguageDetailResp;
import top.continew.admin.system.model.resp.LanguageResp;

import java.util.List;

/**
 * 语言业务接口
 *
 * @author luoqiz
 * @since 2024/10/21 18:15
 */
public interface LanguageService extends BaseService<LanguageResp, LanguageDetailResp, LanguageQuery, LanguageReq> {
    JSON getLanguageByType(String type);
}