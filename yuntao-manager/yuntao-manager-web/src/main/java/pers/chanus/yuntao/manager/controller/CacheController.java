/*
 * Copyright (c) 2018, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.manager.controller;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pers.chanus.yuntao.manager.common.CacheData;
import pers.chanus.yuntao.manager.job.LicenseJob;
import pers.chanus.yuntao.manager.service.DictService;
import pers.chanus.yuntao.manager.service.ParamService;
import pers.chanus.yuntao.manager.service.ScheduleJobService;
import com.chanus.yuntao.utils.extra.quartz.QuartzUtils;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;

/**
 * 缓存系统基础数据
 *
 * @author Chanus
 * @date 2018-09-06 17:39:20
 * @since 0.0.1
 */
@Component
public class CacheController {
    @Autowired
    private ParamService paramService;
    @Autowired
    private DictService dictService;
    @Autowired
    private ScheduleJobService scheduleJobService;
    @Autowired
    private ServletContext servletContext;

    /**
     * 初始化RSA密钥RSA_KEYS_QUEUE
     *
     * @since 0.0.1
     */
    @PostConstruct
    public void initRsaKeysQueue() {
        CacheData.initRsaKeysQueue();
    }

    /**
     * 初始化系统参数SYSTEM_PARAMS_MAP
     *
     * @since 0.0.1
     */
    @PostConstruct
    public void initSysParamsMap() {
        paramService.reloadParam();
    }

    /**
     * 初始化系统字典数据SYSTEM_DICT_MAP，SYSTEM_DICT_ITEM_MAP
     *
     * @since 0.1.1
     */
    @PostConstruct
    public void initSysDictMap() {
        dictService.reloadDict();
    }

    /**
     * 启动授权鉴定定时任务，定时任务每天0点执行一次
     *
     * @since 0.1.8
     */
    @PostConstruct
    public void startLicenseJob() {
        try {
            // 创建调度器Scheduler
            Scheduler scheduler = QuartzUtils.getScheduler();
            JobDetail jobDetail = QuartzUtils.getJobDetailWithDurably(LicenseJob.class, "licenseJob", "yuntao_job");
            scheduler.addJob(jobDetail, true);
            Trigger trigger = QuartzUtils.getCronTrigger("licenseJobTrigger", "yuntao_trigger", "0 0 0 * * ?", jobDetail);
            scheduler.scheduleJob(trigger);
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 启动定时任务
     *
     * @since 0.1.7
     */
    @PostConstruct
    public void startScheduleJob() {
        scheduleJobService.startAll();
    }
}
