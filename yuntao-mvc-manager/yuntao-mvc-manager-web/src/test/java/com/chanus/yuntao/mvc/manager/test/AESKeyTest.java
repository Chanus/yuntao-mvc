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

import com.chanus.yuntao.utils.core.encrypt.AESUtils;

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
