package top.continew.admin.wms.service.impl;

import cn.hutool.core.bean.BeanUtil;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import top.continew.starter.data.mp.base.BaseMapper;
import top.continew.starter.extension.crud.model.entity.BaseIdDO;
import top.continew.starter.extension.crud.service.impl.BaseServiceImpl;
import top.continew.admin.wms.mapper.AddrMapper;
import top.continew.admin.wms.model.entity.AddrDO;
import top.continew.admin.wms.model.query.AddrQuery;
import top.continew.admin.wms.model.req.AddrReq;
import top.continew.admin.wms.model.resp.AddrDetailResp;
import top.continew.admin.wms.model.resp.AddrResp;
import top.continew.admin.wms.service.AddrService;

/**
 * 仓库地址业务实现
 *
 * @author luoqiz
 * @since 2024/11/06 18:05
 */
@Service
@RequiredArgsConstructor
public class AddrServiceImpl extends BaseServiceImpl<AddrMapper, AddrDO, AddrResp, AddrDetailResp, AddrQuery, AddrReq> implements AddrService {

}