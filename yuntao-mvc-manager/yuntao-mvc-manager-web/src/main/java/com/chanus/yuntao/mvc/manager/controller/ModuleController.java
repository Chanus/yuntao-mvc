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
import com.chanus.yuntao.mvc.manager.model.Module;
import com.chanus.yuntao.mvc.manager.service.ModuleService;
import com.chanus.yuntao.mvc.framework.annotation.Log;
import com.chanus.yuntao.mvc.framework.controller.BaseController;
import com.chanus.yuntao.mvc.framework.enums.LogTypeEnum;

import java.util.Arrays;
import java.util.List;

/**
 * 模块管理
 *
 * @author Chanus
 * @date 2018-09-08 16:05:23
 * @since 0.0.1
 */
@Controller
@RequestMapping("system/module")
public class ModuleController extends BaseController {
    @Autowired
    private ModuleService moduleService;

    private final String currentModuleCode = "MODULE";

    /**
     * 首页
     *
     * @param model
     * @return
     */
    @GetMapping(value = "main.do")
    public String main(Model model) {
        model.addAttribute("powers", ModulePowerUtils.getPowers(currentModuleCode));
        return "system/module/list";
    }

    /**
     * 分页查询
     *
     * @return
     */
    @ResponseBody
    @PostMapping(value = "list.do", produces = "application/json; charset=utf-8")
    public Page<Module> list() {
        return moduleService.listPagination(getParams());
    }

    /**
     * 获取模块列表树DOM
     *
     * @return
     */
    @ResponseBody
    @PostMapping(value = "tree.do", produces = "application/json; charset=utf-8")
    public Object tree() {
        return JSON.parse(moduleService.createTree());
    }

    /**
     * 跳转到添加页面
     *
     * @param moduleId 上级模块ID
     * @param level    菜单层级
     * @param model
     * @return
     * @since 0.0.1
     */
    @GetMapping(value = "add.do")
    public String add(Integer moduleId, Integer level, Model model) {
        model.addAttribute("module", new Module());
        model.addAttribute("moduleId", moduleId);
        model.addAttribute("level", level);
        model.addAttribute("cmd", "add");
        return "system/module/add-update";
    }

    /**
     * 添加
     *
     * @param module
     * @return
     */
    @ResponseBody
    @Log(module = currentModuleCode, logType = LogTypeEnum.INSERT)
    @PostMapping(value = "add.do", produces = "application/json; charset=utf-8")
    public Message add(Module module) {
        return moduleService.insert(module);
    }

    /**
     * 跳转到修改页面
     *
     * @param moduleId 模块ID
     * @param level    菜单层级
     * @param model
     * @return
     */
    @GetMapping(value = "update.do")
    public String update(Integer moduleId, Integer level, Model model) {
        model.addAttribute("module", moduleService.get(moduleId));
        model.addAttribute("level", level);
        model.addAttribute("cmd", "update");
        return "system/module/add-update";
    }

    /**
     * 修改
     *
     * @param module
     * @return
     */
    @ResponseBody
    @Log(module = currentModuleCode, logType = LogTypeEnum.UPDATE)
    @PostMapping(value = "update.do", produces = "application/json; charset=utf-8")
    public Message update(Module module) {
        return moduleService.update(module);
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
        return moduleService.delete(Arrays.asList(ids));
    }

    /**
     * 调整模块优先级
     *
     * @return
     */
    @ResponseBody
    @Log(module = currentModuleCode, description = "调整模块优先级", logType = LogTypeEnum.UPDATE)
    @PostMapping(value = "priority.do", produces = "application/json; charset=utf-8")
    public Message priority() {
        return moduleService.priority(getParams());
    }

    /**
     * 获取模块列表
     *
     * @return
     */
    @ResponseBody
    @PostMapping(value = "modules.do", produces = "application/json; charset=utf-8")
    public List<Module> modules() {
        return moduleService.list(getParams());
    }

    /**
     * 模块迁移
     *
     * @param moduleId       待迁移的模块ID
     * @param moduleParentId 要迁移到的模块ID
     * @return
     */
    @ResponseBody
    @Log(module = currentModuleCode, description = "模块迁移", logType = LogTypeEnum.UPDATE)
    @PostMapping(value = "transfer.do", produces = "application/json; charset=utf-8")
    public Message transfer(Integer moduleId, Integer moduleParentId) {
        LOGGER.info("moduleId = {}, moduleParentId = {}", moduleId, moduleParentId);
        return moduleService.transfer(moduleId, moduleParentId);
    }
}
