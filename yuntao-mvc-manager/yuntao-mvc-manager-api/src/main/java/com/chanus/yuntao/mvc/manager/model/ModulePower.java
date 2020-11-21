package com.chanus.yuntao.mvc.manager.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 模块权限项关系表
 *
 * @author Chanus
 * @date 2018-09-08 20:14:11
 * @since 0.0.1
 */
public class ModulePower implements Serializable {
    private static final long serialVersionUID = 7581034261159771845L;

    /**
     * 主键
     */
    @TableId("mp_id")
    private Integer mpId;
    /**
     * 模块代码
     */
    private String moduleCode;
    /**
     * 权限项
     */
    private String powerItem;
    /**
     * 权限项别名
     */
    private String aliasName;
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
    /**
     * 模块名称
     */
    @TableField(exist = false)
    private String moduleName;
    /**
     * 判断模块是否具有此权限，不为空则具有此权限
     */
    @TableField(exist = false)
    private Integer hasPower;

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
                "]" ;
    }
}