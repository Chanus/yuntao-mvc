/*
 * Copyright (c) 2018, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Http请求URL处理工具类
 *
 * @author Chanus
 * @date 2018-08-30 15:22:11
 * @since 0.0.1
 */
public class UrlUtils {
    /**
     * 解析http请求URI获取请求参数，转换为Map键值对
     *
     * @param query     http请求URI
     * @param duplicate 重复参数名的参数值之间的连接符，连接后的字符串作为该参数的参数值，若为null，则不允许重复参数名出现，靠后的参数值会覆盖掉靠前的参数值
     * @return 请求参数{@code Map}集合
     * @since 0.0.1
     */
    public static Map<String, String> getParamsMap(String query, String duplicate) {
        if (StringUtils.isBlank(query) || !query.contains("="))
            return null;

        Map<String, String> params = new HashMap<>();
        if (query.contains("&")) {// 多个参数
            String[] kvs = query.split("&");
            String[] kv;
            for (String p : kvs) {
                if (StringUtils.isBlank(p))
                    continue;
                kv = p.split("=");
                if (kv.length != 2 || StringUtils.isBlank(kv[0]) || StringUtils.isBlank(kv[1]))
                    continue;
                if (params.containsKey(kv[0])) {
                    params.put(kv[0], StringUtils.isBlank(duplicate) ? kv[1] : (params.get(kv[0]) + duplicate + kv[1]));
                } else {
                    params.put(kv[0], kv[1]);
                }
            }
        } else {// 一个参数
            String[] kv = query.split("=");
            if (kv.length == 2 && StringUtils.isNotBlank(kv[0]) && StringUtils.isNotBlank(kv[1]))
                params.put(kv[0], kv[1]);
        }

        return params;
    }

    /**
     * 将Map集合转换成http请求URI
     *
     * @param params Map集合
     * @return http请求URI
     * @since 0.0.1
     */
    public static String getParamsUri(Map<String, Object> params) {
        if (CollectionUtils.isEmpty(params))
            return null;

        StringBuilder uri = new StringBuilder();
        for (String key : params.keySet()) {
            if (params.get(key) == null)
                continue;

            uri.append(key).append("=").append(params.get(key).toString()).append("&");
        }

        return uri.substring(0, uri.length() - 1);
    }

    /**
     * 获取http请求URL中指定参数的值
     *
     * @param url       http请求URL
     * @param paramName 参数名
     * @return 返回参数名{@code paramName}对应的值
     * @since 0.0.8
     */
    public static String getParamValue(String url, String paramName) {
        int temp_index = url.indexOf("?");
        if (temp_index != -1) {
            int param_index = url.indexOf(paramName + "=", temp_index + 1);
            if (param_index != -1) {
                temp_index = url.indexOf("&", param_index + paramName.length() + 1);
                if (temp_index != -1) {
                    return url.substring(param_index + paramName.length() + 1, temp_index);
                }
                return url.substring(param_index + paramName.length() + 1);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * 向http请求URL中追加参数
     *
     * @param url        http请求URL
     * @param paramName  参数名
     * @param paramValue 参数值
     * @return 返回追加参数后的URL
     * @since 0.0.8
     */
    public static String setParam(String url, String paramName, String paramValue) {
        int temp_index = url.indexOf("?");
        if (temp_index != -1) {// url 中已带有参数
            int param_index = url.indexOf(paramName + "=", temp_index + 1);
            if (param_index != -1) {// url 中已存在要追加的参数
                temp_index = url.indexOf("&", param_index + paramName.length() + 1);
                if (temp_index != -1) {
                    return url.substring(0, param_index) + paramName + "=" + paramValue + url.substring(temp_index);
                }
                return url.substring(0, param_index) + paramName + "=" + paramValue;
            } else {// url 中不存在要追加的参数
                // url 以 & 结尾
                if (url.lastIndexOf("&") == url.length() - 1) {
                    return url + paramName + "=" + paramValue;
                }
                // url 不以 & 结尾
                return url + "&" + paramName + "=" + paramValue;
            }
        } else {// url 中不带参数
            return url + "?" + paramName + "=" + paramValue;
        }
    }

    /**
     * 向http请求URL中追加参数
     *
     * @param url    http请求URL
     * @param params 参数名和参数值Map
     * @return 返回追加参数后的URL
     * @since 0.1.9
     */
    public static String setParam(String url, Map<String, Object> params) {
        String uri = getParamsUri(params);
        if (null == uri)
            return url;

        int temp_index = url.indexOf("?");
        if (temp_index != -1) {// url 中已带有参数
            // url 以 & 结尾
            if (url.lastIndexOf("&") == url.length() - 1) {
                return url + uri;
            }
            // url 不以 & 结尾
            return url + "&" + uri;
        } else {// url 中不带参数
            return url + "?" + uri;
        }
    }

    /**
     * 移除http请求URL中的参数
     *
     * @param url       http请求URL
     * @param paramName 参数名
     * @return 返回移除参数{@code paramName}后的URL
     * @since 0.0.8
     */
    public static String removeParam(String url, String paramName) {
        int temp_index = url.indexOf("?");
        if (temp_index != -1) {
            int param_index = url.indexOf(paramName + "=", temp_index + 1);
            if (param_index != -1) {
                temp_index = url.indexOf("&", param_index + paramName.length() + 1);
                if (temp_index != -1) {
                    return url.substring(0, param_index) + url.substring(temp_index + 1);
                }
                return url.substring(0, param_index - 1);

            } else {
                return url;
            }
        } else {
            return url;
        }
    }

    /**
     * 批量移除http请求URL中的参数
     *
     * @param url        http请求URL
     * @param paramNames 参数名数组
     * @return 返回移除参数{@code paramNames}后的URL
     * @since 0.0.8
     */
    public static String removeParam(String url, String... paramNames) {
        for (String paramName : paramNames) {
            url = removeParam(url, paramName);
        }
        return url;
    }
}
