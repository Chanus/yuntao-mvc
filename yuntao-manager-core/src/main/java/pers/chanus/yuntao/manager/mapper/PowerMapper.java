package pers.chanus.yuntao.manager.mapper;

import org.apache.ibatis.annotations.Select;
import pers.chanus.yuntao.manager.model.Power;
import pers.chanus.yuntao.server.mapper.SuperMapper;

import java.util.Map;

public interface PowerMapper extends SuperMapper<Power> {
    @Select("select max(priority) from sys_power")
    Integer getMaxPriority();

    int priority(Map<String, Object> params);
}