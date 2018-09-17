package pers.chanus.yuntao.manager.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import pers.chanus.yuntao.manager.model.Param;
import pers.chanus.yuntao.server.mapper.BaseMapper;

public interface ParamMapper extends BaseMapper<Param, Integer> {
	@Select("select max(priority) from sys_param")
	Integer getMaxPriority();
    
    int priority(Map<String, Object> params);
    
    @Select("select param_code, param_data from sys_param where valid_status = '1'")
    List<Param> listValidParam();
}