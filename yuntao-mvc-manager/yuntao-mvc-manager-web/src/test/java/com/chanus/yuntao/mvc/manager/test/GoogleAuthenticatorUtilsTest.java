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
package com.chanus.yuntao.mvc.manager.test;

import org.junit.Test;

import com.chanus.yuntao.utils.core.GoogleAuthenticatorUtils;

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
