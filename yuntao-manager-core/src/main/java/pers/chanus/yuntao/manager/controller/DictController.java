package pers.chanus.yuntao.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.chanus.yuntao.commons.constant.LogTypeEnum;
import pers.chanus.yuntao.commons.pojo.CustomMap;
import pers.chanus.yuntao.commons.pojo.Message;
import pers.chanus.yuntao.commons.pojo.PageBean;
import pers.chanus.yuntao.manager.common.ModulePowerUtils;
import pers.chanus.yuntao.manager.model.Dict;
import pers.chanus.yuntao.manager.service.DictItemService;
import pers.chanus.yuntao.manager.service.DictService;
import pers.chanus.yuntao.server.annotation.SystemLog;
import pers.chanus.yuntao.springmvc.controller.BaseController;
import pers.chanus.yuntao.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;

/**
 * 数据字典集管理
 *
 * @author Chanus
 * @date 2019-06-07 12:55:23
 * @since 0.1.1
 */
@Controller
@RequestMapping("system/dict")
public class DictController extends BaseController {
    @Autowired
    private DictService dictService;
    @Autowired
    private DictItemService dictItemService;

    private final int currentModuleId = 1012;

    /**
     * 首页
     * @param model
     * @return
     */
    @GetMapping(value = "main.do")
    public String main(Model model) {
        model.addAttribute("powers", ModulePowerUtils.getPowers(getSession(), currentModuleId));
        return "system/dict/list";
    }

    /**
     * 分页查询
     * @return
     */
    @ResponseBody
    @PostMapping(value = "list.do", produces = "application/json; charset=utf-8")
    public PageBean list() {
        CustomMap params = getParams();
        String dictName = (String) params.get("dictName");
        try {
            if (StringUtils.isNotBlank(dictName))
                params.put("dictName", URLDecoder.decode(dictName, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("查询系统数据字典转码异常", e);
        }

        return dictService.listPagination(params);
    }

    /**
     * 跳转到添加页面
     * @param model
     * @return
     */
    @GetMapping(value = "add.do")
    public String add(Model model) {
        model.addAttribute("dict", new Dict());
        model.addAttribute("cmd", "add");
        return "system/dict/add-update";
    }

    /**
     * 添加
     * @param dict
     * @return
     */
    @ResponseBody
    @SystemLog(module = currentModuleId, logType = LogTypeEnum.INSERT)
    @PostMapping(value = "add.do", produces = "application/json; charset=utf-8")
    public Message add(Dict dict) {
        return dictService.insert(dict);
    }

    /**
     * 跳转到修改页面
     * @param id    主键
     * @param model
     * @return
     */
    @GetMapping(value = "update.do")
    public String update(Integer id, Model model) {
        model.addAttribute("dict", dictService.get(id));
        model.addAttribute("cmd", "update");
        return "system/dict/add-update";
    }

    /**
     * 修改
     * @param dict
     * @return
     */
    @ResponseBody
    @SystemLog(module = currentModuleId, logType = LogTypeEnum.UPDATE)
    @PostMapping(value = "update.do", produces = "application/json; charset=utf-8")
    public Message update(Dict dict) {
        return dictService.update(dict);
    }

    /**
     * 删除/批量删除
     * @param ids 被删除记录主键数组
     * @return
     */
    @ResponseBody
    @SystemLog(module = currentModuleId, logType = LogTypeEnum.DELETE)
    @PostMapping(value = "delete.do", produces = "application/json; charset=utf-8")
    public Message delete(Integer[]ids) {
        return dictService.delete(Arrays.asList(ids));
    }

    /**
     * 重载数据字典
     *
     * @return
     */
    @ResponseBody
    @PostMapping(value = "reload-dict.do", produces = "application/json; charset=utf-8")
    public Message reloadDict() {
        return dictItemService.reloadDict();
    }

}
