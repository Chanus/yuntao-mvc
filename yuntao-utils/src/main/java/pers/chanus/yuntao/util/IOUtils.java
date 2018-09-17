/*
 * Copyright (c) 2018, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.util;

import java.io.Closeable;
import java.io.IOException;

/**
 * I/O工具类
 * 
 * @author Chanus
 * @date 2018-08-30 15:22:57
 * @since 0.0.1
 */
public class IOUtils {
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
