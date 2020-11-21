package com.chanus.yuntao.mvc.manager.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 系统字典项表
 *
 * @author Chanus
 * @date 2019-06-07 14:01:03
 * @since 0.1.1
 */
public class DictItem implements Serializable {
    private static final long serialVersionUID = -6297883859641376369L;

    /**
     * ID
     */
    private Integer id;
    /**
     * 字典集代码
     */
    private String dictCode;
    /**
     * 字典项代码
     */
    private String itemCode;
    /**
     * 字典项名称
     */
    private String itemName;
    /**
     * 字典项值
     */
    private String itemData;
    /**
     * 备注
     */
    private String remark;
    /**
     * 状态：0-停用，1-启用
     */
    private String validStatus;
    /**
     * 优先级
     */
    private Integer priority;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDictCode() {
        return dictCode;
    }

    public void setDictCode(String dictCode) {
        this.dictCode = dictCode == null ? null : dictCode.trim();
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode == null ? null : itemCode.trim();
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName == null ? null : itemName.trim();
    }

    public String getItemData() {
        return itemData;
    }

    public void setItemData(String itemData) {
        this.itemData = itemData == null ? null : itemData.trim();
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

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
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
        return "DictItem [" +
                "id=" + id +
                ", dictCode=" + dictCode +
                ", itemCode=" + itemCode +
                ", itemName=" + itemName +
                ", itemData=" + itemData +
                ", remark=" + remark +
                ", validStatus=" + validStatus +
                ", priority=" + priority +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                "]" ;
    }
}
