/*
 * Copyright (c) 2018, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.manager.controller;

import com.chanus.yuntao.utils.core.lang.Message;
import com.chanus.yuntao.utils.core.lang.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.chanus.yuntao.manager.common.ModulePowerUtils;
import pers.chanus.yuntao.manager.model.WhiteIp;
import pers.chanus.yuntao.manager.service.WhiteIpService;
import pers.chanus.yuntao.springmvc.annotation.SystemLog;
import pers.chanus.yuntao.springmvc.controller.BaseController;
import pers.chanus.yuntao.springmvc.enums.LogTypeEnum;

import java.util.Arrays;

/**
 * IP白名单管理
 *
 * @author Chanus
 * @date 2018-09-09 15:39:19
 * @since 0.0.1
 */
@Controller
@RequestMapping("system/whiteip")
public class WhiteIpController extends BaseController {
    @Autowired
    private WhiteIpService whiteIpService;

    private final String currentModuleCode = "WHITE_IP";

    /**
     * 首页
     *
     * @param model
     * @return
     */
    @GetMapping(value = "main.do")
    public String main(Model model) {
        model.addAttribute("powers", ModulePowerUtils.getPowers(getSession(), currentModuleCode));
        return "system/whiteip/list";
    }

    /**
     * 分页查询
     *
     * @return
     */
    @ResponseBody
    @PostMapping(value = "list.do", produces = "application/json; charset=utf-8")
    public Page<WhiteIp> list() {
        return whiteIpService.listPagination(getParams());
    }

    /**
     * 跳转到添加页面
     *
     * @param model
     * @return
     */
    @GetMapping(value = "add.do")
    public String add(Model model) {
        model.addAttribute("whiteIp", new WhiteIp());
        model.addAttribute("cmd", "add");
        return "system/whiteip/add-update";
    }

    /**
     * 添加
     *
     * @param whiteIp
     * @return
     */
    @ResponseBody
    @SystemLog(module = currentModuleCode, logType = LogTypeEnum.INSERT)
    @PostMapping(value = "add.do", produces = "application/json; charset=utf-8")
    public Message add(WhiteIp whiteIp) {
        return whiteIpService.insert(whiteIp);
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
        model.addAttribute("whiteIp", whiteIpService.get(id));
        model.addAttribute("cmd", "update");
        return "system/whiteip/add-update";
    }

    /**
     * 修改
     *
     * @param whiteIp
     * @return
     */
    @ResponseBody
    @SystemLog(module = currentModuleCode, logType = LogTypeEnum.UPDATE)
    @PostMapping(value = "update.do", produces = "application/json; charset=utf-8")
    public Message update(WhiteIp whiteIp) {
        return whiteIpService.update(whiteIp);
    }

    /**
     * 删除/批量删除
     *
     * @param ids 被删除记录主键数组
     * @return
     */
    @ResponseBody
    @SystemLog(module = currentModuleCode, logType = LogTypeEnum.DELETE)
    @PostMapping(value = "delete.do", produces = "application/json; charset=utf-8")
    public Message delete(Integer[] ids) {
        return whiteIpService.delete(Arrays.asList(ids));
    }

}
