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

import com.chanus.yuntao.utils.core.lang.Message;
import org.junit.Test;
import com.chanus.yuntao.mvc.framework.LicenseUtils;

/**
 * 生成证书
 *
 * @author Chanus
 * @date 2020-05-09 17:08
 * @since 0.1.8
 */
public class LicenseUtilsTest {
    @Test
    public void licenseTest() {
        String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAImZ6XJVDQspW55LunRBrlMrozvh5EXxN7Zjlr03NLenFU2dAJ5moGdGrPlLhMHUDPAqrEh93pUJeLlLIiYErY+8FwDu6Tim/wASgcCihe2mauUqrEiXHmuTjIiEggY7WteT8anDGuuZFvBNhrOMSMIg8B0fs8mdf+jDv5bAn0rLAgMBAAECgYAos17q0591Ermn2+x94MOolDrHRqzXx17cUx7OPxsXy5MwjuUU9XQyuCQH5T+SeZ+OFoE5PAnveYq8/C1Qo4eWkddu/+uN5oNlvoRI5yXR17utMOEEgv5ek2wUw2mOasbARTOHjXDqNGXGjJDH/tFipWmpdGTbXP5SRyp+Wm4/2QJBAM6hxJXTsIViI2pQE35DdhMv4T2ha9M6p0lEZ8ZMgV93Uuffq5jg45KqkfVXLhyTi0D5ELjId/R9kN/O2wQasH8CQQCqegS4Ilt0R2bmQyR3VF/qbGg4BhpfFtcjVAzwyL4hHOKHR+B9wyDf9ElKGoQiXjTexb0ZRsmc8WV1tlDjDv+1AkBm33rz7PSRM47WOw+5aPS8ELZe7KK2AxmODJRt0WEHmmyYLG9OKYijWHBaaRD4ASzvKBD/Li8qdelOw7mejXsZAkEAlZoOmihMaXZfbqJtVerNYUNREhkI+3lnwrD3yjXf498dS+pfrv2V0/LkfgtW+XICo5yTnaLpOrj8eJhN03ROIQJAQ0h+fP9/lSIwha4q4z/miMFB0zYsbBxiOiUctkIKO7uHQNmJfnvVUbApiX/xJ5n2yjTf2OGph1VbAZQ6/cv1pw==";
        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCJmelyVQ0LKVueS7p0Qa5TK6M74eRF8Te2Y5a9NzS3pxVNnQCeZqBnRqz5S4TB1AzwKqxIfd6VCXi5SyImBK2PvBcA7uk4pv8AEoHAooXtpmrlKqxIlx5rk4yIhIIGO1rXk/GpwxrrmRbwTYazjEjCIPAdH7PJnX/ow7+WwJ9KywIDAQAB";

        // String path = "F:/license.lic";// windows
        String path = "/Users/Chanus/Documents/license.lic";// mac

        String name = "yundao-mvc-manager";
        String version = "1.0.0";
        // Set<String> macAddress = SystemUtils.getHostMac();
        String macAddress = null;
        String limit = "0";
        String enable = "0";

        String ciphertext = LicenseUtils.createLicense(name, version, macAddress, limit, enable, privateKey);
        System.out.println(ciphertext);

        LicenseUtils.createLicense(name, version, macAddress, limit, enable, privateKey, path);

        Message message = LicenseUtils.verify(ciphertext, publicKey);
        System.out.println(message.toString());
    }
}
