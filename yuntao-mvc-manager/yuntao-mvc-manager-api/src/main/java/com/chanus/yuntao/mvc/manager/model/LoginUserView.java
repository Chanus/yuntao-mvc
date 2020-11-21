package com.chanus.yuntao.mvc.manager.model;

import java.io.Serializable;

/**
 * 登录用户视图
 *
 * @author Chanus
 * @date 2018-09-02 01:15:21
 * @since 0.0.1
 */
public class LoginUserView implements Serializable {
    private static final long serialVersionUID = -798620052381070095L;

    /**
     * 登录账号
     */
    private String loginNo;
    /**
     * 登录名称
     */
    private String loginName;
    /**
     * 登录密码
     */
    private String password;
    /**
     * 登录角色代码
     */
    private String roleCode;
    /**
     * 登录用户主账号，非子账号为当前登录账号，子账号为其主账号
     */
    private String masterNo;
    /**
     * 登录用户主账号角色代码，非子账号为当前登录账号角色代码，子账号为其主账号角色代码
     */
    private String masterRoleCode;
    /**
     * 用户状态：0-不可登录，1-可以登录
     */
    private String validStatus;
    /**
     * 登录账号头像
     */
    private String headImage;

    public String getLoginNo() {
        return loginNo;
    }

    public void setLoginNo(String loginNo) {
        this.loginNo = loginNo == null ? null : loginNo.trim();
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode == null ? null : roleCode.trim();
    }

    public String getMasterNo() {
        return masterNo;
    }

    public void setMasterNo(String masterNo) {
        this.masterNo = masterNo == null ? null : masterNo.trim();
    }

    public String getMasterRoleCode() {
        return masterRoleCode;
    }

    public void setMasterRoleCode(String masterRoleCode) {
        this.masterRoleCode = masterRoleCode == null ? null : masterRoleCode.trim();
    }

    public String getValidStatus() {
        return validStatus;
    }

    public void setValidStatus(String validStatus) {
        this.validStatus = validStatus == null ? null : validStatus.trim();
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage == null ? null : headImage.trim();
    }

    @Override
    public String toString() {
        return "LoginUserView [" +
                "loginNo=" + loginNo +
                ", loginName=" + loginName +
                ", password=" + password +
                ", roleCode=" + roleCode +
                ", masterNo=" + masterNo +
                ", masterRoleCode=" + masterRoleCode +
                ", validStatus=" + validStatus +
                ", headImage=" + headImage +
                "]" ;
    }
}