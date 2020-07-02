package pers.chanus.yuntao.manager.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class RoleModulePower implements Serializable {
    private static final long serialVersionUID = 3975361197766244676L;

    private Integer rmpId;

    private String roleCode;

    private String moduleCode;

    private String powerItem;

    private String subNo;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;

    public Integer getRmpId() {
        return rmpId;
    }

    public void setRmpId(Integer rmpId) {
        this.rmpId = rmpId;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode == null ? null : roleCode.trim();
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode == null ? null : moduleCode.trim();
    }

    public String getPowerItem() {
        return powerItem;
    }

    public void setPowerItem(String powerItem) {
        this.powerItem = powerItem == null ? null : powerItem.trim();
    }

    public String getSubNo() {
        return subNo;
    }

    public void setSubNo(String subNo) {
        this.subNo = subNo == null ? null : subNo.trim();
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
        return "RoleModulePower [" +
                "rmpId=" + rmpId +
                ", roleCode=" + roleCode +
                ", moduleCode=" + moduleCode +
                ", powerItem=" + powerItem +
                ", subNo=" + subNo +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                "]";
    }
}