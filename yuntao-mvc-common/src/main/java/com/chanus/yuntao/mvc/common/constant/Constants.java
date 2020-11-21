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
package com.chanus.yuntao.mvc.common.constant;

import java.math.BigDecimal;

/**
 * 系统常量配置
 *
 * @author Chanus
 * @date 2018-08-31 22:22:27
 * @since 0.0.1
 */
public class Constants {
    /**
     * 状态：否
     */
    public final static String STATUS_NO = "0";
    /**
     * 状态：是
     */
    public final static String STATUS_YES = "1";

    /**
     * 定时任务状态：停止
     */
    public final static String STATUS_JOB_STOP = "0";
    /**
     * 定时任务状态：启动
     */
    public final static String STATUS_JOB_START = "1";
    /**
     * 定时任务状态：暂停
     */
    public final static String STATUS_JOB_PAUSE = "2";

    /**
     * 系统内置角色：超级管理员
     */
    public final static String ROLE_ADMIN_0 = "0";
    /**
     * 系统内置角色：系统操作员
     */
    public final static String ROLE_ADMIN_10 = "10";
    /**
     * 系统内置角色：子账号
     */
    public final static String ROLE_SUB_1 = "1";

    /**
     * 来源： Web
     */
    public final static String ORIGIN_WEB = "0";
    /**
     * 来源： 安卓
     */
    public final static String ORIGIN_ANDROID = "1";
    /**
     * 来源： iOS
     */
    public final static String ORIGIN_IOS = "2";
    /**
     * 来源： 手机Web
     */
    public final static String ORIGIN_PHONE_WEB = "3";

    /**
     * BigDecimal常量100
     */
    public final static BigDecimal DECIMAL_100 = new BigDecimal(100);
}
