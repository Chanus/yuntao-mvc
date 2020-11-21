package com.chanus.yuntao.mvc.manager.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 系统基础参数表
 *
 * @author Chanus
 * @date 2018-09-06 17:50:17
 * @since 0.0.1
 */
public class Param implements Serializable {
    private static final long serialVersionUID = 2142559998968841695L;

    /**
     * 主键
     */
    private Integer id;
    /**
     * 参数代码
     */
    private String paramCode;
    /**
     * 参数值
     */
    private String paramData;
    /**
     * 备注
     */
    private String remark;
    /**
     * 状态：0-停用，1-启用
     */
    private String validStatus;
    /**
     * 可以看到该参数的角色，多个角色用‘,’隔开，为空则所有角色都可以看到
     */
    private String modifiedRoleCode;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getParamCode() {
        return paramCode;
    }

    public void setParamCode(String paramCode) {
        this.paramCode = paramCode == null ? null : paramCode.trim();
    }

    public String getParamData() {
        return paramData;
    }

    public void setParamData(String paramData) {
        this.paramData = paramData == null ? null : paramData.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getValidStatus() {
        return validStatus;
    }

    public void setValidStatus(String validStatus) {
        this.validStatus = validStatus == null ? null : validStatus.trim();
    }

    public String getModifiedRoleCode() {
        return modifiedRoleCode;
    }

    public void setModifiedRoleCode(String modifiedRoleCode) {
        this.modifiedRoleCode = modifiedRoleCode == null ? null : modifiedRoleCode.trim();
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
        return "Param [" +
                "id=" + id +
                ", paramCode=" + paramCode +
                ", paramData=" + paramData +
                ", remark=" + remark +
                ", validStatus=" + validStatus +
                ", modifiedRoleCode=" + modifiedRoleCode +
                ", priority=" + priority +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                "]" ;
    }
}