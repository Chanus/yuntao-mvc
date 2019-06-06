/*
 * Copyright (c) 2018, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.commons.pojo;

import java.util.HashMap;

/**
 * 自定义Map，继承HashMap
 *
 * @author Chanus
 * @date 2018-08-31 16:58:30
 * @since 0.0.1
 */
public class CustomMap extends HashMap<String, Object> {
    private static final long serialVersionUID = 5729847671639982023L;

    /**
     * 获取CustomMap实例
     *
     * @return {@code CustomMap}实例
     * @since 0.0.1
     */
    public static CustomMap get() {
        return new CustomMap();
    }

    /**
     * 存储数据并返回当前Map对象
     *
     * @param key   键
     * @param value 值
     * @return 当前{@code CustomMap}实例
     * @since 0.0.1
     */
    public CustomMap putNext(String key, Object value) {
        this.put(key, value);
        return this;
    }
}
