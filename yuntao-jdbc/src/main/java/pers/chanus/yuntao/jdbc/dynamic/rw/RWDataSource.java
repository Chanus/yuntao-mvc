package pers.chanus.yuntao.jdbc.dynamic.rw;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import pers.chanus.yuntao.jdbc.dynamic.DataSourceEnum;
import pers.chanus.yuntao.jdbc.dynamic.DynamicDataSourceHolder;

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
