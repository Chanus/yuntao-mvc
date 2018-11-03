/*
 * Copyright (c) 2018, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pers.chanus.yuntao.commons.pojo.PageBean;
import pers.chanus.yuntao.manager.common.ModulePowerUtils;
import pers.chanus.yuntao.manager.service.CodeGenerationService;
import pers.chanus.yuntao.springmvc.ConfigUtils;
import pers.chanus.yuntao.springmvc.controller.BaseController;

/**
 * 代码自动生成工具
 * 
 * @author Chanus
 * @date 2018-10-30 20:30:30
 * @since 0.0.3
 */
@Controller
@RequestMapping("system/code")
public class CodeGenerationController extends BaseController {
	@Autowired
	private CodeGenerationService codeGenerationService;
	
	private final int currentModuleId = 1010;

	/**
	 * 系统数据库表首页
	 * @param model
	 * @return
	 */
	@GetMapping(value = "main.do")
	public String main(Model model) {
		model.addAttribute("powers", ModulePowerUtils.getPowers(getSession(), currentModuleId));
		return "system/code/list";
	}
	
	/**
	 * 分页查询系统数据库表
	 * @return
	 */
	@ResponseBody
	@PostMapping(value = "list.do", produces = "application/json; charset=utf-8")
	public PageBean list() {
		return codeGenerationService.listDataBaseTablePagination(getParams().putNext("tableSchema", ConfigUtils.getProperty("code.generation.database")));
	}

	/**
	 * 系统数据库表字典
	 * @param model
	 * @return
	 */
	@GetMapping(value = "table-column.do")
	public String tableColumn(Model model) {
		model.addAttribute("columns", codeGenerationService.listDataBaseColumn(getParams()));
		return "system/code/table-column";
	}

}
