package pers.chanus.yuntao.manager.model;

import java.io.Serializable;
import java.util.Date;

public class Param implements Serializable {
	private static final long serialVersionUID = 2142559998968841695L;

	private Integer id;

    private String paramCode;

    private String paramData;

    private String remark;

    private String validStatus;

    private String modifiedRoleId;

    private Integer priority;

    private Date gmtCreate;

    private Date gmtModified;

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

    public String getModifiedRoleId() {
        return modifiedRoleId;
    }

    public void setModifiedRoleId(String modifiedRoleId) {
        this.modifiedRoleId = modifiedRoleId == null ? null : modifiedRoleId.trim();
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
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
		return "Param [id=" + id + ", paramCode=" + paramCode + ", paramData=" + paramData + ", remark=" + remark + ", validStatus=" + validStatus + ", modifiedRoleId=" + modifiedRoleId + ", priority=" + priority + ", gmtCreate=" + gmtCreate + ", gmtModified=" + gmtModified + "]";
	}
}