package pers.chanus.yuntao.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pers.chanus.yuntao.commons.constant.LogTypeEnum;
import pers.chanus.yuntao.commons.pojo.Message;
import pers.chanus.yuntao.commons.pojo.PageBean;
import pers.chanus.yuntao.manager.common.ModulePowerUtils;
import pers.chanus.yuntao.manager.model.DictItem;
import pers.chanus.yuntao.manager.service.DictItemService;
import pers.chanus.yuntao.springmvc.annotation.SystemLog;
import pers.chanus.yuntao.springmvc.controller.BaseController;

import java.util.Arrays;

/**
 * 数据字典项管理
 *
 * @author Chanus
 * @date 2019-06-07 14:01:03
 * @since 0.1.1
 */
@Controller
@RequestMapping("system/dict/item")
public class DictItemController extends BaseController {
    @Autowired
    private DictItemService dictItemService;

    private final String currentModuleCode = "DICT";

    /**
     * 首页
     * @param dictCode  数据字典集代码
     * @param model
     * @return
     */
    @GetMapping(value = "main.do")
    public String main(@ModelAttribute("dictCode") String dictCode, Model model) {
        model.addAttribute("powers", ModulePowerUtils.getPowers(getSession(), currentModuleCode));
        return "system/dict/item/list";
    }

    /**
     * 分页查询
     * @return
     */
    @ResponseBody
    @PostMapping(value = "list.do", produces = "application/json; charset=utf-8")
    public PageBean list() {
        return dictItemService.listPagination(getParams());
    }

    /**
     * 跳转到添加页面
     * @param dictCode  数据字典集代码
     * @param model
     * @return
     */
    @GetMapping(value = "add.do")
    public String add(String dictCode, Model model) {
        DictItem dictItem = new DictItem();
        dictItem.setDictCode(dictCode);
        model.addAttribute("dictItem", dictItem);
        model.addAttribute("cmd", "add");
        return "system/dict/item/add-update";
    }

    /**
     * 添加
     * @param dictItem
     * @return
     */
    @ResponseBody
    @SystemLog(module = currentModuleCode, logType = LogTypeEnum.INSERT)
    @PostMapping(value = "add.do", produces = "application/json; charset=utf-8")
    public Message add(DictItem dictItem) {
        return dictItemService.insert(dictItem);
    }

    /**
     * 跳转到修改页面
     * @param id    主键
     * @param model
     * @return
     */
    @GetMapping(value = "update.do")
    public String update(Integer id, Model model) {
        model.addAttribute("dictItem", dictItemService.get(id));
        model.addAttribute("cmd", "update");
        return "system/dict/item/add-update";
    }

    /**
     * 修改
     * @param dictItem
     * @return
     */
    @ResponseBody
    @SystemLog(module = currentModuleCode, logType = LogTypeEnum.UPDATE)
    @PostMapping(value = "update.do", produces = "application/json; charset=utf-8")
    public Message update(DictItem dictItem) {
        return dictItemService.update(dictItem);
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
        return dictItemService.delete(Arrays.asList(ids));
    }

}
