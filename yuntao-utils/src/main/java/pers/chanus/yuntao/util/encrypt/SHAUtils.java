/*
 * Copyright (c) 2018, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.util.encrypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import pers.chanus.yuntao.util.StringUtils;

/**
 * SHA加密工具类
 * 
 * @author Chanus
 * @date 2018-12-20 16:35:47
 * @since 0.0.5
 */
public class SHAUtils {
	/** 用于加密的字符 */
	private static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
	
	private static final String SHA1 = "SHA-1";
	
	private static final String SHA2224 = "SHA-224";
	
	private static final String SHA2256 = "SHA-256";
	
	private static final String SHA2384 = "SHA-384";
	
	private static final String SHA2512 = "SHA-512";
	
	/**
	 * SHA1、SHA2摘要加密算法
	 * 
	 * @param text	明文字符串
	 * @param digestType	加密类型：SHA-1，SHA-224，SHA-256，SHA-384，SHA-512
	 * @return 加密后的密文字符串
	 * @since 0.0.5
	 */
	private final static String digest(String text, String digestType) {
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
			char buf[] = new char[len * 2];
			int k = 0;
			for (int i = 0; i < len; i++) {
				byte b = digest[i];
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
	 * @param text	明文字符串
	 * @return SHA-1加密后的密文字符串
	 * @since 0.0.5
	 */
	public static String sha1(String text) {
		return digest(text, SHA1);
	}

	/**
	 * SHA2-224加密
	 * 
	 * @param text	明文字符串
	 * @return SHA2-224加密后的密文字符串
	 * @since 0.0.5
	 */
	public static String sha2224(String text) {
		return digest(text, SHA2224);
	}

	/**
	 * SHA2-256加密
	 * 
	 * @param text	明文字符串
	 * @return SHA2-256加密后的密文字符串
	 * @since 0.0.5
	 */
	public static String sha2256(String text) {
		return digest(text, SHA2256);
	}

	/**
	 * SHA2-384加密
	 * 
	 * @param text	明文字符串
	 * @return SHA2-384加密后的密文字符串
	 * @since 0.0.5
	 */
	public static String sha2384(String text) {
		return digest(text, SHA2384);
	}

	/**
	 * SHA2-512加密
	 * 
	 * @param text	明文字符串
	 * @return SHA2-512加密后的密文字符串
	 * @since 0.0.5
	 */
	public static String sha2512(String text) {
		return digest(text, SHA2512);
	}
}
