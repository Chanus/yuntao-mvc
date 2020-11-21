package com.chanus.yuntao.mvc.manager.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

/**
 * 中国行政区划表
 *
 * @author Chanus
 * @date 2018-09-15 16:00:17
 * @since 0.0.1
 */
public class ChinaArea implements Serializable {
    private static final long serialVersionUID = 6496548617025494119L;

    /**
     * 行政区划代码
     */
    @TableId(value = "area_id", type = IdType.INPUT)
    private Integer areaId;
    /**
     * 上级行政区划代码
     */
    private Integer areaParentId;
    /**
     * 行政区名称
     */
    private String areaName;
    /**
     * 行政区级别：1-省、直辖市、自治区，2-市，3-区、县
     */
    private String areaLevel;

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public Integer getAreaParentId() {
        return areaParentId;
    }

    public void setAreaParentId(Integer areaParentId) {
        this.areaParentId = areaParentId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName == null ? null : areaName.trim();
    }

    public String getAreaLevel() {
        return areaLevel;
    }

    public void setAreaLevel(String areaLevel) {
        this.areaLevel = areaLevel == null ? null : areaLevel.trim();
    }

    @Override
    public String toString() {
        return "ChinaArea [" +
                "areaId=" + areaId +
                ", areaParentId=" + areaParentId +
                ", areaName=" + areaName +
                ", areaLevel=" + areaLevel +
                "]" ;
    }
}