package pers.chanus.yuntao.manager.model;

import com.alibaba.fastjson.annotation.JSONField;
import pers.chanus.yuntao.util.DateUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * 定时任务触发器表表
 *
 * @author Chanus
 * @date 2020-04-13 23:47:09
 * @since 0.1.7
 */
public class ScheduleTrigger implements Serializable {
    private static final long serialVersionUID = 1L;

    // ID
    private Integer id;
    // 定时任务ID
    private Integer jobId;
    // 触发器名称
    private String triggerName;
    // 触发器组
    private String triggerGroup;
    // Cron表达式
    private String triggerCron;
    // 开始时间
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date triggerStartTime;
    // 结束时间
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date triggerEndTime;
    // 属性配置，json格式
    private String triggerData;
    // 优先级
    private Integer priority;
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

    private String triggerStartTimeStr;

    private String triggerEndTimeStr;

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
     * 获取：定时任务ID
     */
    public Integer getJobId() {
        return jobId;
    }

    /**
     * 设置：定时任务ID
     */
    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    /**
     * 获取：触发器名称
     */
    public String getTriggerName() {
        return triggerName;
    }

    /**
     * 设置：触发器名称
     */
    public void setTriggerName(String triggerName) {
        this.triggerName = triggerName == null ? null : triggerName.trim();
    }

    /**
     * 获取：触发器组
     */
    public String getTriggerGroup() {
        return triggerGroup;
    }

    /**
     * 设置：触发器组
     */
    public void setTriggerGroup(String triggerGroup) {
        this.triggerGroup = triggerGroup == null ? null : triggerGroup.trim();
    }

    /**
     * 获取：Cron表达式
     */
    public String getTriggerCron() {
        return triggerCron;
    }

    /**
     * 设置：Cron表达式
     */
    public void setTriggerCron(String triggerCron) {
        this.triggerCron = triggerCron == null ? null : triggerCron.trim();
    }

    /**
     * 获取：开始时间
     */
    public Date getTriggerStartTime() {
        return triggerStartTime;
    }

    /**
     * 设置：开始时间
     */
    public void setTriggerStartTime(Date triggerStartTime) {
        this.triggerStartTime = triggerStartTime;
        this.triggerStartTimeStr = DateUtils.formatDateTime(triggerStartTime);
    }

    /**
     * 获取：结束时间
     */
    public Date getTriggerEndTime() {
        return triggerEndTime;
    }

    /**
     * 设置：结束时间
     */
    public void setTriggerEndTime(Date triggerEndTime) {
        this.triggerEndTime = triggerEndTime;
        this.triggerEndTimeStr = DateUtils.formatDateTime(triggerEndTime);
    }

    /**
     * 获取：属性配置，json格式
     */
    public String getTriggerData() {
        return triggerData;
    }

    /**
     * 设置：属性配置，json格式
     */
    public void setTriggerData(String triggerData) {
        this.triggerData = triggerData == null ? null : triggerData.trim();
    }

    /**
     * 获取：优先级
     */
    public Integer getPriority() {
        return priority;
    }

    /**
     * 设置：优先级
     */
    public void setPriority(Integer priority) {
        this.priority = priority;
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
                "]";
    }
}
