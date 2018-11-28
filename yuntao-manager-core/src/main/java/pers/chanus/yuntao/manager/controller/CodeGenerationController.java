/*
 * Copyright (c) 2018, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.manager.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

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
import pers.chanus.yuntao.util.IOUtils;

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
		return "system/code/tables";
	}
	
	/**
	 * 分页查询系统数据库表
	 * @return
	 */
	@ResponseBody
	@PostMapping(value = "list.do", produces = "application/json; charset=utf-8")
	public PageBean list() {
		return codeGenerationService.listDataBaseTablePagination(getParams());
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

	/**
	 * 跳转到代码生成页面
	 * @param tableSchema	数据库名称
	 * @param tableName	表名
	 * @param model
	 * @return
	 */
	@GetMapping(value = "generate.do")
	public String generate(String tableSchema, String tableName, Model model) {
		model.addAttribute("tableSchema", tableSchema);
		model.addAttribute("tableName", tableName);
		model.addAttribute("author", ConfigUtils.getProperty("code.generation.author"));
		model.addAttribute("since", ConfigUtils.getProperty("code.generation.since"));
		model.addAttribute("packageName", ConfigUtils.getProperty("code.generation.package"));
		model.addAttribute("tablePrefix", ConfigUtils.getProperty("code.generation.tablePrefix"));
		model.addAttribute("autoRemovePrefix", ConfigUtils.getProperty("code.generation.autoRemovePrefix"));
		model.addAttribute("manyModule", ConfigUtils.getProperty("code.generation.manyModule"));
		return "system/code/generate";
	}

	/**
	 * 代码生成
	 * @param response
	 * @param tableSchema	数据库名称
	 * @param tableName	表名
	 * @since 0.0.3
	 */
	@GetMapping(value = "generate-download.do", produces = "application/json; charset=utf-8")
	public void generateDownload(HttpServletResponse response, String tableSchema, String tableName) {
		String[] tableNames = new String[] { tableName };
		byte[] data = codeGenerationService.generateCode(tableSchema, tableNames, getParams());
		response.reset();
		response.setHeader("Content-Disposition", "attachment; filename=\"generate-code.zip\"");
		response.addHeader("Content-Length", String.valueOf(data.length));
		response.setContentType("application/octet-stream; charset=UTF-8");

		try {
			IOUtils.write(data, response.getOutputStream());
		} catch (IOException e) {
			LOGGER.error("自动生成代码下载失败", e);
		}
	}

}