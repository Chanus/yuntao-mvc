/*
 * Copyright (c) 2018, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.server.mapper;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * MyBatis基础接口
 * 
 * @author Chanus
 * @date 2018-09-01 01:01:20
 * @since 0.0.1
 * @param <T>	实体对象
 * @param <PK>	实体主键类型
 */
public interface BaseMapper<T, PK extends Serializable> {
	/**
	 * 根据主键查询实体
	 * 
	 * @param pk	主键
	 * @return 实体对象
	 * @since 0.0.1
	 */
	T selectByPrimaryKey(PK pk);

	/**
	 * 根据主键删除实体
	 * 
	 * @param pks	主键
	 * @return 删除的行数
	 * @since 0.0.1
	 */
	int deleteByPrimaryKey(Collection<PK> pks);

	/**
	 * 添加实体
	 * 
	 * @param t	实体对象
	 * @return 新插入行的主键(primary key)，需要包含&lt;selectKey&gt;语句，才会返回主键，否则返回值为null
	 * @since 0.0.1
	 */
	int insertSelective(T t);

	/**
	 * 更新实体，过滤空字段
	 * 
	 * @param t	实体对象
	 * @return 更新的行数
	 * @since 0.0.1
	 */
	int updateByPrimaryKeySelective(T t);

	/**
	 * 获取记录条数
	 * 
	 * @param params	参数集合
	 * @return 记录条数
	 * @since 0.0.1
	 */
	int count(Map<String, Object> params);

	/**
	 * 获取记录列表
	 * 
	 * @param params	参数集合
	 * @return 记录列表
	 * @since 0.0.1
	 */
	List<T> list(Map<String, Object> params);
}
