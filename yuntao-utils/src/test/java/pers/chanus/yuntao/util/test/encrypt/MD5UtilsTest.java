/*
 * Copyright (c), 2019-2019, Chanus and/or its affiliates. All rights reserved.
 * FileName: MD5UtilsTest
 * Author:   Chanus
 * Date:     2019-06-22 17:32
 * Description: 工具类测试
 * History:
 * <author>          <time>          <version>          <desc>
 */
package pers.chanus.yuntao.util.test.encrypt;

import org.junit.Test;
import pers.chanus.yuntao.util.encrypt.MD5Utils;

/**
 * MD5Utils工具类测试
 *
 * @author Chanus
 * @date 2019-06-22 17:32
 * @since 0.0.1
 */
public class MD5UtilsTest {
    @Test
    public void md5Test() {
        String text1 = "hello";
        String text2 = "Chanus";
        String text3 = "Hello, Chanus!";
        String text4 = "你好，Chanus！";
        String key = "yuntao";

        String cipher1 = MD5Utils.md5(text1);
        String cipher2 = MD5Utils.md5(text2);
        String cipher3 = MD5Utils.md5(text3);
        String cipher4 = MD5Utils.md5(text4);

        System.out.println("MD5加密text1：" + cipher1);
        System.out.println("MD5加密text2：" + cipher2);
        System.out.println("MD5加密text3：" + cipher3);
        System.out.println("MD5加密text4：" + cipher4);

        String cipher5 = MD5Utils.md5(text1, key);
        String cipher6 = MD5Utils.md5(text2, key);
        String cipher7 = MD5Utils.md5(text3, key);
        String cipher8 = MD5Utils.md5(text4, key);

        System.out.println("MD5根据key加密text1：" + cipher5);
        System.out.println("MD5根据key加密text2：" + cipher6);
        System.out.println("MD5根据key加密text3：" + cipher7);
        System.out.println("MD5根据key加密text4：" + cipher8);

        System.out.println("MD5加密text1校验：" + MD5Utils.verify(text1, cipher1));
        System.out.println("MD5加密text2校验：" + MD5Utils.verify(text2, cipher2));
        System.out.println("MD5加密text3校验：" + MD5Utils.verify(text3, cipher3));
        System.out.println("MD5加密text4校验：" + MD5Utils.verify(text4, cipher4));

        System.out.println("MD5根据key加密text1校验：" + MD5Utils.verify(text1, cipher5, key));
        System.out.println("MD5根据key加密text2校验：" + MD5Utils.verify(text2, cipher6, key));
        System.out.println("MD5根据key加密text3校验：" + MD5Utils.verify(text3, cipher7, key));
        System.out.println("MD5根据key加密text4校验：" + MD5Utils.verify(text4, cipher8, key));
    }
}
