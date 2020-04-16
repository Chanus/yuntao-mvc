/*
 * Copyright (c), 2020-persent, Chanus and/or its affiliates. All rights reserved.
 * FileName: QuartzUtils
 * Author:   Chanus
 * Date:     2020/4/14 14:34
 * Description: 云道任务计划调度工具类
 * History:
 * <author>          <time>          <version>          <desc>
 */
package pers.chanus.yuntao.util;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;
import java.util.Map;

/**
 * Quartz任务计划调度工具类
 *
 * @author Chanus
 * @date 2020/4/14 14:34
 * @since 0.1.7
 */
public class QuartzUtils {
    /**
     * 调度器工厂
     */
    private static SchedulerFactory schedulerFactory = new StdSchedulerFactory();

    /**
     * 默认Job组名
     */
    private static String JOB_GROUP_NAME = "DEFAULT_JOB_GROUP_NAME";

    /**
     * 默认触发器组名
     */
    private static String TRIGGER_GROUP_NAME = "DEFAULT_TRIGGER_GROUP_NAME";

    /**
     * 获取调度器
     *
     * @return Scheduler
     * @throws SchedulerException Scheduler获取异常
     * @since 0.1.7
     */
    public static Scheduler getScheduler() throws SchedulerException {
        return schedulerFactory.getScheduler();
    }

    /**
     * 获取JobDetail
     *
     * @param clazz        任务类
     * @param jobName      任务名
     * @param jobGroupName 任务组名（为空使用默认）
     * @return JobDetail
     * @since 0.1.7
     */
    public static JobDetail getJobDetail(Class<? extends Job> clazz, String jobName, String jobGroupName) {
        if (StringUtils.isBlank(jobGroupName))
            jobGroupName = JOB_GROUP_NAME;

        return JobBuilder.newJob(clazz).withIdentity(jobName, jobGroupName).build();
    }

    /**
     * 获取JobDetail，带参数
     *
     * @param clazz        任务类
     * @param jobName      任务名
     * @param jobGroupName 任务组名（为空使用默认）
     * @param jobDataMap   附带参数
     * @return JobDetail
     * @since 0.1.7
     */
    public static JobDetail getJobDetail(Class<? extends Job> clazz, String jobName, String jobGroupName, JobDataMap jobDataMap) {
        if (StringUtils.isBlank(jobGroupName))
            jobGroupName = JOB_GROUP_NAME;

        if (jobDataMap != null) {
            return JobBuilder.newJob(clazz).withIdentity(jobName, jobGroupName).usingJobData(jobDataMap).build();
        } else {
            return JobBuilder.newJob(clazz).withIdentity(jobName, jobGroupName).build();
        }
    }

    /**
     * 获取JobDetail
     *
     * @param clazz        任务类
     * @param jobName      任务名
     * @param jobGroupName 任务组名（为空使用默认）
     * @return JobDetail
     * @since 0.1.7
     */
    public static JobDetail getJobDetailWithDurably(Class<? extends Job> clazz, String jobName, String jobGroupName) {
        if (StringUtils.isBlank(jobGroupName))
            jobGroupName = JOB_GROUP_NAME;

        return JobBuilder.newJob(clazz).withIdentity(jobName, jobGroupName).storeDurably().build();
    }

    /**
     * 获取JobDetail，带参数
     *
     * @param clazz        任务类
     * @param jobName      任务名
     * @param jobGroupName 任务组名（为空使用默认）
     * @param jobDataMap   附带参数
     * @return JobDetail
     * @since 0.1.7
     */
    public static JobDetail getJobDetailWithDurably(Class<? extends Job> clazz, String jobName, String jobGroupName, JobDataMap jobDataMap) {
        if (StringUtils.isBlank(jobGroupName))
            jobGroupName = JOB_GROUP_NAME;

        if (jobDataMap != null) {
            return JobBuilder.newJob(clazz).withIdentity(jobName, jobGroupName).usingJobData(jobDataMap).storeDurably().build();
        } else {
            return JobBuilder.newJob(clazz).withIdentity(jobName, jobGroupName).storeDurably().build();
        }
    }

