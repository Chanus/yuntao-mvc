/*
 * Copyright (c) 2018, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.server.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import pers.chanus.yuntao.commons.constant.LogTypeEnum;

/**
 * 自定义注解，记录系统操作日志<p>
 * module	模块代码<p>
 * description	日志描述<p>
 * logType	日志类型<p>
 * 
 * @author Chanus
 * @date 2018-09-01 02:16:39
 * @since 0.0.1
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemLog {
	int module() default -1;
	
	String description() default "";
	
	LogTypeEnum logType();
}
