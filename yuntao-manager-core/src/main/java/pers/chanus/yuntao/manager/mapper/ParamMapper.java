package pers.chanus.yuntao.manager.mapper;

import org.apache.ibatis.annotations.Select;
import pers.chanus.yuntao.manager.model.Param;
import pers.chanus.yuntao.server.mapper.SuperMapper;

import java.util.Map;

public interface ParamMapper extends SuperMapper<Param> {
    @Select("select max(priority) from sys_param")
    Integer getMaxPriority();

    int priority(Map<String, Object> params);
}