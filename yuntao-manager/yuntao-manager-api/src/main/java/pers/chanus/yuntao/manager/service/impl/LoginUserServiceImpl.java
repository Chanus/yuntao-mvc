/*
 * Copyright (c) 2018, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.chanus.yuntao.commons.constant.ConfigConsts;
import pers.chanus.yuntao.commons.pojo.CustomMap;
import pers.chanus.yuntao.commons.pojo.LoginUser;
import pers.chanus.yuntao.commons.pojo.Message;
import pers.chanus.yuntao.manager.common.CacheData;
import pers.chanus.yuntao.manager.mapper.LoginUserViewMapper;
import pers.chanus.yuntao.manager.mapper.ModuleMapper;
import pers.chanus.yuntao.manager.mapper.RoleMapper;
import pers.chanus.yuntao.manager.mapper.WhiteIpMapper;
import pers.chanus.yuntao.manager.model.LoginUserView;
import pers.chanus.yuntao.manager.model.Module;
import pers.chanus.yuntao.manager.model.Role;
import pers.chanus.yuntao.manager.service.LoginUserService;
import com.chanus.yuntao.utils.core.CollectionUtils;
import com.chanus.yuntao.utils.core.ObjectUtils;
import com.chanus.yuntao.utils.core.StringUtils;
import com.chanus.yuntao.utils.core.encrypt.SHAUtils;

import java.util.List;

/**
 * 登录用户接口实现
 *
 * @author Chanus
 * @date 2018-09-02 01:19:26
 * @since 0.0.1
 */
@Service
public class LoginUserServiceImpl implements LoginUserService {
    @Autowired
    private LoginUserViewMapper loginUserViewMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private ModuleMapper moduleMapper;
    @Autowired
    private WhiteIpMapper whiteIpMapper;

    @Override
    public Message login(String loginNo, String password, String loginIp) {
        if (StringUtils.isBlank(loginNo))
            return Message.fail("登录账号不能为空");
        if (StringUtils.isBlank(password))
            return Message.fail("登录密码不能为空");

        // 验证IP白名单
        if (ConfigConsts.STATUS_YES.equals(CacheData.SYSTEM_PARAMS_MAP.get("sys_check_white_ip"))) {
            if (StringUtils.isBlank(loginIp))
                return Message.fail("登录IP为空");

            // 验证用户是否固定IP
            String fixedWhiteIps = whiteIpMapper.getFixedWhiteIps(loginNo);
            if (StringUtils.isNotBlank(fixedWhiteIps) && !StringUtils.contains(fixedWhiteIps + ",", loginIp + ",")) {// 用户固定IP，登录IP必须存在于该IP白名单才能登录
                return Message.fail("当前IP不允许登录");
            } else if (StringUtils.isBlank(fixedWhiteIps)) {// 用户没有固定IP，登录IP存在于任何一组白名单IP中即可登录
                int whiteIpsCount = whiteIpMapper.count(CustomMap.get().putNext("validStatus", ConfigConsts.STATUS_YES).putNext("fixedStatus", ConfigConsts.STATUS_NO).putNext("whiteIp", loginIp).putNext("loginNo", loginNo));
                if (whiteIpsCount == 0)
                    return Message.fail("当前IP不允许登录");
            }
        }

        LoginUserView loginUserView = loginUserViewMapper.login(loginNo);
        if (loginUserView == null)
            return Message.fail("当前用户不存在");
        if (ConfigConsts.STATUS_NO.equals(loginUserView.getValidStatus()))
            return Message.fail("当前用户不可用");
        if (!SHAUtils.verifySHA256(password + loginNo, loginUserView.getPassword()))
            return Message.fail("登录密码不正确");

        Role role = roleMapper.getLoginStatus(loginUserView.getRoleCode());
        if (role == null)
            return Message.fail("当前用户角色不存在");
        if (ConfigConsts.STATUS_NO.equals(role.getValidStatus()))
            return Message.fail("当前用户角色被禁用");
        if (ConfigConsts.STATUS_NO.equals(role.getLoginFlag()))
            return Message.fail("当前用户角色不允许登录系统");

        List<Module> menus = moduleMapper.listMenu(loginUserView.getRoleCode(), loginUserView.getLoginNo());
        if (CollectionUtils.isEmpty(menus))
            return Message.fail("当前用户没有系统权限");

        LoginUser loginUser = new LoginUser().setLoginNo(loginUserView.getLoginNo())
                .setLoginName(loginUserView.getLoginName()).setRoleCode(loginUserView.getRoleCode())
                .setMasterNo(ObjectUtils.defaultIfBlank(loginUserView.getMasterNo(), loginUserView.getLoginNo()))
                .setMasterRoleCode(ObjectUtils.defaultIfBlank(loginUserView.getMasterRoleCode(), loginUserView.getRoleCode()))
                .setLoginIp(loginIp).setHeadImage(loginUserView.getHeadImage()).setMenus(menus)
                .setUrls(moduleMapper.listUrl(loginUserView.getRoleCode(), loginUserView.getLoginNo()));
        LoginUser.setLoginUser(loginUser);

        return Message.success("登录成功").initMsg(loginUser);
    }

