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
package com.chanus.yuntao.mvc.jdbc.dynamic;

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
