package pers.chanus.yuntao.manager.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Module implements Serializable {
	private static final long serialVersionUID = -4043140639207121275L;

	private Integer moduleId;

    private Integer moduleParentId;

    private String moduleName;

    private String moduleUrl;

    private String moduleLevel;

    private String moduleIsMenu;

    private String moduleForAdmin;

    private String moduleBelong;

    private String moduleIcon;

    private String remark;

    private String validStatus;

    private Date gmtCreate;

    private Date gmtModified;
    
    private List<Module> children;// 一级模块的子模块
    
    private List<ModulePower> modulePowers;// 模块所具有的权限项

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    public Integer getModuleParentId() {
        return moduleParentId;
    }

    public void setModuleParentId(Integer moduleParentId) {
        this.moduleParentId = moduleParentId;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName == null ? null : moduleName.trim();
    }

    public String getModuleUrl() {
        return moduleUrl;
    }

    public void setModuleUrl(String moduleUrl) {
        this.moduleUrl = moduleUrl == null ? null : moduleUrl.trim();
    }

    public String getModuleLevel() {
        return moduleLevel;
    }

    public void setModuleLevel(String moduleLevel) {
        this.moduleLevel = moduleLevel == null ? null : moduleLevel.trim();
    }

    public String getModuleIsMenu() {
        return moduleIsMenu;
    }

    public void setModuleIsMenu(String moduleIsMenu) {
        this.moduleIsMenu = moduleIsMenu == null ? null : moduleIsMenu.trim();
    }

    public String getModuleForAdmin() {
        return moduleForAdmin;
    }

    public void setModuleForAdmin(String moduleForAdmin) {
        this.moduleForAdmin = moduleForAdmin == null ? null : moduleForAdmin.trim();
    }

    public String getModuleBelong() {
        return moduleBelong;
    }

    public void setModuleBelong(String moduleBelong) {
        this.moduleBelong = moduleBelong == null ? null : moduleBelong.trim();
    }

    public String getModuleIcon() {
        return moduleIcon;
    }

    public void setModuleIcon(String moduleIcon) {
        this.moduleIcon = moduleIcon == null ? null : moduleIcon.trim();
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

	public List<Module> getChildren() {
		return children;
	}

	public void setChildren(List<Module> children) {
		this.children = children;
	}

	public List<ModulePower> getModulePowers() {
		return modulePowers;
	}

	public void setModulePowers(List<ModulePower> modulePowers) {
		this.modulePowers = modulePowers;
	}

	@Override
	public String toString() {
		return "Module [moduleId=" + moduleId + ", moduleParentId=" + moduleParentId + ", moduleName=" + moduleName + ", moduleUrl=" + moduleUrl + ", moduleLevel=" + moduleLevel + ", moduleIsMenu=" + moduleIsMenu + ", moduleForAdmin=" + moduleForAdmin + ", moduleBelong=" + moduleBelong + ", moduleIcon=" + moduleIcon + ", remark=" + remark + ", validStatus=" + validStatus + ", gmtCreate=" + gmtCreate + ", gmtModified=" + gmtModified + "]";
	}
}