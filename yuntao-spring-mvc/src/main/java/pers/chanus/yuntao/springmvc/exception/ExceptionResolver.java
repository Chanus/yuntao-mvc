/*
 * Copyright (c) 2018, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.springmvc.exception;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import pers.chanus.yuntao.commons.constant.MsgCode;
import pers.chanus.yuntao.commons.pojo.Message;
import pers.chanus.yuntao.springmvc.exception.ApplicationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 统一异常处理
 *
 * @author Chanus
 * @date 2018-09-01 02:08:21
 * @since 0.0.1
 */
public class ExceptionResolver implements HandlerExceptionResolver {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionResolver.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) {
        Message message;

        if (exception instanceof ApplicationException) {
            ApplicationException ae = (ApplicationException) exception;
            message = new Message(ae.getCode(), ae.getMessage());
        } else {
            message = new Message(MsgCode.UNKNOW_ERROR, "未知错误");
        }

        LOGGER.error(exception.getMessage(), exception);

        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpStatus.OK.value());
        try {
            PrintWriter writer = response.getWriter();
            writer.write(JSON.toJSONString(message));
            writer.flush();
            writer.close();
        } catch (IOException ie) {
            LOGGER.error("Failed to serialize the object to json for exception resolver!", ie);
        }

        return new ModelAndView();
    }

}
