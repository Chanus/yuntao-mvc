package com.chanus.yuntao.mvc.manager.controller;

import com.alibaba.fastjson.JSON;
import com.chanus.yuntao.mvc.manager.common.ModulePowerUtils;
import com.chanus.yuntao.utils.core.lang.Message;
import com.chanus.yuntao.utils.core.lang.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.chanus.yuntao.mvc.manager.model.Organization;
import com.chanus.yuntao.mvc.manager.service.OrganizationService;
import com.chanus.yuntao.mvc.framework.annotation.Log;
import com.chanus.yuntao.mvc.framework.controller.BaseController;
import com.chanus.yuntao.mvc.framework.enums.LogTypeEnum;

import java.util.Arrays;

/**
 * 组织结构表管理
 *
 * @author Chanus
 * @date 2019-05-06 21:11:46
 * @since 0.0.8
 */
@Controller
@RequestMapping("system/organization")
public class OrganizationController extends BaseController {
    @Autowired
    private OrganizationService organizationService;

    private final String currentModuleCode = "ORGANIZATION";

    /**
     * 首页
     *
     * @param model
     * @return
     */
    @GetMapping(value = "main.do")
    public String main(Model model) {
        model.addAttribute("powers", ModulePowerUtils.getPowers(currentModuleCode));
        return "system/organization/list";
    }

    /**
     * 分页查询
     *
     * @return
     */
    @ResponseBody
    @PostMapping(value = "list.do", produces = "application/json; charset=utf-8")
    public Page<Organization> list() {
        return organizationService.listPagination(getParams());
    }

    /**
     * 获取组织结构列表树DOM
     *
     * @return
     */
    @ResponseBody
    @PostMapping(value = "tree.do", produces = "application/json; charset=utf-8")
    public Object tree() {
        return JSON.parse(organizationService.createTree());
    }

    /**
     * 跳转到添加页面
     *
     * @param orgParentId 上级组织ID
     * @param model
     * @return
     */
    @GetMapping(value = "add.do")
    public String add(Integer orgParentId, Model model) {
        model.addAttribute("organization", new Organization());
        model.addAttribute("orgParentId", orgParentId);
        model.addAttribute("cmd", "add");
        return "system/organization/add-update";
    }

    /**
     * 添加
     *
     * @param organization
     * @return
     */
    @ResponseBody
    @Log(module = currentModuleCode, logType = LogTypeEnum.INSERT)
    @PostMapping(value = "add.do", produces = "application/json; charset=utf-8")
    public Message add(Organization organization) {
        return organizationService.insert(organization);
    }

    /**
     * 跳转到修改页面
     *
     * @param orgId 组织机构ID
     * @param model
     * @return
     */
    @GetMapping(value = "update.do")
    public String update(Integer orgId, Model model) {
        model.addAttribute("organization", organizationService.get(orgId));
        model.addAttribute("cmd", "update");
        return "system/organization/add-update";
    }

    /**
     * 修改
     *
     * @param organization
     * @return
     */
    @ResponseBody
    @Log(module = currentModuleCode, logType = LogTypeEnum.UPDATE)
    @PostMapping(value = "update.do", produces = "application/json; charset=utf-8")
    public Message update(Organization organization) {
        return organizationService.update(organization);
    }

    /**
     * 修改排序
     *
     * @param orgId    组织机构ID
     * @param priority 排序
     * @return
     */
    @ResponseBody
    @Log(module = currentModuleCode, description = "修改排序", logType = LogTypeEnum.UPDATE)
    @PostMapping(value = "priority.do", produces = "application/json; charset=utf-8")
    public Message priority(Integer orgId, Integer priority) {
        return organizationService.priority(orgId, priority);
    }

    /**
     * 删除/批量删除
     *
     * @param ids 被删除记录主键数组
     * @return
     */
    @ResponseBody
    @Log(module = currentModuleCode, logType = LogTypeEnum.DELETE)
    @PostMapping(value = "delete.do", produces = "application/json; charset=utf-8")
    public Message delete(Integer[] ids) {
        return organizationService.delete(Arrays.asList(ids));
    }

}
