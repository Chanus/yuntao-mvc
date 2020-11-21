/*
 * Copyright (c) 2020 Chanus
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.chanus.yuntao.mvc.manager.job;

import com.chanus.yuntao.utils.core.LocalDateTimeUtils;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import com.chanus.yuntao.mvc.manager.service.LogService;
import com.chanus.yuntao.mvc.framework.enums.LogTypeEnum;

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
        logService.insert("scheduler", "0", null, "com.chanus.yuntao.mvc.manager.job.TestJob.execute()", s.toString(), LogTypeEnum.SCHEDULER, "执行定时任务", (int) (end -start));
        System.out.println("执行定时任务结束......TestJob......");
    }
}
