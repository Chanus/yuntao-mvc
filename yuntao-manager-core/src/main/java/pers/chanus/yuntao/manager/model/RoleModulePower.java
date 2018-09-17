package pers.chanus.yuntao.manager.model;

import java.io.Serializable;
import java.util.Date;

public class RoleModulePower implements Serializable {
	private static final long serialVersionUID = 3975361197766244676L;

	private Integer rmpId;

    private String roleId;

    private Integer moduleId;

    private String powerItem;

    private String subNo;

    private Date gmtCreate;

    private Date gmtModified;

    public Integer getRmpId() {
        return rmpId;
    }

    public void setRmpId(Integer rmpId) {
        this.rmpId = rmpId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
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
		return "RoleModulePower [rmpId=" + rmpId + ", roleId=" + roleId + ", moduleId=" + moduleId + ", powerItem=" + powerItem + ", subNo=" + subNo + ", gmtCreate=" + gmtCreate + ", gmtModified=" + gmtModified + "]";
	}
}