package top.continew.admin.wms.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import top.continew.admin.wms.mapper.AddrMapper;
import top.continew.admin.wms.model.entity.AddrDO;
import top.continew.admin.wms.model.query.AddrQuery;
import top.continew.admin.wms.model.req.AddrReq;
import top.continew.admin.wms.model.resp.AddrDetailResp;
import top.continew.admin.wms.model.resp.AddrResp;
import top.continew.admin.wms.service.AddrService;
import top.continew.starter.extension.crud.service.impl.BaseServiceImpl;

import java.util.List;

/**
 * 仓库地址业务实现
 *
 * @author luoqiz
 * @since 2024/11/06 18:05
 */
@Service
@RequiredArgsConstructor
public class AddrServiceImpl extends BaseServiceImpl<AddrMapper, AddrDO, AddrResp, AddrDetailResp, AddrQuery, AddrReq> implements AddrService {

    @Override
    public List<AddrDO> listByIds(List<Long> ids) {
        return baseMapper.selectByIds(ids);
    }
}