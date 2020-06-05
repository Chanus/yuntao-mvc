package pers.chanus.yuntao.manager.mapper;

import pers.chanus.yuntao.manager.model.RoleModulePower;

import java.util.List;

public interface RoleModulePowerMapper {
    int deleteByRoleCode(String roleCode);

    int deleteBySubNo(String subNo);

    int insertBatch(List<RoleModulePower> list);
}