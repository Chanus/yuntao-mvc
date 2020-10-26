/*
 * Copyright (c) 2018, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.manager.service;

import com.chanus.yuntao.utils.core.lang.Message;
import pers.chanus.yuntao.manager.model.Role;
import pers.chanus.yuntao.springmvc.service.BaseService;

/**
 * 系统角色管理接口
 *
 * @author Chanus
 * @date 2018-09-08 21:54:30
 * @since 0.0.1
 */
public interface RoleService extends BaseService<Role> {
    /**
     * 创建角色树
     *
     * @param roleCode    角色代码
     * @param hasOperator 是否可以创建操作员：0-不能，1-能
     * @return
     * @since 0.1.9
     */
    String createTree(String roleCode, String hasOperator);

    /**
     * 创建角色树
     *
     * @param roleCode 角色代码
     * @return
     * @since 0.1.9
     */
    default String createTree(String roleCode) {
        return createTree(roleCode, null);
    }

    /**
     * 角色授权
     *
     * @param roleCode     角色代码
     * @param modulePowers 模块-权限项数组
     * @return
     * @since 0.0.1
     */
    Message grantRoleModulePower(String roleCode, String[] modulePowers);

}
