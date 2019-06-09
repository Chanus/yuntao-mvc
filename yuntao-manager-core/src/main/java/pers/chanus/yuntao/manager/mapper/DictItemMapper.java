package pers.chanus.yuntao.manager.mapper;

import org.apache.ibatis.annotations.Select;
import pers.chanus.yuntao.manager.model.DictItem;
import pers.chanus.yuntao.server.mapper.BaseMapper;

import java.util.List;

/**
 * 系统字典项表
 *
 * @author Chanus
 * @date 2019-06-07 14:01:03
 * @since 0.1.1
 */
public interface DictItemMapper extends BaseMapper<DictItem, Integer> {
    @Select("select sys_dict_item.dict_code, item_code, item_name, item_data from sys_dict " +
            "inner join sys_dict_item on sys_dict.dict_code = sys_dict_item.dict_code " +
            "where sys_dict.valid_status = '1' and sys_dict_item.valid_status = '1' " +
            "order by priority asc")
    List<DictItem> listValidDict();
}