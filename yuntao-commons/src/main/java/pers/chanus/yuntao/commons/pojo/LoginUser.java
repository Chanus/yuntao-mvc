/*
 * Copyright (c) 2018, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.commons.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * 登录用户信息
 *
 * @author Chanus
 * @date 2018-08-31 17:01:05
 * @since 0.0.1
 */
public class LoginUser implements Serializable {
    private static final long serialVersionUID = -1123372285155373443L;

    /**
     * 登录用户本地线程
     */
    public static ThreadLocal<LoginUser> loginUserThread = new ThreadLocal<LoginUser>();

    /**
     * 用户账号
     */
    private String loginNo;

    /**
     * 用户名称
     */
    private String loginName;

    /**
     * 用户角色代码
     */
    private String roleId;

    /**
     * 用户主账号
     */
    private String masterNo;

    /**
     * 用户主账号角色代码
     */
    private String masterRoleId;

    /**
     * 用户登录IP
     */
    private String loginIp;

    /**
     * 用户头像图片路径
     */
    private String headImage;

    /**
     * 用户其他信息
     */
    private Object object;

    /**
     * 用户菜单列表
     */
    private List<?> menus;

    /**
     * 用户请求URL列表
     */
    private List<String> urls;

    /**
     * 设置登录用户信息
     *
     * @param loginUser 登录用户信息
     * @since 0.0.1
     */
    public static void setLoginUser(LoginUser loginUser) {
        loginUserThread.set(loginUser);
    }

    /**
     * 获取登录用户信息
     *
     * @return 当前登录用户信息
     * @since 0.0.1
     */
    public static LoginUser getLoginUser() {
        return loginUserThread.get();
    }

    /**
     * 清除登录用户信息
     *
     * @since 0.0.1
     */
    public static void removeLoginUser() {
        loginUserThread.remove();
    }

    public String getLoginNo() {
        return loginNo;
    }

    public LoginUser setLoginNo(String loginNo) {
        this.loginNo = loginNo;
        return this;
    }

    public String getLoginName() {
        return loginName;
    }

    public LoginUser setLoginName(String loginName) {
        this.loginName = loginName;
        return this;
    }

    public String getRoleId() {
        return roleId;
    }

    public LoginUser setRoleId(String roleId) {
        this.roleId = roleId;
        return this;
    }

    public String getMasterNo() {
        return masterNo;
    }

    public LoginUser setMasterNo(String masterNo) {
        this.masterNo = masterNo;
        return this;
    }

    public String getMasterRoleId() {
        return masterRoleId;
    }

    public LoginUser setMasterRoleId(String masterRoleId) {
        this.masterRoleId = masterRoleId;
        return this;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public LoginUser setLoginIp(String loginIp) {
        this.loginIp = loginIp;
        return this;
    }

    public String getHeadImage() {
        return headImage;
    }

    public LoginUser setHeadImage(String headImage) {
        this.headImage = headImage;
        return this;
    }

    public Object getObject() {
        return object;
    }

    public LoginUser setObject(Object object) {
        this.object = object;
        return this;
    }

    public List<?> getMenus() {
        return menus;
    }

    public LoginUser setMenus(List<?> menus) {
        this.menus = menus;
        return this;
    }

    public List<String> getUrls() {
        return urls;
    }

    public LoginUser setUrls(List<String> urls) {
        this.urls = urls;
        return this;
    }

    @Override
    public String toString() {
        return "LoginUser [" +
                "loginNo=" + loginNo +
                ", loginName=" + loginName +
                ", roleId=" + roleId +
                ", masterNo=" + masterNo +
                ", masterRoleId=" + masterRoleId +
                ", loginIp=" + loginIp +
                ", headImage=" + headImage +
                ", object=" + object +
                "]";
    }

}
