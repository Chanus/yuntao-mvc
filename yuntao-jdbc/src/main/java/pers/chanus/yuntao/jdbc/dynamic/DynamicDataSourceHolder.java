/*
 * Copyright (c), 2020-persent, Chanus and/or its affiliates. All rights reserved.
 * FileName: DynamicDataSourceHolder
 * Author:   Chanus
 * Date:     2020-06-13 12:58:52
 * Description: 用于设置、获取、清空当前线程内的数据源变量
 * History:
 * <author>          <time>          <version>          <desc>
 */
package pers.chanus.yuntao.jdbc.dynamic;

/**
 * 用于设置、获取、清空当前线程内的数据源变量
 *
 * @author Chanus
 * @date 2020-06-13 12:58:52
 * @since 0.2.1
 */
public class DynamicDataSourceHolder {
    private static final ThreadLocal<String> HOLDER = new InheritableThreadLocal<>();

    /**
     * 设置数据源
     *
     * @param dataSource 数据源
     * @since 0.2.1
     */
    public static void setDataSource(String dataSource) {
        HOLDER.set(dataSource);
    }

    /**
     * 取得当前数据源
     *
     * @return 当前数据源
     * @since 0.2.1
     */
    public static String getDataSource() {
        return HOLDER.get();
    }

    /**
     * 清除上下文数据源
     *
     * @since 0.2.1
     */
    public static void clear() {
        HOLDER.remove();
    }
}
