package com.chanus.yuntao.mvc.manager.controller;

import com.chanus.yuntao.mvc.manager.common.CacheData;
import com.chanus.yuntao.utils.core.FileUtils;
import com.chanus.yuntao.utils.core.StringUtils;
import com.chanus.yuntao.utils.core.lang.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.chanus.yuntao.mvc.common.pojo.LoginUser;
import com.chanus.yuntao.mvc.manager.model.Operator;
import com.chanus.yuntao.mvc.manager.service.ModuleService;
import com.chanus.yuntao.mvc.manager.service.OperatorService;
import com.chanus.yuntao.mvc.framework.annotation.Log;
import com.chanus.yuntao.mvc.framework.controller.BaseController;
import com.chanus.yuntao.mvc.framework.enums.LogTypeEnum;

import javax.servlet.http.HttpSession;

/**
 * 系统首页
 *
 * @author Chanus
 * @date 2018-09-06 22:43:51
 * @since 0.0.1
 */
@Controller
@RequestMapping("index")
public class IndexController extends BaseController {
    @Autowired
    private ModuleService moduleService;
    @Autowired
    private OperatorService operatorService;

    /**
     * 系统首页
     *
     * @param model
     * @return
     */
    @GetMapping(value = "index.do")
    public String index(Model model) {
        model.addAttribute("singleLocationLogin", CacheData.SYSTEM_PARAMS_MAP.get("sys_single_location_login"));

        return "index/index";
    }

    /**
     * 重新加载权限
     *
     * @return
     */
    @ResponseBody
    @PostMapping(value = "reload-authority.do", produces = "application/json; charset=utf-8")
    public Message reloadAuthority() {
        Message message;
        try {
            HttpSession session = getSession();
            LoginUser loginUser = (LoginUser) session.getAttribute("loginUser");
            loginUser.setMenus(moduleService.listMenu(loginUser.getRoleCode(), loginUser.getLoginNo()));
            loginUser.setUrls(moduleService.listUrl(loginUser.getRoleCode(), loginUser.getLoginNo()));
            session.setAttribute("loginUser", loginUser);
            message = Message.success("重新加载权限成功");
        } catch (Exception e) {
            LOGGER.error("重新加载权限失败", e);
            message = Message.fail("重新加载权限成功");
        }

        return message;
    }

    /**
     * 跳转到个人资料页面
     *
     * @param model
     * @return
     */
    @GetMapping(value = "user/operator-info.do")
    public String operatorInfo(Model model) {
        model.addAttribute("operator", operatorService.get(LoginUser.getLoginUser().getLoginNo()));
        return "index/user/operator-info";
    }

    /**
     * 修改个人资料
     *
     * @param operator
     * @return
     */
    @ResponseBody
    @Log(description = "修改个人资料", logType = LogTypeEnum.UPDATE)
    @PostMapping(value = "user/operator-info.do", produces = "application/json; charset=utf-8")
    public Message operatorInfo(Operator operator) {
        return operatorService.update(operator);
    }

    /**
     * 头像上传
     *
     * @param file
     * @return
     */
    @ResponseBody
    @Log(description = "上传个人头像", logType = LogTypeEnum.UPDATE)
    @PostMapping(value = "user/headimage.do", produces = "application/json; charset=utf-8")
    public Message headImage(@RequestParam("file") MultipartFile file) {
        String filePath = "upload/headImag";
        Message message = upload(file, filePath);
        if (message.isSuccess()) {
            String operatorNo = LoginUser.getLoginUser().getLoginNo();
            String headImageOld = operatorService.getHeadImage(operatorNo);
            HttpSession session = getSession();
            if (StringUtils.isNotBlank(headImageOld))
                FileUtils.delete(session.getServletContext().getAttribute("parentRealCtx") + "/" + headImageOld);
            String headImage = filePath + "/" + message.getMap().get("fileName");
            message = operatorService.updateHeadImage(operatorNo, headImage);
            if (message.isSuccess()) {
                LoginUser loginUser = (LoginUser) session.getAttribute("loginUser");
                loginUser.setHeadImage(headImage);
                session.setAttribute("loginUser", loginUser);
            }
        }

        return message;
    }

    /**
     * 跳转到修改个人密码页面
     *
     * @return
     */
    @GetMapping(value = "user/update-own-password.do")
    public String updateOwnPassword() {
        return "index/user/operator-password";
    }

    /**
     * 修改个人密码
     *
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @return
     */
    @ResponseBody
    @Log(description = "修改个人密码", logType = LogTypeEnum.UPDATE, ignore = true)
    @PostMapping(value = "user/update-own-password.do", produces = "application/json; charset=utf-8")
    public Message updateOwnPassword(String oldPassword, String newPassword) {
        return operatorService.updatePassword(LoginUser.getLoginUser().getLoginNo(), oldPassword, newPassword, true);
    }

}
