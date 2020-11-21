package com.chanus.yuntao.mvc.manager.mapper;

import org.apache.ibatis.annotations.Select;
import com.chanus.yuntao.mvc.manager.model.Power;
import com.chanus.yuntao.mvc.framework.mapper.SuperMapper;

import java.util.Map;

/**
 * 系统权限项表
 *
 * @author Chanus
 * @date 2018-09-06 19:32:43
 * @since 0.0.1
 */
public interface PowerMapper extends SuperMapper<Power> {
    @Select("select max(priority) from sys_power")
    Integer getMaxPriority();

    int priority(Map<String, Object> params);
}