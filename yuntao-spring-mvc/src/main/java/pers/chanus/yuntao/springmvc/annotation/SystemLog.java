/*
 * Copyright (c) 2018, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.springmvc.annotation;

import pers.chanus.yuntao.springmvc.enums.LogTypeEnum;

import java.lang.annotation.*;

/**
 * 自定义注解，记录系统操作日志<p>
 * module       模块代码<p>
 * description  日志描述<p>
 * logType      日志类型<p>
 * ignore       是否忽略日志内容，默认false<p>
 *
 * @author Chanus
 * @date 2018-09-01 02:16:39
 * @since 0.0.1
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemLog {
    String module() default "";

    String description() default "";

    LogTypeEnum logType();

    boolean ignore() default false;
}
