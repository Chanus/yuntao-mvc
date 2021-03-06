package com.chanus.yuntao.mvc.manager.controller;

import com.chanus.yuntao.mvc.manager.common.CacheData;
import com.chanus.yuntao.mvc.manager.common.ModulePowerUtils;
import com.chanus.yuntao.utils.core.CollectionUtils;
import com.chanus.yuntao.utils.core.lang.Message;
import com.chanus.yuntao.utils.core.lang.Page;
import com.chanus.yuntao.utils.core.map.CustomMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.chanus.yuntao.mvc.common.constant.Constants;
import com.chanus.yuntao.mvc.manager.model.ModulePower;
import com.chanus.yuntao.mvc.manager.model.ModulePowerMethod;
import com.chanus.yuntao.mvc.manager.service.ModulePowerService;
import com.chanus.yuntao.mvc.manager.service.ModuleService;
import com.chanus.yuntao.mvc.manager.service.PowerService;
import com.chanus.yuntao.mvc.framework.annotation.Log;
import com.chanus.yuntao.mvc.framework.controller.BaseController;
import com.chanus.yuntao.mvc.framework.enums.LogTypeEnum;

import java.util.Arrays;
import java.util.Set;

/**
 * 模块权限项管理
 *
 * @author Chanus
 * @date 2018-09-08 20:11:35
 * @since 0.0.1
 */
@Controller
@RequestMapping("system/modulepower")
public class ModulePowerController extends BaseController {
    @Autowired
    private ModulePowerService modulePowerService;
    @Autowired
    private ModuleService moduleService;
    @Autowired
    private PowerService powerService;

    private final String currentModuleCode = "MODULE_POWER";

    /**
     * 首页
     *
     * @param model
     * @return
     */
    @GetMapping(value = "main.do")
    public String main(Model model) {
        model.addAttribute("powers", ModulePowerUtils.getPowers(currentModuleCode));
        model.addAttribute("modules1", moduleService.list(CustomMap.create("moduleParentId", 0)));
        model.addAttribute("powerItems", powerService.list(CustomMap.create("validStatus", Constants.STATUS_YES)));
        return "system/modulepower/list";
    }

    /**
     * 分页查询
     *
     * @return
     */
    @ResponseBody
    @PostMapping(value = "list.do", produces = "application/json; charset=utf-8")
    public Page<ModulePower> list() {
        return modulePowerService.listPagination(getParams());
    }

    /**
     * 跳转到添加页面
     *
     * @param model
     * @return
     */
    @GetMapping(value = "add.do")
    public String add(Model model) {
        model.addAttribute("modulePower", new ModulePower());
        model.addAttribute("cmd", "add");
        return "system/modulepower/add-update";
    }

    /**
     * 添加
     *
     * @param modulePower
     * @return
     */
    @ResponseBody
    @Log(module = currentModuleCode, logType = LogTypeEnum.INSERT)
    @PostMapping(value = "add.do", produces = "application/json; charset=utf-8")
    public Message add(ModulePower modulePower) {
        return modulePowerService.insert(modulePower);
    }

    /**
     * 跳转到修改页面
     *
     * @param mpId  主键
     * @param model
     * @return
     */
    @GetMapping(value = "update.do")
    public String update(Integer mpId, Model model) {
        model.addAttribute("modulePower", modulePowerService.get(mpId));
        model.addAttribute("cmd", "update");
        return "system/modulepower/add-update";
    }

    /**
     * 修改
     *
     * @param modulePower
     * @return
     */
    @ResponseBody
    @Log(module = currentModuleCode, logType = LogTypeEnum.UPDATE)
    @PostMapping(value = "update.do", produces = "application/json; charset=utf-8")
    public Message update(ModulePower modulePower) {
        return modulePowerService.update(modulePower);
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
        return modulePowerService.delete(Arrays.asList(ids));
    }

    /**
     * 跳转到模块权限项方法配置页面
     *
     * @param mpId  模块权限项主键
     * @param model
     * @return
     */
    @GetMapping(value = "method-main.do")
    public String methodMain(Integer mpId, Model model) {
        // 缓存所有的请求URL及Controller类名
        if (CollectionUtils.isEmpty(CacheData.CLASS_URL_MAP.keySet()))
            CacheData.initClassUrlMap(getRequest());

        model.addAttribute("mpId", mpId);
        model.addAttribute("clazzs", CacheData.CLASS_URL_MAP.keySet());
        return "system/modulepower/list-method";
    }

    /**
     * 模块权限项方法列表
     *
     * @param mpId 模块权限项主键
     * @return
     */
    @ResponseBody
    @PostMapping(value = "method-list.do", produces = "application/json; charset=utf-8")
    public Page<ModulePowerMethod> methodList(Integer mpId) {
        return new Page<>(0, modulePowerService.listMethod(mpId));
    }

    /**
     * 获取指定类名下的URL列表
     *
     * @param className 类名
     * @return
     * @since 0.0.1
     */
    @ResponseBody
    @PostMapping(value = "urls.do", produces = "application/json; charset=utf-8")
    public Set<String> urls(String className) {
        return CacheData.CLASS_URL_MAP.get(className);
    }

    /**
     * 添加模块权限项方法
     *
     * @param modulePowerMethod
     * @return
     */
    @ResponseBody
    @Log(module = currentModuleCode, description = "添加模块权限项方法", logType = LogTypeEnum.INSERT)
    @PostMapping(value = "method-add.do", produces = "application/json; charset=utf-8")
    public Message methodAdd(ModulePowerMethod modulePowerMethod) {
        return modulePowerService.insertMethod(modulePowerMethod);
    }

    /**
     * 修改模块权限项方法
     *
     * @param mpmId     主键
     * @param className 类名
     * @param url       请求URL
     * @return
     */
    @ResponseBody
    @Log(module = currentModuleCode, description = "修改模块权限项方法", logType = LogTypeEnum.UPDATE)
    @PostMapping(value = "method-update.do", produces = "application/json; charset=utf-8")
    public Message methodUpdate(Integer mpmId, String className, String url) {
        ModulePowerMethod modulePowerMethod = new ModulePowerMethod();
        modulePowerMethod.setMpmId(mpmId);
        modulePowerMethod.setClassName(className);
        modulePowerMethod.setUrl(url);
        return modulePowerService.updateMethod(modulePowerMethod);
    }

    /**
     * 删除/批量删除模块权限项方法
     *
     * @param ids 被删除记录主键数组
     * @return
     */
    @ResponseBody
    @Log(module = currentModuleCode, description = "删除模块权限项方法", logType = LogTypeEnum.DELETE)
    @PostMapping(value = "method-delete.do", produces = "application/json; charset=utf-8")
    public Message methodDelete(Integer[] ids) {
        return modulePowerService.deleteMethod(Arrays.asList(ids));
    }
}
