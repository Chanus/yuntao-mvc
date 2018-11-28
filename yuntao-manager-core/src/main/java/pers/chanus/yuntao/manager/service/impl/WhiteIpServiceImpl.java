/*
 * Copyright (c) 2018, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pers.chanus.yuntao.manager.mapper.WhiteIpMapper;
import pers.chanus.yuntao.manager.model.WhiteIp;
import pers.chanus.yuntao.manager.service.WhiteIpService;
import pers.chanus.yuntao.server.service.impl.BaseServiceImpl;

/**
 * IP白名单管理接口实现
 * 
 * @author Chanus
 * @date 2018-09-09 15:43:12
 * @since 0.0.1
 */
@Service
public class WhiteIpServiceImpl extends BaseServiceImpl<WhiteIpMapper, WhiteIp, Integer> implements WhiteIpService {

	@Autowired
	public void setMapper(WhiteIpMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public String getFixedWhiteIps(String loginNo) {
		return mapper.getFixedWhiteIps(loginNo);
	}

}