    /**
     * 获取JobDetail
     *
     * @param clazz        任务类
     * @param jobName      任务名
     * @param jobGroupName 任务组名（为空使用默认）
     * @param jobDataMap   附带参数
     * @param isDurably    是否设置为durably
     * @return JobDetail
     * @since 0.1.7
     */
    public static JobDetail getJobDetail(Class<? extends Job> clazz, String jobName, String jobGroupName, JobDataMap jobDataMap, boolean isDurably) {
        if (StringUtils.isBlank(jobGroupName))
            jobGroupName = JOB_GROUP_NAME;

        JobBuilder jobBuilder = JobBuilder.newJob(clazz).withIdentity(jobName, jobGroupName);
        if (jobDataMap != null)
            jobBuilder.usingJobData(jobDataMap);
        if (isDurably)
            jobBuilder.storeDurably();

        return jobBuilder.build();
    }

    /**
     * 获取CronTrigger
     *
     * @param triggerName      触发器名
     * @param triggerGroupName 触发器组名（为空使用默认）
     * @param cronTime         cron表达式
     * @return CronTrigger
     * @since 0.1.7
     */
    public static CronTrigger getCronTrigger(String triggerName, String triggerGroupName, String cronTime) {
        if (StringUtils.isBlank(triggerGroupName))
            triggerGroupName = TRIGGER_GROUP_NAME;

        return TriggerBuilder.newTrigger().withIdentity(triggerName, triggerGroupName)
                .withSchedule(CronScheduleBuilder.cronSchedule(cronTime)).build();
    }

    /**
     * 获取CronTrigger，带参数
     *
     * @param triggerName      触发器名
     * @param triggerGroupName 触发器组名（为空使用默认）
     * @param cronTime         cron表达式
     * @param jobDataMap       附带参数
     * @return CronTrigger
     * @since 0.1.7
     */
    public static CronTrigger getCronTrigger(String triggerName, String triggerGroupName, String cronTime, JobDataMap jobDataMap) {
        if (StringUtils.isBlank(triggerGroupName))
            triggerGroupName = TRIGGER_GROUP_NAME;

        return TriggerBuilder.newTrigger().withIdentity(triggerName, triggerGroupName)
                .withSchedule(CronScheduleBuilder.cronSchedule(cronTime)).usingJobData(jobDataMap).build();
    }

    /**
     * 获取CronTrigger，绑定定时任务
     *
     * @param triggerName      触发器名
     * @param triggerGroupName 触发器组名（为空使用默认）
     * @param cronTime         cron表达式
     * @param jobDetail        定时任务
     * @return CronTrigger
     * @since 0.1.7
     */
    public static CronTrigger getCronTrigger(String triggerName, String triggerGroupName, String cronTime, JobDetail jobDetail) {
        if (StringUtils.isBlank(triggerGroupName))
            triggerGroupName = TRIGGER_GROUP_NAME;

        return TriggerBuilder.newTrigger().withIdentity(triggerName, triggerGroupName)
                .withSchedule(CronScheduleBuilder.cronSchedule(cronTime)).forJob(jobDetail).build();
    }

    /**
     * 获取CronTrigger，带参数，绑定定时任务
     *
     * @param triggerName      触发器名
     * @param triggerGroupName 触发器组名（为空使用默认）
     * @param cronTime         cron表达式
     * @param jobDataMap       附带参数
     * @param jobDetail        定时任务
     * @return CronTrigger
     * @since 0.1.7
     */
    public static CronTrigger getCronTrigger(String triggerName, String triggerGroupName, String cronTime, JobDataMap jobDataMap, JobDetail jobDetail) {
        if (StringUtils.isBlank(triggerGroupName))
            triggerGroupName = TRIGGER_GROUP_NAME;

        return TriggerBuilder.newTrigger().withIdentity(triggerName, triggerGroupName)
                .withSchedule(CronScheduleBuilder.cronSchedule(cronTime)).usingJobData(jobDataMap).forJob(jobDetail).build();
    }

    /**
     * 获取CronTrigger
     *
     * @param triggerName      触发器名
     * @param triggerGroupName 触发器组名（为空使用默认）
     * @param cronTime         cron表达式
     * @param startTime        开始时间
     * @param endTime          停止时间
     * @param priority         优先级
     * @param jobDataMap       附带参数
     * @param jobDetail        定时任务
     * @return CronTrigger
     * @since 0.1.7
     */
    public static CronTrigger getCronTrigger(String triggerName, String triggerGroupName, String cronTime, Date startTime, Date endTime, Integer priority, JobDataMap jobDataMap, JobDetail jobDetail) {
        if (StringUtils.isBlank(triggerGroupName))
            triggerGroupName = TRIGGER_GROUP_NAME;

        TriggerBuilder<CronTrigger> triggerBuilder = TriggerBuilder.newTrigger().withIdentity(triggerName, triggerGroupName)
                .withSchedule(CronScheduleBuilder.cronSchedule(cronTime));
        if (startTime != null)
            triggerBuilder.startAt(startTime);
        if (endTime != null)
            triggerBuilder.endAt(endTime);
        if (priority != null)
            triggerBuilder.withPriority(priority);
        if (jobDataMap != null)
            triggerBuilder.usingJobData(jobDataMap);
        if (jobDetail != null)
            triggerBuilder.forJob(jobDetail);

        return triggerBuilder.build();
    }

