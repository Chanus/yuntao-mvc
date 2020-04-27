/*
 * Copyright (c) 2018, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.server.service.impl;

import pers.chanus.yuntao.commons.pojo.CustomMap;
import pers.chanus.yuntao.commons.pojo.Message;
import pers.chanus.yuntao.commons.pojo.PageBean;
import pers.chanus.yuntao.server.mapper.BaseMapper;
import pers.chanus.yuntao.server.service.BaseService;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Service基类，实现基础的公用CRUD方法
 *
 * @param <Mapper> Mybatis实体接口
 * @param <T>      实体对象
 * @param <PK>     实体主键类型
 * @author Chanus
 * @date 2018-09-01 01:08:22
 * @since 0.0.1
 */
public abstract class BaseServiceImpl<Mapper extends BaseMapper<T, PK>, T, PK extends Serializable> implements BaseService<T, PK> {
    /**
     * 由子类注入实体Mapper
     */
    protected Mapper mapper;

    /**
     * 注入实体Mapper，由子类实现
     *
     * @param mapper 实体Mapper
     * @since 0.0.1
     */
    public abstract void setMapper(Mapper mapper);

    /**
     * 根据主键查询实体
     *
     * @param pk 主键
     * @return 实体对象
     * @since 0.0.1
     */
    @Override
    public T get(PK pk) {
        return mapper.selectByPrimaryKey(pk);
    }

    /**
     * 添加实体
     *
     * @param t 实体对象
     * @return 操作结果信息{@code Message}
     * @since 0.0.1
     */
    @Override
    public Message insert(T t) {
        mapper.insertSelective(t);
        return Message.addSuccess();
    }

    /**
     * 更新实体，过滤空字段
     *
     * @param t 实体对象
     * @return 操作结果信息{@code Message}
     * @since 0.0.1
     */
    @Override
    public Message update(T t) {
        mapper.updateByPrimaryKeySelective(t);
        return Message.updateSuccess();
    }

    /**
     * 根据主键删除实体
     *
     * @param pk 主键
     * @return 操作结果信息{@code Message}
     * @since 0.0.1
     */
    @Override
    public Message delete(PK pk) {
        mapper.deleteByPrimaryKey(Collections.singletonList(pk));
        return Message.deleteSuccess();
    }

    /**
     * 根据主键集合删除实体
     *
     * @param pks 主键集合
     * @return 操作结果信息{@code Message}
     * @since 0.0.1
     */
    @Override
    public Message delete(Collection<PK> pks) {
        mapper.deleteByPrimaryKey(pks);
        return Message.deleteSuccess();
    }

    /**
     * 获取记录条数
     *
     * @param params 参数集合
     * @return 记录条数
     * @since 0.0.1
     */
    @Override
    public int count(CustomMap params) {
        return mapper.count(params);
    }

    /**
     * 获取记录列表
     *
     * @param params 参数集合
     * @return 记录列表
     * @since 0.0.1
     */
    @Override
    public List<T> list(CustomMap params) {
        return mapper.list(params);
    }

    /**
     * 分页查询记录列表
     *
     * @param params 参数集合
     * @return 指定页的数据信息
     * @since 0.0.1
     */
    @Override
    public PageBean listPagination(CustomMap params) {
        int count = mapper.count(params);
        if (count > 0) {
            int page = params.get("page") == null ? 1 : Integer.parseInt(String.valueOf(params.get("page")));
            int limit = params.get("limit") == null ? PageBean.PAGE_SIZE : Integer.parseInt(String.valueOf(params.get("limit")));
            params.putNext("start", (page - 1) * limit).putNext("limit", limit).putNext("pagination", true);
            return PageBean.pagination(count, mapper.list(params));
        }

        return new PageBean();
    }

}
