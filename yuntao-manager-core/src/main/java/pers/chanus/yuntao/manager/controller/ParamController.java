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
import pers.chanus.yuntao.commons.constant.LogTypeEnum;
import pers.chanus.yuntao.commons.pojo.LoginUser;
import pers.chanus.yuntao.commons.pojo.Message;
import pers.chanus.yuntao.commons.pojo.PageBean;
import pers.chanus.yuntao.manager.common.ModulePowerUtils;
import pers.chanus.yuntao.manager.model.Param;
import pers.chanus.yuntao.manager.service.ParamService;
import pers.chanus.yuntao.server.annotation.SystemLog;
import pers.chanus.yuntao.springmvc.controller.BaseController;

import java.util.Arrays;

/**
 * 系统参数配置管理
 *
 * @author Chanus
 * @date 2018-09-09 15:27:35
 * @since 0.0.1
 */
@Controller
@RequestMapping("system/param")
public class ParamController extends BaseController {
    @Autowired
    private ParamService paramService;

    private final int currentModuleId = 1007;

    /**
     * 首页
     *
     * @param model
     * @return
     */
    @GetMapping(value = "main.do")
    public String main(Model model) {
        model.addAttribute("powers", ModulePowerUtils.getPowers(getSession(), currentModuleId));
        return "system/param/list";
    }

    /**
     * 分页查询
     *
     * @return
     */
    @ResponseBody
    @PostMapping(value = "list.do", produces = "application/json; charset=utf-8")
    public PageBean list() {
        return paramService.listPagination(getParams().putNext("loginRoleId", LoginUser.getLoginUser().getMasterRoleId()));
    }

    /**
     * 跳转到添加页面
     *
     * @param model
     * @return
     */
    @GetMapping(value = "add.do")
    public String add(Model model) {
        model.addAttribute("parameter", new Param());
        model.addAttribute("cmd", "add");
        return "system/param/add-update";
    }

    /**
     * 添加
     *
     * @param param
     * @return
     */
    @ResponseBody
    @SystemLog(module = currentModuleId, logType = LogTypeEnum.INSERT)
    @PostMapping(value = "add.do", produces = "application/json; charset=utf-8")
    public Message add(Param param) {
        return paramService.insert(param);
    }

    /**
     * 跳转到修改页面
     *
     * @param id    参数ID
     * @param model
     * @return
     */
    @GetMapping(value = "update.do")
    public String update(Integer id, Model model) {
        model.addAttribute("parameter", paramService.get(id));
        model.addAttribute("cmd", "update");
        return "system/param/add-update";
    }

    /**
     * 修改
     *
     * @param param
     * @return
     */
    @ResponseBody
    @SystemLog(module = currentModuleId, logType = LogTypeEnum.UPDATE)
    @PostMapping(value = "update.do", produces = "application/json; charset=utf-8")
    public Message update(Param param) {
        return paramService.update(param);
    }

    /**
     * 删除/批量删除
     *
     * @param ids 被删除记录主键数组
     * @return
     */
    @ResponseBody
    @SystemLog(module = currentModuleId, logType = LogTypeEnum.DELETE)
    @PostMapping(value = "delete.do", produces = "application/json; charset=utf-8")
    public Message delete(Integer[] ids) {
        return paramService.delete(Arrays.asList(ids));
    }

    /**
     * 调整优先级
     *
     * @return
     */
    @ResponseBody
    @SystemLog(module = currentModuleId, description = "调整系统参数优先级", logType = LogTypeEnum.UPDATE)
    @PostMapping(value = "priority.do", produces = "application/json; charset=utf-8")
    public Message priority() {
        return paramService.priority(getParams());
    }

    /**
     * 重载系统参数
     *
     * @return
     */
    @ResponseBody
    @PostMapping(value = "reload-param.do", produces = "application/json; charset=utf-8")
    public Message reloadParam() {
        return paramService.reloadParam();
    }
}
