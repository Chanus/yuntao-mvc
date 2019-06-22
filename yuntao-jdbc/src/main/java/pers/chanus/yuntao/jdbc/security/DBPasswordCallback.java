/*
 * Copyright (c) 2018, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.jdbc.security;

import com.alibaba.druid.util.DruidPasswordCallback;
import pers.chanus.yuntao.util.encrypt.AESUtils;

import java.util.Properties;

/**
 * 获取数据库密码密文，解密后用来连接数据库
 *
 * @author Chanus
 * @date 2018-08-31 22:54:22
 * @since 0.0.1
 */
public class DBPasswordCallback extends DruidPasswordCallback {
    private static final long serialVersionUID = 1100233602930489210L;
    private static final String KEY = "ChanusYuntaoJDBC";

    @Override
    public void setProperties(Properties properties) {
        super.setProperties(properties);
        String password = properties.getProperty("password");

        if (password != null && password.trim().length() > 0) {
            try {
                // 这里的password是将jdbc.properties配置得到的密码进行解密之后的值，所以这里的代码是将密码进行解密
                password = AESUtils.decrypt(password, KEY);
                setPassword(password.toCharArray());
            } catch (Exception e) {
                setPassword(password.toCharArray());
            }
        }
    }

    /**
     * 获取数据库密码密文
     *
     * @param password 数据库密码明文
     * @return 数据库密码密文
     * @since 0.1.2
     */
    public static String encrypt(String password) {
        return AESUtils.encrypt(password, KEY);
    }

    /**
     * 获取数据库密码密文
     *
     * @param password 数据库密码明文
     * @param key      秘钥
     * @return 数据库密码密文
     * @since 0.1.2
     */
    public static String encrypt(String password, String key) {
        return AESUtils.encrypt(password, key);
    }
}
