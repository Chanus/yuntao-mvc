/*
 * Copyright (c) 2018, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.jdbc.rwdb;

/**
 * 本地线程设置和获取数据源信息
 *
 * @author Chanus
 * @date 2019-01-03 23:01:12
 * @since 0.0.5
 */
public final class DynamicDataSourceHolder {
    private static final ThreadLocal<DynamicDataSourceGlobal> HOLDER = new ThreadLocal<>();

    private DynamicDataSourceHolder() {

    }

    public static void putDataSource(DynamicDataSourceGlobal dataSource) {
        HOLDER.set(dataSource);
    }

    public static DynamicDataSourceGlobal getDataSource() {
        return HOLDER.get();
    }

    public static void clearDataSource() {
        HOLDER.remove();
    }
}
