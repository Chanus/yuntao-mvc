/*
 * Copyright (c) 2018, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.server.exception;

import pers.chanus.yuntao.commons.constant.MsgCode;

/**
 * 数据访问异常
 * 
 * @author Chanus
 * @date 2018-09-01 00:59:32
 * @since 0.0.1
 */
public class DaoException extends ApplicationException {
	private static final long serialVersionUID = -5511724448752379165L;

	public static final String MESSAGE = "数据访问异常";

	public DaoException() {
		super(MESSAGE);
	}

	public DaoException(String message) {
		super(message);
		this.code = MsgCode.DAO_ERROR;
	}

	public DaoException(int code, String message) {
		super(message);
		this.code = code;
	}

	public DaoException(String message, Throwable cause) {
		super(message, cause);
		this.code = MsgCode.DAO_ERROR;
	}

	public DaoException(int code, String message, Throwable cause) {
		super(message, cause);
		this.code = code;
	}

	public DaoException(Throwable cause) {
		super(cause);
		this.code = MsgCode.DAO_ERROR;
	}
}
