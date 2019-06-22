/*
 * Copyright (c) 2018, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.util.encrypt;

import pers.chanus.yuntao.util.StringUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * SHA加密工具类
 *
 * @author Chanus
 * @date 2018-12-20 16:35:47
 * @since 0.0.5
 */
public class SHAUtils {
    /**
     * 用于加密的字符
     */
    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    private static final String SHA1 = "SHA-1";

    private static final String SHA224 = "SHA-224";

    private static final String SHA256 = "SHA-256";

    private static final String SHA384 = "SHA-384";

    private static final String SHA512 = "SHA-512";

    /**
     * SHA1、SHA2摘要加密算法
     *
     * @param text       明文字符串
     * @param digestType 加密类型：SHA-1，SHA-224，SHA-256，SHA-384，SHA-512
     * @return 加密后的密文字符串
     * @since 0.0.5
     */
    private static String digest(String text, String digestType) {
        if (StringUtils.isBlank(text) || StringUtils.isBlank(digestType))
            return null;

        try {
            // 获得摘要算法的 MessageDigest 对象
            MessageDigest messageDigest = MessageDigest.getInstance(digestType);
            // 使用指定的字节更新摘要
            messageDigest.update(text.getBytes());
            // 获得密文
            byte[] digest = messageDigest.digest();
            // 把密文转换成十六进制的字符串形式
            int len = digest.length;
            char[] buf = new char[len * 2];
            int k = 0;
            for (byte b : digest) {
                buf[k++] = HEX_DIGITS[b >>> 4 & 0xf];
                buf[k++] = HEX_DIGITS[b & 0xf];
            }

            return new String(buf);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * SHA-1加密
     *
     * @param text 明文字符串
     * @return SHA-1加密后的密文字符串
     * @since 0.0.5
     */
    public static String sha1(String text) {
        return digest(text, SHA1);
    }

    /**
     * SHA-224加密
     *
     * @param text 明文字符串
     * @return SHA-224加密后的密文字符串
     * @since 0.0.5
     */
    public static String sha224(String text) {
        return digest(text, SHA224);
    }

    /**
     * SHA-256加密
     *
     * @param text 明文字符串
     * @return SHA-256加密后的密文字符串
     * @since 0.0.5
     */
    public static String sha256(String text) {
        return digest(text, SHA256);
    }

    /**
     * SHA-384加密
     *
     * @param text 明文字符串
     * @return SHA-384加密后的密文字符串
     * @since 0.0.5
     */
    public static String sha384(String text) {
        return digest(text, SHA384);
    }

    /**
     * SHA-512加密
     *
     * @param text 明文字符串
     * @return SHA-512加密后的密文字符串
     * @since 0.0.5
     */
    public static String sha512(String text) {
        return digest(text, SHA512);
    }

    /**
     * SHA-1验证字符串
     *
     * @param text   明文字符串
     * @param cipher 密文字符串
     * @return {@code true} 验证通过；{@code false} 验证不通过
     * @since 0.0.8
     */
    public static boolean verifySHA1(String text, String cipher) {
        return sha1(text).equals(cipher);
    }

    /**
     * SHA-224验证字符串
     *
     * @param text   明文字符串
     * @param cipher 密文字符串
     * @return {@code true} 验证通过；{@code false} 验证不通过
     * @since 0.0.8
     */
    public static boolean verifySHA224(String text, String cipher) {
        return sha224(text).equals(cipher);
    }

    /**
     * SHA-256验证字符串
     *
     * @param text   明文字符串
     * @param cipher 密文字符串
     * @return {@code true} 验证通过；{@code false} 验证不通过
     * @since 0.0.8
     */
    public static boolean verifySHA256(String text, String cipher) {
        return sha256(text).equals(cipher);
    }

    /**
     * SHA-384验证字符串
     *
     * @param text   明文字符串
     * @param cipher 密文字符串
     * @return {@code true} 验证通过；{@code false} 验证不通过
     * @since 0.0.8
     */
    public static boolean verifySHA384(String text, String cipher) {
        return sha384(text).equals(cipher);
    }

    /**
     * SHA-512验证字符串
     *
     * @param text   明文字符串
     * @param cipher 密文字符串
     * @return {@code true} 验证通过；{@code false} 验证不通过
     * @since 0.0.8
     */
    public static boolean verifySHA512(String text, String cipher) {
        return sha512(text).equals(cipher);
    }
}
