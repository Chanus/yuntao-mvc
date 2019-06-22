/*
 * Copyright (c), 2019-2019, Chanus and/or its affiliates. All rights reserved.
 * FileName: JdbcPasswordTest
 * Author:   Chanus
 * Date:     2019-06-09 17:34
 * Description: 生成JDBC连接密码密文
 * History:
 * <author>          <time>          <version>          <desc>
 */
package pers.chanus.yuntao.manager.test;

import pers.chanus.yuntao.jdbc.security.DBPasswordCallback;

/**
 * 生成JDBC连接密码密文
 *
 * @author Chanus
 * @date 2019-06-09 17:34
 * @since 0.0.1
 */
public class JdbcPasswordTest {
    public static void main(String[] args) {
        String password = "123456";
        System.out.println("加密后密码：" + DBPasswordCallback.encrypt(password));
    }
}
