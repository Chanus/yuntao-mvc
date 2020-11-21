package com.chanus.yuntao.mvc.manager.service;

import com.chanus.yuntao.mvc.manager.model.ScheduleJob;
import com.chanus.yuntao.mvc.framework.service.BaseService;
import com.chanus.yuntao.utils.core.lang.Message;

/**
 * 定时任务管理接口
 *
 * @author Chanus
 * @date 2020-04-13 23:46:30
 * @since 0.1.7
 */
public interface ScheduleJobService extends BaseService<ScheduleJob> {
    /**
     * 启动定时任务，只能启动已经关闭的定时任务
     *
     * @param id 定时任务主键ID
     * @return
     * @since 0.1.7
     */
    Message start(Integer id);

    /**
     * 暂停定时任务，只能暂停已启动的定时任务
     *
     * @param id 定时任务主键ID
     * @return
     * @since 0.1.7
     */
    Message pause(Integer id);

    /**
     * 恢复定时任务，只能恢复暂停的定时任务
     *
     * @param id 定时任务主键ID
     * @return
     * @since 0.1.7
     */
    Message resume(Integer id);

    /**
     * 关闭定时任务
     *
     * @param id 定时任务主键ID
     * @return
     * @since 0.1.7
     */
    Message stop(Integer id);

    /**
     * 立即执行定时任务，定时任务需要先启动
     *
     * @param id 定时任务主键ID
     * @return
     * @since 0.1.7
     */
    Message trigger(Integer id);

    /**
     * 启动所有定时任务，只启动状态为已启动的定时任务
     *
     * @return
     * @since 0.1.7
     */
    Message startAll();
}
