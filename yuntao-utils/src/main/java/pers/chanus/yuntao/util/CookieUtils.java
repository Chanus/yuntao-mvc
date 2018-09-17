/*
 * Copyright (c) 2018, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pers.chanus.yuntao.util.StringUtils;

/**
 * Cookie操作工具类
 * 
 * @author Chanus
 * @date 2018-09-01 01:31:04
 * @since 0.0.1
 */
public class CookieUtils {
	/**
	 * 添加cookie
	 * 
	 * @param response
	 * @param name	cookie名称
	 * @param value	cookie值
	 * @param expiry	cookie有效时间，不设置的话，则cookie不写入硬盘，而是写在内存，只在当前页面有用，以秒为单位
	 * @param path	cookie路径
	 * @param domain	cookie域
	 * @since 0.0.1
	 */
	public static void addCookie(HttpServletResponse response, String name, String value, Integer expiry, String path, String domain) {
		Cookie cookie = new Cookie(name, value);
		cookie.setMaxAge(expiry);
		cookie.setPath(StringUtils.isBlank(path) ? "/" : path);
		if (StringUtils.isNotBlank(domain))
			cookie.setDomain(domain);
		response.addCookie(cookie);
	}
	
	/**
	 * 修改cookie
	 * 
	 * @param request
	 * @param response
	 * @param name	cookie名称
	 * @param value	cookie值
	 * @param expiry	cookie有效时间，不设置的话，则cookie不写入硬盘，而是写在内存，只在当前页面有用，以秒为单位
	 * @param path	cookie路径
	 * @param domain	cookie域
	 * @since 0.0.1
	 */
	public static void editCookie(HttpServletRequest request, HttpServletResponse response, String name, String value, Integer expiry, String path, String domain) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null && StringUtils.isNotBlank(name)) {
			for (Cookie cookie : cookies) {
				if (name.equals(cookie.getName())) {
					cookie.setValue(value);
					cookie.setMaxAge(expiry);
					if (StringUtils.isNotBlank(path))
						cookie.setPath(path);
					if (StringUtils.isNotBlank(domain))
						cookie.setDomain(domain);
					response.addCookie(cookie);
					break;
				}
			}
		}
	}
	
	/**
	 * 根据名称获取cookie值
	 * 
	 * @param request
	 * @param name	cookie名称
	 * @return cookie值
	 * @since 0.0.1
	 */
	public static String getCookieByName(HttpServletRequest request, String name) {
		Cookie[] cookies = request.getCookies();
		if (cookies == null || StringUtils.isBlank(name))
			return null;

		for (Cookie cookie : cookies) {
			if (name.equals(cookie.getName())) {
				return cookie.getValue();
			}
		}

		return null;
	}

	/**
	 * 根据名称清除cookie
	 * 
	 * @param response
	 * @param name	cookie名称
	 * @param path	cookie路径
	 * @param domain	cookie域
	 * @since 0.0.1
	 */
	public static void removeCookieByName(HttpServletResponse response, String name, String path, String domain) {
		Cookie cookie = new Cookie(name, null);
		if (StringUtils.isNotBlank(path))
			cookie.setPath(path);
		if (StringUtils.isNotBlank(domain))
			cookie.setDomain(domain);

		cookie.setMaxAge(0);
		response.addCookie(cookie);
	}

	/**
	 * 清除所有cookie
	 * 
	 * @param request
	 * @param response
	 * @param path	cookie路径
	 * @param domain	cookie域
	 * @since 0.0.1
	 */
	public static void removeCookies(HttpServletRequest request, HttpServletResponse response, String path, String domain) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				cookie.setValue(null);
				if (StringUtils.isNotBlank(path))
					cookie.setPath(path);
				if (StringUtils.isNotBlank(domain))
					cookie.setDomain(domain);
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}
		}
	}
}
