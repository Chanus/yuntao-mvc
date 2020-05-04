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
import pers.chanus.yuntao.commons.pojo.CustomMap;
import pers.chanus.yuntao.commons.pojo.Message;
import pers.chanus.yuntao.commons.pojo.PageBean;
import pers.chanus.yuntao.manager.common.ModulePowerUtils;
import pers.chanus.yuntao.manager.model.ScheduleJob;
import pers.chanus.yuntao.manager.service.ScheduleJobService;
import pers.chanus.yuntao.server.annotation.SystemLog;
import pers.chanus.yuntao.springmvc.controller.BaseController;

import java.util.Arrays;
import java.util.List;

/**
 * 定时任务管理
 *
 * @author Chanus
 * @date 2020-04-12 19:26:19
 * @since 0.1.7
 */
@Controller
@RequestMapping("system/job")
public class ScheduleJobController extends BaseController {
    @Autowired
    private ScheduleJobService scheduleJobService;

    private final String currentModuleCode = "JOB";

    /**
     * 初始化加载定时任务，启动默认状态为启用的所有定时任务
     */
    public void scheduleJobInit() {
        List<ScheduleJob> scheduleJob = scheduleJobService.list(CustomMap.get().putNext("validStatus", ConfigConsts.STATUS_YES));

    }

    /**
     * 首页
     * @param model
     * @return
     */
    @GetMapping(value = "main.do")
    public String main(Model model) {
        model.addAttribute("powers", ModulePowerUtils.getPowers(getSession(), currentModuleCode));
        return "system/job/list";
    }

    /**
     * 分页查询
     * @return
     */
    @ResponseBody
    @PostMapping(value = "list.do", produces = "application/json; charset=utf-8")
    public PageBean list() {
        return scheduleJobService.listPagination(getParams());
    }

    /**
     * 跳转到添加页面
     * @param model
     * @return
     */
    @GetMapping(value = "add.do")
    public String add(Model model) {
        model.addAttribute("scheduleJob", new ScheduleJob());
        model.addAttribute("cmd", "add");
        return "system/job/add-update";
    }

    /**
     * 添加
     * @param scheduleJob
     * @return
     */
    @ResponseBody
    @SystemLog(module = currentModuleCode, logType = LogTypeEnum.INSERT)
    @PostMapping(value = "add.do", produces = "application/json; charset=utf-8")
    public Message add(ScheduleJob scheduleJob) {
        scheduleJob.setValidStatus(ConfigConsts.STATUS_NO);// 添加定时任务时默认为未启动
        return scheduleJobService.insert(scheduleJob);
    }

    /**
     * 跳转到修改页面
     * @param id    主键
     * @param model
     * @return
     */
    @GetMapping(value = "update.do")
    public String update(Integer id, Model model) {
        model.addAttribute("scheduleJob", scheduleJobService.get(id));
        model.addAttribute("cmd", "update");
        return "system/job/add-update";
    }

    /**
     * 修改
     * @param scheduleJob
     * @return
     */
    @ResponseBody
    @SystemLog(module = currentModuleCode, logType = LogTypeEnum.UPDATE)
    @PostMapping(value = "update.do", produces = "application/json; charset=utf-8")
    public Message update(ScheduleJob scheduleJob) {
        return scheduleJobService.update(scheduleJob);
    }

    /**
     * 删除/批量删除
     * @param ids 被删除记录主键数组
     * @return
     */
    @ResponseBody
    @SystemLog(module = currentModuleCode, logType = LogTypeEnum.DELETE)
    @PostMapping(value = "delete.do", produces = "application/json; charset=utf-8")
    public Message delete(Integer[] ids) {
        return scheduleJobService.delete(Arrays.asList(ids));
    }

    /**
     * 启动定时任务
     * @param id 主键ID
     * @return
     */
    @ResponseBody
    @SystemLog(module = currentModuleCode, description = "启动定时任务", logType = LogTypeEnum.UPDATE)
    @PostMapping(value = "start.do", produces = "application/json; charset=utf-8")
    public Message start(Integer id) {
        return scheduleJobService.start(id);
    }

    /**
     * 暂停定时任务
     * @param id 主键ID
     * @return
     */
    @ResponseBody
    @SystemLog(module = currentModuleCode, description = "暂停定时任务", logType = LogTypeEnum.UPDATE)
    @PostMapping(value = "pause.do", produces = "application/json; charset=utf-8")
    public Message pause(Integer id) {
        return scheduleJobService.pause(id);
    }

    /**
     * 恢复定时任务
     * @param id 主键ID
     * @return
     */
    @ResponseBody
    @SystemLog(module = currentModuleCode, description = "恢复定时任务", logType = LogTypeEnum.UPDATE)
    @PostMapping(value = "resume.do", produces = "application/json; charset=utf-8")
    public Message resume(Integer id) {
        return scheduleJobService.resume(id);
    }

    /**
     * 停止定时任务
     * @param id 主键ID
     * @return
     */
    @ResponseBody
    @SystemLog(module = currentModuleCode, description = "停止定时任务", logType = LogTypeEnum.UPDATE)
    @PostMapping(value = "stop.do", produces = "application/json; charset=utf-8")
    public Message stop(Integer id) {
        return scheduleJobService.stop(id);
    }

    /**
     * 立即执行定时任务
     * @param id 主键ID
     * @return
     */
    @ResponseBody
    @SystemLog(module = currentModuleCode, description = "立即执行定时任务", logType = LogTypeEnum.UPDATE)
    @PostMapping(value = "trigger.do", produces = "application/json; charset=utf-8")
    public Message trigger(Integer id) {
        return scheduleJobService.trigger(id);
    }

}
