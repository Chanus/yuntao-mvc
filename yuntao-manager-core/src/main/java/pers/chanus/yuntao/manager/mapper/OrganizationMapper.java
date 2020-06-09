package pers.chanus.yuntao.manager.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import pers.chanus.yuntao.manager.model.Organization;
import pers.chanus.yuntao.server.mapper.SuperMapper;

/**
 * 组织机构表
 *
 * @author Chanus
 * @date 2019-05-06 21:11:46
 * @since 0.0.8
 */
public interface OrganizationMapper extends SuperMapper<Organization> {
    @Select("select max(priority) from sys_organization where org_parent_id = #{orgParentId,jdbcType=INTEGER}")
    Integer getMaxPriority(Integer orgParentId);

    @Select("select count(*) from sys_organization where org_parent_id = #{orgParentId,jdbcType=INTEGER}")
    int hasChildren(Integer orgParentId);

    @Update("update sys_organization set priority = #{priority,jdbcType=INTEGER} where org_id = #{orgId,jdbcType=INTEGER}")
    int priority(@Param("orgId") Integer orgId, @Param("priority") Integer priority);
}