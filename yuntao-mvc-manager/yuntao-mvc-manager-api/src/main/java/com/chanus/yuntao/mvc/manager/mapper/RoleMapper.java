package com.chanus.yuntao.mvc.manager.mapper;

import org.apache.ibatis.annotations.Select;

import com.chanus.yuntao.mvc.manager.model.Role;
import com.chanus.yuntao.mvc.framework.mapper.SuperMapper;

/**
 * 系统角色表
 *
 * @author Chanus
 * @date 2018-09-08 21:54:30
 * @since 0.0.1
 */
public interface RoleMapper extends SuperMapper<Role> {
    Role getParentRole(String roleCode);

    @Select("select login_flag, valid_status from sys_role where role_code = #{roleCode,jdbcType=VARCHAR}")
    Role getLoginStatus(String roleCode);

    @Select("select max(role_id) from sys_role where parent_role_id = #{parentRoleId,jdbcType=VARCHAR}")
    String getMaxRoleId(String parentRoleId);

    @Select("select max(priority) from sys_role where parent_role_id = #{parentRoleId,jdbcType=VARCHAR}")
    Integer getMaxPriority(String parentRoleId);
}