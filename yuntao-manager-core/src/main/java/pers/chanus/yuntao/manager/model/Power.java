package pers.chanus.yuntao.manager.model;

import java.io.Serializable;
import java.util.Date;

public class Power implements Serializable {
    private static final long serialVersionUID = 7560674783061991206L;

    private Integer id;

    private String powerItem;

    private String powerName;

    private String remark;

    private Integer priority;

    private String validStatus;

    private Date gmtCreate;

    private Date gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPowerItem() {
        return powerItem;
    }

    public void setPowerItem(String powerItem) {
        this.powerItem = powerItem == null ? null : powerItem.trim();
    }

    public String getPowerName() {
        return powerName;
    }

    public void setPowerName(String powerName) {
        this.powerName = powerName == null ? null : powerName.trim();
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

    public String getValidStatus() {
        return validStatus;
    }

    public void setValidStatus(String validStatus) {
        this.validStatus = validStatus == null ? null : validStatus.trim();
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
        return "Power [id=" + id + ", powerItem=" + powerItem + ", powerName=" + powerName + ", remark=" + remark + ", priority=" + priority + ", validStatus=" + validStatus + ", gmtCreate=" + gmtCreate + ", gmtModified=" + gmtModified + "]";
    }
}