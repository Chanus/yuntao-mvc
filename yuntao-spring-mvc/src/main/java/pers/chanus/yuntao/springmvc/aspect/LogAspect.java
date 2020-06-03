/*
 * Copyright (c) 2018, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.springmvc.aspect;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import pers.chanus.yuntao.commons.constant.LogTypeEnum;
import pers.chanus.yuntao.commons.constant.MsgCode;
import pers.chanus.yuntao.commons.pojo.LoginUser;
import pers.chanus.yuntao.commons.pojo.Message;
import pers.chanus.yuntao.server.annotation.SystemLog;
import pers.chanus.yuntao.server.syslog.Log;
import pers.chanus.yuntao.server.syslog.LogMapper;
import pers.chanus.yuntao.util.CollectionUtils;
import pers.chanus.yuntao.util.IpUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Objects;

/**
 * 系统日志切点类
 *
 * @author Chanus
 * @date 2018-09-01 02:22:50
 * @since 0.0.1
 */
@Aspect
@Component
public class LogAspect {
    @Autowired
    private LogMapper logMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(LogAspect.class);

    /**
     * 系统操作日志切点
     *
     * @since 0.0.1
     */
    @Pointcut("@annotation(pers.chanus.yuntao.server.annotation.SystemLog)")
    public void systemLogAspect() {
    }

    /**
     * 记录日志
     *
     * @param proceedingJoinPoint
     * @return
     * @throws Throwable
     * @since 0.0.1
     */
    @Around("systemLogAspect()")
    public Object writeSystemLog(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        SystemLog systemLog = getSystemLog(proceedingJoinPoint);
        if (systemLog == null)
            return proceedingJoinPoint.proceed();

        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();

        LoginUser loginUser = LoginUser.getLoginUser();// 登录用户信息

        Object object = null;

        Log log = new Log();
        if (loginUser != null) {
            log.setOperateNo(loginUser.getLoginNo());
            log.setOperateRoleCode(loginUser.getRoleCode());
        }
        log.setOperateIp(IpUtils.getIpAddress(request));
        log.setOperateModuleCode(systemLog.module());
        log.setOperateUrl(String.valueOf(request.getRequestURL()));// 请求URL
        log.setOperateMethod(proceedingJoinPoint.getTarget().getClass().getName() + "." + proceedingJoinPoint.getSignature().getName() + "()");// 方法描述
        log.setOperateType(systemLog.logType().name());
        log.setOperateTypeDesc(systemLog.description());
        log.setOperateTime(new Date());
        if (LogTypeEnum.LOGOUT.equals(systemLog.logType())) {// 退出系统时
            object = proceedingJoinPoint.proceed();
            log.setOperateConsumeTime((int) (System.currentTimeMillis() - start));
            logMapper.insertSelective(log);
        } else {
            try {
                object = proceedingJoinPoint.proceed();
                if (LogTypeEnum.LOGIN.equals(systemLog.logType())) {// 登录系统时
                    loginUser = LoginUser.getLoginUser();// 登录用户信息
                    if (loginUser != null) {
                        log.setOperateNo(loginUser.getLoginNo());
                        log.setOperateRoleCode(loginUser.getRoleCode());
                    }
                }
                if (object instanceof Message && ((Message) object).getCode() == MsgCode.SUCCESS) {// 操作成功
                    log.setOperateContent(getParameters(proceedingJoinPoint, null, systemLog.ignore()));// 操作内容
                    log.setOperateConsumeTime((int) (System.currentTimeMillis() - start));
                    logMapper.insertSelective(log);
                }
            } catch (Exception ex) {// 记录异常日志
                LOGGER.error(ex.getMessage(), ex);
                log.setOperateType(LogTypeEnum.EXCEPTION.name());
                log.setOperateException(ex.getClass().getName());// 异常代码
                log.setOperateContent(getParameters(proceedingJoinPoint, ex, systemLog.ignore()));// 异常信息
                log.setOperateConsumeTime((int) (System.currentTimeMillis() - start));
                logMapper.insertSelective(log);
            }
        }

        return object == null ? Message.initMsg(MsgCode.FAIL, "系统异常") : object;
    }

    /**
     * 获取系统操作日志注解
     *
     * @param joinPoint
     * @return
     * @since 0.0.1
     */
    private SystemLog getSystemLog(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        if (method == null)
            return null;

        return method.getAnnotation(SystemLog.class);
    }

    /**
     * 获取请求的参数信息
     *
     * @param joinPoint
     * @param e
     * @param ignore
     * @return 请求的参数信息
     * @since 0.0.1
     */
    private String getParameters(JoinPoint joinPoint, Throwable e, boolean ignore) {
        StringBuilder parameters = new StringBuilder();
        if (!ignore) {
            int length = joinPoint.getArgs() == null ? 0 : joinPoint.getArgs().length;
            if (length > 0) {
                for (int i = 0; i < length; i++) {
                    try {
                        parameters.append(JSON.toJSONString(joinPoint.getArgs()[i])).append(";");
                    } catch (Exception ignored) {
                    }

                }
            }
        }

        if (e != null) {
            parameters.append("\r\n").append(e).append("\r\n").append(CollectionUtils.join(e.getStackTrace()));
        }

        return parameters.toString();
    }
}
