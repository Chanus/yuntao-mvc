package com.chanus.yuntao.mvc.manager.service;

import com.chanus.yuntao.mvc.manager.model.Power;
import com.chanus.yuntao.mvc.framework.service.BaseService;
import com.chanus.yuntao.utils.core.lang.Message;

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
