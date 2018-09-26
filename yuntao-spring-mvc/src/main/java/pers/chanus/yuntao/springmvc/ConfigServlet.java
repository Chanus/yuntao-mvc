/*
 * Copyright (c) 2018, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.springmvc;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pers.chanus.yuntao.springmvc.ConfigUtils;
import pers.chanus.yuntao.util.StringUtils;

/**
 * 初始化全局参数
 * 
 * @author Chanus
 * @date 2018-09-01 14:01:12
 * @since 0.0.1
 */
public class ConfigServlet extends HttpServlet {
	private static final long serialVersionUID = -6682155111608500310L;
	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigServlet.class);

	/**
	 * 初始化全局参数
	 * @since 0.0.1
	 */
	public void init() throws ServletException {
		ServletContext servletContext = getServletContext();
		servletContext.setAttribute("ctx", servletContext.getContextPath());
		servletContext.setAttribute("parentCtx", StringUtils.isBlank(servletContext.getContextPath()) ? File.separator : new File(servletContext.getContextPath()).getParent());
		servletContext.setAttribute("parentRealCtx", new File(servletContext.getRealPath("")).getParent());
		try {
			servletContext.setAttribute("system_name", ConfigUtils.getProperty("system.name"));
			servletContext.setAttribute("system_version", ConfigUtils.getProperty("system.version"));
			servletContext.setAttribute("system_copyright", ConfigUtils.getProperty("system.copyright"));
		} catch (Exception e) {
			LOGGER.error("系统初始化参数配置有误", e);
		}
	}
}
