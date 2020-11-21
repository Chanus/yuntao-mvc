package com.chanus.yuntao.mvc.manager.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 系统IP白名单
 *
 * @author Chanus
 * @date 2018-09-09 15:42:09
 * @since 0.0.1
 */
public class WhiteIp implements Serializable {
    private static final long serialVersionUID = -3654093253098142154L;

    /**
     * 自增长字段
     */
    private Integer id;
    /**
     * IP列表，多个以逗号分隔
     */
    private String whiteIp;
    /**
     * 登录账号列表，若为null则该组IP所有用户都可以登录
     */
    private String loginNo;
    /**
     * 备注
     */
    private String remark;
    /**
     * 是否固定IP：0-不固定，1-固定。若不固定IP，则其他组IP满足条件也可登录，若固定IP，则只能使用当前组IP登录
     */
    private String fixedStatus;
    /**
     * 状态：0-停用，1-启用
     */
    private String validStatus;
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
        return "WhiteIp [" +
                "id=" + id +
                ", whiteIp=" + whiteIp +
                ", loginNo=" + loginNo +
                ", remark=" + remark +
                ", fixedStatus=" + fixedStatus +
                ", validStatus=" + validStatus +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                "]" ;
    }
}