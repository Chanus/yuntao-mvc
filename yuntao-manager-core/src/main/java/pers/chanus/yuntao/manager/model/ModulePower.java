package pers.chanus.yuntao.manager.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.util.Date;

public class ModulePower implements Serializable {
    private static final long serialVersionUID = 7581034261159771845L;

    @TableId("mp_id")
    private Integer mpId;

    private String moduleCode;

    private String powerItem;

    private String aliasName;

    private Date gmtCreate;

    private Date gmtModified;

    @TableField(exist = false)
    private String moduleName;

    @TableField(exist = false)
    private Integer hasPower;// 判断模块是否具有此权限，不为空则具有此权限

    public Integer getMpId() {
        return mpId;
    }

    public void setMpId(Integer mpId) {
        this.mpId = mpId;
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
        return "ModulePower [" +
                "mpId=" + mpId +
                ", moduleCode=" + moduleCode +
                ", powerItem=" + powerItem +
                ", aliasName=" + aliasName +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                "]";
    }
}