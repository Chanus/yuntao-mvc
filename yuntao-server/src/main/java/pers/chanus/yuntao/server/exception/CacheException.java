/*
 * Copyright (c) 2018, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.server.exception;

import pers.chanus.yuntao.commons.constant.MsgCode;

/**
 * 缓存异常
 * 
 * @author Chanus
 * @date 2018-09-01 00:59:48
 * @since 0.0.1
 */
public class CacheException extends ApplicationException {
	private static final long serialVersionUID = -7180746359235299074L;

	public static final String MESSAGE = "缓存异常";

	public CacheException() {
		super(MESSAGE);
	}

	public CacheException(String message) {
		super(message);
		this.code = MsgCode.CACHE_ERROR;
	}

	public CacheException(int code, String message) {
		super(message);
		this.code = code;
	}

	public CacheException(String message, Throwable cause) {
		super(message, cause);
		this.code = MsgCode.CACHE_ERROR;
	}

	public CacheException(int code, String message, Throwable cause) {
		super(message, cause);
		this.code = code;
	}

	public CacheException(Throwable cause) {
		super(cause);
		this.code = MsgCode.CACHE_ERROR;
	}
}
