/*
 * Copyright (c) 2018, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.manager.service;

import pers.chanus.yuntao.commons.pojo.Message;
import pers.chanus.yuntao.manager.model.ModulePower;
import pers.chanus.yuntao.manager.model.ModulePowerMethod;
import pers.chanus.yuntao.server.service.BaseService;

import java.util.Collection;
import java.util.List;

/**
 * 模块权限项接口
 *
 * @author Chanus
 * @date 2018-09-08 20:14:11
 * @since 0.0.1
 */
public interface ModulePowerService extends BaseService<ModulePower> {
    /**
     * 获取模块权限项方法列表
     *
     * @param mpId 模块权限项主键
     * @return
     * @since 0.0.1
     */
    List<ModulePowerMethod> listMethod(Integer mpId);

    /**
     * 添加模块权限项方法
     *
     * @param modulePowerMethod
     * @return
     * @since 0.0.1
     */
    Message insertMethod(ModulePowerMethod modulePowerMethod);

    /**
     * 修改模块权限项方法
     *
     * @param modulePowerMethod
     * @return
     * @since 0.0.1
     */
    Message updateMethod(ModulePowerMethod modulePowerMethod);

    /**
     * 删除模块权限项方法
     *
     * @param ids
     * @return
     * @since 0.0.1
     */
    Message deleteMethod(Collection<Integer> ids);
}