    /**
     * 设置JobDetail 和 CronTrigger 到 Scheduler（已获取的调度器中，无需重复调用）
     *
     * @param scheduler        调度器
     * @param clazz            任务类
     * @param jobName          任务名
     * @param jobGroupName     任务组名（为空使用默认）
     * @param triggerName      触发器名
     * @param triggerGroupName 触发器组名（为空使用默认）
     * @param cronTime         cron表达式
     * @param jobDataMap       附带参数
     * @return 设置成功与否
     * @throws SchedulerException 调度器异常
     * @since 0.1.7
     */
    public static void setJobDetailAndCronTriggerInScheduler(Scheduler scheduler, Class<? extends Job> clazz, String jobName, String jobGroupName, String triggerName, String triggerGroupName,
                                                             String cronTime, JobDataMap jobDataMap) throws SchedulerException {
        if (StringUtils.isBlank(jobGroupName))
            jobGroupName = JOB_GROUP_NAME;
        if (StringUtils.isBlank(triggerGroupName))
            triggerGroupName = TRIGGER_GROUP_NAME;

        JobDetail jobDetail = getJobDetail(clazz, jobName, jobGroupName, jobDataMap);
        CronTrigger trigger = getCronTrigger(triggerName, triggerGroupName, cronTime);
        scheduler.scheduleJob(jobDetail, trigger);
        if (!scheduler.isShutdown()) {
            scheduler.start();
        }
    }

