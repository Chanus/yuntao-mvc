/*
 * Copyright (c) 2018, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.util;

import java.io.*;
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
        StringBuilder result = new StringBuilder();// 返回的结果
        BufferedReader bufferedReader = null;
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
                bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));

                String line;
                // 读取返回的内容
                while ((line = bufferedReader.readLine()) != null) {
                    result.append(line);
                }
            } else {
                throw new RuntimeException("response code is not 200 ... ");
            }
        } catch (Exception e) {
            throw new RuntimeException("Request exception", e);
        } finally {
            IOUtils.close(bufferedReader);
        }
        return result.toString();
    }

    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url        发送请求的URL
     * @param parameters 请求参数
     * @return 远程资源的响应结果
     * @since 0.0.1
     */
    public static String get(String url, final Map<String, Object> parameters) {
        url = UrlUtils.setParam(url, parameters);
        return get(url);
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
     * @param url   发送请求的URL，可以包含请求参数
     * @param param 请求参数
     * @return 远程资源的响应结果
     * @since 0.1.9
     */
    public static String post(final String url, final String param) {
        StringBuilder result = new StringBuilder();// 返回的结果
        BufferedReader bufferedReader = null;// 读取响应输入流
        BufferedWriter bufferedWriter = null;// 写入参数输出流
        try {
            // 创建URL对象
            URL connURL = new URL(url);
            // 打开URL连接
            HttpURLConnection connection = (HttpURLConnection) connURL.openConnection();
            // 设置通用属性，请求头信息
            connection.setRequestProperty("Accept", "*/*");
            connection.setRequestProperty("Connection", "Keep-Alive");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            // 设定请求的方法，默认是GET
            connection.setRequestMethod("POST");
            // 设置是否向 connection 输出
            connection.setDoOutput(true);
            // 设置是否从 connection 读入，默认情况下是true
            connection.setDoInput(true);
            // POST 请求不能使用缓存
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setReadTimeout(TIMEOUT_IN_MILLIONS);
            connection.setConnectTimeout(TIMEOUT_IN_MILLIONS);
            // 建立实际的连接
            connection.connect();

            // POST请求参数
            if (StringUtils.isNotBlank(param)) {
                bufferedWriter = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), StandardCharsets.UTF_8));
                bufferedWriter.write(param);
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
     * @param url 发送请求的URL，可以包含请求参数
     * @return 远程资源的响应结果
     * @since 0.0.1
     */
    public static String post(final String url) {
        return post(url, (String) null);
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
        return post(url, uri);
    }

    /**
     * 向指定URL发送异步POST方法的请求
     *
     * @param url      发送请求的URL，包含请求参数
     * @param param    请求参数
     * @param callBack 回调方法
     * @since 0.1.9
     */
    public static void postAsyn(final String url, final String param, final CallBack callBack) {
        new Thread(() -> {
            try {
                String result = post(url, param);
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

    /**
     * 文件上传
     *
     * @param url   发送请求的URL
     * @param file  需要上传的文件
     * @param param 请求参数
     * @return 远程资源的响应结果
     * @since 0.1.9
     */
    public static String upload(final String url, final String param, File file) {
        StringBuilder result = new StringBuilder();// 返回的结果
        DataOutputStream dataOutputStream = null;
        BufferedReader bufferedReader = null;// 读取响应输入流
        BufferedWriter bufferedWriter = null;// 写入参数输出流

        // 必须多两道线
        String twoHyphens = "--";
        // 边界
        String boundary = "*****";
        // 结尾
        String end = "\r\n";
        try {
            // 创建URL对象
            URL connURL = new URL(url);
            // 打开URL连接
            HttpURLConnection connection = (HttpURLConnection) connURL.openConnection();
            // 设置通用属性，请求头信息
            connection.setRequestProperty("Accept", "*/*");
            connection.setRequestProperty("Connection", "Keep-Alive");
            connection.setRequestProperty("Charset", "UTF-8");
            connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
            // 设定请求的方法，默认是GET
            connection.setRequestMethod("POST");
            // 设置是否向 connection 输出
            connection.setDoOutput(true);
            // 设置是否从 connection 读入，默认情况下是true
            connection.setDoInput(true);
            // POST 请求不能使用缓存
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setReadTimeout(TIMEOUT_IN_MILLIONS);
            connection.setConnectTimeout(TIMEOUT_IN_MILLIONS);
            // 建立实际的连接
            connection.connect();

            // 获得输出流
            dataOutputStream = new DataOutputStream(connection.getOutputStream());
            // 输出表头
            String head = twoHyphens + boundary + end +
                    // 上传头像
                    "Content-Disposition: form-data;name=\"media\";filename=\"" + file.getName() + "\"" + end +
                    // 上传多媒体
                    // stringBuilder.append("Content-Disposition: form-data;name=\"file\";filename=\""+ file.getName()).append("\"").append(end);
                    // 获取文件类型设置成请求头
                    "Content-Type: application/octet-stream" + end + end;
            dataOutputStream.writeBytes(head);
            // 把文件以流文件的方式推入到 url 中
            DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file));
            byte[] buffer = new byte[1024];
            int length;
            while ((length = dataInputStream.read(buffer)) != -1) {
                dataOutputStream.write(buffer, 0, length);
            }
            dataInputStream.close();
            // 定义数据分隔线
            dataOutputStream.writeBytes(twoHyphens + boundary + twoHyphens + end);
            dataOutputStream.flush();

            // POST请求参数
            if (StringUtils.isNotBlank(param)) {
                bufferedWriter = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), StandardCharsets.UTF_8));
                bufferedWriter.write(param);
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
            IOUtils.close(dataOutputStream);
            IOUtils.close(bufferedWriter);
            IOUtils.close(bufferedReader);
        }
        return result.toString();
    }

    /**
     * 文件上传
     *
     * @param url  发送请求的URL
     * @param file 需要上传的文件
     * @return 远程资源的响应结果
     * @since 0.1.9
     */
    public static String upload(final String url, File file) {
        return upload(url, null, file);
    }

    /**
     * 文件下载
     *
     * @param url      发送请求的URL
     * @param savePath 下载文件保存路径
     * @return 下载的文件
     * @since 0.1.9
     */
    public static File download(final String url, final String savePath) {
        File file;
        BufferedInputStream bufferedInputStream = null;
        OutputStream out = null;

        try {
            // 创建URL对象
            URL connURL = new URL(url);
            // 打开URL连接
            HttpURLConnection connection = (HttpURLConnection) connURL.openConnection();
            // 设置通用属性，请求头信息
            connection.setRequestProperty("Accept", "*/*");
            connection.setRequestProperty("Connection", "Keep-Alive");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            // 设定请求的方法，默认是GET
            connection.setRequestMethod("POST");
            // 设置是否向 connection 输出
            connection.setDoOutput(true);
            // 设置是否从 connection 读入，默认情况下是true
            connection.setDoInput(true);
            // POST 请求不能使用缓存
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setReadTimeout(TIMEOUT_IN_MILLIONS);
            connection.setConnectTimeout(TIMEOUT_IN_MILLIONS);
            // 建立实际的连接
            connection.connect();

            // 文件大小
            // int fileLength = connection.getContentLength();
            // 文件路径
            String filePath = connection.getURL().getFile();
            // 文件名
            String fileFullName = filePath.substring(filePath.lastIndexOf(File.separatorChar) + 1);

            // 获得输入流
            bufferedInputStream = new BufferedInputStream(connection.getInputStream());
            String path = savePath + File.separatorChar + fileFullName;
            file = new File(path);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }

            out = new FileOutputStream(file);
            int size;
            byte[] buffer = new byte[1024];
            while ((size = bufferedInputStream.read(buffer)) != -1) {
                out.write(buffer, 0, size);
            }
        } catch (Exception e) {
            throw new RuntimeException("Request exception", e);
        } finally {
            IOUtils.close(out);
            IOUtils.close(bufferedInputStream);
        }
        return file;
    }
}
