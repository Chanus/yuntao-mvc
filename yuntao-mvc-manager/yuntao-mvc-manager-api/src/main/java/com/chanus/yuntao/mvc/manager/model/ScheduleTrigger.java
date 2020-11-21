package com.chanus.yuntao.mvc.manager.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.chanus.yuntao.utils.core.LocalDateTimeUtils;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 定时任务触发器表表
 *
 * @author Chanus
 * @date 2020-04-13 23:47:09
 * @since 0.1.7
 */
public class ScheduleTrigger implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Integer id;
    /**
     * 定时任务ID
     */
    private Integer jobId;
    /**
     * 触发器名称
     */
    private String triggerName;
    /**
     * 触发器组
     */
    private String triggerGroup;
    /**
     * Cron表达式
     */
    private String triggerCron;
    /**
     * 开始时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private LocalDateTime triggerStartTime;
    /**
     * 结束时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private LocalDateTime triggerEndTime;
    /**
     * 属性配置，json格式
     */
    private String triggerData;
    /**
     * 优先级
     */
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private Integer priority;
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
     * 开始时间字符串
     */
    @TableField(exist = false)
    private String triggerStartTimeStr;
    /**
     * 结束时间字符串
     */
    @TableField(exist = false)
    private String triggerEndTimeStr;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public String getTriggerName() {
        return triggerName;
    }

    public void setTriggerName(String triggerName) {
        this.triggerName = triggerName == null ? null : triggerName.trim();
    }

    public String getTriggerGroup() {
        return triggerGroup;
    }

    public void setTriggerGroup(String triggerGroup) {
        this.triggerGroup = triggerGroup == null ? null : triggerGroup.trim();
    }

    public String getTriggerCron() {
        return triggerCron;
    }

    public void setTriggerCron(String triggerCron) {
        this.triggerCron = triggerCron == null ? null : triggerCron.trim();
    }

    public LocalDateTime getTriggerStartTime() {
        return triggerStartTime;
    }

    public void setTriggerStartTime(LocalDateTime triggerStartTime) {
        this.triggerStartTime = triggerStartTime;
        this.triggerStartTimeStr = LocalDateTimeUtils.formatDateTime(triggerStartTime);
    }

    public LocalDateTime getTriggerEndTime() {
        return triggerEndTime;
    }

    public void setTriggerEndTime(LocalDateTime triggerEndTime) {
        this.triggerEndTime = triggerEndTime;
        this.triggerEndTimeStr = LocalDateTimeUtils.formatDateTime(triggerEndTime);
    }

    public String getTriggerData() {
        return triggerData;
    }

    public void setTriggerData(String triggerData) {
        this.triggerData = triggerData == null ? null : triggerData.trim();
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
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

    public String getTriggerStartTimeStr() {
        return triggerStartTimeStr;
    }

    public void setTriggerStartTimeStr(String triggerStartTimeStr) {
        this.triggerStartTimeStr = triggerStartTimeStr;
    }

    public String getTriggerEndTimeStr() {
        return triggerEndTimeStr;
    }

    public void setTriggerEndTimeStr(String triggerEndTimeStr) {
        this.triggerEndTimeStr = triggerEndTimeStr;
    }

    @Override
    public String toString() {
        return "ScheduleTrigger [" +
                "id=" + id +
                ", jobId=" + jobId +
                ", triggerName=" + triggerName +
                ", triggerGroup=" + triggerGroup +
                ", triggerCron=" + triggerCron +
                ", triggerStartTime=" + triggerStartTime +
                ", triggerEndTime=" + triggerEndTime +
                ", triggerData=" + triggerData +
                ", priority=" + priority +
                ", remark=" + remark +
                ", validStatus=" + validStatus +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                "]" ;
    }
}
