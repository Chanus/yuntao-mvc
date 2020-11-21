package com.chanus.yuntao.mvc.manager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chanus.yuntao.mvc.manager.mapper.ModulePowerMethodMapper;
import com.chanus.yuntao.mvc.manager.model.ModulePower;
import com.chanus.yuntao.mvc.manager.model.ModulePowerMethod;
import com.chanus.yuntao.mvc.framework.service.impl.BaseServiceImpl;
import com.chanus.yuntao.utils.core.CollectionUtils;
import com.chanus.yuntao.utils.core.lang.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.chanus.yuntao.mvc.manager.mapper.ModulePowerMapper;
import com.chanus.yuntao.mvc.manager.service.ModulePowerService;

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
