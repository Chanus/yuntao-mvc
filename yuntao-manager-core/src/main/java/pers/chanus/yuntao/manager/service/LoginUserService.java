/*
 * Copyright (c) 2018, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.manager.service;

import pers.chanus.yuntao.commons.pojo.Message;

/**
 * 登录用户接口
 * 
 * @author Chanus
 * @date 2018-09-02 01:15:21
 * @since 0.0.1
 */
public interface LoginUserService {
	/**
	 * 用户登录
	 * 
	 * @param loginNo	登录账号
	 * @param password	登录密码
	 * @param loginIp	登录IP
	 * @return
	 * @since 0.0.1
	 */
	Message login(String loginNo, String password, String loginIp);
}
