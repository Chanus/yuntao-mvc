/*
 * Copyright (c) 2018, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.util.encrypt;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * AES(对称加密)加密解密工具类
 *
 * @author Chanus
 * @date 2018-08-30 15:36:54
 * @since 0.0.1
 */
public class AESUtils {
    /**
     * AES加密
     */
    private static final String KEY_ALGORITHM = "AES";
    /**
     * AES CBC加密算法
     */
    private static final String CIPHER_ALGORITHM_CBC = "AES/CBC/PKCS5PADDING";
    /**
     * AES ECB加密算法
     */
    private static final String CIPHER_ALGORITHM_ECB = "AES/ECB/PKCS5PADDING";
    /**
     * AES密钥（长度可以选择128bits【16bytes】，192bits【24bytes】和256bits【32bytes】密钥，其他不行）
     */
    private static final String DEFAULT_KEY = "ChanusYun@yuntao";
    /**
     * 初始化向量参数，AES为16bytes
     */
    private static final String INIT_VECTOR = "yuntao0123456789";

    /**
     * 生成AES加密密钥字符串，种子相同则生成的密钥字符串相同
     *
     * @param seed 生成强随机数的种子
     * @return AES加密密钥字符串
     * @since 0.0.1
     */
    public static String generateKey(final String seed) {
        try {
            // 实例化
            KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_ALGORITHM);
            // 设置密钥长度128
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(seed.getBytes(StandardCharsets.UTF_8));
            keyGenerator.init(128, secureRandom);
            // 生成密钥
            SecretKey secretKey = keyGenerator.generateKey();
            // 返回密钥
            return Base64.getEncoder().encodeToString(secretKey.getEncoded());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * AES CBC模式加密数据
     *
     * @param text   待加密内容
     * @param key    128位的密钥字符串，或generateKey方法生成的密钥字符串
     * @param vector 128位的初始化向量字符串
     * @return Base64转码后的加密数据
     * @since 0.0.1
     */
    public static String encrypt(final String text, final String key, final String vector) {
        try {
            // 兼容128位任意字符串和generateKey生成的Base64转码的密钥字符串
            byte[] b = key.length() == 16 ? key.getBytes(StandardCharsets.UTF_8) : Base64.getDecoder().decode(key);
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM_CBC);
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(b, KEY_ALGORITHM), new IvParameterSpec(vector.getBytes(StandardCharsets.UTF_8)));
            return Base64.getEncoder().encodeToString(cipher.doFinal(text.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * AES CBC模式加密数据，使用默认初始化向量
     *
     * @param text 待加密内容
     * @param key  128位的密钥字符串，或generateKey方法生成的密钥字符串
     * @return Base64转码后的加密数据
     * @since 0.0.1
     */
    public static String encrypt(final String text, final String key) {
        return encrypt(text, key, INIT_VECTOR);
    }

    /**
     * AES CBC模式加密数据，使用默认密钥和初始化向量
     *
     * @param text 待加密内容
     * @return Base64转码后的加密数据
     * @since 0.0.1
     */
    public static String encrypt(final String text) {
        return encrypt(text, DEFAULT_KEY, INIT_VECTOR);
    }

    /**
     * AES CBC模式解密数据
     *
     * @param text   待解密内容
     * @param key    128位的密钥字符串，或generateKey方法生成的密钥字符串
     * @param vector 128位的初始化向量字符串
     * @return 解密后数据
     * @since 0.0.1
     */
    public static String decrypt(final String text, final String key, final String vector) {
        try {
            // 兼容128位任意字符串和generateKey生成的Base64转码的密钥字符串
            byte[] b = key.length() == 16 ? key.getBytes(StandardCharsets.UTF_8) : Base64.getDecoder().decode(key);
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM_CBC);
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(b, KEY_ALGORITHM), new IvParameterSpec(vector.getBytes(StandardCharsets.UTF_8)));
            return new String(cipher.doFinal(Base64.getDecoder().decode(text)), StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * AES CBC模式解密数据，使用默认初始化向量
     *
     * @param text 待解密内容
     * @param key  128位的密钥字符串，或generateKey方法生成的密钥字符串
     * @return 解密后数据
     * @since 0.0.1
     */
    public static String decrypt(String text, String key) {
        return decrypt(text, key, INIT_VECTOR);
    }

    /**
     * AES CBC模式解密数据，使用默认密钥和初始化向量
     *
     * @param text 待解密内容
     * @return 解密后数据
     * @since 0.0.1
     */
    public static String decrypt(String text) {
        return decrypt(text, DEFAULT_KEY, INIT_VECTOR);
    }

    /**
     * AES ECB模式加密数据
     *
     * @param text 待加密内容
     * @param key  128位的密钥字符串，或generateKey方法生成的密钥字符串
     * @return Base64转码后的加密数据
     * @since 0.0.1
     */
    public static String encryptWithEcb(String text, String key) {
        try {
            // 兼容128位任意字符串和generateKey生成的Base64转码的密钥字符串
            byte[] b = key.length() == 16 ? key.getBytes(StandardCharsets.UTF_8) : Base64.getDecoder().decode(key);
            // 创建密码器
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM_ECB);
            // 使用密钥初始化，设置为加密模式
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(b, KEY_ALGORITHM));
            // 通过Base64转码加密数据后返回
            return Base64.getEncoder().encodeToString(cipher.doFinal(text.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * AES ECB模式解密数据
     *
     * @param text 待解密内容
     * @param key  128位的密钥字符串，或generateKey方法生成的密钥字符串
     * @return 解密后数据
     * @since 0.0.1
     */
    public static String decryptWithEcb(String text, String key) {
        try {
            // 兼容128位任意字符串和generateKey生成的Base64转码的密钥字符串
            byte[] b = key.length() == 16 ? key.getBytes(StandardCharsets.UTF_8) : Base64.getDecoder().decode(key);
            // 创建密码器
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM_ECB);
            // 使用密钥初始化，设置为解密模式
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(b, KEY_ALGORITHM));
            // 返回解密后数据
            return new String(cipher.doFinal(Base64.getDecoder().decode(text)), StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
