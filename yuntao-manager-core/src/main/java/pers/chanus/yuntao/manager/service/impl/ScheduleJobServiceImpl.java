package pers.chanus.yuntao.manager.service.impl;

import org.quartz.*;
import org.springframework.stereotype.Service;
import pers.chanus.yuntao.commons.constant.ConfigConsts;
import pers.chanus.yuntao.commons.pojo.Message;
import pers.chanus.yuntao.manager.mapper.ScheduleJobMapper;
import pers.chanus.yuntao.manager.model.ScheduleJob;
import pers.chanus.yuntao.manager.model.ScheduleTrigger;
import pers.chanus.yuntao.manager.service.ScheduleJobService;
import pers.chanus.yuntao.server.service.impl.BaseServiceImpl;
import pers.chanus.yuntao.util.CollectionUtils;
import pers.chanus.yuntao.util.QuartzUtils;
import pers.chanus.yuntao.util.StringUtils;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 定时任务管理接口实现
 *
 * @author Chanus
 * @date 2020-04-13 23:46:30
 * @since 0.1.7
 */
@Service
public class ScheduleJobServiceImpl extends BaseServiceImpl<ScheduleJobMapper, ScheduleJob> implements ScheduleJobService {
    @Override
    public Message update(ScheduleJob scheduleJob) {
        // 定时任务停止后才能编辑
        String validStatus = baseMapper.getValidStatus(scheduleJob.getId());
        if (!ConfigConsts.STATUS_JOB_STOP.equals(validStatus))
            return Message.fail("请先停止定时任务");

        return super.update(scheduleJob);
    }

    @Override
    public Message delete(Serializable id) {
        // 先停止定时任务
        this.stop((Integer) id);

        return super.delete(id);
    }

    @Override
    public Message delete(Collection<Serializable> ids) {
        // 先停止定时任务
        for (Serializable id : ids) {
            stop((Integer) id);
        }

        return super.delete(ids);
    }

    @Override
    public Message start(Integer id) {
        ScheduleJob scheduleJob = baseMapper.getScheduleJob(id);
        // 定时任务状态为停止时才可以启动
        if (!ConfigConsts.STATUS_JOB_STOP.equals(scheduleJob.getValidStatus()))
            return Message.fail("定时任务未停止");
        // 定时任务未绑定有效的触发器，不能启动
        List<ScheduleTrigger> scheduleTriggers = scheduleJob.getScheduleTriggers();
        if (CollectionUtils.isEmpty(scheduleTriggers))
            return Message.fail("定时任务未绑定有效的触发器");

        try {
            // 创建调度器Scheduler
            Scheduler scheduler = QuartzUtils.getScheduler();

            // 构建定时任务
            Class<? extends Job> clazz = (Class<? extends Job>) Class.forName(scheduleJob.getJobClassName()).newInstance().getClass();
            JobDetail jobDetail = QuartzUtils.getJobDetailWithDurably(clazz, scheduleJob.getJobName(), scheduleJob.getJobGroup());
            // 设置任务属性配置
            jobDetail.getJobDataMap().put("jobParams", scheduleJob.getJobData());
            scheduler.addJob(jobDetail, true);

            // 绑定触发器
            Trigger trigger;
            for (ScheduleTrigger scheduleTrigger : scheduleTriggers) {
                trigger = QuartzUtils.getCronTrigger(scheduleTrigger.getTriggerName(), scheduleTrigger.getTriggerGroup(), scheduleTrigger.getTriggerCron(),
                        scheduleTrigger.getTriggerStartTime(), scheduleTrigger.getTriggerEndTime(), scheduleTrigger.getPriority(), null, jobDetail);
                trigger.getJobDataMap().put("triggerParams", scheduleTrigger.getTriggerData());
                scheduler.scheduleJob(trigger);
            }

            // 启动调度器
            scheduler.start();
        } catch (SchedulerException | IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            e.printStackTrace();
            return Message.fail("启动失败");
        }
        // 启动成功后更新任务状态
        baseMapper.updateValidStatus(id, ConfigConsts.STATUS_JOB_START);

        return Message.success("启动成功");
    }

