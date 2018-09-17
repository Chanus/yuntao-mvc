/*
 * Copyright (c) 2018, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.jdbc.security;

import java.util.Properties;

import com.alibaba.druid.util.DruidPasswordCallback;

import pers.chanus.yuntao.commons.constant.CacheConsts;
import pers.chanus.yuntao.util.encrypt.AESUtils;

/**
 * 获取数据库密码密文，解密后用来连接数据库
 * 
 * @author Chanus
 * @date 2018-08-31 22:54:22
 * @since 0.0.1
 */
public class DBPasswordCallback extends DruidPasswordCallback {
	private static final long serialVersionUID = 1100233602930489210L;
	private static final String PRIVATE_KEY_STRING = "ChanusYuntaoJDBC";

	@Override
	public void setProperties(Properties properties) {
		super.setProperties(properties);
		String password = properties.getProperty("password");
		
		if (password != null && password.trim().length() > 0) {
			try {
				// 这里的password是将jdbc.properties配置得到的密码进行解密之后的值，所以这里的代码是将密码进行解密
				password = AESUtils.decrypt(CacheConsts.SERVER_CONFIG_MAP.get("jdbc_key") == null ? PRIVATE_KEY_STRING : CacheConsts.SERVER_CONFIG_MAP.get("jdbc_key"), password);
				setPassword(password.toCharArray());
			} catch (Exception e) {
				setPassword(password.toCharArray());
			}
		}
	}
}
