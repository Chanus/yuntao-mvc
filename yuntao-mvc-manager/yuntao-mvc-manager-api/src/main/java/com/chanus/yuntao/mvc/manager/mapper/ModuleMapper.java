package com.chanus.yuntao.mvc.manager.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import com.chanus.yuntao.mvc.manager.model.Module;
import com.chanus.yuntao.mvc.framework.mapper.SuperMapper;

import java.util.List;
import java.util.Map;

/**
 * 系统模块表
 *
 * @author Chanus
 * @date 2018-09-06 23:00:02
 * @since 0.0.1
 */
public interface ModuleMapper extends SuperMapper<Module> {
    Integer getMaxModuleId(Integer moduleParentId);

    String getMaxModuleLevel(Integer moduleParentId);

    Module getModuleForPriority(Map<String, Object> params);

    List<Module> listRoleModulePowerForAdmin();

    List<Module> listRoleModulePowerForNotAdmin(@Param("roleCode") String roleCode, @Param("parentRoleCode") String parentRoleCode);

    List<Module> listSubModulePower(@Param("subNo") String subNo, @Param("masterRoleCode") String masterRoleCode);

    List<Module> listMenu(@Param("roleCode") String roleCode, @Param("subNo") String subNo);

    List<String> listUrl(@Param("roleCode") String roleCode, @Param("subNo") String subNo);

    @Select("select 1 from sys_module where module_code = #{moduleCode,jdbcType=VARCHAR} limit 1")
    Integer isExist(String moduleCode);

    @Update("update sys_module set module_id = #{moduleId,jdbcType=INTEGER}, module_parent_id = #{moduleParentId,jdbcType=INTEGER}, " +
            "module_level = #{moduleLevel,jdbcType=CHAR} where module_id = #{oldModuleId,jdbcType=INTEGER}")
    int update(@Param("oldModuleId") Integer oldModuleId, @Param("moduleId") Integer moduleId, @Param("moduleParentId") Integer moduleParentId, @Param("moduleLevel") String moduleLevel);

    @Update("update sys_module set module_level = module_level - 1 " +
            "where module_parent_id = #{moduleParentId,jdbcType=INTEGER} " +
            "and module_level > #{moduleLevel,jdbcType=CHAR}")
    int bottom(@Param("moduleParentId") Integer moduleParentId, @Param("moduleLevel") String moduleLevel);
}