package top.continew.admin.wms.service;

import top.continew.admin.wms.model.entity.AddrDO;
import top.continew.admin.wms.model.query.AddrQuery;
import top.continew.admin.wms.model.req.AddrReq;
import top.continew.admin.wms.model.resp.AddrDetailResp;
import top.continew.admin.wms.model.resp.AddrResp;
import top.continew.starter.extension.crud.service.BaseService;

import java.util.List;

/**
 * 仓库地址业务接口
 *
 * @author luoqiz
 * @since 2024/11/06 18:05
 */
public interface AddrService extends BaseService<AddrResp, AddrDetailResp, AddrQuery, AddrReq> {
    List<AddrDO> listByIds(List<Long> ids);
}