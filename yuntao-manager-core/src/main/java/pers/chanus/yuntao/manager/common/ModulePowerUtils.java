/*
 * Copyright (c) 2018, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.manager.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import pers.chanus.yuntao.commons.pojo.LoginUser;
import pers.chanus.yuntao.manager.model.Module;
import pers.chanus.yuntao.manager.model.ModulePower;
import pers.chanus.yuntao.util.CollectionUtils;
import pers.chanus.yuntao.util.StringUtils;

/**
 * 系统模块权限项工具类
 * 
 * @author Chanus
 * @date 2018-09-06 22:01:51
 * @since 0.0.1
 */
public class ModulePowerUtils {
	/**
	 * 获取用户的模块权限
	 * 
	 * @param session
	 * @param moduleId	模块ID
	 * @return 用户的模块权限列表
	 * @since 0.0.1
	 */
	@SuppressWarnings("unchecked")
	public static List<ModulePower> getModulePowers(HttpSession session, Integer moduleId) {
		List<Module> menus = (List<Module>) ((LoginUser) session.getAttribute("loginUser")).getMenus();
		if (CollectionUtils.isEmpty(menus))
			return null;
		
		// 遍历一级模块菜单
		for (Module module1 : menus) {
			if (module1 == null || CollectionUtils.isEmpty(module1.getChildren()))
				continue;
			// 遍历二级模块菜单
			for (Module module2 : module1.getChildren()) {
				// 返回该模块的权限组
				if (moduleId.equals(module2.getModuleId()))
					return module2.getModulePowers();
			}
		}
		
		return null;
	}
	
	/**
	 * 获取用户的模块权限
	 * 
	 * @param session
	 * @param moduleId	模块ID
	 * @return 用户的模块权限Map集合
	 * @since 0.0.1
	 */
	public static Map<String, Boolean> getPowers(HttpSession session, Integer moduleId) {
		List<ModulePower> modulePowers = getModulePowers(session, moduleId);
		Map<String, Boolean> powerMap = new HashMap<String, Boolean>();
		if (!CollectionUtils.isEmpty(modulePowers)) {
			for (ModulePower power : modulePowers) {
				if (power != null)
					powerMap.put(power.getPowerItem(), true);
			}
		}
		
		return powerMap;
	}
	
	/**
	 * 判断用户是否具有指定的模块权限
	 * 
	 * @param moduleId	模块ID
	 * @param powerItem	权限项
	 * @return {@code true} 有权限；{@code false} 没有权限
	 * @since 0.0.1
	 */
	public static boolean checkModulePower(List<ModulePower> modulePowers, String powerItem) {
		if (CollectionUtils.isEmpty(modulePowers))
			return false;
		
		// 遍历模块的权限组
		for (ModulePower power : modulePowers) {
			if (power != null && StringUtils.equals(powerItem, power.getPowerItem()))
				return true;
		}
		
		return false;
	}
}
