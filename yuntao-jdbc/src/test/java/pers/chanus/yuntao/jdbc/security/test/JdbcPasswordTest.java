/*
 * Copyright (c) 2018, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.jdbc.security.test;

import org.junit.Test;

import pers.chanus.yuntao.util.encrypt.AESUtils;

/**
 * 加密jdbc连接密码测试
 *
 * @author Chanus
 * @date 2018-08-31 23:38:54
 * @since 0.0.1
 */
public class JdbcPasswordTest {
    @Test
    public void encryptionJdbcPasswordTest() {
        String password = "123456";
        String key = "ChanusYuntaoJDBC";
        System.out.println("加密前：" + password);
        password = AESUtils.encrypt(key, password);
        System.out.println("加密后：" + password);
        password = AESUtils.decrypt(key, password);
        System.out.println("解密后：" + password);
    }
}
