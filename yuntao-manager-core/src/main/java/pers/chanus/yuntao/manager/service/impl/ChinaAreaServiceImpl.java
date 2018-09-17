/*
 * Copyright (c) 2018, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pers.chanus.yuntao.manager.mapper.ChinaAreaMapper;
import pers.chanus.yuntao.manager.model.ChinaArea;
import pers.chanus.yuntao.manager.service.ChinaAreaService;
import pers.chanus.yuntao.server.service.impl.BaseServiceImpl;

/**
 * 中国区划信息管理接口实现
 * 
 * @author Chanus
 * @date 2018-09-15 16:00:17
 * @since 0.0.1
 */
@Service
public class ChinaAreaServiceImpl extends BaseServiceImpl<ChinaAreaMapper, ChinaArea, Integer> implements ChinaAreaService {

	@Autowired
	public void setMapper(ChinaAreaMapper mapper) {
		this.mapper = mapper;
	}

}
