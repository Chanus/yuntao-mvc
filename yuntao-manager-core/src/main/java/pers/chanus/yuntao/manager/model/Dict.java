package pers.chanus.yuntao.manager.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 系统字典集表
 *
 * @author Chanus
 * @date 2019-06-07 12:55:23
 * @since 0.1.1
 */
public class Dict implements Serializable {
    private static final long serialVersionUID = 1L;

    // ID
    private Integer id;
    // 字典集代码
    private String dictCode;
    // 字典集名称
    private String dictName;
    // 备注
    private String remark;
    // 状态：0-停用，1-启用
    private String validStatus;
    // 创建时间
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date gmtCreate;
    // 更新时间
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date gmtModified;

    private List<DictItem> dictItems;

    /**
     * 获取：ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置：ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取：字典集代码
     */
    public String getDictCode() {
        return dictCode;
    }

    /**
     * 设置：字典集代码
     */
    public void setDictCode(String dictCode) {
        this.dictCode = dictCode == null ? null : dictCode.trim();
    }

    /**
     * 获取：字典集名称
     */
    public String getDictName() {
        return dictName;
    }

    /**
     * 设置：字典集名称
     */
    public void setDictName(String dictName) {
        this.dictName = dictName == null ? null : dictName.trim();
    }

    /**
     * 获取：备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置：备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 获取：状态：0-停用，1-启用
     */
    public String getValidStatus() {
        return validStatus;
    }

    /**
     * 设置：状态：0-停用，1-启用
     */
    public void setValidStatus(String validStatus) {
        this.validStatus = validStatus == null ? null : validStatus.trim();
    }

    /**
     * 获取：创建时间
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * 设置：创建时间
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * 获取：更新时间
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * 设置：更新时间
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public List<DictItem> getDictItems() {
        return dictItems;
    }

    public void setDictItems(List<DictItem> dictItems) {
        this.dictItems = dictItems;
    }

    @Override
    public String toString() {
        return "Dict [" +
                "id=" + id +
                ", dictCode=" + dictCode +
                ", dictName=" + dictName +
                ", remark=" + remark +
                ", validStatus=" + validStatus +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                "]";
    }
}
