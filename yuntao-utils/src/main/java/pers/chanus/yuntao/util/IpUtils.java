/*
 * Copyright (c) 2018, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

import pers.chanus.yuntao.util.StringUtils;

/**
 * IP地址工具类
 * 
 * @author Chanus
 * @date 2018-09-01 01:29:47
 * @since 0.0.1
 */
public class IpUtils {
	/**
	 * 获取当前网络IP地址
	 * 
	 * @param request
	 * @return IP地址
	 * @since 0.0.1
	 */
	public static String getIpAddress(HttpServletRequest request) {
		String ipAddress = request.getHeader("X-Real-IP");
		if (StringUtils.isBlank(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("X-Forwarded-For");
		}
		
		if (StringUtils.isBlank(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("Proxy-Client-IP");
		}
		
		if (StringUtils.isBlank(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("WL-Proxy-Client-IP");
		}
		
		if (StringUtils.isBlank(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("HTTP_CLIENT_IP");
		}
		
		if (StringUtils.isBlank(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		
		if (StringUtils.isBlank(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getRemoteAddr();
			if ("127.0.0.1".equals(ipAddress) || "0:0:0:0:0:0:0:1".equals(ipAddress)) {
				// 根据网卡取本机配置的IP
				InetAddress inet = null;
				try {
					inet = InetAddress.getLocalHost();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
				ipAddress = inet.getHostAddress();
			}
		} else {
			String[] ips = ipAddress.split(",");
			for (int index = 0; index < ips.length; index++) {
				String strIp = (String) ips[index];
				if (!("unknown".equalsIgnoreCase(strIp))) {
					ipAddress = strIp;
					break;
				}
			}
		}

		return ipAddress;
	}
}
