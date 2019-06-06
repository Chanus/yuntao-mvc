package pers.chanus.yuntao.manager.mapper;

import org.apache.ibatis.annotations.Select;

import pers.chanus.yuntao.manager.model.Role;
import pers.chanus.yuntao.server.mapper.BaseMapper;

public interface RoleMapper extends BaseMapper<Role, Integer> {
    Role getByRoleId(String roleId);

    @Select("select login_flag, valid_status from sys_role where role_id = #{roleId,jdbcType=VARCHAR}")
    Role getLoginStatus(String roleId);

    @Select("select max(role_id) from sys_role where parent_role_id = #{parentRoleId,jdbcType=VARCHAR}")
    String getMaxRoleId(String parentRoleId);

    @Select("select max(priority) from sys_role where parent_role_id = #{parentRoleId,jdbcType=VARCHAR}")
    Integer getMaxPriority(String parentRoleId);
}