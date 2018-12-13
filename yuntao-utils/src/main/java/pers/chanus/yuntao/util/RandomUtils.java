/*
 * Copyright (c) 2018, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.util;

import java.util.Random;
import java.util.UUID;

/**
 * 获取随机数工具类
 * 
 * @author Chanus
 * @date 2018-08-30 15:05:54
 * @since 0.0.1
 */
public class RandomUtils {
	/** 数字和大小写字母 */
	private static final String ALLCHAR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	/** 大小写字母 */
	private static final String LETTERCHAR = "abcdefghijkllmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	/** 数字 */
	private static final String NUMBERCHAR = "0123456789";
	
	/**
	 * 随机获取整数
	 * 
	 * @param min	最小值
	 * @param max	最大值
	 * @return {@code min}到{@code max}之间的整数
	 * @since 0.0.1
	 */
	public static int getRandomInt(int min, int max) {
		return min + new Double(Math.random() * (max - min + 1)).intValue();
	}

	/**
	 * 获取固定长度的随机数字
	 *
	 * @param length	数字长度
	 * @return {@code length}位数字字符串
	 * @since 0.0.1
	 */
	public static String getRandomInt(int length) {
		StringBuffer result = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			result.append(NUMBERCHAR.charAt(random.nextInt(NUMBERCHAR.length())));
		}
		return result.toString();
	}
	
	/**
	 * 随机获取浮点数
	 * 
	 * @param min	最小值
	 * @param max	最大值
	 * @return {@code min}到{@code max}之间的浮点数
	 * @since 0.0.1
	 */
	public static double getRandomDouble(double min, double max) {
		return min + (Math.random() * (max - min));
	}
	
	/**
	 * 随机获取ASCII码33到126之间字符
	 * 
	 * @return ASCII码33到126之间的字符
	 * @since 0.0.1
	 */
	public static char getRandomChar() {
		return (char) (getRandomInt(33, 126));
	}
	
	/**
	 * 随机获取0-9，a-z，A-Z之间的字符
	 * 
	 * @return 0-9，a-z，A-Z之间的字符
	 * @since 0.0.1
	 */
	public static char getRandomNormalChar() {
		return ALLCHAR.charAt(new Random().nextInt(ALLCHAR.length()));
	}
	
	/**
	 * 随机获取a-z，A-Z之间的字符
	 * 
	 * @return a-z，A-Z之间的字符
	 * @since 0.0.1
	 */
	public static char getRandomLetterChar() {
		return LETTERCHAR.charAt(new Random().nextInt(LETTERCHAR.length()));
	}
	
	/**
	 * 随机获取由ASCII码33到126之间的字符组成的字符串
	 * 
	 * @param length	字符串长度
	 * @return ASCII码33到126之间的字符组成的字符串
	 * @since 0.0.1
	 */
	public static String getRandomString(int length) {
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < length; i++) {
			result.append(getRandomChar());
		}
		return result.toString();
	}
	
	/**
	 * 随机获取由0-9，a-z，A-Z之间的字符组成的字符串
	 * 
	 * @param length	字符串长度
	 * @return 0-9，a-z，A-Z之间的字符组成的字符串
	 * @since 0.0.1
	 */
	public static String getRandomNormalString(int length) {
		StringBuffer result = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			result.append(ALLCHAR.charAt(random.nextInt(ALLCHAR.length())));
		}
		return result.toString();
	}
	
	/**
	 * 随机获取由a-z，A-Z之间的字符组成的字符串
	 * 
	 * @param length	字符串长度
	 * @return a-z，A-Z之间的字符组成的字符串
	 * @since 0.0.1
	 */
	public static String getRandomLetterString(int length) {
		StringBuffer result = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			result.append(LETTERCHAR.charAt(random.nextInt(LETTERCHAR.length())));
		}
		return result.toString();
	}
	
	/**
	 * 获取由时间戳和5位随机数字组成的纯数字字符串
	 * 
	 * @return 16位纯数字字符串
	 * @since 0.0.1
	 */
	public static String getRandomUniqueNo() {
		return String.valueOf(System.currentTimeMillis()) + String.valueOf((int) ((Math.random() * 9 + 1) * 10000));
	}
	
	/**
	 * 根据UUID的hashCode值生成的纯数字字符串
	 * 
	 * @param machineId	机器码，为1-9之间的数据
	 * @param length	生成字符串的长度(不包含一位机器码)，length必须大于10，否则默认10
	 * @return <code>length + 1</code>位纯数字字符串
	 * @since 0.0.4
	 */
	public static String getUUIDUniqueNo(int machineId, int length) {
		machineId = (machineId < 1 || machineId > 10) ? 1 : machineId;
		length = length >= 10 ? length : 10;
		int hashCodeV = UUID.randomUUID().toString().hashCode();
		if (hashCodeV < 0) {// 有可能是负数
			hashCodeV = -hashCodeV;
		}

		return machineId + String.format("%0" + length + "d", hashCodeV);
	}
	
	/**
	 * 获取由UUID的hashCode值和5位随机数字组成的纯数字字符串
	 * 
	 * @param machineId	机器码，为1-9之间的数据
	 * @return 16位纯数字字符串
	 * @since 0.0.4
	 */
	public static String getRandomUUIDUniqueNo(int machineId) {
		machineId = (machineId < 1 || machineId > 10) ? 1 : machineId;
		int hashCodeV = UUID.randomUUID().toString().hashCode();
		if (hashCodeV < 0) {// 有可能是负数
			hashCodeV = -hashCodeV;
		}

		return machineId + String.format("%015d", Long.parseLong(hashCodeV + String.valueOf((int) ((Math.random() * 9 + 1) * 10000))));
	}

	/**
	 * 获取无连接符"-"的小写UUID
	 *
	 * @return 无连接符"-"的小写UUID
	 * @since 0.0.1
	 */
	public static String getLowercaseUUID() {
		String s = UUID.randomUUID().toString();
		return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) + s.substring(19, 23) + s.substring(24);
	}

	/**
	 * 获取无连接符"-"的大写UUID
	 *
	 * @return 无连接符"-"的大写UUID
	 * @since 0.0.1
	 */
	public static String getUppercaseUUID() {
		return getLowercaseUUID().toUpperCase();
	}
}
