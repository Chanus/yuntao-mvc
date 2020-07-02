/*
 * Copyright (c) 2018, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.manager.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.chanus.yuntao.manager.model.ChinaArea;
import pers.chanus.yuntao.manager.service.ChinaAreaService;
import pers.chanus.yuntao.springmvc.controller.BaseController;
import com.chanus.yuntao.utils.core.ObjectUtils;

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
     * @param areaParentId 上级行政区划代码
     * @param areaLevel    行政区级别：1-省、直辖市、自治区，2-市，3-区、县
     * @return
     */
    @ResponseBody
    @PostMapping(value = "china-areas.do", produces = "application/json; charset=utf-8")
    public List<ChinaArea> chinaAreas(Integer areaParentId, String areaLevel) {
        return chinaAreaService.list(new QueryWrapper<ChinaArea>().lambda()
                .eq(ObjectUtils.isNotEmpty(areaParentId), ChinaArea::getAreaParentId, areaParentId)
                .eq(ObjectUtils.isNotEmpty(areaLevel), ChinaArea::getAreaLevel, areaLevel));
    }

}
