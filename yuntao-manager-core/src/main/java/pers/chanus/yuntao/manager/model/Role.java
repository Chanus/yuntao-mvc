package pers.chanus.yuntao.manager.model;

import java.io.Serializable;
import java.util.Date;

public class Role implements Serializable {
    private static final long serialVersionUID = -3654625894207910719L;

    private Integer id;

    private String roleId;

    private String roleCode;

    private String roleName;

    private String parentRoleId;

    private String loginFlag;

    private String hasOperator;

    private String validStatus;

    private String remark;

    private Integer priority;

    private Date gmtCreate;

    private Date gmtModified;

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

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    @Override
    public String toString() {
        return "Role [id=" + id + ", roleId=" + roleId + ", roleCode=" + roleCode + ", roleName=" + roleName + ", parentRoleId=" + parentRoleId + ", loginFlag=" + loginFlag + ", hasOperator=" + hasOperator + ", validStatus=" + validStatus + ", remark=" + remark + ", priority=" + priority + ", gmtCreate=" + gmtCreate + ", gmtModified=" + gmtModified + "]";
    }
}