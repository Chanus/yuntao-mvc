package pers.chanus.yuntao.manager.mapper;

import org.apache.ibatis.annotations.Select;
import pers.chanus.yuntao.manager.model.Power;
import pers.chanus.yuntao.server.mapper.BaseMapper;

import java.util.Map;

public interface PowerMapper extends BaseMapper<Power, Integer> {
    @Select("select max(priority) from sys_power")
    Integer getMaxPriority();

    int priority(Map<String, Object> params);
}