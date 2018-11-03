/*
 * Copyright (c) 2018, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.manager.model;

import java.io.Serializable;

/**
 * 数据库表字段
 * 
 * @author Chanus
 * @date 2018-10-30 16:01:06
 * @since 0.0.3
 */
public class DataBaseColumn implements Serializable {
	private static final long serialVersionUID = 7383901221399009751L;

	private String tableSchema;

	private String tableName;

	private String columnName;

	private String columnDefault;

	private String nullable;

	private String characterSetName;

	private String collationName;

	private String columnType;

	private String columnComment;

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

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getColumnDefault() {
		return columnDefault;
	}

	public void setColumnDefault(String columnDefault) {
		this.columnDefault = columnDefault;
	}

	public String getNullable() {
		return nullable;
	}

	public void setNullable(String nullable) {
		this.nullable = nullable;
	}

	public String getCharacterSetName() {
		return characterSetName;
	}

	public void setCharacterSetName(String characterSetName) {
		this.characterSetName = characterSetName;
	}

	public String getCollationName() {
		return collationName;
	}

	public void setCollationName(String collationName) {
		this.collationName = collationName;
	}

	public String getColumnType() {
		return columnType;
	}

	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}

	public String getColumnComment() {
		return columnComment;
	}

	public void setColumnComment(String columnComment) {
		this.columnComment = columnComment;
	}

	@Override
	public String toString() {
		return "DataBaseColumn [tableSchema=" + tableSchema + ", tableName=" + tableName + ", columnName=" + columnName + ", columnDefault=" + columnDefault + ", nullable=" + nullable + ", characterSetName=" + characterSetName + ", collationName=" + collationName + ", columnType=" + columnType + ", columnComment=" + columnComment + "]";
	}
}
