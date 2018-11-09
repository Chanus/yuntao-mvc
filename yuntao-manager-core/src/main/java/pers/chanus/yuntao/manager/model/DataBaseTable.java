/*
 * Copyright (c) 2018, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.manager.model;

import java.io.Serializable;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 数据库表
 * 
 * @author Chanus
 * @date 2018-10-30 13:14:16
 * @since 0.0.3
 */
public class DataBaseTable implements Serializable {
	private static final long serialVersionUID = 5884851173396565869L;

	/**
	 * 数据库名称
	 */
	private String tableSchema;

	/**
	 * 表名称
	 */
	private String tableName;

	/**
	 * 表类型，TABLE，VIEW
	 */
	private String tableType;

	/**
	 * 数据库引擎
	 */
	private String engine;

	/**
	 * 数据行数
	 */
	private String tableRows;

	/**
	 * 主键自增长值
	 */
	private String autoIncrement;

	/**
	 * 创建时间
	 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private String createTime;

	/**
	 * 字符序
	 */
	private String tableCollation;

	/**
	 * 表备注
	 */
	private String tableComment;

    /**
     * 表的主键
     */
    private DataBaseColumn pk;

    /**
     * 表的列名(不包含主键)
     */
    private List<DataBaseColumn> columns;

	/**
	 * 类名(首字母大写)
	 */
	private String className;

	/**
	 * 类名(首字母小写)
	 */
	private String classname;

	public String getTableSchema() {
		return tableSchema;
	}

	public void setTableSchema(String tableSchema) {
		this.tableSchema = tableSchema;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getTableType() {
		return tableType;
	}

	public void setTableType(String tableType) {
		this.tableType = tableType;
	}

	public String getEngine() {
		return engine;
	}

	public void setEngine(String engine) {
		this.engine = engine;
	}

	public String getTableRows() {
		return tableRows;
	}

	public void setTableRows(String tableRows) {
		this.tableRows = tableRows;
	}

	public String getAutoIncrement() {
		return autoIncrement;
	}

	public void setAutoIncrement(String autoIncrement) {
		this.autoIncrement = autoIncrement;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getTableCollation() {
		return tableCollation;
	}

	public void setTableCollation(String tableCollation) {
		this.tableCollation = tableCollation;
	}

	public String getTableComment() {
		return tableComment;
	}

	public void setTableComment(String tableComment) {
		this.tableComment = tableComment;
	}

	public DataBaseColumn getPk() {
		return pk;
	}

	public void setPk(DataBaseColumn pk) {
		this.pk = pk;
	}

	public List<DataBaseColumn> getColumns() {
		return columns;
	}

	public void setColumns(List<DataBaseColumn> columns) {
		this.columns = columns;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	@Override
	public String toString() {
		return "DataBaseTable [tableSchema=" + tableSchema + ", tableName=" + tableName + ", tableType=" + tableType + ", engine=" + engine + ", tableRows=" + tableRows + ", autoIncrement=" + autoIncrement + ", createTime=" + createTime + ", tableCollation=" + tableCollation + ", tableComment=" + tableComment + "]";
	}
}
