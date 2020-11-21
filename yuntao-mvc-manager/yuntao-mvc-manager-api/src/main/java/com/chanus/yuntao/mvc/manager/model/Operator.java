package com.chanus.yuntao.mvc.manager.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 操作员表
 *
 * @author Chanus
 * @date 2018-09-06 22:45:43
 * @since 0.0.1
 */
public class Operator implements Serializable {
    private static final long serialVersionUID = -8513495354479579451L;

    /**
     * 主键
     */
    private Integer id;
    /**
     * 操作员账号
     */
    private String operatorNo;
    /**
     * 操作员名称
     */
    private String operatorName;
    /**
     * 操作员密码
     */
    private String operatorPassword;
    /**
     * 操作员角色
     */
    private String operatorRoleCode;
    /**
     * 主账号
     */
    private String masterNo;
    /**
     * 主账号角色
     */
    private String masterRoleCode;
    /**
     * 邮箱账号
     */
    private String email;
    /**
     * 电话号码
     */
    private String tel;
    /**
     * 头像图片路径
     */
    private String headImage;
    /**
     * 备注
     */
    private String remark;
    /**
     * 状态：0-停用，1-启用
     */
    private String validStatus;
    /**
     * 所有上级角色代码
     */
    private String superior;
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
     * 角色名称
     */
    @TableField(exist = false)
    private String roleName;
    /**
     * 邮箱 AES 加密密钥
     */
    @TableField(exist = false)
    private String aesEmailKey;
    /**
     * 手机号 AES 加密密钥
     */
    @TableField(exist = false)
    private String aesPhoneKey;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOperatorNo() {
        return operatorNo;
    }

    public void setOperatorNo(String operatorNo) {
        this.operatorNo = operatorNo == null ? null : operatorNo.trim();
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName == null ? null : operatorName.trim();
    }

    public String getOperatorPassword() {
        return operatorPassword;
    }

    public void setOperatorPassword(String operatorPassword) {
        this.operatorPassword = operatorPassword == null ? null : operatorPassword.trim();
    }

    public String getOperatorRoleCode() {
        return operatorRoleCode;
    }

    public void setOperatorRoleCode(String operatorRoleCode) {
        this.operatorRoleCode = operatorRoleCode == null ? null : operatorRoleCode.trim();
    }

    public String getMasterNo() {
        return masterNo;
    }

    public void setMasterNo(String masterNo) {
        this.masterNo = masterNo == null ? null : masterNo.trim();
    }

    public String getMasterRoleCode() {
        return masterRoleCode;
    }

    public void setMasterRoleCode(String masterRoleCode) {
        this.masterRoleCode = masterRoleCode == null ? null : masterRoleCode.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage == null ? null : headImage.trim();
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

    public String getSuperior() {
        return superior;
    }

    public void setSuperior(String superior) {
        this.superior = superior == null ? null : superior.trim();
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

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getAesEmailKey() {
        return aesEmailKey;
    }

    public void setAesEmailKey(String aesEmailKey) {
        this.aesEmailKey = aesEmailKey;
    }

    public String getAesPhoneKey() {
        return aesPhoneKey;
    }

    public void setAesPhoneKey(String aesPhoneKey) {
        this.aesPhoneKey = aesPhoneKey;
    }

    @Override
    public String toString() {
        return "Operator [" +
                "id=" + id +
                ", operatorNo=" + operatorNo +
                ", operatorName=" + operatorName +
                ", operatorPassword=" + operatorPassword +
                ", operatorRoleCode=" + operatorRoleCode +
                ", masterNo=" + masterNo +
                ", masterRoleCode=" + masterRoleCode +
                ", email=" + email +
                ", tel=" + tel +
                ", headImage=" + headImage +
                ", remark=" + remark +
                ", validStatus=" + validStatus +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                "]" ;
    }
}