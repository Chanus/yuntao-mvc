/*
 * Copyright (c), 2020-persent, Chanus and/or its affiliates. All rights reserved.
 * FileName: DataSourceEnum
 * Author:   Chanus
 * Date:     2020-06-13 13:11:28
 * Description: 多数据源枚举类
 * History:
 * <author>          <time>          <version>          <desc>
 */
package pers.chanus.yuntao.jdbc.dynamic;

/**
 * 多数据源枚举类
 *
 * @author Chanus
 * @date 2020-06-13 13:11:28
 * @since 0.2.1
 */
public enum DataSourceEnum {
    DEFAULT("defaultDataSource"),
    SECOND("secondDataSource");

    private final String value;

    DataSourceEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
