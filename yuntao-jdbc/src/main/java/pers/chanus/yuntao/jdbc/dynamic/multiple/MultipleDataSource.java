/*
 * Copyright (c), 2020-persent, Chanus and/or its affiliates. All rights reserved.
 * FileName: MultipleDataSource
 * Author:   Chanus
 * Date:     2020-06-13 13:06:06
 * Description: 动态创建及访问多数据源
 * History:
 * <author>          <time>          <version>          <desc>
 */
package pers.chanus.yuntao.jdbc.dynamic.multiple;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import pers.chanus.yuntao.jdbc.dynamic.DynamicDataSourceHolder;

/**
 * 动态创建及访问多数据源
 *
 * @author Chanus
 * @date 2020-06-13 13:06:06
 * @since 0.2.1
 */
public class MultipleDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceHolder.getDataSource();
    }
}
