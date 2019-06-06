/*
 * Copyright (c) 2018, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.commons.constant;

import java.math.BigDecimal;

/**
 * 系统常量配置
 *
 * @author Chanus
 * @date 2018-08-31 22:22:27
 * @since 0.0.1
 */
public class ConfigConsts {
    /**
     * 状态：否
     */
    public final static String STATUS_NO = "0";
    /**
     * 状态：是
     */
    public final static String STATUS_YES = "1";
    /**
     * 状态：进行中
     */
    public final static String STATUS_HAND = "2";

    /**
     * 系统内置角色：超级管理员
     */
    public final static String ROLE_ADMIN_0 = "0";
    /**
     * 系统内置角色：子账号
     */
    public final static String ROLE_SUB_1 = "1";

    /**
     * 来源： Web
     */
    public final static String ORIGIN_WEB = "0";
    /**
     * 来源： 安卓
     */
    public final static String ORIGIN_ANDROID = "1";
    /**
     * 来源： iOS
     */
    public final static String ORIGIN_IOS = "2";
    /**
     * 来源： 手机Web
     */
    public final static String ORIGIN_PHONE_WEB = "3";

    /**
     * BigDecimal常量100
     */
    public static final BigDecimal DECIMAL_100 = new BigDecimal(100);
}
