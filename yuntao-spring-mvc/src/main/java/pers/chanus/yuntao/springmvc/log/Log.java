package pers.chanus.yuntao.springmvc.log;

import com.alibaba.fastjson.annotation.JSONField;
import com.chanus.yuntao.utils.core.StringUtils;
import pers.chanus.yuntao.springmvc.enums.LogTypeEnum;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

public class Log implements Serializable {
    private static final long serialVersionUID = -529685078374966607L;

    private Long id;

    private String operateNo;

    private String operateRoleCode;

    private String operateIp;

    private String operateModuleCode;

    private String operateUrl;

    private String operateMethod;

    private String operateException;

    private String operateContent;

    private String operateType;

    private String operateTypeDesc;

    private Integer operateConsumeTime;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime operateTime;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;

    private String operateRoleName;

    private String operateModuleName;

    private String operateMasterRoleCode;

    private final static Map<String, String> LOGTYPE_MAP = new LinkedHashMap<>();

    static {
        LOGTYPE_MAP.put(LogTypeEnum.LOGIN.name(), "登录系统");
        LOGTYPE_MAP.put(LogTypeEnum.LOGOUT.name(), "退出系统");
        LOGTYPE_MAP.put(LogTypeEnum.INSERT.name(), "添加");
        LOGTYPE_MAP.put(LogTypeEnum.DELETE.name(), "删除");
        LOGTYPE_MAP.put(LogTypeEnum.UPDATE.name(), "更新");
        LOGTYPE_MAP.put(LogTypeEnum.SAVE.name(), "添加或更新");
        LOGTYPE_MAP.put(LogTypeEnum.EXCEPTION.name(), "系统异常");
        LOGTYPE_MAP.put(LogTypeEnum.SCHEDULER.name(), "定时任务");
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOperateNo() {
        return operateNo;
    }

    public Log setOperateNo(String operateNo) {
        this.operateNo = operateNo == null ? null : operateNo.trim();
        return this;
    }

    public String getOperateRoleCode() {
        return operateRoleCode;
    }

    public Log setOperateRoleCode(String operateRoleCode) {
        this.operateRoleCode = operateRoleCode == null ? null : operateRoleCode.trim();
        return this;
    }

    public String getOperateIp() {
        return operateIp;
    }

    public Log setOperateIp(String operateIp) {
        this.operateIp = operateIp;
        return this;
    }

    public String getOperateModuleCode() {
        return operateModuleCode;
    }

    public Log setOperateModuleCode(String operateModuleCode) {
        this.operateModuleCode = operateModuleCode == null ? null : operateModuleCode.trim();
        return this;
    }

    public String getOperateUrl() {
        return operateUrl;
    }

    public Log setOperateUrl(String operateUrl) {
        this.operateUrl = operateUrl == null ? null : operateUrl.trim();
        return this;
    }

    public String getOperateMethod() {
        return operateMethod;
    }

    public Log setOperateMethod(String operateMethod) {
        this.operateMethod = operateMethod == null ? null : operateMethod.trim();
        return this;
    }

    public String getOperateException() {
        return operateException;
    }

    public Log setOperateException(String operateException) {
        this.operateException = operateException == null ? null : operateException.trim();
        return this;
    }

    public String getOperateContent() {
        return operateContent;
    }

    public Log setOperateContent(String operateContent) {
        this.operateContent = operateContent;
        return this;
    }

    public String getOperateType() {
        return operateType;
    }

    public Log setOperateType(String operateType) {
        this.operateType = operateType == null ? null : operateType.trim();
        return this;
    }

    public String getOperateTypeDesc() {
        return StringUtils.isBlank(operateTypeDesc) ? LOGTYPE_MAP.get(this.operateType) : operateTypeDesc;
    }

    public Log setOperateTypeDesc(String operateTypeDesc) {
        this.operateTypeDesc = operateTypeDesc == null ? null : operateTypeDesc.trim();
        return this;
    }

    public Integer getOperateConsumeTime() {
        return operateConsumeTime;
    }

    public Log setOperateConsumeTime(Integer operateConsumeTime) {
        this.operateConsumeTime = operateConsumeTime;
        return this;
    }

    public LocalDateTime getOperateTime() {
        return operateTime;
    }

    public Log setOperateTime(LocalDateTime operateTime) {
        this.operateTime = operateTime;
        return this;
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

    public String getOperateRoleName() {
        return operateRoleName;
    }

    public void setOperateRoleName(String operateRoleName) {
        this.operateRoleName = operateRoleName;
    }

    public String getOperateModuleName() {
        return operateModuleName;
    }

    public void setOperateModuleName(String operateModuleName) {
        this.operateModuleName = operateModuleName;
    }

    public String getOperateMasterRoleCode() {
        return operateMasterRoleCode;
    }

    public void setOperateMasterRoleCode(String operateMasterRoleCode) {
        this.operateMasterRoleCode = operateMasterRoleCode;
    }

    @Override
    public String toString() {
        return "Log [" +
                "id=" + id +
                ", operateNo=" + operateNo +
                ", operateRoleCode=" + operateRoleCode +
                ", operateIp=" + operateIp +
                ", operateModuleCode=" + operateModuleCode +
                ", operateUrl=" + operateUrl +
                ", operateMethod=" + operateMethod +
                ", operateException=" + operateException +
                ", operateType=" + operateType +
                ", operateTypeDesc=" + operateTypeDesc +
                ", operateConsumeTime=" + operateConsumeTime +
                ", operateTime=" + operateTime +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                "]";
    }
}