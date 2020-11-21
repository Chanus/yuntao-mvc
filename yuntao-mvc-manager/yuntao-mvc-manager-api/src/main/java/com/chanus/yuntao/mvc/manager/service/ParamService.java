package com.chanus.yuntao.mvc.manager.service;

import com.chanus.yuntao.mvc.manager.model.Param;
import com.chanus.yuntao.mvc.framework.service.BaseService;
import com.chanus.yuntao.utils.core.lang.Message;

import java.util.Map;

/**
 * 系统参数配置管理接口
 *
 * @author Chanus
 * @date 2018-09-06 17:46:23
 * @since 0.0.1
 */
public interface ParamService extends BaseService<Param> {
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
