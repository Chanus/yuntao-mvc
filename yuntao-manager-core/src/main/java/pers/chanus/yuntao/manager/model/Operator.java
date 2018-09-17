package pers.chanus.yuntao.manager.model;

import java.io.Serializable;
import java.util.Date;

public class Operator implements Serializable {
	private static final long serialVersionUID = -8513495354479579451L;

	private Integer id;

    private String operatorNo;

    private String operatorName;

    private String operatorPassword;

    private String operatorRoleId;

    private String masterNo;

    private String masterRoleId;

    private String email;

    private String tel;

    private String headImage;

    private String remark;

    private String validStatus;

    private Date gmtCreate;

    private Date gmtModified;

    private String roleName;

    private String aesEmailKey;

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

    public String getOperatorRoleId() {
        return operatorRoleId;
    }

    public void setOperatorRoleId(String operatorRoleId) {
        this.operatorRoleId = operatorRoleId == null ? null : operatorRoleId.trim();
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
		return "Operator [id=" + id + ", operatorNo=" + operatorNo + ", operatorName=" + operatorName + ", operatorPassword=" + operatorPassword + ", operatorRoleId=" + operatorRoleId + ", masterNo=" + masterNo + ", masterRoleId=" + masterRoleId + ", email=" + email + ", tel=" + tel + ", headImage=" + headImage + ", remark=" + remark + ", validStatus=" + validStatus + ", gmtCreate=" + gmtCreate + ", gmtModified=" + gmtModified + "]";
	}
}