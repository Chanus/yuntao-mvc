package pers.chanus.yuntao.manager.mapper;

import java.util.List;

import pers.chanus.yuntao.manager.model.RoleModulePower;

public interface RoleModulePowerMapper {
	int deleteByRoleId(String roleId);
    
    int deleteBySubNo(String subNo);
    
    int insertBatch(List<RoleModulePower> list);
}