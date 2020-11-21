package com.chanus.yuntao.mvc.manager.mapper;

import com.chanus.yuntao.mvc.manager.model.DataBaseColumn;

import java.util.List;
import java.util.Map;

/**
 * 数据库表字段
 *
 * @author Chanus
 * @date 2018-10-30 16:55:30
 * @since 0.0.3
 */
public interface DataBaseColumnMapper {
    int count(Map<String, Object> params);

    List<DataBaseColumn> list(Map<String, Object> params);
}
