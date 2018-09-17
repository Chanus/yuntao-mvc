/*
 * Copyright (c) 2018, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.manager.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pers.chanus.yuntao.commons.constant.CacheConsts;
import pers.chanus.yuntao.commons.pojo.CustomMap;
import pers.chanus.yuntao.commons.pojo.Message;
import pers.chanus.yuntao.manager.mapper.ParamMapper;
import pers.chanus.yuntao.manager.model.Param;
import pers.chanus.yuntao.manager.service.ParamService;
import pers.chanus.yuntao.server.service.impl.BaseServiceImpl;
import pers.chanus.yuntao.util.CollectionUtils;

/**
 * 系统参数配置管理接口实现
 * 
 * @author Chanus
 * @date 2018-09-06 17:50:17
 * @since 0.0.1
 */
@Service
public class ParamServiceImpl extends BaseServiceImpl<ParamMapper, Param, Integer> implements ParamService {

	@Autowired
	public void setMapper(ParamMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public Message insert(Param t) {
		int count = mapper.count(CustomMap.get().putNext("paramCode", t.getParamCode()));
		if (count > 0)
			return Message.fail("当前参数代码已存在");
		
		Integer priority = mapper.getMaxPriority();
		t.setPriority(priority == null ? 1 : (priority + 1));
		
		return super.insert(t);
	}

	@Override
	public Message priority(Map<String, Object> params) {
		mapper.priority(params);
		return Message.success("调整优先级成功");
	}

	@Override
	public Message reloadParam() {
		CacheConsts.SYSTEM_PARAMS_MAP.clear();
		List<Param> params = mapper.listValidParam();
		if (!CollectionUtils.isEmpty(params)) {
			for (Param param : params) {
				CacheConsts.SYSTEM_PARAMS_MAP.put(param.getParamCode(), param.getParamData());
			}
		}
		
		return Message.success("系统参数重载成功");
	}

}
