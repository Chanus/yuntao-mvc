/*
 * Copyright (c) 2018, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.springmvc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * MyBatis基础接口
 *
 * @param <T>  实体对象
 * @author Chanus
 * @date 2018-09-01 01:01:20
 * @since 0.0.1
 */
public interface SuperMapper<T> extends BaseMapper<T> {
    /**
     * 获取记录条数
     *
     * @param params 参数集合
     * @return 记录条数
     * @since 0.0.1
     */
    int count(Map<String, Object> params);

    /**
     * 获取记录列表
     *
     * @param params 参数集合
     * @return 记录列表
     * @since 0.0.1
     */
    List<T> list(Map<String, Object> params);
}
