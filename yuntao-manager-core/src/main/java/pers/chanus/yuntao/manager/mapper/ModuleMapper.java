package pers.chanus.yuntao.manager.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
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

    @Select("select module_name from sys_module where module_id = #{moduleId,jdbcType=INTEGER}")
    String getModuleName(Integer moduleId);
}