    @Override
    public Message login(String loginNo, String password, String roleCode, String loginIp) {
        if (StringUtils.isBlank(loginNo))
            return Message.fail("登录账号不能为空");
        if (StringUtils.isBlank(password))
            return Message.fail("登录密码不能为空");

        // 验证IP白名单
        if (ConfigConsts.STATUS_YES.equals(CacheData.SYSTEM_PARAMS_MAP.get("sys_check_white_ip"))) {
            if (StringUtils.isBlank(loginIp))
                return Message.fail("登录IP为空");

            // 验证用户是否固定IP
            String fixedWhiteIps = whiteIpMapper.getFixedWhiteIps(loginNo);
            if (StringUtils.isNotBlank(fixedWhiteIps) && !StringUtils.contains(fixedWhiteIps + ",", loginIp + ",")) {// 用户固定IP，登录IP必须存在于该IP白名单才能登录
                return Message.fail("当前IP不允许登录");
            } else if (StringUtils.isBlank(fixedWhiteIps)) {// 用户没有固定IP，登录IP存在于任何一组白名单IP中即可登录
                int whiteIpsCount = whiteIpMapper.count(CustomMap.get().putNext("validStatus", ConfigConsts.STATUS_YES).putNext("fixedStatus", ConfigConsts.STATUS_NO).putNext("whiteIp", loginIp).putNext("loginNo", loginNo));
                if (whiteIpsCount == 0)
                    return Message.fail("当前IP不允许登录");
            }
        }

        LoginUserView loginUserView = loginUserViewMapper.login(loginNo);
        if (loginUserView == null)
            return Message.fail("当前用户不存在");
        if (ConfigConsts.STATUS_NO.equals(loginUserView.getValidStatus()))
            return Message.fail("当前用户不可用");
        if (!SHAUtils.verifySHA256(password + loginNo, loginUserView.getPassword()))
            return Message.fail("登录密码不正确");
        if (StringUtils.isBlank(loginUserView.getRoleCode()))
            return Message.fail("当前用户角色不存在");
        String[] userRoleCodes = loginUserView.getRoleCode().split(",");
        if (!CollectionUtils.contains(userRoleCodes, roleCode))
            return Message.fail("当前用户角色不存在");

        Role role = roleMapper.getLoginStatus(roleCode);
        if (role == null)
            return Message.fail("当前用户角色不存在");
        if (ConfigConsts.STATUS_NO.equals(role.getValidStatus()))
            return Message.fail("当前用户角色被禁用");
        if (ConfigConsts.STATUS_NO.equals(role.getLoginFlag()))
            return Message.fail("当前用户角色不允许登录系统");

        List<Module> menus = moduleMapper.listMenu(roleCode, loginUserView.getLoginNo());
        if (CollectionUtils.isEmpty(menus))
            return Message.fail("当前用户没有系统权限");

        LoginUser loginUser = new LoginUser().setLoginNo(loginUserView.getLoginNo())
                .setLoginName(loginUserView.getLoginName()).setRoleCode(roleCode)
                .setMasterNo(ObjectUtils.defaultIfBlank(loginUserView.getMasterNo(), loginUserView.getLoginNo()))
                .setMasterRoleCode(ObjectUtils.defaultIfBlank(loginUserView.getMasterRoleCode(), roleCode))
                .setLoginIp(loginIp).setHeadImage(loginUserView.getHeadImage()).setMenus(menus)
                .setUrls(moduleMapper.listUrl(roleCode, loginUserView.getLoginNo()));
        LoginUser.setLoginUser(loginUser);

        return Message.success("登录成功").initMsg(loginUser);
    }

    @Override
    public LoginUserView getUser(String loginNo) {
        return loginUserViewMapper.getUser(loginNo);
    }

    @Override
    public LoginUserView getLoginUser(String loginNo) {
        return loginUserViewMapper.login(loginNo);
    }

}