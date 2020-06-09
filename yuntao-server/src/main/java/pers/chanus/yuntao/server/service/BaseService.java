/*
 * Copyright (c) 2018, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pers.chanus.yuntao.commons.pojo.CustomMap;
import pers.chanus.yuntao.commons.pojo.Message;
import pers.chanus.yuntao.commons.pojo.PageBean;
import pers.chanus.yuntao.util.CollectionUtils;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Service超接口
 *
 * @param <T> 实体对象
 * @author Chanus
 * @date 2018-09-01 01:04:21
 * @since 0.0.1
 */
public interface BaseService<T> extends IService<T> {
    /**
     * 根据主键查询实体
     *
     * @param pk 主键
     * @return 实体对象
     * @since 0.0.1
     */
    default T get(Serializable pk) {
        return this.getById(pk);
    }

    /**
     * 添加实体
     *
     * @param t 实体对象
     * @return 操作结果信息{@code Message}
     * @since 0.0.1
     */
    default Message insert(T t) {
        boolean b = this.save(t);
        return b ? Message.addSuccess() : Message.addFail();
    }

    /**
     * 更新实体，过滤空字段
     *
     * @param t 实体对象
     * @return 操作结果信息{@code Message}
     * @since 0.0.1
     */
    default Message update(T t) {
        boolean b = this.updateById(t);
        return b ? Message.updateSuccess() : Message.updateFail();
    }

    /**
     * 根据主键删除实体
     *
     * @param pk 主键
     * @return 操作结果信息{@code Message}
     * @since 0.0.1
     */
    default Message delete(Serializable pk) {
        boolean b = this.removeById(pk);
        return b ? Message.deleteSuccess() : Message.deleteFail();
    }

    /**
     * 根据主键集合批量删除实体
     *
     * @param pks 主键集合
     * @return 操作结果信息{@code Message}
     * @since 0.0.1
     */
    default Message delete(Collection<? extends Serializable> pks) {
        boolean b = true;
        if (!CollectionUtils.isEmpty(pks))
            b = this.removeByIds(pks);
        return b ? Message.deleteSuccess() : Message.deleteFail();
    }

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
