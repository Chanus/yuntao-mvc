/*
 * Copyright (c) 2018, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.util;

import java.io.*;
import java.nio.charset.Charset;

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
     * @param data 字节数组
     * @param os   输出流
     * @throws IOException I/O异常
     * @see StreamUtils#write(byte[], OutputStream)
     * @since 0.0.3
     */
    @Deprecated
    public static void write(final byte[] data, final OutputStream os) throws IOException {
        if (data != null)
            os.write(data);
    }

    /**
     * 将字符从字符串写入使用指定的字符编码输出流
     *
     * @param data     字符串
     * @param os       输出流
     * @param encoding 字符编码
     * @throws IOException                  I/O异常
     * @throws UnsupportedEncodingException 字符编码不支持时抛出
     * @see StreamUtils#write(String, OutputStream, String)
     * @since 0.0.3
     */
    @Deprecated
    public static void write(final String data, final OutputStream os, final String encoding) throws IOException {
        if (data != null)
            os.write(data.getBytes(encoding));
    }

    /**
     * String 转为流
     *
     * @param content     内容
     * @param charsetName 编码
     * @return 字节流
     * @since 0.1.8
     */
    public static ByteArrayInputStream toStream(String content, String charsetName) {
        return toStream(content, Charset.forName(charsetName));
    }

    /**
     * String 转为流
     *
     * @param content 内容
     * @param charset 编码
     * @return 字节流
     * @since 0.1.8
     */
    public static ByteArrayInputStream toStream(String content, Charset charset) {
        if (content == null) {
            return null;
        }
        return toStream(content.getBytes(charset));
    }

    /**
     * String 转为UTF-8编码的字节流流
     *
     * @param content 内容
     * @return 字节流
     * @since 0.1.8
     */
    public static ByteArrayInputStream toUtf8Stream(String content) {
        return toStream(content, "utf-8");
    }

    /**
     * 文件转为流
     *
     * @param file 文件
     * @return {@link FileInputStream}
     * @since 0.1.8
     */
    public static FileInputStream toStream(File file) {
        try {
            return new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * String 转为流
     *
     * @param content 内容bytes
     * @return 字节流
     * @since 0.1.8
     */
    public static ByteArrayInputStream toStream(byte[] content) {
        if (content == null) {
            return null;
        }
        return new ByteArrayInputStream(content);
    }

    /**
     * 关闭资源
     *
     * @param closeable 待关闭的资源
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
     * @param closeable 待关闭的资源
     * @since 0.0.1
     */
    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException ignored) {
            }
        }
    }

}
