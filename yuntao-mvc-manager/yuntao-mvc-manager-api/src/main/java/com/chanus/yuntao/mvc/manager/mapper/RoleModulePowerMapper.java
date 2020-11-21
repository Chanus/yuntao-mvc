package com.chanus.yuntao.mvc.manager.mapper;

import com.chanus.yuntao.mvc.manager.model.RoleModulePower;

import java.util.List;

/**
 * 系统角色权限表
 *
 * @author Chanus
 * @date 2018-09-06 22:03:11
 * @since 0.0.1
 */
public interface RoleModulePowerMapper {
    int deleteByRoleCode(String roleCode);

    int deleteBySubNo(String subNo);

    int insertBatch(List<RoleModulePower> list);
}