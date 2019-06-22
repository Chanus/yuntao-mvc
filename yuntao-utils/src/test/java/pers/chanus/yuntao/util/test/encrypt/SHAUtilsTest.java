/*
 * Copyright (c), 2019-2019, Chanus and/or its affiliates. All rights reserved.
 * FileName: SHAUtilsTest
 * Author:   Chanus
 * Date:     2019-06-22 16:51
 * Description: 工具类测试
 * History:
 * <author>          <time>          <version>          <desc>
 */
package pers.chanus.yuntao.util.test.encrypt;

import org.junit.Test;
import pers.chanus.yuntao.util.encrypt.SHAUtils;

/**
 * SHAUtils工具类测试
 *
 * @author Chanus
 * @date 2019-06-22 16:51
 * @since 0.0.1
 */
public class SHAUtilsTest {
    @Test
    public void shaTest() {
        String text = "我是密码，请对我进行SHA加密。";

        String sha1 = SHAUtils.sha1(text);
        System.out.println("SHA-1加密：" + sha1);

        String sha224 = SHAUtils.sha224(text);
        System.out.println("SHA-224加密：" + sha224);

        String sha256 = SHAUtils.sha256(text);
        System.out.println("SHA-256加密：" + sha256);

        String sha384 = SHAUtils.sha384(text);
        System.out.println("SHA-384加密：" + sha384);

        String sha512 = SHAUtils.sha512(text);
        System.out.println("SHA-512加密：" + sha512);

        System.out.println("SHA-1加密校验：" + SHAUtils.verifySHA1(text, sha1));
        System.out.println("SHA-224加密校验：" + SHAUtils.verifySHA224(text, sha224));
        System.out.println("SHA-256加密校验：" + SHAUtils.verifySHA256(text, sha256));
        System.out.println("SHA-384加密校验：" + SHAUtils.verifySHA384(text, sha384));
        System.out.println("SHA-512加密校验：" + SHAUtils.verifySHA512(text, sha512));
    }
}
