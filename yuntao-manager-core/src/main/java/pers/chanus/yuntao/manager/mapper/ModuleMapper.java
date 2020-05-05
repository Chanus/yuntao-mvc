package pers.chanus.yuntao.manager.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import pers.chanus.yuntao.manager.model.Module;
import pers.chanus.yuntao.server.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

public interface ModuleMapper extends BaseMapper<Module, Integer> {
    Integer getMaxModuleId(Integer moduleParentId);

    String getMaxModuleLevel(Integer moduleParentId);

    Module getModuleForPriority(Map<String, Object> params);

    List<Module> listRoleModulePowerForAdmin();

    List<Module> listRoleModulePowerForNotAdmin(@Param("roleId") String roleId, @Param("parentRoleId") String parentRoleId);

    List<Module> listSubModulePower(@Param("subNo") String subNo, @Param("masterRoleId") String masterRoleId);

    List<Module> listMenu(@Param("roleId") String roleId, @Param("subNo") String subNo);

    List<String> listUrl(@Param("roleId") String roleId, @Param("subNo") String subNo);

    @Select("select count(*) from sys_module where module_code = #{moduleCode,jdbcType=VARCHAR}")
    int checkModuleCode(String moduleCode);

    @Update("update sys_module set module_id = #{moduleId,jdbcType=INTEGER}, module_parent_id = #{moduleParentId,jdbcType=INTEGER}, " +
            "module_level = #{moduleLevel,jdbcType=CHAR} where module_id = #{oldModuleId,jdbcType=INTEGER}")
    int update(@Param("oldModuleId") Integer oldModuleId, @Param("moduleId") Integer moduleId, @Param("moduleParentId") Integer moduleParentId, @Param("moduleLevel") String moduleLevel);

    @Update("update sys_module set module_level = module_level - 1 " +
            "where module_parent_id = #{moduleParentId,jdbcType=INTEGER} " +
            "and module_level > #{moduleLevel,jdbcType=CHAR}")
    int bottom(@Param("moduleParentId") Integer moduleParentId, @Param("moduleLevel") String moduleLevel);
}