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
package com.chanus.yuntao.mvc.jdbc.security.test;

import com.chanus.yuntao.utils.core.encrypt.AESUtils;
import org.junit.Test;

/**
 * 加密 jdbc 连接密码测试
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
        String cipher = AESUtils.encrypt(password, key);
        System.out.println("加密后：" + cipher);
        System.out.println("解密后：" + AESUtils.decrypt(cipher, key));
    }
}
