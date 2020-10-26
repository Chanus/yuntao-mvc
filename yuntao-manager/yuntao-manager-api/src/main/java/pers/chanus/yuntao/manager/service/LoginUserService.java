/*
 * Copyright (c) 2018, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.manager.service;

import com.chanus.yuntao.utils.core.lang.Message;
import pers.chanus.yuntao.manager.model.LoginUserView;

/**
 * 登录用户接口
 *
 * @author Chanus
 * @date 2018-09-02 01:15:21
 * @since 0.0.1
 */
public interface LoginUserService {
    /**
     * 用户登录
     *
     * @param loginNo  登录账号
     * @param password 登录密码
     * @param loginIp  登录IP
     * @return
     * @since 0.0.1
     */
    Message login(String loginNo, String password, String loginIp);

    /**
     * 用户指定角色登录
     *
     * @param loginNo  登录账号
     * @param password 登录密码
     * @param roleCode 登录角色
     * @param loginIp  登录IP
     * @return
     * @since 0.0.3
     */
    Message login(String loginNo, String password, String roleCode, String loginIp);

    /**
     * 根据登录账号获取用户信息，不区分账号字母大小写
     *
     * @param loginNo 登录账号
     * @return
     * @since 0.0.4
     */
    LoginUserView getUser(String loginNo);

    /**
     * 根据登录账号获取用户信息，区分账号字母大小写
     *
     * @param loginNo 登录账号
     * @return
     * @since 0.0.4
     */
    LoginUserView getLoginUser(String loginNo);
}
