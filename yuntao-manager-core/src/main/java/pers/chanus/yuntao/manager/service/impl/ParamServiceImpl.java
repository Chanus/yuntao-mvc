/*
 * Copyright (c) 2018, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.manager.service.impl;

import org.springframework.stereotype.Service;
import pers.chanus.yuntao.commons.pojo.CustomMap;
import pers.chanus.yuntao.commons.pojo.Message;
import pers.chanus.yuntao.manager.common.CacheData;
import pers.chanus.yuntao.manager.mapper.ParamMapper;
import pers.chanus.yuntao.manager.model.Param;
import pers.chanus.yuntao.manager.service.ParamService;
import pers.chanus.yuntao.server.service.impl.BaseServiceImpl;
import pers.chanus.yuntao.util.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * 系统参数配置管理接口实现
 *
 * @author Chanus
 * @date 2018-09-06 17:50:17
 * @since 0.0.1
 */
@Service
public class ParamServiceImpl extends BaseServiceImpl<ParamMapper, Param> implements ParamService {
    @Override
    public Message insert(Param t) {
        int count = baseMapper.count(CustomMap.get().putNext("paramCode", t.getParamCode()));
        if (count > 0)
            return Message.fail("当前参数代码已存在");

        Integer priority = baseMapper.getMaxPriority();
        t.setPriority(priority == null ? 1 : (priority + 1));

        return super.insert(t);
    }

    @Override
    public Message priority(Map<String, Object> params) {
        baseMapper.priority(params);
        return Message.success("调整优先级成功");
    }

    @Override
    public Message reloadParam() {
        CacheData.SYSTEM_PARAMS_MAP.clear();
        List<Param> params = baseMapper.listValidParam();
        if (!CollectionUtils.isEmpty(params))
            params.forEach(param -> CacheData.SYSTEM_PARAMS_MAP.put(param.getParamCode(), param.getParamData()));

        return Message.success("系统参数重载成功");
    }

}
