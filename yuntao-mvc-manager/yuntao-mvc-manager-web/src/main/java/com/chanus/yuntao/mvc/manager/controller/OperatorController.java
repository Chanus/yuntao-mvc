package com.chanus.yuntao.mvc.manager.controller;

import com.alibaba.fastjson.JSON;
import com.chanus.yuntao.mvc.manager.common.ModulePowerUtils;
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
import com.chanus.yuntao.mvc.common.pojo.LoginUser;
import com.chanus.yuntao.mvc.manager.model.Operator;
import com.chanus.yuntao.mvc.manager.service.OperatorService;
import com.chanus.yuntao.mvc.manager.service.RoleService;
import com.chanus.yuntao.mvc.framework.annotation.Log;
import com.chanus.yuntao.mvc.framework.controller.BaseController;
import com.chanus.yuntao.mvc.framework.enums.LogTypeEnum;

import java.util.Arrays;

/**
 * 操作员管理
 *
 * @author Chanus
 * @date 2018-09-09 09:48:01
 * @since 0.0.1
 */
@Controller
@RequestMapping("system/operator")
public class OperatorController extends BaseController {
    @Autowired
    private OperatorService operatorService;
    @Autowired
    private RoleService roleService;

    private final String currentModuleCode = "OPERATOR";

    /**
     * 首页
     *
     * @param model
     * @return
     */
    @GetMapping(value = "main.do")
    public String main(Model model) {
        model.addAttribute("powers", ModulePowerUtils.getPowers(currentModuleCode));
        return "system/operator/list";
    }

    /**
     * 分页查询
     *
     * @return
     */
    @ResponseBody
    @PostMapping(value = "list.do", produces = "application/json; charset=utf-8")
    public Page<Operator> list() {
        CustomMap params = getParams();
        String loginRoleCode = LoginUser.getLoginUser().getMasterRoleCode();
        if (!Constants.ROLE_ADMIN_0.equals(loginRoleCode))
            params.put("loginRoleCode", loginRoleCode);

        return operatorService.listPagination(params);
    }

    /**
     * 获取角色列表树DOM
     *
     * @return
     */
    @ResponseBody
    @PostMapping(value = "tree.do", produces = "application/json; charset=utf-8")
    public Object tree() {
        return JSON.parse(roleService.createTree(LoginUser.getLoginUser().getMasterRoleCode()));
    }

    /**
     * 跳转到添加页面
     *
     * @param operatorRoleCode 操作员角色代码
     * @param model
     * @return
     */
    @GetMapping(value = "add.do")
    public String add(Integer operatorRoleCode, Model model) {
        model.addAttribute("operator", new Operator());
        model.addAttribute("operatorRoleCode", operatorRoleCode);
        model.addAttribute("cmd", "add");
        return "system/operator/add-update";
    }

    /**
     * 添加
     *
     * @param operator
     * @return
     */
    @ResponseBody
    @Log(module = currentModuleCode, logType = LogTypeEnum.INSERT)
    @PostMapping(value = "add.do", produces = "application/json; charset=utf-8")
    public Message add(Operator operator) {
        return operatorService.insert(operator);
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
        model.addAttribute("operator", operatorService.get(id));
        model.addAttribute("cmd", "update");
        return "system/operator/add-update";
    }

    /**
     * 修改
     *
     * @param operator
     * @return
     */
    @ResponseBody
    @Log(module = currentModuleCode, logType = LogTypeEnum.UPDATE)
    @PostMapping(value = "update.do", produces = "application/json; charset=utf-8")
    public Message update(Operator operator) {
        return operatorService.update(operator);
    }

    /**
     * 跳转到修改操作员密码页面
     *
     * @param operatorNo 操作员账号
     * @param model
     * @return
     */
    @GetMapping(value = "password.do")
    public String password(String operatorNo, Model model) {
        model.addAttribute("operatorNo", operatorNo);
        return "system/operator/update-password";
    }

    /**
     * 修改操作员密码
     *
     * @param operatorNo 操作员账号
     * @param password   新密码
     * @return
     */
    @ResponseBody
    @Log(module = currentModuleCode, description = "修改操作员密码", logType = LogTypeEnum.UPDATE, ignore = true)
    @PostMapping(value = "password.do", produces = "application/json; charset=utf-8")
    public Message password(String operatorNo, String password) {
        return operatorService.updatePassword(operatorNo, null, password, false);
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
        return operatorService.delete(Arrays.asList(ids));
    }
}
