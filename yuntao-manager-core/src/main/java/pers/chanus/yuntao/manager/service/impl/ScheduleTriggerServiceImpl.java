package pers.chanus.yuntao.manager.service.impl;

import org.springframework.stereotype.Service;
import pers.chanus.yuntao.commons.pojo.Message;
import pers.chanus.yuntao.manager.mapper.ScheduleTriggerMapper;
import pers.chanus.yuntao.manager.model.ScheduleTrigger;
import pers.chanus.yuntao.manager.service.ScheduleTriggerService;
import pers.chanus.yuntao.server.service.impl.BaseServiceImpl;
import pers.chanus.yuntao.util.DateUtils;

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
        scheduleTrigger.setTriggerStartTime(DateUtils.parseDateTime(triggerStartTimeStr));
        scheduleTrigger.setTriggerEndTime(DateUtils.parseDateTime(triggerEndTimeStr));

        return super.insert(scheduleTrigger);
    }

    @Override
    public Message update(ScheduleTrigger scheduleTrigger) {
        String triggerStartTimeStr = scheduleTrigger.getTriggerStartTimeStr();
        String triggerEndTimeStr = scheduleTrigger.getTriggerEndTimeStr();
        scheduleTrigger.setTriggerStartTime(DateUtils.parseDateTime(triggerStartTimeStr));
        scheduleTrigger.setTriggerEndTime(DateUtils.parseDateTime(triggerEndTimeStr));

        return super.update(scheduleTrigger);
    }
}
