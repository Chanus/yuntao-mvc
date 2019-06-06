/*
 * Copyright (c) 2018, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.server.exception;

import pers.chanus.yuntao.commons.constant.MsgCode;

/**
 * 应用异常
 *
 * @author Chanus
 * @date 2018-09-01 00:56:20
 * @since 0.0.1
 */
public class ApplicationException extends RuntimeException {
    private static final long serialVersionUID = 846739013478189753L;

    public static final String MESSAGE = "应用异常";
    protected int code = MsgCode.APPLICATION_ERROR;

    public ApplicationException() {
        super(MESSAGE);
    }

    public ApplicationException(String message) {
        super(message);
    }

    public ApplicationException(int code, String message) {
        super(message);
        this.code = code;
    }

    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApplicationException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public ApplicationException(Throwable cause) {
        super(cause);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
