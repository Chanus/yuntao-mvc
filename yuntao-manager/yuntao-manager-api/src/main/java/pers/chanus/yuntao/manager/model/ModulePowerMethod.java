package pers.chanus.yuntao.manager.model;

import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ModulePowerMethod implements Serializable {
    private static final long serialVersionUID = 5917034100446628692L;

    @TableId("mpm_id")
    private Integer mpmId;

    private Integer mpId;

    private String className;

    private String url;

    private LocalDateTime gmtCreate;

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
                "]";
    }
}