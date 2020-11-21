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
package com.chanus.yuntao.mvc.framework.annotation;

import com.chanus.yuntao.mvc.framework.enums.LogTypeEnum;

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
public @interface Log {
    String module() default "";

    String description() default "";

    LogTypeEnum logType();

    boolean ignore() default false;
}
