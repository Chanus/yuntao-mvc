/*
 * Copyright (c) 2018, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.manager.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import pers.chanus.yuntao.commons.constant.ConfigConsts;
import pers.chanus.yuntao.commons.constant.LogTypeEnum;
import pers.chanus.yuntao.commons.pojo.LoginUser;
import pers.chanus.yuntao.commons.pojo.Message;
import pers.chanus.yuntao.commons.pojo.PageBean;
import pers.chanus.yuntao.manager.common.ModulePowerUtils;
import pers.chanus.yuntao.manager.model.Operator;
import pers.chanus.yuntao.manager.service.OperatorService;
import pers.chanus.yuntao.manager.service.RoleService;
import pers.chanus.yuntao.server.annotation.SystemLog;
import pers.chanus.yuntao.springmvc.controller.BaseController;

/**
 * 操作员管理
 * 
 * @author Chanus
 * @date 2018-09-09 09:48:01
 * @since 0.0.1
 */
@Controller
@RequestMapping("system/operator")
public class OperatorController extends BaseController {
	@Autowired
	private OperatorService operatorService;
	@Autowired
	private RoleService roleService;
	
	private final int currentModuleId = 1005;
	
	/**
	 * 首页
	 * @param model
	 * @return
	 */
	@GetMapping(value = "main.do")
	public String main(Model model) {
		model.addAttribute("powers", ModulePowerUtils.getPowers(getSession(), currentModuleId));
		return "system/operator/list";
	}
	
	/**
	 * 分页查询
	 * @return
	 */
	@ResponseBody
	@PostMapping(value = "list.do", produces = "application/json; charset=utf-8")
	public PageBean list() {
		return operatorService.listPagination(getParams());
	}
	
	/**
	 * 获取角色列表树DOM
	 * @return
	 */
	@ResponseBody
	@PostMapping(value = "tree.do", produces = "application/json; charset=utf-8")
	public Object tree() {
		return JSON.parse(roleService.createTree(getParams().putNext("roleId", LoginUser.getLoginUser().getMasterRoleId()).putNext("hasOperator", ConfigConsts.STATUS_YES)));
	}
	
	/**
	 * 跳转到添加页面
	 * @param operatorRoleId	操作员角色代码
	 * @param model
	 * @return
	 */
	@GetMapping(value = "add.do")
	public String add(Integer operatorRoleId, Model model) {
		model.addAttribute("operator", new Operator());
		model.addAttribute("operatorRoleId", operatorRoleId);
		model.addAttribute("cmd", "add");
		return "system/operator/add-update";
	}
	
	/**
	 * 添加
	 * @param operator
	 * @return
	 */
	@ResponseBody
	@SystemLog(module = currentModuleId, logType = LogTypeEnum.INSERT)
	@PostMapping(value = "add.do", produces = "application/json; charset=utf-8")
	public Message add(Operator operator) {
		return operatorService.insert(operator);
	}
	
	/**
	 * 跳转到修改页面
	 * @param id	主键
	 * @param model
	 * @return
	 */
	@GetMapping(value = "update.do")
	public String update(Integer id, Model model) {
		model.addAttribute("operator", operatorService.getById(id));
		model.addAttribute("cmd", "update");
		return "system/operator/add-update";
	}
	
	/**
	 * 修改
	 * @param operator
	 * @return
	 */
	@ResponseBody
	@SystemLog(module = currentModuleId, logType = LogTypeEnum.UPDATE)
	@PostMapping(value = "update.do", produces = "application/json; charset=utf-8")
	public Message update(Operator operator) {
		return operatorService.update(operator);
	}
	
	/**
	 * 跳转到修改操作员密码页面
	 * @param operatorNo	操作员账号
	 * @param model
	 * @return
	 */
	@GetMapping(value = "password.do")
	public String password(String operatorNo, Model model) {
		model.addAttribute("operatorNo", operatorNo);
		return "system/operator/update-password";
	}
	
	/**
	 * 修改操作员密码
	 * @param operatorNo	操作员账号
	 * @param password	新密码
	 * @return
	 */
	@ResponseBody
	@SystemLog(module = currentModuleId, description = "修改操作员密码", logType = LogTypeEnum.UPDATE)
	@PostMapping(value = "password.do", produces = "application/json; charset=utf-8")
	public Message password(String operatorNo, String password) {
		return operatorService.updatePassword(operatorNo, null, password, false);
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
		return operatorService.delete(Arrays.asList(ids));
	}
}
