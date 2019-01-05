/*
 * Copyright (c) 2018, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

/**
 * 流操作工具类
 * 
 * @author Chanus
 * @date 2018-08-30 15:01:34
 * @since 0.0.1
 */
public class StreamUtils {
	/**
	 * 从输入流中读取数据到字节数组
	 * 
	 * @param inputStream	输入流
	 * @return 数据字节数组
	 * @since 0.0.1
	 */
	public static byte[] readInputStream2Byte(InputStream inputStream) {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		int len = 0;
		byte[] buffer = new byte[1024];
		try {
			while ((len = inputStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, len);
			}
			buffer = outStream.toByteArray();// 网页的二进制数据
		} catch (IOException e) {
			throw new RuntimeException("IOException occurred.", e);
		} finally {
			IOUtils.close(outStream);
			IOUtils.close(inputStream);
		}
		return buffer;
	}

	/**
	 * 从输入流中读取数据到字符串
	 * 
	 * @param inputStream	输入流
	 * @return 数据字符串
	 * @since 0.0.1
	 */
	public static String readInputStream2String(InputStream inputStream) {
		StringBuffer stringBuffer = new StringBuffer();
		byte[] b = new byte[4096];
		try {
			for (int n; (n = inputStream.read(b)) != -1;) {
				stringBuffer.append(new String(b, 0, n));
			}
		} catch (IOException e) {
			throw new RuntimeException("IOException occurred.", e);
		} finally {
			IOUtils.close(inputStream);
		}
		return stringBuffer.toString();
	}
	
	/**
	 * 将字节数组写入输出流
	 * 
	 * @param data	字节数组
	 * @param os	输出流
	 * @throws IOException	I/O异常
	 * @since 0.0.5
	 */
	public static void write(final byte[] data, final OutputStream os) throws IOException {
		if (data != null)
			os.write(data);
	}
	
	/**
	 * 将字符从字符串写入使用指定的字符编码输出流
	 * 
	 * @param data	字符串
	 * @param os	输出流
	 * @param encoding	字符编码
	 * @throws IOException I/O异常
	 * @throws UnsupportedEncodingException 字符编码不支持时抛出
	 * @since 0.0.5
	 */
	public static void write(final String data, final OutputStream os, final String encoding) throws IOException {
		if (data != null)
			os.write(data.getBytes(encoding));
	}
}