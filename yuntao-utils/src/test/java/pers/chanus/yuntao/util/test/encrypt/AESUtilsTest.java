/*
 * Copyright (c), 2019-2019, Chanus and/or its affiliates. All rights reserved.
 * FileName: AESUtilsTest
 * Author:   Chanus
 * Date:     2019-06-22 23:53
 * Description: 工具类测试
 * History:
 * <author>          <time>          <version>          <desc>
 */
package pers.chanus.yuntao.util.test.encrypt;

import org.junit.Test;
import pers.chanus.yuntao.util.encrypt.AESUtils;

/**
 * AESUtils工具类测试
 *
 * @author Chanus
 * @date 2019-06-22 23:53
 * @since 0.0.1
 */
public class AESUtilsTest {
    String key = "2wH7a7TxmIsWb3+1JM0OVQ==";
    String customKey = "0123456789ABCDEF";

    String text = "测试AES加密！";

    @Test
    public void createKeyTest() {
        String seed = "yuntao";
        String key1 = AESUtils.generateKey(seed);
        String key2 = AESUtils.generateKey(seed);
        String key3 = AESUtils.generateKey(seed);

        System.out.println("key1 = " + key1);
        System.out.println("key2 = " + key2);
        System.out.println("key3 = " + key3);
    }

    @Test
    public void aesCBCTest() {
        String vector = "ABCDEFGHIJKLMNOP";

        String cipher1 = AESUtils.encrypt(text, key, vector);
        System.out.println("指定秘钥和向量加密：" + cipher1);

        String cipher2 = AESUtils.encrypt(text, key);
        System.out.println("指定秘钥，使用默认向量加密：" + cipher2);

        String cipher3 = AESUtils.encrypt(text);
        System.out.println("使用默认秘钥和向量加密：" + cipher3);

        String text1 = AESUtils.decrypt(cipher1, key, vector);
        System.out.println("指定秘钥和向量解密：" + text1);

        String text2 = AESUtils.decrypt(cipher2, key);
        System.out.println("指定秘钥，使用默认向量解密：" + text2);

        String text3 = AESUtils.decrypt(cipher3);
        System.out.println("使用默认秘钥和向量解密：" + text3);

        String cipher4 = AESUtils.encrypt(text, customKey, vector);
        System.out.println("指定自定义秘钥和向量加密：" + cipher4);

        String cipher5 = AESUtils.encrypt(text, customKey);
        System.out.println("指定自定义秘钥，使用默认向量加密：" + cipher5);

        String text4 = AESUtils.decrypt(cipher4, customKey, vector);
        System.out.println("指定自定义秘钥和向量解密：" + text4);

        String text5 = AESUtils.decrypt(cipher5, customKey);
        System.out.println("指定自定义秘钥，使用默认向量解密：" + text5);
    }

    @Test
    public void aesECBTest() {
        String cipher1 = AESUtils.encryptWithEcb(text, key);
        System.out.println("指定秘钥加密：" + cipher1);

        String text1 = AESUtils.decryptWithEcb(cipher1, key);
        System.out.println("指定秘钥解密：" + text1);

        String cipher2 = AESUtils.encryptWithEcb(text, customKey);
        System.out.println("指定自定义秘钥加密：" + cipher2);

        String text2 = AESUtils.decryptWithEcb(cipher2, customKey);
        System.out.println("指定自定义秘钥解密：" + text2);
    }
}
