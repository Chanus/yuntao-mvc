/*
 * Copyright (c) 2018, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.springmvc.enums;

/**
 * 系统日志操作类型
 *
 * @author Chanus
 * @date 2018-09-01 02:15:33
 * @since 0.0.1
 */
public enum LogTypeEnum {
    /**
     * 登录
     */
    LOGIN,
    /**
     * 注销
     */
    LOGOUT,
    /**
     * 添加
     */
    INSERT,
    /**
     * 删除
     */
    DELETE,
    /**
     * 更新
     */
    UPDATE,
    /**
     * 添加或更新
     */
    SAVE,
    /**
     * 系统异常
     */
    EXCEPTION,
    /**
     * 定时任务
     */
    SCHEDULER
}
