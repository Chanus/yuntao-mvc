/*
 * Copyright (c), 2020-persent, Chanus and/or its affiliates. All rights reserved.
 * FileName: TestJob
 * Author:   Chanus
 * Date:     2020/4/13 13:17
 * Description: 定时任务测试
 * History:
 * <author>          <time>          <version>          <desc>
 */
package pers.chanus.yuntao.manager.job;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import pers.chanus.yuntao.commons.constant.LogTypeEnum;
import pers.chanus.yuntao.manager.service.LogService;
import pers.chanus.yuntao.util.LocalDateTimeUtils;

/**
 * 定时任务测试
 *
 * @author Chanus
 * @date 2020/4/13 13:17
 * @since 0.1.7
 */
@Component
public class TestJob implements Job {
    @Autowired
    private LogService logService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);// 解决无法依赖注入Bean的问题

        System.out.println("执行定时任务......TestJob......");
        long start = System.currentTimeMillis();
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        JobDataMap triggerDataMap = context.getTrigger().getJobDataMap();
        StringBuilder s = new StringBuilder();
        s.append("时间：").append(LocalDateTimeUtils.nowDateTime())
                .append(" 任务名：").append(context.getJobDetail().getKey().getName()).append(" 任务组：").append(context.getJobDetail().getKey().getGroup())
                .append(" 任务参数：").append(jobDataMap.get("jobParams"))
                .append(" 触发器名：").append(context.getTrigger().getKey().getName()).append(" 触发器组：").append(context.getTrigger().getKey().getGroup())
                .append(" 触发器参数：").append(triggerDataMap.get("triggerParams"));
        System.out.println(s.toString());
        long end = System.currentTimeMillis();
        logService.insert("scheduler", "0", null, "pers.chanus.yuntao.manager.job.TestJob.execute()", s.toString(), LogTypeEnum.SCHEDULER, "执行定时任务", (int) (end -start));
        System.out.println("执行定时任务结束......TestJob......");
    }
}
