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
     * 用户类型
     */
    private String userType;

    /**
     * 用户权限限制
     */
    private String authority;

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

    public void setLoginNo(String loginNo) {
        this.loginNo = loginNo;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getMasterNo() {
        return masterNo;
    }

    public void setMasterNo(String masterNo) {
        this.masterNo = masterNo;
    }

    public String getMasterRoleId() {
        return masterRoleId;
    }

    public void setMasterRoleId(String masterRoleId) {
        this.masterRoleId = masterRoleId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType == null ? null : userType.trim();
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority == null ? null : authority.trim();
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public List<?> getMenus() {
        return menus;
    }

    public void setMenus(List<?> menus) {
        this.menus = menus;
    }

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

    @Override
    public String toString() {
        return "LoginUser [loginNo=" + loginNo + ", loginName=" + loginName + ", roleId=" + roleId + ", masterNo=" + masterNo + ", masterRoleId=" + masterRoleId + ", userType=" + userType + ", authority=" + authority + ", loginIp=" + loginIp + ", headImage=" + headImage + ", object=" + object + "]";
    }

}
