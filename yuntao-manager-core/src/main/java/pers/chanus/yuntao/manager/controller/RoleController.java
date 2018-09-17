/*
 * Copyright (c) 2018, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.manager.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import pers.chanus.yuntao.commons.constant.LogTypeEnum;
import pers.chanus.yuntao.commons.pojo.LoginUser;
import pers.chanus.yuntao.commons.pojo.Message;
import pers.chanus.yuntao.commons.pojo.PageBean;
import pers.chanus.yuntao.manager.common.ModulePowerUtils;
import pers.chanus.yuntao.manager.model.Module;
import pers.chanus.yuntao.manager.model.Role;
import pers.chanus.yuntao.manager.service.ModuleService;
import pers.chanus.yuntao.manager.service.RoleService;
import pers.chanus.yuntao.server.annotation.SystemLog;
import pers.chanus.yuntao.springmvc.controller.BaseController;

/**
 * 角色管理
 * 
 * @author Chanus
 * @date 2018-09-08 22:13:18
 * @since 0.0.1
 */
@Controller
@RequestMapping("system/role")
public class RoleController extends BaseController {
	@Autowired
	private RoleService roleService;
	@Autowired
	private ModuleService moduleService;
	
	private final int currentModuleId = 1004;
	
	/**
	 * 首页
	 * @param model
	 * @return
	 */
	@GetMapping(value = "main.do")
	public String main(Model model) {
		model.addAttribute("powers", ModulePowerUtils.getPowers(getSession(), currentModuleId));
		return "system/role/list";
	}
	
	/**
	 * 分页查询
	 * @return
	 */
	@ResponseBody
	@PostMapping(value = "list.do", produces = "application/json; charset=utf-8")
	public PageBean list() {
		return roleService.listPagination(getParams().putNext("roleId", LoginUser.getLoginUser().getMasterRoleId()));
	}
	
	/**
	 * 获取角色列表树根DOM
	 * @return
	 */
	@ResponseBody
	@PostMapping(value = "tree.do", produces = "application/json; charset=utf-8")
	public Object tree() {
		return JSON.parse(roleService.createTree(getParams().putNext("roleId", LoginUser.getLoginUser().getMasterRoleId())));
	}
	
	/**
	 * 跳转到添加页面
	 * @param model
	 * @return
	 */
	@GetMapping(value = "add.do")
	public String add(Model model) {
		model.addAttribute("role", new Role());
		model.addAttribute("parentRoles", roleService.list(getParams().putNext("roleId", LoginUser.getLoginUser().getMasterRoleId())));
		model.addAttribute("cmd", "add");
		return "system/role/add-update";
	}
	
	/**
	 * 添加
	 * @param role
	 * @return
	 */
	@ResponseBody
	@SystemLog(module = currentModuleId, logType = LogTypeEnum.INSERT)
	@PostMapping(value = "add.do", produces = "application/json; charset=utf-8")
	public Message add(Role role) {
		return roleService.insert(role);
	}
	
	/**
	 * 跳转到修改页面
	 * @param id	主键
	 * @param model
	 * @return
	 */
	@GetMapping(value = "update.do")
	public String update(Integer id, Model model) {
		model.addAttribute("role", roleService.get(id));
		model.addAttribute("cmd", "update");
		return "system/role/add-update";
	}
	
	/**
	 * 修改
	 * @param role
	 * @return
	 */
	@ResponseBody
	@SystemLog(module = currentModuleId, logType = LogTypeEnum.UPDATE)
	@PostMapping(value = "update.do", produces = "application/json; charset=utf-8")
	public Message update(Role role) {
		return roleService.update(role);
	}
	
	/**
	 * 删除/批量删除
	 * @param ids	被删除记录主键数组
	 * @return
	 */
	@ResponseBody
	@SystemLog(module = currentModuleId, logType = LogTypeEnum.DELETE)
	@PostMapping(value = "delete.do", produces = "application/json; charset=utf-8")
	public Message delete(Integer[] ids) {
		return roleService.delete(Arrays.asList(ids));
	}
	
	/**
	 * 获取角色的模块权限列表
	 * @param roleId	角色代码
	 * @return
	 */
	@ResponseBody
	@PostMapping(value = "list-role-module-power.do", produces = "application/json; charset=utf-8")
	public List<Module> listRoleModulePower(String roleId) {
		return moduleService.listRoleModulePower(roleId);
	}
	
	/**
	 * 角色授权
	 * @param roleId	角色代码
	 * @param modulePowers	模块-权限项数组
	 * @return
	 */
	@ResponseBody
	@SystemLog(module = currentModuleId, description = "角色授权", logType = LogTypeEnum.UPDATE)
	@PostMapping(value = "grant.do", produces = "application/json; charset=utf-8")
	public Message grant(String roleId, String[] modulePowers) {
		return roleService.grantRoleModulePower(roleId, modulePowers);
	}
}
