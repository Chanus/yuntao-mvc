/*
 * Copyright (c) 2018, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.util;

import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

/**
 * I/O工具类
 * 
 * @author Chanus
 * @date 2018-08-30 15:22:57
 * @since 0.0.1
 */
public class IOUtils {
	/**
	 * 将字节数组写入输出流
	 * 
	 * @param data	字节数组
	 * @param output	输出流
	 * @throws IOException	I/O异常
	 * @since 0.0.3
	 */
	public static void write(final byte[] data, final OutputStream output) throws IOException {
		if (data != null)
			output.write(data);
	}
	
	/**
	 * 将字符从字符串写入使用指定的字符编码输出流
	 * 
	 * @param data	字符串
	 * @param output	输出流
	 * @param encoding	字符编码
	 * @throws IOException I/O异常
	 * @throws UnsupportedEncodingException 字符编码不支持时抛出
	 * @since 0.0.3
	 */
	public static void write(final String data, final OutputStream output, final String encoding) throws IOException {
		if (data != null)
			output.write(data.getBytes(encoding));
	}
	
	/**
	 * 关闭资源
	 * 
	 * @param closeable	待关闭的资源
	 * @since 0.0.1
	 */
	public static void close(Closeable closeable) {
		if (closeable != null) {
			try {
				closeable.close();
			} catch (IOException e) {
				throw new RuntimeException("IOException occurred.", e);
			}
		}
	}

	/**
	 * 安静的关闭资源
	 * 
	 * @param closeable	待关闭的资源
	 * @since 0.0.1
	 */
	public static void closeQuietly(Closeable closeable) {
		if (closeable != null) {
			try {
				closeable.close();
			} catch (IOException e) {}
		}
	}

}
