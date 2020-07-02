package pers.chanus.yuntao.manager.mapper;

import org.apache.ibatis.annotations.Select;

import pers.chanus.yuntao.manager.model.Role;
import pers.chanus.yuntao.springmvc.mapper.SuperMapper;

public interface RoleMapper extends SuperMapper<Role> {
    Role getParentRole(String roleCode);

    @Select("select login_flag, valid_status from sys_role where role_code = #{roleCode,jdbcType=VARCHAR}")
    Role getLoginStatus(String roleCode);

    @Select("select max(role_id) from sys_role where parent_role_id = #{parentRoleId,jdbcType=VARCHAR}")
    String getMaxRoleId(String parentRoleId);

    @Select("select max(priority) from sys_role where parent_role_id = #{parentRoleId,jdbcType=VARCHAR}")
    Integer getMaxPriority(String parentRoleId);
}