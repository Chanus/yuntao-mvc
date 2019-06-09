package pers.chanus.yuntao.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.chanus.yuntao.commons.pojo.Message;
import pers.chanus.yuntao.manager.common.CacheData;
import pers.chanus.yuntao.manager.mapper.DictItemMapper;
import pers.chanus.yuntao.manager.model.DictItem;
import pers.chanus.yuntao.manager.service.DictItemService;
import pers.chanus.yuntao.server.service.impl.BaseServiceImpl;
import pers.chanus.yuntao.util.CollectionUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * 数据字典项管理接口实现
 *
 * @author Chanus
 * @date 2019-06-07 14:01:03
 * @since 0.1.1
 */
@Service
public class DictItemServiceImpl extends BaseServiceImpl<DictItemMapper, DictItem, Integer> implements DictItemService {

    @Autowired
    public void setMapper(DictItemMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Message reloadDict() {
        CacheData.SYSTEM_DICT_MAP.clear();
        List<DictItem> dicts = mapper.listValidDict();
        if (!CollectionUtils.isEmpty(dicts)) {
            dicts.forEach(dictItem -> {
                CacheData.SYSTEM_DICT_MAP.computeIfAbsent(dictItem.getDictCode(), k -> new LinkedList<>());

                CacheData.SYSTEM_DICT_MAP.get(dictItem.getDictCode()).add(dictItem);
            });
        }

        return Message.success("系统字典数据重载成功");
    }
}
