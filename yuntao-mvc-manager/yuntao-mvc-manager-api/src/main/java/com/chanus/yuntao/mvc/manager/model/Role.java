package com.chanus.yuntao.mvc.manager.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 系统角色表
 *
 * @author Chanus
 * @date 2018-09-08 21:54:30
 * @since 0.0.1
 */
public class Role implements Serializable {
    private static final long serialVersionUID = -3654625894207910719L;

    /**
     * 主键
     */
    private Integer id;
    /**
     * 角色ID
     */
    private String roleId;
    /**
     * 角色代码
     */
    private String roleCode;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 上级角色ID
     */
    private String parentRoleId;
    /**
     * 能否登录系统：0-不能登录，1-能登录
     */
    private String loginFlag;
    /**
     * 是否可以创建操作员：0-不能，1-能
     */
    private String hasOperator;
    /**
     * 状态：0-停用，1-启用
     */
    private String validStatus;
    /**
     * 备注
     */
    private String remark;
    /**
     * 优先级
     */
    private Integer priority;
    /**
     * 所有上级角色代码
     */
    private String superior;
    /**
     * 创建时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime gmtCreate;
    /**
     * 更新时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode == null ? null : roleCode.trim();
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getParentRoleId() {
        return parentRoleId;
    }

    public void setParentRoleId(String parentRoleId) {
        this.parentRoleId = parentRoleId == null ? null : parentRoleId.trim();
    }

    public String getLoginFlag() {
        return loginFlag;
    }

    public void setLoginFlag(String loginFlag) {
        this.loginFlag = loginFlag == null ? null : loginFlag.trim();
    }

    public String getHasOperator() {
        return hasOperator;
    }

    public void setHasOperator(String hasOperator) {
        this.hasOperator = hasOperator == null ? null : hasOperator.trim();
    }

    public String getValidStatus() {
        return validStatus;
    }

    public void setValidStatus(String validStatus) {
        this.validStatus = validStatus == null ? null : validStatus.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getSuperior() {
        return superior;
    }

    public void setSuperior(String superior) {
        this.superior = superior == null ? null : superior.trim();
    }

    public LocalDateTime getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(LocalDateTime gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public LocalDateTime getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(LocalDateTime gmtModified) {
        this.gmtModified = gmtModified;
    }

    @Override
    public String toString() {
        return "Role [" +
                "id=" + id +
                ", roleId=" + roleId +
                ", roleCode=" + roleCode +
                ", roleName=" + roleName +
                ", parentRoleId=" + parentRoleId +
                ", loginFlag=" + loginFlag +
                ", hasOperator=" + hasOperator +
                ", validStatus=" + validStatus +
                ", remark=" + remark +
                ", priority=" + priority +
                ", superior=" + superior +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                "]";
    }
}