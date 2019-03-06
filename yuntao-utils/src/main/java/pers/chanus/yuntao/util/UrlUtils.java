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
	 * 解析http请求URI获取请求参数
	 * 
	 * @param query	http请求URI
	 * @param duplicate	重复参数名的参数值之间的连接符，连接后的字符串作为该参数的参数值，若为null，则不允许重复参数名出现，靠后的参数值会覆盖掉靠前的参数值
	 * @return 请求参数{@code Map}集合
	 * @since 0.0.1
	 */
	public static Map<String, String> getParamsMap(String query, String duplicate) {
		if (StringUtils.isBlank(query) || !query.contains("="))
			return null;
		Map<String, String> params = new HashMap<String, String>();
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
	 * @param params	Map集合
	 * @return http请求URI
	 * @since 0.0.1
	 */
	public static String getParamsUri(Map<String, Object> params) {
		if (CollectionUtils.isEmpty(params)) 
			return null;
		
		StringBuffer uri = new StringBuffer();
		for (String key : params.keySet()) {
			uri.append(key).append("=").append(params.get(key).toString()).append("&");
		}
		
		return uri.substring(0, uri.length() - 1);
	}
}
