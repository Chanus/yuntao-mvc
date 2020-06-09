/*
 * Copyright (c) 2018, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import pers.chanus.yuntao.commons.pojo.CustomMap;
import pers.chanus.yuntao.commons.pojo.Message;
import pers.chanus.yuntao.commons.pojo.PageBean;
import pers.chanus.yuntao.server.mapper.SuperMapper;
import pers.chanus.yuntao.server.service.BaseService;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Service基类，实现基础的公用CRUD方法
 *
 * @param <Mapper> Mybatis实体接口
 * @param <T>      实体对象
 * @author Chanus
 * @date 2018-09-01 01:08:22
 * @since 0.0.1
 */
public abstract class BaseServiceImpl<Mapper extends SuperMapper<T>, T> extends ServiceImpl<Mapper, T> implements BaseService<T> {
    /**
     * 根据主键查询实体
     *
     * @param pk 主键
     * @return 实体对象
     * @since 0.0.1
     */
    @Override
    public T get(Serializable pk) {
        return baseMapper.selectById(pk);
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
        baseMapper.insert(t);
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
        baseMapper.updateById(t);
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
    public Message delete(Serializable pk) {
        baseMapper.deleteById(pk);
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
    public Message delete(Collection<Serializable> pks) {
        baseMapper.deleteBatchIds(pks);
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
        return baseMapper.count(params);
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
        return baseMapper.list(params);
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
        int count = baseMapper.count(params);
        if (count > 0) {
            int page = params.get("page") == null ? 1 : Integer.parseInt(String.valueOf(params.get("page")));
            int limit = params.get("limit") == null ? PageBean.PAGE_SIZE : Integer.parseInt(String.valueOf(params.get("limit")));
            params.putNext("start", (page - 1) * limit).putNext("limit", limit).putNext("pagination", true);
            return PageBean.pagination(count, baseMapper.list(params));
        }

        return new PageBean();
    }

}
