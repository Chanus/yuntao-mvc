/*
 * Copyright (c) 2018, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.commons.constant;

/**
 * 系统级异常代码
 * 
 * @author Chanus
 * @date 2018-09-01 00:53:36
 * @since 0.0.1
 */
public class MsgCode {
	/** 操作成功 */
	public final static int SUCCESS = 0;
	/** 操作失败 */
	public final static int FAIL = 1;

	/** 通用错误代码{@code 99999}：未知错误 */
	public final static int UNKNOW_ERROR = 99999;
	/** 通用错误代码{@code 90000}：应用级错误 */
	public final static int APPLICATION_ERROR = 90000;
	/** 通用错误代码{@code 91000}：业务逻辑错误 */
	public final static int SERVICE_ERROR = 91000;
	/** 通用错误代码{@code 92000}：数据访问错误 */
	public final static int DAO_ERROR = 92000;
	/** 通用错误代码{@code 93000}：缓存访问错误 */
	public final static int CACHE_ERROR = 93000;
}
