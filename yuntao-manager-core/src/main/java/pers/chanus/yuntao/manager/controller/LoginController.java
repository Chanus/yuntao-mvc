/*
 * Copyright (c) 2018, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.manager.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pers.chanus.yuntao.commons.constant.CacheConsts;
import pers.chanus.yuntao.commons.constant.LogTypeEnum;
import pers.chanus.yuntao.commons.constant.MsgCode;
import pers.chanus.yuntao.commons.pojo.Message;
import pers.chanus.yuntao.manager.common.CacheData;
import pers.chanus.yuntao.manager.service.LoginUserService;
import pers.chanus.yuntao.server.annotation.SystemLog;
import pers.chanus.yuntao.springmvc.ConfigUtils;
import pers.chanus.yuntao.springmvc.controller.BaseController;
import pers.chanus.yuntao.util.GoogleAuthenticatorUtils;
import pers.chanus.yuntao.util.IpUtils;
import pers.chanus.yuntao.util.StringUtils;
import pers.chanus.yuntao.util.VerifyCodeUtils;
import pers.chanus.yuntao.util.encrypt.RSAUtils;

/**
 * 登录、退出
 * 
 * @author Chanus
 * @date 2018-09-01 21:51:40
 * @since 0.0.1
 */
@Controller
public class LoginController extends BaseController {
	@Autowired
	private LoginUserService loginUserService;
	
	/**
	 * 用户跳转到登录界面
	 * @param model
	 * @return
	 */
	@GetMapping(value = "login")
	public String login(Model model) {
		model.addAttribute("isCheckVerifyCode", CacheConsts.SYSTEM_PARAMS_MAP.get("sys_check_verify_code"));
		model.addAttribute("isCheckGoogleAuthenticator", CacheConsts.SYSTEM_PARAMS_MAP.get("sys_check_google_authenticator"));
		return "login";
	}
	
	/**
	 * 跳转到重新登录界面
	 * @return
	 */
	@GetMapping(value = "relogin")
	public String relogin() {
		return "relogin";
	}
	
	/**
	 * 点击登录时获取RSA公钥，服务器保存私钥
	 * @return
	 */
	@ResponseBody
	@PostMapping(value = "rsa-public-key")
	public String rsaPublicKey() {
		Map<String, String> rsaKey = CacheData.getRsaKey();
		CacheData.RSA_KEYS_MAP.put(rsaKey.get(RSAUtils.PUBLIC_KEY), rsaKey.get(RSAUtils.PRIVATE_KEY));
		return rsaKey.get(RSAUtils.PUBLIC_KEY);
	}

	/**
	 * 生成验证码
	 * @throws IOException
	 */
	@GetMapping(value = "verify-code")
	public void verifyCode(HttpServletResponse response) throws IOException {
		VerifyCodeUtils verifyCodeUtils = new VerifyCodeUtils();
		// 获取生成的随机数字
		Map<String, Object> verifyCodeMap = verifyCodeUtils.generate();
		// 将四位数字的验证码保存到Session中
		getSession().setAttribute("verifyCode", verifyCodeMap.get("randomCode").toString());
		// 禁止图像缓存
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");
		// 将图像输出到Servlet输出流中
		ServletOutputStream sos = response.getOutputStream();
		ImageIO.write((BufferedImage) verifyCodeMap.get("buffImg"), "jpeg", sos);
		sos.close();
	}
	
	/**
	 * 账号登录验证:验证码校验、用户信息校验
	 * @return
	 */
	@ResponseBody
	@SystemLog(logType = LogTypeEnum.LOGIN)
	@PostMapping(value = "login", produces = "application/json; charset=utf-8")
	public Message login() {
		HttpSession session = getSession();
		Map<String, Object> params = getParams();
		String rsaPublicKey = (String) params.get("rsaPublicKey");
		
		if ("1".equals(CacheConsts.SYSTEM_PARAMS_MAP.get("sys_check_verify_code"))) {// 需要验证码校验
			String verifyCode = (String) params.get("verifyCode");
			String realVerifyCode = (String) session.getAttribute("verifyCode");
			
			if (StringUtils.isBlank(verifyCode)) {
				CacheData.RSA_KEYS_MAP.remove(rsaPublicKey);
				return Message.fail("验证码不能为空");
			}
			if (!verifyCode.equalsIgnoreCase(realVerifyCode)) {
				CacheData.RSA_KEYS_MAP.remove(rsaPublicKey);
				return Message.fail("验证码输入错误");
			}
		}
		
		if ("1".equals(CacheConsts.SYSTEM_PARAMS_MAP.get("sys_check_google_authenticator"))) {// 启用谷歌验证器
			String googleAuthenticatorCode = (String) params.get("googleAuthenticatorCode");
			
			if (StringUtils.isBlank(googleAuthenticatorCode))
				return Message.initMsg(MsgCode.FAIL, "动态验证码不能为空");
			
			if (!StringUtils.isNumeric(googleAuthenticatorCode))
				return Message.initMsg(MsgCode.FAIL, "动态验证码不正确");
			
			GoogleAuthenticatorUtils googleAuthenticatorUtils = new GoogleAuthenticatorUtils();
			googleAuthenticatorUtils.setWindowSize(5);
			long t = System.currentTimeMillis();
			String secret = ConfigUtils.getProperty("google.authenticator.secret");
			
			if (!googleAuthenticatorUtils.check_code(secret, Long.parseLong(googleAuthenticatorCode), t))
				return Message.initMsg(MsgCode.FAIL, "动态验证码不正确");
		}

		String loginname = (String) params.get("loginname");
		String roleId = (String) params.get("roleId");
		String password = (String) params.get("password");
		try {
			password = new String(RSAUtils.decryptByPrivateKey(Base64.getDecoder().decode(password), CacheData.RSA_KEYS_MAP.get(rsaPublicKey)), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return Message.fail("密码解密异常");
		} finally {
			CacheData.RSA_KEYS_MAP.remove(rsaPublicKey);
		}
		
		Message message = null;
		if (StringUtils.isBlank(roleId)) {
			message = loginUserService.login(loginname, password, IpUtils.getIpAddress(getRequest()));
		} else {
			message = loginUserService.login(loginname, password, roleId, IpUtils.getIpAddress(getRequest()));
		}
		
		if (message.getCode() == MsgCode.SUCCESS)// 存储登录账号信息
			session.setAttribute("loginUser", message.getData());
		
		return message;
	}
	
	/**
	 * 用户退出系统，跳转到登录界面
	 * @return
	 */
	@SystemLog(logType = LogTypeEnum.LOGOUT)
	@GetMapping(value = "logout.do")
	public String logout(Model model) {
		getSession().invalidate();
		model.addAttribute("isCheckVerifyCode", CacheConsts.SYSTEM_PARAMS_MAP.get("sys_check_verify_code"));
		model.addAttribute("isCheckGoogleAuthenticator", CacheConsts.SYSTEM_PARAMS_MAP.get("sys_check_google_authenticator"));
		return "login";
	}

}
