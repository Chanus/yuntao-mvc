/*
 * Copyright (c) 2018, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.jdbc.rwdb;

import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态数据源实现读写分离
 * 
 * @author Chanus
 * @date 2019-01-03 22:55:11
 * @since 0.0.5
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
	// 写数据源
	private Object writeDataSource;
	// 读数据源
	private Object readDataSource;

	@Override
	public void afterPropertiesSet() {
		if (this.writeDataSource == null) {
			throw new IllegalArgumentException("Property 'writeDataSource' is required");
		}
		setDefaultTargetDataSource(writeDataSource);
		Map<Object, Object> targetDataSources = new HashMap<>();
		targetDataSources.put(DynamicDataSourceGlobal.WRITE.name(), writeDataSource);
		if (readDataSource != null) {
			targetDataSources.put(DynamicDataSourceGlobal.READ.name(), readDataSource);
		}
		setTargetDataSources(targetDataSources);
		super.afterPropertiesSet();
	}

	@Override
	protected Object determineCurrentLookupKey() {
		DynamicDataSourceGlobal dynamicDataSourceGlobal = DynamicDataSourceHolder.getDataSource();

		if (dynamicDataSourceGlobal == null || dynamicDataSourceGlobal == DynamicDataSourceGlobal.WRITE) {
			return DynamicDataSourceGlobal.WRITE.name();
		}

		return DynamicDataSourceGlobal.READ.name();
	}

	public Object getWriteDataSource() {
		return writeDataSource;
	}

	public void setWriteDataSource(Object writeDataSource) {
		this.writeDataSource = writeDataSource;
	}

	public Object getReadDataSource() {
		return readDataSource;
	}

	public void setReadDataSource(Object readDataSource) {
		this.readDataSource = readDataSource;
	}

}
