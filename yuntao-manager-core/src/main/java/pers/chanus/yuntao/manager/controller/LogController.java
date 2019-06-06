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
import pers.chanus.yuntao.commons.pojo.CustomMap;
import pers.chanus.yuntao.commons.pojo.LoginUser;
import pers.chanus.yuntao.commons.pojo.PageBean;
import pers.chanus.yuntao.manager.service.LogService;
import pers.chanus.yuntao.springmvc.controller.BaseController;
import pers.chanus.yuntao.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * 日志查询
 *
 * @author Chanus
 * @date 2018-09-09 15:50:08
 * @since 0.0.1
 */
@Controller
@RequestMapping("system/log")
public class LogController extends BaseController {
    @Autowired
    private LogService logService;

    /**
     * 首页
     *
     * @return
     */
    @GetMapping(value = "main.do")
    public String main() {
        return "system/log/list";
    }

    /**
     * 分页查询
     *
     * @return
     */
    @ResponseBody
    @PostMapping(value = "list.do", produces = "application/json; charset=utf-8")
    public PageBean list() {
        CustomMap params = getParams();
        String operateContent = (String) params.get("operateContent");
        try {
            if (StringUtils.isNotBlank(operateContent))
                params.put("operateContent", URLDecoder.decode(operateContent, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("查询系统日志转码异常", e);
        }
        return logService.listPagination(params.putNext("operateRoleId", LoginUser.getLoginUser().getMasterRoleId()));
    }

    /**
     * 跳转到日志内容页面
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping(value = "content.do")
    public String content(Long id, Model model) {
        model.addAttribute("log", logService.get(id));
        return "system/log/content";
    }
}
