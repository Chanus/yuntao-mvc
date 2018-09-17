package pers.chanus.yuntao.manager.model;

import java.io.Serializable;

public class ChinaArea implements Serializable {
	private static final long serialVersionUID = -2057199923039898023L;

	private Integer areaId;

    private Integer areaParentId;

    private String areaName;

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
		return "ChinaArea [areaId=" + areaId + ", areaParentId=" + areaParentId + ", areaName=" + areaName + ", areaLevel=" + areaLevel + "]";
	}
}