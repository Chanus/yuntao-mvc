package com.chanus.yuntao.mvc.manager.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 系统模块表
 *
 * @author Chanus
 * @date 2018-09-06 23:00:02
 * @since 0.0.1
 */
public class Module implements Serializable {
    private static final long serialVersionUID = -4043140639207121275L;

    /**
     * 模块ID
     */
    @TableId(value = "module_id", type = IdType.INPUT)
    private Integer moduleId;
    /**
     * 上级模块ID，一级模块默认为0
     */
    private Integer moduleParentId;
    /**
     * 模块代码
     */
    private String moduleCode;
    /**
     * 模块名称
     */
    private String moduleName;
    /**
     * 模块URL
     */
    private String moduleUrl;
    /**
     * 模块层级，一级模块从10开始，二级模块与上级模块层级拼接，从01开始，如1001
     */
    private String moduleLevel;
    /**
     * 是否是菜单，0-不是菜单，1-是菜单，默认1
     */
    private String moduleIsMenu;
    /**
     * 是否需要设置权限，0-不需要，1-需要，默认1
     */
    private String moduleForPermission;
    /**
     * 是否是超级管理员私有菜单，0-不是，1-是，默认0
     */
    private String moduleForAdmin;
    /**
     * 菜单分组，0-共有，1-操作员，2-非操作员，默认0
     */
    private String moduleBelong;
    /**
     * 模块图标
     */
    private String moduleIcon;
    /**
     * 模块页面打开位置：0-页签打开，1-新页面打开
     */
    private String moduleTarget;
    /**
     * 备注
     */
    private String remark;
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
    /**
     * 一级模块的子模块
     */
    @TableField(exist = false)
    private List<Module> children;
    /**
     * 模块所具有的权限项
     */
    @TableField(exist = false)
    private List<ModulePower> modulePowers;

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
                "]" ;
    }
}