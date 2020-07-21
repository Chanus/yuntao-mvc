package pers.chanus.yuntao.manager.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class Module implements Serializable {
    private static final long serialVersionUID = -4043140639207121275L;

    @TableId(value = "module_id", type = IdType.INPUT)
    private Integer moduleId;

    private Integer moduleParentId;

    private String moduleCode;

    private String moduleName;

    private String moduleUrl;

    private String moduleLevel;

    private String moduleIsMenu;

    private String moduleForPermission;

    private String moduleForAdmin;

    private String moduleBelong;

    private String moduleIcon;

    private String moduleTarget;

    private String remark;

    private String validStatus;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;

    @TableField(exist = false)
    private List<Module> children;// 一级模块的子模块

    @TableField(exist = false)
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

    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode == null ? null : moduleCode.trim();
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

    public String getModuleForPermission() {
        return moduleForPermission;
    }

    public void setModuleForPermission(String moduleForPermission) {
        this.moduleForPermission = moduleForPermission == null ? null : moduleForPermission.trim();
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

    public String getModuleTarget() {
        return moduleTarget;
    }

    public void setModuleTarget(String moduleTarget) {
        this.moduleTarget = moduleTarget == null ? null : moduleTarget.trim();
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
        return "Module [" +
                "moduleId=" + moduleId +
                ", moduleParentId=" + moduleParentId +
                ", moduleCode=" + moduleCode +
                ", moduleName=" + moduleName +
                ", moduleUrl=" + moduleUrl +
                ", moduleLevel=" + moduleLevel +
                ", moduleIsMenu=" + moduleIsMenu +
                ", moduleForPermission=" + moduleForPermission +
                ", moduleForAdmin=" + moduleForAdmin +
                ", moduleBelong=" + moduleBelong +
                ", moduleIcon=" + moduleIcon +
                ", moduleTarget=" + moduleTarget +
                ", remark=" + remark +
                ", validStatus=" + validStatus +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                "]";
    }
}