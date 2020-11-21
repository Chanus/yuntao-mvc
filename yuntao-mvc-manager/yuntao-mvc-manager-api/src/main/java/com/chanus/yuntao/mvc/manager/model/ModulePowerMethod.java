package com.chanus.yuntao.mvc.manager.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 模块权限项方法配置表
 *
 * @author Chanus
 * @date 2018-09-08 20:15:16
 * @since 0.0.1
 */
public class ModulePowerMethod implements Serializable {
    private static final long serialVersionUID = 5917034100446628692L;

    /**
     * 主键ID
     */
    @TableId("mpm_id")
    private Integer mpmId;
    /**
     * 模块权限项ID
     */
    private Integer mpId;
    /**
     * 类名
     */
    private String className;
    /**
     * 请求URL
     */
    private String url;
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

    public Integer getMpmId() {
        return mpmId;
    }

    public void setMpmId(Integer mpmId) {
        this.mpmId = mpmId;
    }

    public Integer getMpId() {
        return mpId;
    }

    public void setMpId(Integer mpId) {
        this.mpId = mpId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className == null ? null : className.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
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
        return "ModulePowerMethod [" +
                "mpmId=" + mpmId +
                ", mpId=" + mpId +
                ", className=" + className +
                ", url=" + url +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                "]" ;
    }
}