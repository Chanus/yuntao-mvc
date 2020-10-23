/*
 * Copyright (c) 2018, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.manager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.chanus.yuntao.commons.pojo.Message;
import pers.chanus.yuntao.manager.mapper.ModulePowerMapper;
import pers.chanus.yuntao.manager.mapper.ModulePowerMethodMapper;
import pers.chanus.yuntao.manager.model.ModulePower;
import pers.chanus.yuntao.manager.model.ModulePowerMethod;
import pers.chanus.yuntao.manager.service.ModulePowerService;
import pers.chanus.yuntao.springmvc.service.impl.BaseServiceImpl;
import com.chanus.yuntao.utils.core.CollectionUtils;

import java.util.Collection;
import java.util.List;

/**
 * 模块权限项接口实现
 *
 * @author Chanus
 * @date 2018-09-08 20:17:56
 * @since 0.0.1
 */
@Service
public class ModulePowerServiceImpl extends BaseServiceImpl<ModulePowerMapper, ModulePower> implements ModulePowerService {
    @Autowired
    private ModulePowerMethodMapper modulePowerMethodMapper;

    @Override
    public List<ModulePowerMethod> listMethod(Integer mpId) {
        return modulePowerMethodMapper.selectList(new QueryWrapper<ModulePowerMethod>().lambda().eq(ModulePowerMethod::getMpId, mpId));
    }

    @Override
    public Message insertMethod(ModulePowerMethod modulePowerMethod) {
        modulePowerMethodMapper.insert(modulePowerMethod);
        return Message.addSuccess();
    }

    @Override
    public Message updateMethod(ModulePowerMethod modulePowerMethod) {
        modulePowerMethodMapper.updateById(modulePowerMethod);
        return Message.updateSuccess();
    }

    @Override
    public Message deleteMethod(Collection<Integer> ids) {
        if (CollectionUtils.isNotEmpty(ids))
            modulePowerMethodMapper.deleteBatchIds(ids);

        return Message.deleteSuccess();
    }

}
