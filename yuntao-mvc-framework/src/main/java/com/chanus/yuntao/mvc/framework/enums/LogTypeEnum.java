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
package com.chanus.yuntao.mvc.framework.enums;

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