    /**
     * 使用CronTrigger类型添加定时任务
     *
     * @param scheduler        调度器
     * @param clazz            任务类
     * @param jobName          任务名
     * @param jobGroupName     任务组名（为空使用默认）
     * @param triggerName      触发器名
     * @param triggerGroupName 触发器组名（为空使用默认）
     * @param cronTime         cron表达式
     * @param jobDataMap       附带参数
     * @return {@code true} 添加成功；{@code false} 添加失败
     * @since 0.1.7
     */
    private static void addJobByCronTrigger(Scheduler scheduler, Class<? extends Job> clazz, String jobName, String jobGroupName,
                                            String triggerName, String triggerGroupName, String cronTime, JobDataMap jobDataMap) {
        try {
            setJobDetailAndCronTriggerInScheduler(scheduler, clazz, jobName, jobGroupName, triggerName, triggerGroupName, cronTime, jobDataMap);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加定时任务
     *
     * @param clazz            任务类
     * @param jobName          任务名
     * @param jobGroupName     任务组名（为空使用默认）
     * @param triggerName      触发器名
     * @param triggerGroupName 触发器组名（为空使用默认）
     * @param cronTime         cron表达式
     * @param jobDataMap       附带参数
     * @return {@code true} 添加成功；{@code false} 添加失败
     * @since 0.1.7
     */
    public static void addJobByCronTrigger(Class<? extends Job> clazz, String jobName, String jobGroupName,
                                           String triggerName, String triggerGroupName, String cronTime, JobDataMap jobDataMap) {
        try {
            Scheduler scheduler = getScheduler();
            setJobDetailAndCronTriggerInScheduler(scheduler, clazz, jobName, jobGroupName, triggerName, triggerGroupName, cronTime, jobDataMap);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改定时任务时间
     *
     * @param jobName          任务名
     * @param jobGroupName     任务组名（为空使用默认）
     * @param triggerName      触发器名
     * @param triggerGroupName 触发器组名（为空使用默认）
     * @param cronTime         cron表达式
     * @param jobDataMap       附带参数
     * @return {@code true} 修改成功；{@code false} 修改失败
     * @since 0.1.7
     */
    public static void modifyJobTime(String jobName, String jobGroupName,
                                     String triggerName, String triggerGroupName, String cronTime, JobDataMap jobDataMap) {
        try {
            Scheduler scheduler = getScheduler();
            if (StringUtils.isBlank(triggerGroupName))
                triggerGroupName = TRIGGER_GROUP_NAME;

            TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            if (trigger == null)
                return;

            String oldTime = trigger.getCronExpression();
            if (!oldTime.equalsIgnoreCase(cronTime)) {
                if (StringUtils.isBlank(jobGroupName))
                    jobGroupName = JOB_GROUP_NAME;

                JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
                JobDetail jobDetail = scheduler.getJobDetail(jobKey);
                Class<? extends Job> jobClass = jobDetail.getJobClass();
                removeJob(scheduler, jobKey, triggerKey);
                addJobByCronTrigger(scheduler, jobClass, jobName, jobGroupName, triggerName, triggerGroupName, cronTime, jobDataMap);
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改定时任务JobDataMap
     *
     * @param clazz            任务类
     * @param jobName          任务名
     * @param jobGroupName     任务组名（为空使用默认）
     * @param triggerName      触发器名
     * @param triggerGroupName 触发器组名（为空使用默认）
     * @param cronTime         cron表达式
     * @param jobDataMap       附带参数
     * @return {@code true} 修改成功；{@code false} 修改失败
     * @since 0.1.7
     */
    public static void modifyJobDateMap(Class<? extends Job> clazz, String jobName, String jobGroupName, String triggerName, String triggerGroupName, String cronTime, JobDataMap jobDataMap) {
        if (StringUtils.isBlank(jobGroupName))
            jobGroupName = JOB_GROUP_NAME;
        if (StringUtils.isBlank(triggerGroupName))
            triggerGroupName = TRIGGER_GROUP_NAME;

        TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);
        try {
            Scheduler scheduler = getScheduler();
            JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
            JobDetail jobDetail = scheduler.getJobDetail(jobKey);
            if (jobDetail == null)
                return;

            JobDataMap oldJobDataMap = jobDetail.getJobDataMap();
            if (!oldJobDataMap.equals(jobDataMap)) {
                Class<? extends Job> jobClass = jobDetail.getJobClass();
                removeJob(scheduler, jobKey, triggerKey);
                addJobByCronTrigger(scheduler, jobClass, jobName, jobGroupName, triggerName, triggerGroupName, cronTime, jobDataMap);
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 从调度器中移除Job
     *
     * @param scheduler  调度器
     * @param jobKey     任务key（名，组）
     * @param triggerKey 触发器key（名，组）
     * @since 0.1.7
     */
    public static void removeJob(Scheduler scheduler, JobKey jobKey, TriggerKey triggerKey) {
        try {
            // 停止触发器
            scheduler.pauseTrigger(triggerKey);
            // 移除触发器
            scheduler.unscheduleJob(triggerKey);
            // 删除任务
            scheduler.deleteJob(jobKey);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 从调度器中移除Job
     *
     * @param jobName          任务名
     * @param jobGroupName     任务组名（为空使用默认）
     * @param triggerName      触发器名
     * @param triggerGroupName 触发器组名（为空使用默认）
     * @since 0.1.7
     */
    public static void removeJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName) {
        try {
            if (StringUtils.isBlank(jobGroupName))
                jobGroupName = JOB_GROUP_NAME;
            if (StringUtils.isBlank(triggerGroupName))
                triggerGroupName = TRIGGER_GROUP_NAME;

            Scheduler scheduler = getScheduler();
            JobKey jobKey = JobKey.jobKey(jobName, jobGroupName); // 通过任务名和组名获取JobKey
            // 定时任务不存在
            if (!isJobKey(scheduler, jobKey))
                return;

            TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName); // 通过触发器名和组名获取TriggerKey
            scheduler.pauseTrigger(triggerKey); // 停止触发器
            scheduler.unscheduleJob(triggerKey);// 移除触发器
            scheduler.deleteJob(jobKey); // 删除任务
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 从调度器中移除Job，绑定多个触发器
     *
     * @param jobName      任务名
     * @param jobGroupName 任务组名（为空使用默认）
     * @param triggerMap   触发器集合
     * @since 0.1.7
     */
    public static void removeJob(String jobName, String jobGroupName, Map<String, String> triggerMap) {
        try {
            if (StringUtils.isBlank(jobGroupName))
                jobGroupName = JOB_GROUP_NAME;

            Scheduler scheduler = getScheduler();
            JobKey jobKey = JobKey.jobKey(jobName, jobGroupName); // 通过任务名和组名获取JobKey
            // 定时任务不存在
            if (!isJobKey(scheduler, jobKey))
                return;

            if (!CollectionUtils.isEmpty(triggerMap)) {// 存在触发器
                TriggerKey triggerKey = null;
                for (String triggerName : triggerMap.keySet()) {
                    String triggerGroupName = StringUtils.isBlank(triggerMap.get(triggerName)) ? TRIGGER_GROUP_NAME : triggerMap.get(triggerName);
                    triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName); // 通过触发器名和组名获取TriggerKey
                    scheduler.pauseTrigger(triggerKey); // 停止触发器
                    scheduler.unscheduleJob(triggerKey);// 移除触发器
                }
            }
            scheduler.deleteJob(jobKey); // 删除任务
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 启动所有定时任务
     *
     * @since 0.1.7
     */
    public static void startJobs() {
        try {
            Scheduler scheduler = getScheduler();
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 停止所有定时任务
     *
     * @since 0.1.7
     */
    public static void shutdownJobs() {
        try {
            Scheduler scheduler = getScheduler();
            if (!scheduler.isShutdown())
                scheduler.shutdown();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 暂停定时任务
     *
     * @param jobName      任务名
     * @param jobGroupName 任务组名（空位默认）
     * @return {@code true} 停止成功；{@code false} 停止失败
     * @since 0.1.7
     */
    public static boolean pauseJob(String jobName, String jobGroupName) {
        if (StringUtils.isBlank(jobGroupName))
            jobGroupName = JOB_GROUP_NAME;
        try {
            Scheduler scheduler = getScheduler();
            scheduler.pauseJob(JobKey.jobKey(jobName, jobGroupName));
            return true;
        } catch (SchedulerException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 暂停所有定时任务
     *
     * @return {@code true} 停止成功；{@code false} 停止失败
     * @since 0.1.7
     */
    public static boolean pauseAllJob() {
        try {
            Scheduler scheduler = getScheduler();
            scheduler.pauseAll();
            return true;
        } catch (SchedulerException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 恢复定时任务
     *
     * @param jobName      任务名
     * @param jobGroupName 任务组名（空位默认）
     * @return {@code true} 恢复成功；{@code false} 恢复失败
     * @since 0.1.7
     */
    public static boolean resumeJob(String jobName, String jobGroupName) {
        if (StringUtils.isBlank(jobGroupName))
            jobGroupName = JOB_GROUP_NAME;
        try {
            Scheduler scheduler = getScheduler();
            scheduler.resumeJob(JobKey.jobKey(jobName, jobGroupName));
            return true;
        } catch (SchedulerException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 恢复所有定时任务
     *
     * @return {@code true} 恢复成功；{@code false} 恢复失败
     * @since 0.1.7
     */
    public static boolean resumeAllJob() {
        try {
            Scheduler scheduler = getScheduler();
            scheduler.resumeAll();
            return true;
        } catch (SchedulerException e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 触发定时任务
     *
     * @param jobName      任务名
     * @param jobGroupName 任务组名（空位默认）
     * @since 0.1.7
     */
    public static void triggerJob(String jobName, String jobGroupName) {
        if (StringUtils.isBlank(jobGroupName))
            jobGroupName = JOB_GROUP_NAME;
        try {
            Scheduler scheduler = getScheduler();
            scheduler.triggerJob(JobKey.jobKey(jobName, jobGroupName));
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 触发定时任务，带参数
     *
     * @param jobName      任务名
     * @param jobGroupName 任务组名（空位默认）
     * @param jobDataMap   附带参数
     * @since 0.1.7
     */
    public static void triggerJob(String jobName, String jobGroupName, JobDataMap jobDataMap) {
        if (StringUtils.isBlank(jobGroupName))
            jobGroupName = JOB_GROUP_NAME;
        try {
            Scheduler scheduler = getScheduler();
            scheduler.triggerJob(JobKey.jobKey(jobName, jobGroupName), jobDataMap);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断是否存在JobKey
     *
     * @param scheduler 任务调度器
     * @param jobKey    任务key（名，组）
     * @return {@code true} 存在JobKey；{@code false} 不存在JobKey
     * @since 0.1.7
     */
    public static boolean isJobKey(Scheduler scheduler, JobKey jobKey) {
        try {
            return !(scheduler.getJobDetail(jobKey) == null);
        } catch (SchedulerException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 判断是否存在JobKey
     *
     * @param scheduler    任务调度器
     * @param jobName      任务名
     * @param jobGroupName 任务组名
     * @return {@code true} 存在JobKey；{@code false} 不存在JobKey
     * @since 0.1.7
     */
    public static boolean isJobKey(Scheduler scheduler, String jobName, String jobGroupName) {
        if (StringUtils.isBlank(jobGroupName))
            jobGroupName = JOB_GROUP_NAME;
        JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
        try {
            return !(scheduler.getJobDetail(jobKey) == null);
        } catch (SchedulerException e) {
            e.printStackTrace();
            return false;
        }
    }
}
