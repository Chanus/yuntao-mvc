/*
 * Copyright (c) 2018, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.manager.service;

import pers.chanus.yuntao.commons.pojo.Message;
import pers.chanus.yuntao.manager.model.Param;
import pers.chanus.yuntao.server.service.BaseService;

import java.util.Map;

/**
 * 系统参数配置管理接口
 *
 * @author Chanus
 * @date 2018-09-06 17:46:23
 * @since 0.0.1
 */
public interface ParamService extends BaseService<Param, Integer> {
    /**
     * 调整系统参数优先级
     *
     * @param params <priority> 当前系统参数的优先级
     *               <direction>    up-提升优先级，down-降低优先级，top-置顶优先级
     * @return
     * @since 0.0.1
     */
    Message priority(Map<String, Object> params);

    /**
     * 重载有效的系统参数
     *
     * @return
     * @since 0.0.1
     */
    Message reloadParam();
}
