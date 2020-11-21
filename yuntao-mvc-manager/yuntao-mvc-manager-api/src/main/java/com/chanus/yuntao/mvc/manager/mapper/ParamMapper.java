package com.chanus.yuntao.mvc.manager.mapper;

import com.chanus.yuntao.mvc.manager.model.Param;
import com.chanus.yuntao.mvc.framework.mapper.SuperMapper;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

/**
 * 系统基础参数表
 *
 * @author Chanus
 * @date 2018-09-06 17:50:17
 * @since 0.0.1
 */
public interface ParamMapper extends SuperMapper<Param> {
    @Select("select max(priority) from sys_param")
    Integer getMaxPriority();

    int priority(Map<String, Object> params);
}