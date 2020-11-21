package com.chanus.yuntao.mvc.manager.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 组织机构表
 *
 * @author Chanus
 * @date 2019-05-06 21:11:46
 * @since 0.0.8
 */
public class Organization implements Serializable {
    private static final long serialVersionUID = 5899364625950108670L;

    /**
     * 组织ID
     */
    @TableId("org_id")
    private Integer orgId;
    /**
     * 组织代码
     */
    private String orgCode;
    /**
     * 组织名称
     */
    private String orgName;
    /**
     * 上级组织ID
     */
    private Integer orgParentId;
    /**
     * 组织简称
     */
    private String orgShortName;
    /**
     * 组织全称
     */
    private String orgLongName;
    /**
     * 组织地址
     */
    private String orgLocation;
    /**
     * 组织联系方式
     */
    private String orgPhone;
    /**
     * 状态：0-停用，1-启用
     */
    private String validStatus;
    /**
     * 优先级
     */
    private Integer priority;
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

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    public Integer getOrgParentId() {
        return orgParentId;
    }

    public void setOrgParentId(Integer orgParentId) {
        this.orgParentId = orgParentId;
    }

    public String getOrgShortName() {
        return orgShortName;
    }

    public void setOrgShortName(String orgShortName) {
        this.orgShortName = orgShortName == null ? null : orgShortName.trim();
    }

    public String getOrgLongName() {
        return orgLongName;
    }

    public void setOrgLongName(String orgLongName) {
        this.orgLongName = orgLongName == null ? null : orgLongName.trim();
    }

    public String getOrgLocation() {
        return orgLocation;
    }

    public void setOrgLocation(String orgLocation) {
        this.orgLocation = orgLocation == null ? null : orgLocation.trim();
    }

    public String getOrgPhone() {
        return orgPhone;
    }

    public void setOrgPhone(String orgPhone) {
        this.orgPhone = orgPhone == null ? null : orgPhone.trim();
    }

    public String getValidStatus() {
        return validStatus;
    }

    public void setValidStatus(String validStatus) {
        this.validStatus = validStatus == null ? null : validStatus.trim();
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
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
        return "Organization [" +
                "orgId=" + orgId +
                ", orgCode=" + orgCode +
                ", orgName=" + orgName +
                ", orgParentId=" + orgParentId +
                ", orgShortName=" + orgShortName +
                ", orgLongName=" + orgLongName +
                ", orgLocation=" + orgLocation +
                ", orgPhone=" + orgPhone +
                ", validStatus=" + validStatus +
                ", priority=" + priority +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                "]" ;
    }
}
