package com.chanus.yuntao.mvc.manager.service.impl;

import com.chanus.yuntao.mvc.manager.mapper.PowerMapper;
import com.chanus.yuntao.mvc.manager.model.Power;
import com.chanus.yuntao.mvc.framework.service.impl.BaseServiceImpl;
import com.chanus.yuntao.utils.core.lang.Message;
import org.springframework.stereotype.Service;
import com.chanus.yuntao.mvc.manager.service.PowerService;

import java.util.Map;

/**
 * 权限项管理接口实现
 *
 * @author Chanus
 * @date 2018-09-06 20:05:25
 * @since 0.0.1
 */
@Service
public class PowerServiceImpl extends BaseServiceImpl<PowerMapper, Power> implements PowerService {
    @Override
    public Message insert(Power t) {
        Integer priority = getBaseMapper().getMaxPriority();
        t.setPriority(priority == null ? 1 : (priority + 1));
        return super.insert(t);
    }

    @Override
    public Message priority(Map<String, Object> params) {
        getBaseMapper().priority(params);
        return Message.success("调整优先级成功");
    }

}
