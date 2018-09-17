/*
 * Copyright (c) 2018, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * 字符串工具类
 * 
 * @author Chanus
 * @date 2018-08-30 15:00:50
 * @since 0.0.1
 */
public class StringUtils {
	/**
	 * 判断字符串是否为空或空格
	 * 
	 * @param s	源字符串
	 * @return {@code true} 字符串为空；{@code false} 字符串不为空
	 * @since 0.0.1
	 */
	public static boolean isBlank(final String s) {
		return s == null || s.trim().length() == 0;
	}

	/**
	 * 判断字符串是否为非空或非空格
	 * 
	 * @param s	源字符串
	 * @return {@code true} 字符串不为空；{@code false} 字符串为空
	 * @since 0.0.1
	 */
	public static boolean isNotBlank(final String s) {
		return !isBlank(s);
	}

	/**
	 * 去除字符串首尾的空格
	 * 
	 * @param s	源字符串
	 * @return 去除首尾空格后的字符串
	 * @since 0.0.1
	 */
	public static String trim(String s) {
		return isBlank(s) ? null : s.trim();
	}

	/**
	 * 判断字符串是否包含某字符串
	 * 
	 * @param s	源字符串
	 * @param t	目标字符串
	 * @return {@code true} s包含字符串t；{@code false} s不包含字符串t
	 * @since 0.0.1
	 */
	public static boolean contains(String s, String t) {
		return (isBlank(s) || isBlank(t) || s.indexOf(t) == -1) ? false : true;
	}

	/**
	 * 安全的比较两个字符串是否相等
	 * 
	 * @param s	字符串1
	 * @param t	字符串2
	 * @return {@code true} 两个字符串相等；{@code false} 两个字符串不相等
	 * @since 0.0.1
	 */
	public static boolean equals(String s, String t) {
		return s != null ? s.equals(t) : t == null;
	}

	/**
	 * 判断字符串是否为纯数字
	 * 
	 * @param s	源字符串
	 * @return {@code true} 字符串是纯数字；{@code false} 字符串不是纯数字
	 * @since 0.0.1
	 */
	public static boolean isNumeric(String s) {
		return isNotBlank(s) && Pattern.compile("[0-9]*").matcher(s).matches();
	}
	
	/**
	 * 将Map转换成指定字符连接和分割的字符串
	 * 
	 * @param map	Map集合数据
	 * @param link	key和value之间的连接符，如"="
	 * @param separator	每组key和value之间的分割符，如"&"
	 * @return {@code link}与{@code separator}连接和分割的字符串
	 * @since 0.0.1
	 */
	public static String map2String(Map<String, ?> map, String link, String separator) {
		if (CollectionUtils.isEmpty(map))
			return null;
		
		StringBuffer string = new StringBuffer();
		for (String key : map.keySet()) {
			string.append(key).append(link).append(map.get(key).toString()).append(separator);
		}
		
		return string.substring(0, string.length() - separator.length());
	}
	
	/**
	 * 将Map转换成字符串
	 * 
	 * @param map	Map集合数据
	 * @return key1=value1&key2=value2...格式字符串
	 * @since 0.0.1
	 */
	public static String map2String(Map<String, ?> map) {
		return map2String(map, "=", "&");
	}
	
	/**
	 * 将数组转换成以{@code separator}分割的字符串
	 * 
	 * @param array	数据数组
	 * @param separator	分割符
	 * @return 以{@code separator}分割的字符串
	 * @since 0.0.1
	 */
	public static String array2String(Object[] array, String separator) {
		if (CollectionUtils.isEmpty(array) || isBlank(separator))
			return null;
		
		StringBuffer string = new StringBuffer();
		for (Object object : array) {
			if (object != null)
				string.append(object.toString()).append(separator);
		}
		
		return string.substring(0, string.length() - separator.length());
	}
	
	/**
	 * 将数组转换成以逗号分割的字符串
	 * 
	 * @param array	数据数组
	 * @return value1, value2...格式字符串
	 * @since 0.0.1
	 */
	public static String array2String(Object[] array) {
		return array2String(array, ",");
	}
	
