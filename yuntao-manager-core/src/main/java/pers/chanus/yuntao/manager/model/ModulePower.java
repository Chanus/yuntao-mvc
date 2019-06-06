package pers.chanus.yuntao.manager.model;

import java.io.Serializable;
import java.util.Date;

public class ModulePower implements Serializable {
    private static final long serialVersionUID = 7581034261159771845L;

    private Integer mpId;

    private Integer moduleId;

    private String powerItem;

    private String aliasName;

    private Date gmtCreate;

    private Date gmtModified;

    private String moduleName;

    private Integer hasPower;// 判断模块是否具有此权限，不为空则具有此权限

    public Integer getMpId() {
        return mpId;
    }

    public void setMpId(Integer mpId) {
        this.mpId = mpId;
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

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName == null ? null : aliasName.trim();
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

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName == null ? null : moduleName.trim();
    }

    public Integer getHasPower() {
        return hasPower;
    }

    public void setHasPower(Integer hasPower) {
        this.hasPower = hasPower;
    }

    @Override
    public String toString() {
        return "ModulePower [mpId=" + mpId + ", moduleId=" + moduleId + ", powerItem=" + powerItem + ", aliasName=" + aliasName + ", gmtCreate=" + gmtCreate + ", gmtModified=" + gmtModified + "]";
    }
}