    @Override
    public Message pause(Integer id) {
        ScheduleJob scheduleJob = baseMapper.selectById(id);
        if (ConfigConsts.STATUS_JOB_STOP.equals(scheduleJob.getValidStatus()))
            return Message.fail("定时任务已停止");
        if (ConfigConsts.STATUS_JOB_PAUSE.equals(scheduleJob.getValidStatus()))
            return Message.fail("定时任务已暂停");

        QuartzUtils.pauseJob(scheduleJob.getJobName(), scheduleJob.getJobGroup());
        // 暂停成功后更新任务状态
        baseMapper.updateValidStatus(id, ConfigConsts.STATUS_JOB_PAUSE);

        return Message.success("暂停成功");
    }

    @Override
    public Message resume(Integer id) {
        ScheduleJob scheduleJob = baseMapper.selectById(id);
        if (ConfigConsts.STATUS_JOB_STOP.equals(scheduleJob.getValidStatus()))
            return Message.fail("定时任务已停止");
        if (ConfigConsts.STATUS_JOB_START.equals(scheduleJob.getValidStatus()))
            return Message.fail("定时任务已启动");

        QuartzUtils.resumeJob(scheduleJob.getJobName(), scheduleJob.getJobGroup());
        // 暂停成功后更新任务状态
        baseMapper.updateValidStatus(id, ConfigConsts.STATUS_JOB_START);

        return Message.success("恢复成功");
    }

    @Override
    public Message stop(Integer id) {
        ScheduleJob scheduleJob = baseMapper.getScheduleJob(id);
        Map<String, String> triggerMap = new HashMap<>();
        List<ScheduleTrigger> scheduleTriggers = scheduleJob.getScheduleTriggers();
        if (!CollectionUtils.isEmpty(scheduleTriggers)) {
            scheduleTriggers.forEach(o -> {
                triggerMap.put(o.getTriggerName(), o.getTriggerGroup());
            });
        }

        QuartzUtils.removeJob(scheduleJob.getJobName(), scheduleJob.getJobGroup(), triggerMap);
        // 停止成功后更新任务状态
        baseMapper.updateValidStatus(id, ConfigConsts.STATUS_JOB_STOP);

        return Message.success("停止成功");
    }

    @Override
    public Message trigger(Integer id) {
        ScheduleJob scheduleJob = baseMapper.selectById(id);
        JobDataMap jobDataMap = new JobDataMap();
        if (StringUtils.isNotBlank(scheduleJob.getJobData()))
            jobDataMap.put("jobParams", scheduleJob.getJobData());

        QuartzUtils.triggerJob(scheduleJob.getJobName(), scheduleJob.getJobGroup(), jobDataMap);

        return Message.success("执行成功");
    }

    @Override
    public Message startAll() {
        List<ScheduleJob> scheduleJobs = baseMapper.listScheduleJob();
        if (!CollectionUtils.isEmpty(scheduleJobs)) {
            try {
                // 创建调度器Scheduler
                Scheduler scheduler = QuartzUtils.getScheduler();

                Class<? extends Job> clazz;
                JobDetail jobDetail;
                Trigger trigger;
                List<ScheduleTrigger> scheduleTriggers;
                for (ScheduleJob scheduleJob : scheduleJobs) {
                    // 构建定时任务
                    clazz = (Class<? extends Job>) Class.forName(scheduleJob.getJobClassName()).newInstance().getClass();
                    jobDetail = QuartzUtils.getJobDetailWithDurably(clazz, scheduleJob.getJobName(), scheduleJob.getJobGroup());
                    // 设置任务属性配置
                    jobDetail.getJobDataMap().put("jobParams", scheduleJob.getJobData());
                    scheduler.addJob(jobDetail, true);

                    // 绑定触发器
                    scheduleTriggers = scheduleJob.getScheduleTriggers();
                    for (ScheduleTrigger scheduleTrigger : scheduleTriggers) {
                        trigger = QuartzUtils.getCronTrigger(scheduleTrigger.getTriggerName(), scheduleTrigger.getTriggerGroup(), scheduleTrigger.getTriggerCron(),
                                scheduleTrigger.getTriggerStartTime(), scheduleTrigger.getTriggerEndTime(), scheduleTrigger.getPriority(), null, jobDetail);
                        trigger.getJobDataMap().put("triggerParams", scheduleTrigger.getTriggerData());
                        scheduler.scheduleJob(trigger);
                    }
                }

                // 启动调度器
                scheduler.start();
            } catch (SchedulerException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
                return Message.success("启动失败");
            }
        }

        return Message.success("启动成功");
    }
}
