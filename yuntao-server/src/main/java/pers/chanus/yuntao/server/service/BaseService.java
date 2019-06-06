/*
 * Copyright (c) 2018, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.server.service;

import pers.chanus.yuntao.commons.pojo.CustomMap;
import pers.chanus.yuntao.commons.pojo.Message;
import pers.chanus.yuntao.commons.pojo.PageBean;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Service超接口
 *
 * @param <T>  实体对象
 * @param <PK> 实体主键类型
 * @author Chanus
 * @date 2018-09-01 01:04:21
 * @since 0.0.1
 */
public interface BaseService<T, PK extends Serializable> {
    /**
     * 根据主键查询实体
     *
     * @param pk 主键
     * @return 实体对象
     * @since 0.0.1
     */
    T get(PK pk);

    /**
     * 添加实体
     *
     * @param t 实体对象
     * @return 操作结果信息{@code Message}
     * @since 0.0.1
     */
    Message insert(T t);

    /**
     * 更新实体，过滤空字段
     *
     * @param t 实体对象
     * @return 操作结果信息{@code Message}
     * @since 0.0.1
     */
    Message update(T t);

    /**
     * 根据主键删除实体
     *
     * @param pk 主键
     * @return 操作结果信息{@code Message}
     * @since 0.0.1
     */
    Message delete(PK pk);

    /**
     * 根据主键集合批量删除实体
     *
     * @param pks 主键集合
     * @return 操作结果信息{@code Message}
     * @since 0.0.1
     */
    Message delete(Collection<PK> pks);

    /**
     * 获取记录条数
     *
     * @param params 参数集合
     * @return 记录条数
     * @since 0.0.1
     */
    int count(CustomMap params);

    /**
     * 获取记录列表
     *
     * @param params 参数集合
     * @return 记录列表
     * @since 0.0.1
     */
    List<T> list(CustomMap params);

    /**
     * 分页查询记录列表
     *
     * @param params 参数集合
     * @return 指定页的数据信息
     * @since 0.0.1
     */
    PageBean listPagination(CustomMap params);
}
