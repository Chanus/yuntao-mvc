package pers.chanus.yuntao.manager.model;

import java.io.Serializable;
import java.util.Date;

public class WhiteIp implements Serializable {
    private static final long serialVersionUID = -3654093253098142154L;

    private Integer id;

    private String whiteIp;

    private String loginNo;

    private String remark;

    private String fixedStatus;

    private String validStatus;

    private Date gmtCreate;

    private Date gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWhiteIp() {
        return whiteIp;
    }

    public void setWhiteIp(String whiteIp) {
        this.whiteIp = whiteIp == null ? null : whiteIp.trim();
    }

    public String getLoginNo() {
        return loginNo;
    }

    public void setLoginNo(String loginNo) {
        this.loginNo = loginNo == null ? null : loginNo.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getFixedStatus() {
        return fixedStatus;
    }

    public void setFixedStatus(String fixedStatus) {
        this.fixedStatus = fixedStatus == null ? null : fixedStatus.trim();
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
        return "WhiteIp [id=" + id + ", whiteIp=" + whiteIp + ", loginNo=" + loginNo + ", remark=" + remark + ", fixedStatus=" + fixedStatus + ", validStatus=" + validStatus + ", gmtCreate=" + gmtCreate + ", gmtModified=" + gmtModified + "]";
    }
}