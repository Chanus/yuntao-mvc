/*
 * Copyright (c) 2018, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * Http请求工具类
 *
 * @author Chanus
 * @date 2018-08-30 15:23:28
 * @since 0.0.1
 */
public class HttpUtils {
    /**
     * 请求超时时间60s
     */
    private static final int TIMEOUT_IN_MILLIONS = 60000;

    /**
     * 异步请求回调接口
     *
     * @author chanus
     * @date 2018-05-31 16:27:06
     * @since 0.0.1
     */
    public interface CallBack {
        void onRequestComplete(String result);
    }

    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url 发送请求的URL，包含请求参数
     * @return 远程资源的响应结果
     * @since 0.0.1
     */
    public static String get(final String url) {
        InputStream is = null;
        ByteArrayOutputStream baos = null;
        try {
            // 创建URL对象
            URL connURL = new URL(url);
            // 打开URL连接
            HttpURLConnection connection = (HttpURLConnection) connURL.openConnection();
            // 设置通用属性
            connection.setRequestProperty("Accept", "*/*");
            connection.setRequestProperty("Connection", "Keep-Alive");
            connection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1)");
            connection.setRequestMethod("GET");
            connection.setReadTimeout(TIMEOUT_IN_MILLIONS);
            connection.setConnectTimeout(TIMEOUT_IN_MILLIONS);
            // 建立实际的连接
            connection.connect();
            // 响应头部获取
            // Map<String, List<String>> headers = httpConn.getHeaderFields();

            if (connection.getResponseCode() == 200) {
                is = connection.getInputStream();
                baos = new ByteArrayOutputStream();
                int len;
                byte[] buf = new byte[128];

                while ((len = is.read(buf)) != -1) {
                    baos.write(buf, 0, len);
                }
                baos.flush();
                return baos.toString();
            } else {
                throw new RuntimeException("response code is not 200 ... ");
            }
        } catch (Exception e) {
            throw new RuntimeException("Request exception", e);
        } finally {
            IOUtils.close(is);
            IOUtils.close(baos);
        }
    }

    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url        发送请求的URL
     * @param parameters 请求参数
     * @return 远程资源的响应结果
     * @since 0.0.1
     */
    public static String get(final String url, final Map<String, Object> parameters) {
        String uri = UrlUtils.getParamsUri(parameters);
        return get(StringUtils.isBlank(uri) ? url : (url + "?" + uri));
    }

    /**
     * 向指定URL发送异步GET方法的请求
     *
     * @param url      发送请求的URL，包含请求参数
     * @param callBack 回调方法
     * @since 0.0.1
     */
    public static void getAsyn(final String url, final CallBack callBack) {
        new Thread(() -> {
            try {
                String result = get(url);
                if (callBack != null) {
                    callBack.onRequestComplete(result);
                }
            } catch (Exception e) {
                throw new RuntimeException("Asynchronous request exception", e);
            }
        }).start();
    }

    /**
     * 向指定URL发送异步GET方法的请求
     *
     * @param url        发送请求的URL
     * @param parameters 请求参数
     * @param callBack   回调方法
     * @since 0.0.1
     */
    public static void getAsyn(final String url, final Map<String, Object> parameters, final CallBack callBack) {
        new Thread(() -> {
            try {
                String result = get(url, parameters);
                if (callBack != null) {
                    callBack.onRequestComplete(result);
                }
            } catch (Exception e) {
                throw new RuntimeException("Asynchronous request exception", e);
            }
        }).start();
    }

    /**
     * 向指定URL发送POST方法的请求
     *
     * @param url 发送请求的URL，包含请求参数
     * @return 远程资源的响应结果
     * @since 0.0.1
     */
    public static String post(final String url) {
        StringBuilder result = new StringBuilder();// 返回的结果
        BufferedReader bufferedReader = null;// 读取响应输入流
        BufferedWriter bufferedWriter = null;// 写入参数输出流
        try {
            // 分割url
            String[] urlArr = url.split("\\?");
            // 创建URL对象
            URL connURL = new URL(urlArr[0]);
            // 打开URL连接
            HttpURLConnection connection = (HttpURLConnection) connURL.openConnection();
            // 设置通用属性

            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setReadTimeout(TIMEOUT_IN_MILLIONS);
            connection.setConnectTimeout(TIMEOUT_IN_MILLIONS);
            // 建立实际的连接
            connection.connect();

            // POST请求参数
            if (urlArr.length == 2) {// 存在参数
                bufferedWriter = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), StandardCharsets.UTF_8));
                bufferedWriter.write(urlArr[1]);
                bufferedWriter.flush();
            }

            // 读取响应
            // 定义BufferedReader输入流来读取URL的响应，并设置编码方式
            bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
            String line;
            // 读取返回的内容
            while ((line = bufferedReader.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {
            throw new RuntimeException("Request exception", e);
        } finally {
            IOUtils.close(bufferedWriter);
            IOUtils.close(bufferedReader);
        }
        return result.toString();
    }

    /**
     * 向指定URL发送POST方法的请求
     *
     * @param url        发送请求的URL
     * @param parameters 请求参数
     * @return 远程资源的响应结果
     * @since 0.0.1
     */
    public static String post(final String url, final Map<String, Object> parameters) {
        String uri = UrlUtils.getParamsUri(parameters);
        return post(StringUtils.isBlank(uri) ? url : (url + "?" + uri));
    }

    /**
     * 向指定URL发送异步POST方法的请求
     *
     * @param url      发送请求的URL，包含请求参数
     * @param callBack 回调方法
     * @since 0.0.1
     */
    public static void postAsyn(final String url, final CallBack callBack) {
        new Thread(() -> {
            try {
                String result = post(url);
                if (callBack != null) {
                    callBack.onRequestComplete(result);
                }
            } catch (Exception e) {
                throw new RuntimeException("Asynchronous request exception", e);
            }
        }).start();
    }

    /**
     * 向指定URL发送异步POST方法的请求
     *
     * @param url        发送请求的URL
     * @param parameters 请求参数
     * @param callBack   回调方法
     * @since 0.0.1
     */
    public static void postAsyn(final String url, final Map<String, Object> parameters, final CallBack callBack) {
        new Thread(() -> {
            try {
                String result = post(url, parameters);
                if (callBack != null) {
                    callBack.onRequestComplete(result);
                }
            } catch (Exception e) {
                throw new RuntimeException("Asynchronous request exception", e);
            }
        }).start();
    }
}
