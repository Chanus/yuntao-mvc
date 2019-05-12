package pers.chanus.yuntao.server.syslog;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import com.alibaba.fastjson.annotation.JSONField;

import pers.chanus.yuntao.commons.constant.LogTypeEnum;
import pers.chanus.yuntao.util.StringUtils;

public class Log implements Serializable {
	private static final long serialVersionUID = -529685078374966607L;

	private Long id;

    private String operateNo;

    private String operateRoleId;

    private String operateIp;

    private Integer operateModuleId;

    private String operateUrl;

    private String operateMethod;

    private String operateException;

    private String operateContent;

    private String operateType;

    private String operateTypeDesc;

    private Integer operateConsumeTime;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date operateTime;

    private Date gmtCreate;

    private Date gmtModified;

    private String operateRoleName;

    private String operateModuleName;
    
    private final static Map<String, String> LOGTYPE_MAP = new LinkedHashMap<String, String>();
    static {
		LOGTYPE_MAP.put(LogTypeEnum.LOGIN.name(), "登录系统");
		LOGTYPE_MAP.put(LogTypeEnum.LOGOUT.name(), "退出系统");
		LOGTYPE_MAP.put(LogTypeEnum.INSERT.name(), "添加");
		LOGTYPE_MAP.put(LogTypeEnum.DELETE.name(), "删除");
		LOGTYPE_MAP.put(LogTypeEnum.UPDATE.name(), "更新");
		LOGTYPE_MAP.put(LogTypeEnum.SAVE.name(), "添加或更新");
		LOGTYPE_MAP.put(LogTypeEnum.EXCEPTION.name(), "系统异常");
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

    public void setOperateNo(String operateNo) {
        this.operateNo = operateNo == null ? null : operateNo.trim();
    }

    public String getOperateRoleId() {
        return operateRoleId;
    }

    public void setOperateRoleId(String operateRoleId) {
        this.operateRoleId = operateRoleId == null ? null : operateRoleId.trim();
    }

    public String getOperateIp() {
        return operateIp;
    }

    public void setOperateIp(String operateIp) {
        this.operateIp = operateIp;
    }

    public Integer getOperateModuleId() {
        return operateModuleId;
    }

    public void setOperateModuleId(Integer operateModuleId) {
        this.operateModuleId = operateModuleId;
    }

    public String getOperateUrl() {
        return operateUrl;
    }

    public void setOperateUrl(String operateUrl) {
        this.operateUrl = operateUrl == null ? null : operateUrl.trim();
    }

    public String getOperateMethod() {
        return operateMethod;
    }

    public void setOperateMethod(String operateMethod) {
        this.operateMethod = operateMethod == null ? null : operateMethod.trim();
    }

    public String getOperateException() {
        return operateException;
    }

    public void setOperateException(String operateException) {
        this.operateException = operateException == null ? null : operateException.trim();
    }

    public String getOperateContent() {
		return operateContent;
	}

	public void setOperateContent(String operateContent) {
		this.operateContent = operateContent;
	}

	public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType == null ? null : operateType.trim();
    }

    public String getOperateTypeDesc() {
    	return StringUtils.isBlank(operateTypeDesc) ? LOGTYPE_MAP.get(this.operateType) : operateTypeDesc;
    }

    public void setOperateTypeDesc(String operateTypeDesc) {
        this.operateTypeDesc = operateTypeDesc == null ? null : operateTypeDesc.trim();
    }

    public Integer getOperateConsumeTime() {
		return operateConsumeTime;
	}

	public void setOperateConsumeTime(Integer operateConsumeTime) {
		this.operateConsumeTime = operateConsumeTime;
	}

	public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
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

	@Override
	public String toString() {
		return "Log [id=" + id + ", operateNo=" + operateNo + ", operateRoleId=" + operateRoleId + ", operateIp=" + operateIp + ", operateModuleId=" + operateModuleId + ", operateUrl=" + operateUrl + ", operateMethod=" + operateMethod + ", operateException=" + operateException + ", operateType=" + operateType + ", operateTypeDesc=" + operateTypeDesc + ", operateConsumeTime=" + operateConsumeTime + ", operateTime=" + operateTime + ", gmtCreate=" + gmtCreate + ", gmtModified=" + gmtModified + "]";
	}
}