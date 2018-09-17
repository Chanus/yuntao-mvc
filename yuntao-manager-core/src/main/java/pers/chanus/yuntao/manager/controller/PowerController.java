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

import pers.chanus.yuntao.commons.constant.LogTypeEnum;
import pers.chanus.yuntao.commons.pojo.Message;
import pers.chanus.yuntao.commons.pojo.PageBean;
import pers.chanus.yuntao.manager.common.ModulePowerUtils;
import pers.chanus.yuntao.manager.model.Power;
import pers.chanus.yuntao.manager.service.PowerService;
import pers.chanus.yuntao.server.annotation.SystemLog;
import pers.chanus.yuntao.springmvc.controller.BaseController;

/**
 * 权限项管理
 * 
 * @author Chanus
 * @date 2018-09-06 21:46:18
 * @since 0.0.1
 */
@Controller
@RequestMapping("system/power")
public class PowerController extends BaseController {
	@Autowired
	private PowerService powerService;
	
	private final int currentModuleId = 1001;
	
	/**
	 * 首页
	 * @param model
	 * @return
	 */
	@GetMapping(value = "main.do")
	public String main(Model model) {
		model.addAttribute("powers", ModulePowerUtils.getPowers(getSession(), currentModuleId));
		return "system/power/list";
	}
	
	/**
	 * 分页查询
	 * @return
	 */
	@ResponseBody
	@PostMapping(value = "list.do", produces = "application/json; charset=utf-8")
	public PageBean list() {
		return powerService.listPagination(getParams());
	}
	
	/**
	 * 跳转到添加页面
	 * @param model
	 * @return
	 */
	@GetMapping(value = "add.do")
	public String add(Model model) {
		model.addAttribute("power", new Power());
		model.addAttribute("cmd", "add");
		return "system/power/add-update";
	}
	
	/**
	 * 添加
	 * @param power
	 * @return
	 */
	@ResponseBody
	@SystemLog(module = currentModuleId, logType = LogTypeEnum.INSERT)
	@PostMapping(value = "add.do", produces = "application/json; charset=utf-8")
	public Message add(Power power) {
		return powerService.insert(power);
	}
	
	/**
	 * 跳转到修改页面
	 * @param id	主键
	 * @param model
	 * @return
	 */
	@GetMapping(value = "update.do")
	public String update(Integer id, Model model) {
		model.addAttribute("power", powerService.get(id));
		model.addAttribute("cmd", "update");
		return "system/power/add-update";
	}
	
	/**
	 * 修改
	 * @param power
	 * @return
	 */
	@ResponseBody
	@SystemLog(module = currentModuleId, logType = LogTypeEnum.UPDATE)
	@PostMapping(value = "update.do", produces = "application/json; charset=utf-8")
	public Message update(Power power) {
		return powerService.update(power);
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
		return powerService.delete(Arrays.asList(ids));
	}
	
	/**
	 * 调整优先级
	 * @param priority	当前记录的优先级
	 * @param direction	up-提升优先级，down-降低优先级，top-置顶优先级
	 * @return
	 */
	@ResponseBody
	@SystemLog(module = currentModuleId, description = "调整权限项优先级", logType = LogTypeEnum.UPDATE)
	@PostMapping(value = "priority.do", produces = "application/json; charset=utf-8")
	public Message priority() {
		return powerService.priority(getParams());
	}

}