	/**
	 * 验证字符串是否由数字和分隔符组成的
	 * 
	 * @param str	由指定分隔符分割的字符串，如1,02,3,4,55，不能为空
	 * @param count	分隔符分割的数字个数，若为null则不验证
	 * @param length	由分隔符分割的数字最大长度，若为null则不验证
	 * @param min	由分隔符分割的数字最小值，null不验证
	 * @param max	由分隔符分割的数字最大值，null不验证
	 * @param separator	分隔符，不能为空
	 * @param fixed	是否固定长度length，true则固定，false不固定
	 * @param ignore0	是否忽略0，若为null则不验证，true则02合法，false则02不合法
	 * @param repeat	是否允许重复，true则允许，false则不允许
	 * @return {@code true} 字符串符合指定格式；{@code false} 字符串不符合指定格式
	 * @since 0.0.1
	 */
	public static boolean isNumericWithSeparator(String str, Integer count, Integer length, Integer min, Integer max, String separator, boolean fixed, Boolean ignore0, boolean repeat) {
		if (isBlank(str) || isBlank(separator))
			return false;
		if (!isNumeric(str.substring(0, 1)) || !isNumeric(str.substring(str.length() - 1)))// 第一个或最后一个字符不为数字，如,1,02,3,4,55,
			return false;
		String arr[] = str.split(separator);
		if (count != null && arr.length != count)// 分隔符分割的数字个数不匹配
			return false;
		Set<String> set = new HashSet<String>();
		if (length == null) {// 不限制数字长度
			for (String s : arr) {
				if (!isNumeric(s))
					return false;
				if (ignore0 != null && !ignore0 && s.charAt(0) == '0')
					return false;
				if (min != null && Integer.parseInt(s) < min)
					return false;
				if (max != null && Integer.parseInt(s) > max)
					return false;
				set.add(s);
			}
		} else {
			for (String s : arr) {
				if (!isNumeric(s) || s.length() > length)// 非数字或数字长度大于最大长度
					return false;
				if (fixed && s.length() != length)// 数字固定长度length
					return false;
				if (ignore0 != null && !ignore0 && s.length() > 1 && s.charAt(0) == '0')
					return false;
				if (min != null && Integer.parseInt(s) < min)
					return false;
				if (max != null && Integer.parseInt(s) > max)
					return false;
				set.add(s);
			}
		}
		return repeat ? true : (set.size() == arr.length ? true : false);
	}

	/**
	 * 压缩字符串
	 * 
	 * @param s	待压缩的字符串内容
	 * @return 压缩后的字节数组
	 * @since 0.0.1
	 */
	public static byte[] compress(String s) {
		if (isBlank(s))
			return null;
		
		ByteArrayOutputStream baos = null;
		GZIPOutputStream gzos = null;
		try {
			baos = new ByteArrayOutputStream();
			gzos = new GZIPOutputStream(baos);
			gzos.write(s.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (gzos != null) {
				try {
					gzos.finish();
					gzos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (baos != null) {
				try {
					baos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return baos.toByteArray();
	}

	/**
	 * 解压流
	 * 
	 * @param b	待解压的内容
	 * @return 解压后的字节数组
	 * @since 0.0.1
	 */
	public static byte[] decompress(byte[] b) {
		if (b == null || b.length == 0)
			return null;
		
		ByteArrayInputStream bais = null;
		ByteArrayOutputStream baos = null;
		GZIPInputStream gzis = null;
		try {
			baos = new ByteArrayOutputStream();
			bais = new ByteArrayInputStream(b);
			gzis = new GZIPInputStream(bais);
			byte[] buffer = new byte[1024];
			int i = -1;
			while ((i = gzis.read(buffer, 0, buffer.length)) != -1)
				baos.write(buffer, 0, i);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (gzis != null) {
				try {
					gzis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (bais != null) {
				try {
					bais.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (baos != null) {
				try {
					baos.flush();
					baos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return baos.toByteArray();
	}

	/**
	 * 将源字符串重复生成{@code num}次组成新的字符串
	 *
	 * @param s	源字符串
	 * @param num	重复生成次数
	 * @return 返回已生成的重复字符串
	 * @since 0.0.1
	 */
	public static String repeat(final String s, final int num) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < num; i++)
			sb.append(s);
		return sb.toString();
	}
	
	/**
	 * 将源字符串指定位置的字符替换成指定个数的目标字符串
	 * 
	 * @param source	源字符串
	 * @param target	目标字符串
	 * @param begin	开始替换的位置
	 * @param end	结束替换的位置
	 * @param num	目标字符串重复次数
	 * @return 返回替换后的字符串
	 * @since 0.0.1
	 */
	public static String replace(final String source, final String target, final int begin, final int end, final int num) {
		return source.substring(0, begin) + repeat(target, num) + source.substring(end);
	}
	
	/**
	 * 将源字符串指定位置的字符替换成"*"
	 * 
	 * @param source	源字符串
	 * @param begin	开始替换的位置
	 * @param end	结束替换的位置
	 * @return 返回替换后的字符串
	 * @since 0.0.1
	 */
	public static String replace(final String source, final int begin, final int end) {
		return source.substring(0, begin) + repeat("*", end - begin) + source.substring(end);
	}
}
