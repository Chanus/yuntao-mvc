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
import pers.chanus.yuntao.commons.constant.ConfigConsts;
import pers.chanus.yuntao.commons.constant.LogTypeEnum;
import pers.chanus.yuntao.commons.pojo.LoginUser;
import pers.chanus.yuntao.commons.pojo.Message;
import pers.chanus.yuntao.commons.pojo.PageBean;
import pers.chanus.yuntao.manager.common.ModulePowerUtils;
import pers.chanus.yuntao.manager.model.Module;
import pers.chanus.yuntao.manager.model.Operator;
import pers.chanus.yuntao.manager.service.ModuleService;
import pers.chanus.yuntao.manager.service.OperatorService;
import pers.chanus.yuntao.server.annotation.SystemLog;
import pers.chanus.yuntao.springmvc.controller.BaseController;

import java.util.Arrays;
import java.util.List;

/**
 * 子账号管理
 *
 * @author Chanus
 * @date 2018-09-09 12:18:39
 * @since 0.0.1
 */
@Controller
@RequestMapping("system/sub")
public class SubController extends BaseController {
    @Autowired
    private OperatorService operatorService;
    @Autowired
    private ModuleService moduleService;

    private final int currentModuleId = 1006;

    /**
     * 首页
     *
     * @param model
     * @return
     */
    @GetMapping(value = "main.do")
    public String main(Model model) {
        model.addAttribute("powers", ModulePowerUtils.getPowers(getSession(), currentModuleId));
        return "system/sub/list";
    }

    /**
     * 分页查询
     *
     * @return
     */
    @ResponseBody
    @PostMapping(value = "list.do", produces = "application/json; charset=utf-8")
    public PageBean list() {
        return operatorService.listSubPagination(getParams().putNext("loginNo", LoginUser.getLoginUser().getLoginNo()).putNext("loginRoleId", LoginUser.getLoginUser().getRoleId()));
    }

    /**
     * 跳转到添加页面
     *
     * @param model
     * @return
     */
    @GetMapping(value = "add.do")
    public String add(Model model) {
        model.addAttribute("operator", new Operator());
        model.addAttribute("cmd", "add");
        return "system/sub/add-update";
    }

    /**
     * 添加
     *
     * @param operator
     * @return
     */
    @ResponseBody
    @SystemLog(module = currentModuleId, logType = LogTypeEnum.INSERT)
    @PostMapping(value = "add.do", produces = "application/json; charset=utf-8")
    public Message add(Operator operator) {
        operator.setOperatorRoleId(ConfigConsts.ROLE_SUB_1);
        if (!ConfigConsts.ROLE_ADMIN_0.equals(LoginUser.getLoginUser().getRoleId()))
            operator.setMasterNo(LoginUser.getLoginUser().getLoginNo());

        return operatorService.insert(operator);
    }

    /**
     * 跳转到修改页面
     *
     * @param id    主键
     * @param model
     * @return
     */
    @GetMapping(value = "update.do")
    public String update(Integer id, Model model) {
        model.addAttribute("operator", operatorService.getById(id));
        model.addAttribute("cmd", "update");
        return "system/sub/add-update";
    }

    /**
     * 修改
     *
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
     * 跳转到修改子账号密码页面
     *
     * @param subNo 子账号
     * @param model
     * @return
     */
    @GetMapping(value = "password.do")
    public String password(String subNo, Model model) {
        model.addAttribute("subNo", subNo);
        return "system/sub/update-password";
    }

    /**
     * 修改子账号密码
     *
     * @param subNo    子账号
     * @param password 新密码
     * @return
     */
    @ResponseBody
    @SystemLog(module = currentModuleId, description = "修改子账号密码", logType = LogTypeEnum.UPDATE, ignore = true)
    @PostMapping(value = "password.do", produces = "application/json; charset=utf-8")
    public Message password(String subNo, String password) {
        return operatorService.updatePassword(subNo, null, password, false);
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
        return operatorService.delete(Arrays.asList(ids));
    }

    /**
     * 跳转到配置子账号权限页面
     *
     * @param subNo        子账号
     * @param masterRoleId 主账号角色
     * @param model
     * @return
     */
    @GetMapping(value = "configure.do")
    public String configure(String subNo, String masterRoleId, Model model) {
        // 获取主账号的权限菜单
        List<Module> menus = moduleService.listSubModulePower(subNo, masterRoleId);
        model.addAttribute("menus", menus);
        model.addAttribute("subNo", subNo);
        return "system/sub/configure";
    }

    /**
     * 子账号授权
     *
     * @param subNo        子账号
     * @param modulePowers 模块-权限项数组
     * @return
     */
    @ResponseBody
    @SystemLog(module = currentModuleId, description = "子账号授权", logType = LogTypeEnum.UPDATE)
    @PostMapping(value = "configure.do", produces = "application/json; charset=utf-8")
    public Message configure(String subNo, String[] modulePowers) {
        return operatorService.grantSubModulePower(subNo, modulePowers);
    }
}
