package pers.chanus.yuntao.manager.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Param implements Serializable {
    private static final long serialVersionUID = 2142559998968841695L;

    private Integer id;

    private String paramCode;

    private String paramData;

    private String remark;

    private String validStatus;

    private String modifiedRoleCode;

    private Integer priority;

    private LocalDateTime gmtCreate;

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
                "]";
    }
}