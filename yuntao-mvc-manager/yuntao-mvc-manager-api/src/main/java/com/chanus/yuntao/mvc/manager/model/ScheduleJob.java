package com.chanus.yuntao.mvc.manager.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 定时任务定义表
 *
 * @author Chanus
 * @date 2020-04-13 23:46:30
 * @since 0.1.7
 */
public class ScheduleJob implements Serializable {
    private static final long serialVersionUID = 6039530090620369854L;

    /**
     * ID
     */
    private Integer id;
    /**
     * 任务名称
     */
    private String jobName;
    /**
     * 任务组
     */
    private String jobGroup;
    /**
     * 执行类
     */
    private String jobClassName;
    /**
     * 属性配置，json格式
     */
    private String jobData;
    /**
     * 备注
     */
    private String remark;
    /**
     * 状态：0-停止，1-启动，2-暂停
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
     * 定时任务触发器
     */
    @TableField(exist = false)
    private List<ScheduleTrigger> scheduleTriggers;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName == null ? null : jobName.trim();
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup == null ? null : jobGroup.trim();
    }

    public String getJobClassName() {
        return jobClassName;
    }

    public void setJobClassName(String jobClassName) {
        this.jobClassName = jobClassName == null ? null : jobClassName.trim();
    }

    public String getJobData() {
        return jobData;
    }

    public void setJobData(String jobData) {
        this.jobData = jobData == null ? null : jobData.trim();
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

    public List<ScheduleTrigger> getScheduleTriggers() {
        return scheduleTriggers;
    }

    public void setScheduleTriggers(List<ScheduleTrigger> scheduleTriggers) {
        this.scheduleTriggers = scheduleTriggers;
    }

    @Override
    public String toString() {
        return "ScheduleJob [" +
                "id=" + id +
                ", jobName=" + jobName +
                ", jobGroup=" + jobGroup +
                ", jobClassName=" + jobClassName +
                ", jobData=" + jobData +
                ", remark=" + remark +
                ", validStatus=" + validStatus +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                "]" ;
    }
}
