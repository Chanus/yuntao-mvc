/*
 * Copyright (c) 2018, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.manager.mapper;

import java.util.List;
import java.util.Map;

import pers.chanus.yuntao.manager.model.DataBaseTable;

/**
 * 数据库表
 * 
 * @author Chanus
 * @date 2018-10-30 16:47:34
 * @since 0.0.3
 */
public interface DataBaseTableMapper {
	int count(Map<String, Object> params);

	List<DataBaseTable> list(Map<String, Object> params);
}
