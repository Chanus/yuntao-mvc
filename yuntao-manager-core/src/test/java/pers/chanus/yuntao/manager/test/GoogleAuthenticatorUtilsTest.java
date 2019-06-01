/*
 * Copyright (c) 2019, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.manager.test;

import org.junit.Test;

import pers.chanus.yuntao.util.GoogleAuthenticatorUtils;

/**
 * google身份验证器
 * 
 * @author Chanus
 * @date 2019-05-28 20:59:06
 * @since 0.0.8
 */
public class GoogleAuthenticatorUtilsTest {
    @Test
    public void genSecretTest() {
        String secret = GoogleAuthenticatorUtils.generateSecretKey();
        System.out.println("secret: " + secret);
        // 把这个qrcode生成二维码，用google身份验证器扫描二维码就能添加成功
        String qrcode = GoogleAuthenticatorUtils.getQRBarcode("jinhe@gmail.com", secret);
        System.out.println("qrcode: " + qrcode);
    }
}
