/*
 * Copyright (c) 2018, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.manager.service;

import java.util.List;

import pers.chanus.yuntao.commons.pojo.CustomMap;
import pers.chanus.yuntao.commons.pojo.PageBean;
import pers.chanus.yuntao.manager.model.DataBaseColumn;

/**
 * 系统代码自动生成接口
 * 
 * @author Chanus
 * @date 2018-10-30 20:14:49
 * @since 0.0.3
 */
public interface CodeGenerationService {
	/**
	 * 分页查询系统数据库表
	 * 
	 * @param params
	 *  tableSchema	数据库名称
	 *  tableName	表名称
	 * @return
	 * @since 0.0.3
	 */
	PageBean listDataBaseTablePagination(CustomMap params);
	
	/**
	 * 获取系统数据库表下的字段
	 * 
	 * @param params
	 *  tableSchema	数据库名称
	 *  tableName	表名称
	 * @return
	 * @since 0.0.3
	 */
	List<DataBaseColumn> listDataBaseColumn(CustomMap params);
}
