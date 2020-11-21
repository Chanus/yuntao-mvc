package com.chanus.yuntao.mvc.manager.mapper;

import com.chanus.yuntao.mvc.manager.model.Dict;
import com.chanus.yuntao.mvc.framework.mapper.SuperMapper;

import java.util.List;

/**
 * 系统字典集表
 *
 * @author Chanus
 * @date 2019-06-07 12:55:23
 * @since 0.1.1
 */
public interface DictMapper extends SuperMapper<Dict> {
    List<Dict> listWithDictItems(String validStatus);
}