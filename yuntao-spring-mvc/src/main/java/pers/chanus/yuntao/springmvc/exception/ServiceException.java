/*
 * Copyright (c) 2018, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.springmvc.exception;

import pers.chanus.yuntao.commons.constant.MsgCode;

/**
 * 业务逻辑异常
 *
 * @author Chanus
 * @date 2018-09-01 00:59:21
 * @since 0.0.1
 */
public class ServiceException extends ApplicationException {
    private static final long serialVersionUID = -4883205302627033167L;

    public static final String MESSAGE = "业务逻辑异常";

    public ServiceException() {
        super(MESSAGE);
    }

    public ServiceException(String message) {
        super(message);
        this.code = MsgCode.SERVICE_ERROR;
    }

    public ServiceException(int code, String message) {
        super(message);
        this.code = code;
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
        this.code = MsgCode.SERVICE_ERROR;
    }

    public ServiceException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public ServiceException(Throwable cause) {
        super(cause);
        this.code = MsgCode.SERVICE_ERROR;
    }
}
