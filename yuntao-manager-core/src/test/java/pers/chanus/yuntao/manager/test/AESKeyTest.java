/*
 * Copyright (c) 2018, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.manager.test;

import org.junit.Test;

import pers.chanus.yuntao.util.encrypt.AESUtils;

/**
 * AES加密密钥
 *
 * @author Chanus
 * @date 2018-09-09 10:35:57
 * @since 0.0.1
 */
public class AESKeyTest {
    @Test
    public void createAESKey() {
        System.out.println("手机号加密密钥：" + AESUtils.generateKey("CHANUS_PHONE"));
        System.out.println("邮箱账号加密密钥：" + AESUtils.generateKey("CHANUS_EMAIL"));
    }
}
