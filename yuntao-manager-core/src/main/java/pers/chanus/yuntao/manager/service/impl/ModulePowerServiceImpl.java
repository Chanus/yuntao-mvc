/*
 * Copyright (c) 2018, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.chanus.yuntao.commons.pojo.CustomMap;
import pers.chanus.yuntao.commons.pojo.Message;
import pers.chanus.yuntao.commons.pojo.PageBean;
import pers.chanus.yuntao.manager.mapper.ModulePowerMapper;
import pers.chanus.yuntao.manager.mapper.ModulePowerMethodMapper;
import pers.chanus.yuntao.manager.model.ModulePower;
import pers.chanus.yuntao.manager.model.ModulePowerMethod;
import pers.chanus.yuntao.manager.service.ModulePowerService;
import pers.chanus.yuntao.server.service.impl.BaseServiceImpl;

import java.util.Collection;

/**
 * 模块权限项接口实现
 *
 * @author Chanus
 * @date 2018-09-08 20:17:56
 * @since 0.0.1
 */
@Service
public class ModulePowerServiceImpl extends BaseServiceImpl<ModulePowerMapper, ModulePower, Integer> implements ModulePowerService {
    @Autowired
    private ModulePowerMethodMapper modulePowerMethodMapper;

    @Autowired
    public void setMapper(ModulePowerMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public PageBean listMethodPagination(CustomMap params) {
        return new PageBean(0, modulePowerMethodMapper.list(params));
    }

    @Override
    public Message insertMethod(ModulePowerMethod modulePowerMethod) {
        modulePowerMethodMapper.insertSelective(modulePowerMethod);
        return Message.success("添加成功");
    }

    @Override
    public Message updateMethod(ModulePowerMethod modulePowerMethod) {
        modulePowerMethodMapper.updateByPrimaryKeySelective(modulePowerMethod);
        return Message.success("修改成功");
    }

    @Override
    public Message deleteMethod(Collection<Integer> ids) {
        modulePowerMethodMapper.deleteByPrimaryKey(ids);
        return Message.success("删除成功");
    }

}
