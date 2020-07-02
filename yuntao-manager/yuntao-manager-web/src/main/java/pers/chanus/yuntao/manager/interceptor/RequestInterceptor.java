/*
 * Copyright (c) 2018, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.manager.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import pers.chanus.yuntao.commons.pojo.LoginUser;
import pers.chanus.yuntao.commons.pojo.Message;
import com.chanus.yuntao.utils.core.CollectionUtils;
import com.chanus.yuntao.utils.core.StringUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 请求地址拦截器
 *
 * @author Chanus
 * @date 2018-09-01 23:38:46
 * @since 0.0.1
 */
public class RequestInterceptor implements HandlerInterceptor {
    private static final String LOGIN_URL = "/relogin";
    private static final String LICENSE_URL = "/license";

    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) {

    }

    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3) {

    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
        // 验证授权信息
        ServletContext servletContext = request.getServletContext();
        Message message = (Message) servletContext.getAttribute("licenseMessage");
        if (message.getCode() != 0) {
            response.sendRedirect(request.getContextPath() + LICENSE_URL);
            return false;
        }

        HttpSession session = request.getSession(true);
        LoginUser loginUser = (LoginUser) session.getAttribute("loginUser");// 登录账号信息

        // 验证用户是否登录
        if (loginUser == null || StringUtils.isBlank(loginUser.getLoginNo()) || StringUtils.isBlank(loginUser.getRoleCode()) || CollectionUtils.isEmpty(loginUser.getMenus())) {
            // 判断是否为ajax请求
            if (request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
                response.getWriter().print("{\"code\":2,\"msg\":\"登录超时\"}");
                return false;
            } else {
                response.sendRedirect(request.getContextPath() + LOGIN_URL);
                return false;
            }
        }

        LoginUser.setLoginUser(loginUser);

        // 验证请求URL是否有效
        if (CollectionUtils.isEmpty(loginUser.getUrls()) || !loginUser.getUrls().contains(request.getServletPath())) {
            response.getWriter().print("{\"code\":3,\"msg\":\"请求URL无效：" + request.getServletPath() + "\"}");
            return false;
        }

        return true;
    }
}
