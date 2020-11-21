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
package com.chanus.yuntao.mvc.jdbc.dynamic.multiple;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import com.chanus.yuntao.mvc.jdbc.dynamic.DynamicDataSourceHolder;

import java.lang.reflect.Method;

/**
 * 动态切换数据源切面类，解析注解上的值来切换数据源
 *
 * @author Chanus
 * @date 2020-06-13 13:19:47
 * @since 0.2.1
 */
@Aspect
@Component
@Order(-1)
public class DataSourceAspect {
    /**
     * 切点，@within 匹配类上的注解，@annotation 匹配方法上的注解
     */
    @Pointcut("@within(com.chanus.yuntao.mvc.jdbc.dynamic.multiple.DataSource) || @annotation(com.chanus.yuntao.mvc.jdbc.dynamic.multiple.DataSource)")
    public void pointCut() {
    }

    /**
     * 执行前切换数据源为注解的数据源
     *
     * @param joinPoint
     */
    @Before("pointCut()")
    public void doBefore(JoinPoint joinPoint) {
        try {
            Class<?> clazz = joinPoint.getTarget().getClass();
            String methodName = joinPoint.getSignature().getName();
            Method[] methods = clazz.getMethods();
            DataSource clazzDataSource = clazz.getAnnotation(DataSource.class);
            DataSource dataSource = null;
            for (Method method : methods) {
                if (methodName.equals(method.getName())) {
                    // 获取方法注解的数据源
                    dataSource = method.getAnnotation(DataSource.class);
                    if (dataSource == null) {// 如果方法注解的数据源为空，则使用类注解的数据源
                        dataSource = clazzDataSource;
                    }
                    break;
                }
            }

            if (dataSource != null)
                DynamicDataSourceHolder.setDataSource(dataSource.value().getValue());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 执行后清除上下文数据源，切换回默认数据源
     */
    @After("pointCut()")
    public void doAfter() {
        DynamicDataSourceHolder.clear();
    }
}
