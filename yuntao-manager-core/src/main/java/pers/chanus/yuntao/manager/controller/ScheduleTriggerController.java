package pers.chanus.yuntao.manager.controller;

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
import pers.chanus.yuntao.manager.model.ScheduleTrigger;
import pers.chanus.yuntao.manager.service.ScheduleTriggerService;
import pers.chanus.yuntao.server.annotation.SystemLog;
import pers.chanus.yuntao.springmvc.controller.BaseController;

import java.util.Arrays;

/**
 * 定时任务触发器管理
 *
 * @author Chanus
 * @date 2020-04-13 23:47:09
 * @since 0.1.7
 */
@Controller
@RequestMapping("system/job/trigger")
public class ScheduleTriggerController extends BaseController {
    @Autowired
    private ScheduleTriggerService scheduleTriggerService;

    private final int currentModuleId = 1013;

    /**
     * 首页
     * @param jobId 定时任务ID
     * @param model
     * @return
     */
    @GetMapping(value = "main.do")
    public String main(Integer jobId, Model model) {
        model.addAttribute("powers", ModulePowerUtils.getPowers(getSession(), currentModuleId));
        model.addAttribute("jobId", jobId);
        return "system/job/trigger/list";
    }

    /**
     * 分页查询
     * @return
     */
    @ResponseBody
    @PostMapping(value = "list.do", produces = "application/json; charset=utf-8")
    public PageBean list() {
        return scheduleTriggerService.listPagination(getParams());
    }

    /**
     * 跳转到添加页面
     * @param jobId 定时任务ID
     * @param model
     * @return
     */
    @GetMapping(value = "add.do")
    public String add(Integer jobId, Model model) {
        ScheduleTrigger scheduleTrigger = new ScheduleTrigger();
        scheduleTrigger.setJobId(jobId);
        model.addAttribute("scheduleTrigger", scheduleTrigger);
        model.addAttribute("cmd", "add");
        return "system/job/trigger/add-update";
    }

    /**
     * 添加
     * @param scheduleTrigger
     * @return
     */
    @ResponseBody
    @SystemLog(module = currentModuleId, logType = LogTypeEnum.INSERT)
    @PostMapping(value = "add.do", produces = "application/json; charset=utf-8")
    public Message add(ScheduleTrigger scheduleTrigger) {
        return scheduleTriggerService.insert(scheduleTrigger);
    }

    /**
     * 跳转到修改页面
     * @param id    主键
     * @param model
     * @return
     */
    @GetMapping(value = "update.do")
    public String update(Integer id, Model model) {
        model.addAttribute("scheduleTrigger", scheduleTriggerService.get(id));
        model.addAttribute("cmd", "update");
        return "system/job/trigger/add-update";
    }

    /**
     * 修改
     * @param scheduleTrigger
     * @return
     */
    @ResponseBody
    @SystemLog(module = currentModuleId, logType = LogTypeEnum.UPDATE)
    @PostMapping(value = "update.do", produces = "application/json; charset=utf-8")
    public Message update(ScheduleTrigger scheduleTrigger) {
        return scheduleTriggerService.update(scheduleTrigger);
    }

    /**
     * 删除/批量删除
     * @param ids 被删除记录主键数组
     * @return
     */
    @ResponseBody
    @SystemLog(module = currentModuleId, logType = LogTypeEnum.DELETE)
    @PostMapping(value = "delete.do", produces = "application/json; charset=utf-8")
    public Message delete(Integer[] ids) {
        return scheduleTriggerService.delete(Arrays.asList(ids));
    }

}
