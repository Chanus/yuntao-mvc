/*
 * Copyright (c) 2020 Chanus
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.chanus.yuntao.mvc.framework.aspect;

import com.alibaba.fastjson.JSON;
import com.chanus.yuntao.mvc.framework.annotation.Log;
import com.chanus.yuntao.utils.core.ArrayUtils;
import com.chanus.yuntao.utils.core.IpUtils;
import com.chanus.yuntao.utils.core.StringUtils;
import com.chanus.yuntao.utils.core.lang.Message;
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
import com.chanus.yuntao.mvc.common.pojo.LoginUser;
import com.chanus.yuntao.mvc.framework.enums.LogTypeEnum;
import com.chanus.yuntao.mvc.framework.log.LogMapper;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
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
    @Pointcut("@annotation(com.chanus.yuntao.mvc.framework.annotation.Log)")
    public void logAspect() {
    }

    /**
     * 记录日志
     *
     * @param proceedingJoinPoint {@link ProceedingJoinPoint}
     * @return object
     * @throws Throwable 异常
     * @since 0.0.1
     */
    @Around("logAspect()")
    public Object writeLog(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        Log log = getLog(proceedingJoinPoint);
        if (log == null)
            return proceedingJoinPoint.proceed();

        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();

        LoginUser loginUser = LoginUser.getLoginUser();// 登录用户信息

        Object object = null;

        com.chanus.yuntao.mvc.framework.log.Log sysLog = new com.chanus.yuntao.mvc.framework.log.Log();
        if (loginUser != null) {
            sysLog.setOperateNo(loginUser.getLoginNo());
            sysLog.setOperateRoleCode(loginUser.getRoleCode());
            sysLog.setOperateMasterRoleCode(loginUser.getMasterRoleCode());
        }
        sysLog.setOperateIp(IpUtils.getIpAddress(request));
        sysLog.setOperateModuleCode(log.module());
        sysLog.setOperateUrl(String.valueOf(request.getRequestURL()));// 请求URL
        sysLog.setOperateMethod(proceedingJoinPoint.getTarget().getClass().getName() + "." + proceedingJoinPoint.getSignature().getName() + "()");// 方法描述
        sysLog.setOperateType(log.logType().name());
        sysLog.setOperateTypeDesc(log.description());
        sysLog.setOperateTime(LocalDateTime.now());
        if (LogTypeEnum.LOGOUT.equals(log.logType())) {// 退出系统时
            object = proceedingJoinPoint.proceed();
            sysLog.setOperateConsumeTime((int) (System.currentTimeMillis() - start));
            logMapper.insertSelective(sysLog);
        } else {
            try {
                object = proceedingJoinPoint.proceed();
                if (LogTypeEnum.LOGIN.equals(log.logType())) {// 登录系统时
                    loginUser = LoginUser.getLoginUser();// 登录用户信息
                    if (loginUser != null) {
                        sysLog.setOperateNo(loginUser.getLoginNo());
                        sysLog.setOperateRoleCode(loginUser.getRoleCode());
                        sysLog.setOperateMasterRoleCode(loginUser.getMasterRoleCode());
                    }
                }
                if (object instanceof Message && ((Message) object).isSuccess()) {// 操作成功
                    sysLog.setOperateContent(getParameters(proceedingJoinPoint, null, log.ignore()));// 操作内容
                    sysLog.setOperateConsumeTime((int) (System.currentTimeMillis() - start));
                    logMapper.insertSelective(sysLog);
                }
            } catch (Exception ex) {// 记录异常日志
                LOGGER.error(ex.getMessage(), ex);
                sysLog.setOperateType(LogTypeEnum.EXCEPTION.name());
                sysLog.setOperateException(ex.getClass().getName());// 异常代码
                sysLog.setOperateContent(getParameters(proceedingJoinPoint, ex, log.ignore()));// 异常信息
                sysLog.setOperateConsumeTime((int) (System.currentTimeMillis() - start));
                logMapper.insertSelective(sysLog);
            }
        }

        return object == null ? Message.fail("系统异常") : object;
    }

    /**
     * 获取系统操作日志注解
     *
     * @param joinPoint {@link JoinPoint}
     * @return {@link Log}
     * @since 0.0.1
     */
    private Log getLog(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        if (method == null)
            return null;

        return method.getAnnotation(Log.class);
    }

    /**
     * 获取请求的参数信息
     *
     * @param joinPoint {@link JoinPoint}
     * @param e         异常
     * @param ignore    是否忽略参数信息
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
            parameters.append("\r\n").append(e).append("\r\n")
                    .append(ArrayUtils.joinIgnoreNull(e.getStackTrace(), StringUtils.COMMA));
        }

        return parameters.toString();
    }
}
