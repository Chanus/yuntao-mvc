/*
 * Copyright (c) 2018, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pers.chanus.yuntao.commons.pojo.CustomMap;
import pers.chanus.yuntao.commons.pojo.PageBean;
import pers.chanus.yuntao.manager.mapper.DataBaseColumnMapper;
import pers.chanus.yuntao.manager.mapper.DataBaseTableMapper;
import pers.chanus.yuntao.manager.model.DataBaseColumn;
import pers.chanus.yuntao.manager.service.CodeGenerationService;

/**
 * 系统代码自动生成接口实现
 * 
 * @author Chanus
 * @date 2018-10-30 20:24:46
 * @since 0.0.3
 */
@Service
public class CodeGenerationServiceImpl implements CodeGenerationService {
	@Autowired
	private DataBaseTableMapper dataBaseTableMapper;
	@Autowired
	private DataBaseColumnMapper dataBaseColumnMapper;

	@Override
	public PageBean listDataBaseTablePagination(CustomMap params) {
		int count = dataBaseTableMapper.count(params);
		if (count > 0) {
			Integer page = params.get("page") == null ? 1 : Integer.parseInt(String.valueOf(params.get("page")));
			Integer limit = params.get("limit") == null ? PageBean.PAGE_SIZE : Integer.parseInt(String.valueOf(params.get("limit")));
			params.putNext("start", (page - 1) * limit).putNext("limit", limit).putNext("pagination", true);
			return PageBean.pagination(count, dataBaseTableMapper.list(params));
		}
		
		return new PageBean();
	}

	@Override
	public List<DataBaseColumn> listDataBaseColumn(CustomMap params) {
		return dataBaseColumnMapper.list(params);
	}

}
