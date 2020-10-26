/*
 * Copyright (c) 2018, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.manager.service;

import com.chanus.yuntao.utils.core.lang.Message;
import pers.chanus.yuntao.manager.model.Power;
import pers.chanus.yuntao.springmvc.service.BaseService;

import java.util.Map;

/**
 * 权限项管理接口
 *
 * @author Chanus
 * @date 2018-09-06 19:32:43
 * @since 0.0.1
 */
public interface PowerService extends BaseService<Power> {
    /**
     * 调整权限项优先级
     *
     * @param params <priority> 当前权限项的优先级
     *               <direction>    up-提升优先级，down-降低优先级，top-置顶优先级
     * @return
     * @since 0.0.1
     */
    Message priority(Map<String, Object> params);
}
