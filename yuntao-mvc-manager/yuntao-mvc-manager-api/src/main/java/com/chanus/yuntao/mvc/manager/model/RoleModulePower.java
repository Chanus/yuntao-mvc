package com.chanus.yuntao.mvc.manager.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 系统角色权限表
 *
 * @author Chanus
 * @date 2018-09-06 22:03:11
 * @since 0.0.1
 */
public class RoleModulePower implements Serializable {
    private static final long serialVersionUID = 3975361197766244676L;

    /**
     * 主键
     */
    @TableId("rmp_id")
    private Integer rmpId;
    /**
     * 角色代码
     */
    private String roleCode;
    /**
     * 模块代码
     */
    private String moduleCode;
    /**
     * 权限项
     */
    private String powerItem;
    /**
     * 子账号
     */
    private String subNo;
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
                "]" ;
    }
}