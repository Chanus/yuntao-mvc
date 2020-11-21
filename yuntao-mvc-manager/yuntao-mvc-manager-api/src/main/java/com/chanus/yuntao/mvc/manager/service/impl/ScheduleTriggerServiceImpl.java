package com.chanus.yuntao.mvc.manager.service.impl;

import com.chanus.yuntao.mvc.manager.mapper.ScheduleTriggerMapper;
import com.chanus.yuntao.mvc.manager.model.ScheduleTrigger;
import com.chanus.yuntao.mvc.framework.service.impl.BaseServiceImpl;
import com.chanus.yuntao.utils.core.lang.Message;
import org.springframework.stereotype.Service;
import com.chanus.yuntao.mvc.manager.service.ScheduleTriggerService;
import com.chanus.yuntao.utils.core.LocalDateTimeUtils;

/**
 * 定时任务触发器管理接口实现
 *
 * @author Chanus
 * @date 2020-04-13 23:47:09
 * @since 0.1.7
 */
@Service
public class ScheduleTriggerServiceImpl extends BaseServiceImpl<ScheduleTriggerMapper, ScheduleTrigger> implements ScheduleTriggerService {
    @Override
    public Message insert(ScheduleTrigger scheduleTrigger) {
        String triggerStartTimeStr = scheduleTrigger.getTriggerStartTimeStr();
        String triggerEndTimeStr = scheduleTrigger.getTriggerEndTimeStr();
        scheduleTrigger.setTriggerStartTime(LocalDateTimeUtils.parseDateTime(triggerStartTimeStr));
        scheduleTrigger.setTriggerEndTime(LocalDateTimeUtils.parseDateTime(triggerEndTimeStr));

        return super.insert(scheduleTrigger);
    }

    @Override
    public Message update(ScheduleTrigger scheduleTrigger) {
        String triggerStartTimeStr = scheduleTrigger.getTriggerStartTimeStr();
        String triggerEndTimeStr = scheduleTrigger.getTriggerEndTimeStr();
        scheduleTrigger.setTriggerStartTime(LocalDateTimeUtils.parseDateTime(triggerStartTimeStr));
        scheduleTrigger.setTriggerEndTime(LocalDateTimeUtils.parseDateTime(triggerEndTimeStr));

        return super.update(scheduleTrigger);
    }
}
