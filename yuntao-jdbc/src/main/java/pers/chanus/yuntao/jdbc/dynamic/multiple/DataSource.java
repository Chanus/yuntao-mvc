/*
 * Copyright (c), 2020-persent, Chanus and/or its affiliates. All rights reserved.
 * FileName: DataSource
 * Author:   Chanus
 * Date:     2020-06-13 13:15:28
 * Description: 数据源切换注解，用于 aop 类中当作切入点来选择数据源
 * History:
 * <author>          <time>          <version>          <desc>
 */
package pers.chanus.yuntao.jdbc.dynamic.multiple;

import pers.chanus.yuntao.jdbc.dynamic.DataSourceEnum;

import java.lang.annotation.*;

/**
 * 数据源切换注解，用于 aop 类中当作切入点来选择数据源
 *
 * @author Chanus
 * @date 2020-06-13 13:15:28
 * @since 0.2.1
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {
    DataSourceEnum value() default DataSourceEnum.DEFAULT;
}
