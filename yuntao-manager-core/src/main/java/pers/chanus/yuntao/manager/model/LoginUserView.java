package pers.chanus.yuntao.manager.model;

import java.io.Serializable;

public class LoginUserView implements Serializable {
    private static final long serialVersionUID = -798620052381070095L;

    private String loginNo;

    private String loginName;

    private String password;

    private String roleId;

    private String masterNo;

    private String masterRoleId;

    private String validStatus;

    private String userType;

    private String headImage;

    public String getLoginNo() {
        return loginNo;
    }

    public void setLoginNo(String loginNo) {
        this.loginNo = loginNo == null ? null : loginNo.trim();
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public String getMasterNo() {
        return masterNo;
    }

    public void setMasterNo(String masterNo) {
        this.masterNo = masterNo == null ? null : masterNo.trim();
    }

    public String getMasterRoleId() {
        return masterRoleId;
    }

    public void setMasterRoleId(String masterRoleId) {
        this.masterRoleId = masterRoleId == null ? null : masterRoleId.trim();
    }

    public String getValidStatus() {
        return validStatus;
    }

    public void setValidStatus(String validStatus) {
        this.validStatus = validStatus == null ? null : validStatus.trim();
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType == null ? null : userType.trim();
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage == null ? null : headImage.trim();
    }

    @Override
    public String toString() {
        return "LoginUserView [" +
                "loginNo=" + loginNo +
                ", loginName=" + loginName +
                ", password=" + password +
                ", roleId=" + roleId +
                ", masterNo=" + masterNo +
                ", masterRoleId=" + masterRoleId +
                ", validStatus=" + validStatus +
                ", userType=" + userType +
                ", headImage=" + headImage +
                "]";
    }
}