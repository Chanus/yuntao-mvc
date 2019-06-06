/*
 * Copyright (c) 2018, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.chanus.yuntao.manager.model.ChinaArea;
import pers.chanus.yuntao.manager.service.ChinaAreaService;
import pers.chanus.yuntao.springmvc.controller.BaseController;

import java.util.List;

/**
 * 中国行政区划管理
 *
 * @author Chanus
 * @date 2018-09-16 15:55:15
 * @since 0.0.1
 */
@Controller
@RequestMapping("system/chinaarea")
public class ChinaAreaController extends BaseController {
    @Autowired
    private ChinaAreaService chinaAreaService;

    /**
     * 获取中国行政区划信息
     *
     * @return
     */
    @ResponseBody
    @PostMapping(value = "china-areas.do", produces = "application/json; charset=utf-8")
    public List<ChinaArea> chinaAreas() {
        return chinaAreaService.list(getParams());
    }

}
