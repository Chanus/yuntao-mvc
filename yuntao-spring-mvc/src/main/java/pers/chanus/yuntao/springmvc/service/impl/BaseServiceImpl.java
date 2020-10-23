/*
 * Copyright (c) 2018, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.springmvc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chanus.yuntao.utils.core.map.CustomMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pers.chanus.yuntao.commons.pojo.PageBean;
import pers.chanus.yuntao.springmvc.mapper.SuperMapper;
import pers.chanus.yuntao.springmvc.service.BaseService;

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
    protected Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 获取记录条数
     *
     * @param params 参数集合
     * @return 记录条数
     * @since 0.0.1
     */
    @Override
    public int count(CustomMap params) {
        return getBaseMapper().count(params);
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
        return getBaseMapper().list(params);
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
        int count = getBaseMapper().count(params);
        if (count > 0) {
            int page = params.get("page") == null ? 1 : Integer.parseInt(String.valueOf(params.get("page")));
            int limit = params.get("limit") == null ? PageBean.PAGE_SIZE : Integer.parseInt(String.valueOf(params.get("limit")));
            params.putNext("start", (page - 1) * limit).putNext("limit", limit).putNext("pagination", true);
            return PageBean.pagination(count, getBaseMapper().list(params));
        }

        return new PageBean();
    }

}
