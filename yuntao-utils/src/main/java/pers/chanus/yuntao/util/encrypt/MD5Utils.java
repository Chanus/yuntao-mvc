/*
 * Copyright (c) 2018, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.util.encrypt;

import java.security.MessageDigest;

/**
 * MD5加密工具类
 * 
 * @author Chanus
 * @date 2018-08-30 15:37:15
 * @since 0.0.1
 */
public class MD5Utils {
	/** 用于加密的字符 */
	private static final String[] HEX_DIGITS = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

	private static String byteArrayToHexString(byte[] b) {
		StringBuffer hex = new StringBuffer();
		for (int i = 0; i < b.length; i++)
			hex.append(byteToHexString(b[i]));

		return hex.toString();
	}

	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n += 256;
		int d1 = n / 16;
		int d2 = n % 16;
		return HEX_DIGITS[d1] + HEX_DIGITS[d2];
	}

	/**
	 * MD5加密字符串
	 * 
	 * @param text	明文字符串
	 * @return MD5加密后的密文字符串
	 * @since 0.0.1
	 */
	public static String md5(String text) {
		try {
			return byteArrayToHexString(MessageDigest.getInstance("MD5").digest(text.getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * MD5根据密钥加密字符串
	 * 
	 * @param text	明文字符串
	 * @param key	密钥
	 * @return MD5加密后的密文字符串
	 * @since 0.0.1
	 */
	public static String md5(String text, String key) {
		return md5(key + text);
	}
	
	/**
	 * MD5验证字符串
	 * 
	 * @param text	明文字符串
	 * @param ciphertext	密文字符串
	 * @return {@code true} 验证通过；{@code false} 验证不通过
	 * @since 0.0.1
	 */
	public static boolean verify(String text, String ciphertext) {
		return md5(text).equals(ciphertext) ? true : false;
	}
	
	/**
	 * MD5根据密钥验证字符串
	 * 
	 * @param text	明文字符串
	 * @param key	密钥
	 * @param ciphertext	密文字符串
	 * @return {@code true} 验证通过；{@code false} 验证不通过
	 * @since 0.0.1
	 */
	public static boolean verify(String text, String key, String ciphertext) {
		return verify(key + text, ciphertext);
	}
}