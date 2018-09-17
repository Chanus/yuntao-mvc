/*
 * Copyright (c) 2018, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.manager.service;

import java.util.Map;

import pers.chanus.yuntao.commons.pojo.Message;
import pers.chanus.yuntao.manager.model.Role;
import pers.chanus.yuntao.server.service.BaseService;

/**
 * 系统角色管理接口
 * 
 * @author Chanus
 * @date 2018-09-08 21:54:30
 * @since 0.0.1
 */
public interface RoleService extends BaseService<Role, Integer> {
	/**
	 * 创建角色树
	 * 
	 * @return
	 * @since 0.0.1
	 */
	String createTree(Map<String, Object> params);
	
	/**
	 * 角色授权
	 * 
	 * @param roleId	角色代码
	 * @param modulePowers	模块-权限项数组
	 * @return
	 * @since 0.0.1
	 */
	Message grantRoleModulePower(String roleId, String[] modulePowers);
}
