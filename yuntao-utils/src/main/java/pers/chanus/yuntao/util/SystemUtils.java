/*
 * Copyright (c) 2018, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.util;

import java.lang.management.ManagementFactory;
import com.sun.management.OperatingSystemMXBean;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 * 获取系统信息相关的工具类
 * 
 * @author Chanus
 * @date 2018-12-13 20:54:39
 * @since 0.0.5
 */
@SuppressWarnings("restriction")
public class SystemUtils {
	/**
	 * JDK的版本
	 */
	public static final String JDK_VERSION = System.getProperty("java.version");
	/**
	 * JVM的编码
	 */
	public static final String JVM_ENCODING = System.getProperty("file.encoding");
	/**
	 * 操作系统名称
	 */
	public static final String OS_NAME = System.getProperty("os.name");
	/**
	 * 操作系统版本
	 */
	public static final String OS_VERSION = System.getProperty("os.version");
	/**
	 * 主机架构
	 */
	public static String OS_ARCH = System.getProperty("os.arch");
	/**
	 * 主机名
	 */
	public static String HOST_NAME;
	/**
	 * 主机mac地址
	 */
	public static String HOST_MAC;
	/**
	 * 主机本地IP
	 */
	public static String HOST_IP;
	/**
	 * 当前用户
	 */
	public static final String CURRENT_USER = System.getProperty("user.name");
	/**
	 * 当前用户的家目录
	 */
	public static final String CURRENT_USER_HOME = System.getProperty("user.home");
	
	/**
	 * 系统总的物理内存
	 */
	public static long totalPhysicalMemorySize;
	
	private static OperatingSystemMXBean osmxb;
	
	/**
	 * 1KB = 1024B
	 */
	public static final int KB = 1024;
	/**
	 * 1MB = 1048576B
	 */
	public static final int MB = 1048576;
	/**
	 * 1GB = 1073741824B
	 */
	public static final int GB = 1073741824;
	/**
	 * 1TB = 1099511627776B
	 */
	public static final long TB = 1099511627776L;

	static {
		try {
			InetAddress inetAddress = InetAddress.getLocalHost();
			HOST_NAME = inetAddress.getHostName();
			
			StringBuilder sb = new StringBuilder();
			byte[] mac = NetworkInterface.getByInetAddress(inetAddress).getHardwareAddress();
			for (int i = 0; i < mac.length; i++) {
				if (i != 0) {
					sb.append("-");
				}
				String str = Integer.toHexString(mac[i] & 0xff);
				if (str.length() == 1) {
					sb.append("0").append(str);
				} else {
					sb.append(str);
				}
			}
			HOST_MAC = sb.toString().toUpperCase();
			
			Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
			sb = new StringBuilder();
			while (nets.hasMoreElements()) {
				NetworkInterface networkInterface = (NetworkInterface) nets.nextElement();
				Enumeration<InetAddress> enumIpAddr = networkInterface.getInetAddresses();
				while (enumIpAddr.hasMoreElements()) {
					InetAddress ia = (InetAddress) enumIpAddr.nextElement();
					if (!ia.isLoopbackAddress() && !ia.isLinkLocalAddress() && ia.isSiteLocalAddress()) {
						sb.append(ia.getHostAddress().toString());
					}
				}
			}
			HOST_IP = sb.toString();
		} catch (Exception e) {
			System.out.println("获取服务器本地IP出错");
		}

		try {
			osmxb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
			totalPhysicalMemorySize = osmxb.getTotalPhysicalMemorySize();
		} catch (Exception e) {
			System.out.println("获取系统物理内存失败");
		}
	}

	/**
	 * 可用的物理内存
	 * 
	 * @return
	 * @since 0.0.5
	 */
	public final static long getFreePhysicalMemorySize() {
		return osmxb == null ? 0L : osmxb.getFreePhysicalMemorySize();
	}

	/**
	 * 已使用的物理内存
	 * 
	 * @return
	 * @since 0.0.5
	 */
	public final static long getUsedPhysicalMemorySize() {
		return osmxb == null ? 0L : (osmxb.getTotalPhysicalMemorySize() - osmxb.getFreePhysicalMemorySize());
	}

	/**
	 * 获取JVM总的内存空间
	 * 
	 * @return
	 * @since 0.0.5
	 */
	public final static long getTotalJVMMemorySize() {
		return Runtime.getRuntime().totalMemory();
	}

	/**
	 * 获取JVM空闲的内存空间
	 * 
	 * @return
	 * @since 0.0.5
	 */
	public final static long getFreeJVMMemorySize() {
		return Runtime.getRuntime().freeMemory();
	}

	/**
	 * 获取JVM已用的内存空间
	 * 
	 * @return
	 * @since 0.0.5
	 */
	public final static long getUsedJVMMemorySize() {
		return Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
	}

	/**
	 * 获取JVM最大的内存空间
	 * 
	 * @return
	 * @since 0.0.5
	 */
	public final static long getMaxJVMMemorySize() {
		return Runtime.getRuntime().maxMemory();
	}
}
