/*
 * Copyright (c) 2018, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.manager.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pers.chanus.yuntao.commons.pojo.Message;
import pers.chanus.yuntao.manager.mapper.PowerMapper;
import pers.chanus.yuntao.manager.model.Power;
import pers.chanus.yuntao.manager.service.PowerService;
import pers.chanus.yuntao.server.service.impl.BaseServiceImpl;

/**
 * 权限项管理接口实现
 * 
 * @author Chanus
 * @date 2018-09-06 20:05:25
 * @since 0.0.1
 */
@Service
public class PowerServiceImpl extends BaseServiceImpl<PowerMapper, Power, Integer> implements PowerService {

	@Autowired
	public void setMapper(PowerMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public Message insert(Power t) {
		Integer priority = mapper.getMaxPriority();
		t.setPriority(priority == null ? 1 : (priority + 1));
		return super.insert(t);
	}

	@Override
	public Message priority(Map<String, Object> params) {
		mapper.priority(params);
		return Message.success("调整优先级成功");
	}

}
