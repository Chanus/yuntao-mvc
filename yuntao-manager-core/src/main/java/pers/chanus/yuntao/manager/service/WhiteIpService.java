/*
 * Copyright (c) 2018, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.manager.service;

import pers.chanus.yuntao.manager.model.WhiteIp;
import pers.chanus.yuntao.server.service.BaseService;

/**
 * IP白名单管理接口
 * 
 * @author Chanus
 * @date 2018-09-09 15:42:09
 * @since 0.0.1
 */
public interface WhiteIpService extends BaseService<WhiteIp, Integer> {
	/**
	 * 获取登录账号的固定登录IP
	 * 
	 * @param loginNo
	 * @return
	 * @since 0.0.4
	 */
	String getFixedWhiteIps(String loginNo);
}
