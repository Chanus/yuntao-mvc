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
package com.chanus.yuntao.mvc.jdbc.security;

import com.alibaba.druid.util.DruidPasswordCallback;
import com.chanus.yuntao.utils.core.StringUtils;
import com.chanus.yuntao.utils.core.encrypt.AESUtils;

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
        String encryptKey = properties.getProperty("encryptKey");

        if (password != null && password.trim().length() > 0) {
            try {
                // 这里的 password 是将 jdbc.properties 配置得到的密码进行解密之后的值，所以这里的代码是将密码进行解密
                password = AESUtils.decrypt(password, StringUtils.isBlank(encryptKey) ? KEY : encryptKey);
                setPassword(password.toCharArray());
            } catch (Exception e) {
                setPassword(password.toCharArray());
            }
        }
    }
}
