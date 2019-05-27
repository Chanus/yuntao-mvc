/*
 * Copyright (c) 2019, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 属性文件操作工具类
 * 
 * @author Chanus
 * @date 2019-05-26 15:25:36
 * @since 0.0.8
 */
public class PropertiesUtils {
	/**
	 * 默认的属性文件为classes目录下的config.properties
	 */
	private final static String properiesName = "config.properties";
	
	/**
	 * 从属性文件中读取属性值
	 * 
	 * @param filePath	属性文件路径
	 * @param key	要读取的属性名称
	 * @return 属性值
	 * @since 0.0.8
	 */
	public static String read(String filePath, String key) {
		Properties properties = new Properties();
		InputStream is = null;
		try {
			is = new FileInputStream(filePath);
			properties.load(is);
			return properties.getProperty(key);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			IOUtils.closeQuietly(is);
		}
	}
	
	/**
	 * 从属性文件中读取全部属性值，并封装到Map中
	 * 
	 * @param filePath	属性文件路径
	 * @return 存储属性值的Map
	 * @since 0.0.8
	 */
	public static Map<String, String> read(String filePath) {
		Properties properties = new Properties();
		InputStream is = null;
		try {
			is = new FileInputStream(filePath);
			properties.load(is);
			Enumeration<?> en = properties.propertyNames();
			Map<String, String> map = new HashMap<String, String>();
			String key = null;
			while (en.hasMoreElements()) {
				key = (String) en.nextElement();
				map.put(key, properties.getProperty(key));
			}
			return map;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			IOUtils.closeQuietly(is);
		}
	}
	
	/**
	 * 从默认的属性文件中读取属性值
	 * 
	 * @param key	要读取的属性名称
	 * @return 属性值
	 * @since 0.0.8
	 */
	public static String readDefault(String key) {
		Properties properties = new Properties();
		InputStream is = null;
		try {
			is = PropertiesUtils.class.getClassLoader().getResourceAsStream(properiesName);
			properties.load(is);
			return properties.getProperty(key);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			IOUtils.closeQuietly(is);
		}
	}
	
	/**
	 * 从默认的属性文件中读取全部属性值，并封装到Map中
	 * 
	 * @return 存储属性值的Map
	 * @since 0.0.8
	 */
	public static Map<String, String> readDefault() {
		Properties properties = new Properties();
		InputStream is = null;
		try {
			is = PropertiesUtils.class.getClassLoader().getResourceAsStream(properiesName);
			properties.load(is);
			Enumeration<?> en = properties.propertyNames();
			Map<String, String> map = new HashMap<String, String>();
			String key = null;
			while (en.hasMoreElements()) {
				key = (String) en.nextElement();
				map.put(key, properties.getProperty(key));
			}
			return map;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			IOUtils.closeQuietly(is);
		}
	}
	
	/**
	 * 向属性文件中写入信息
	 * 
	 * @param filePath	属性文件路径
	 * @param key	属性名称
	 * @param value	属性值
	 * @since 0.0.8
	 */
	public static void write(String filePath, String key, String value) {
		Properties properties = new Properties();
		InputStream is = null;
		OutputStream os = null;
		try {
			is = new FileInputStream(filePath);
			properties.load(is);
			os = new FileOutputStream(filePath);
			properties.setProperty(key, value);
			properties.store(os, "Update '" + key + "' value");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(is);
			IOUtils.closeQuietly(os);
		}
	}
	
	/**
	 * 向默认的属性文件中写入信息
	 * 
	 * @param key	属性名称
	 * @param value	属性值
	 * @since 0.0.8
	 */
	public static void writeDefault(String key, String value) {
		Properties properties = new Properties();
		InputStream is = null;
		OutputStream os = null;
		try {
			is = new FileInputStream(properiesName);
			properties.load(is);
			os = new FileOutputStream(PropertiesUtils.class.getClassLoader().getResource(properiesName).getFile());
			properties.setProperty(key, value);
			properties.store(os, "Update '" + key + "' value");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(is);
			IOUtils.closeQuietly(os);
		}
	}
}
