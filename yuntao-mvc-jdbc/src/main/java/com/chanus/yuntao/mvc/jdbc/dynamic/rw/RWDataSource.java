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
package com.chanus.yuntao.mvc.jdbc.dynamic.rw;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import com.chanus.yuntao.mvc.jdbc.dynamic.DataSourceEnum;
import com.chanus.yuntao.mvc.jdbc.dynamic.DynamicDataSourceHolder;

import java.util.HashMap;
import java.util.Map;

/**
 * 动态数据源实现读写分离
 *
 * @author Chanus
 * @date 2020-06-13 16:43:33
 * @since 0.2.1
 */
public class RWDataSource extends AbstractRoutingDataSource {
    // 写数据源
    private Object defaultDataSource;
    // 读数据源
    private Object secondDataSource;

    @Override
    public void afterPropertiesSet() {
        if (this.defaultDataSource == null) {
            throw new IllegalArgumentException("Property 'defaultDataSource' is required");
        }
        setDefaultTargetDataSource(defaultDataSource);
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceEnum.DEFAULT.getValue(), defaultDataSource);
        if (secondDataSource != null) {
            targetDataSources.put(DataSourceEnum.SECOND.getValue(), secondDataSource);
        }
        setTargetDataSources(targetDataSources);
        super.afterPropertiesSet();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        String dataSource = DynamicDataSourceHolder.getDataSource();

        if (DataSourceEnum.SECOND.getValue().equals(dataSource))
            return DataSourceEnum.SECOND.getValue();

        return DataSourceEnum.DEFAULT.getValue();
    }

    public Object getDefaultDataSource() {
        return defaultDataSource;
    }

    public void setDefaultDataSource(Object defaultDataSource) {
        this.defaultDataSource = defaultDataSource;
    }

    public Object getSecondDataSource() {
        return secondDataSource;
    }

    public void setSecondDataSource(Object secondDataSource) {
        this.secondDataSource = secondDataSource;
    }
}
