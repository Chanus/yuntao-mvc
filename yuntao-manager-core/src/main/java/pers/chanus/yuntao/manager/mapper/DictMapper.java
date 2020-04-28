package pers.chanus.yuntao.manager.mapper;

import pers.chanus.yuntao.manager.model.Dict;
import pers.chanus.yuntao.server.mapper.BaseMapper;

import java.util.List;

/**
 * 系统字典集表
 *
 * @author Chanus
 * @date 2019-06-07 12:55:23
 * @since 0.1.1
 */
public interface DictMapper extends BaseMapper<Dict, Integer> {
    List<Dict> listWithDictItems(String validStatus);
}