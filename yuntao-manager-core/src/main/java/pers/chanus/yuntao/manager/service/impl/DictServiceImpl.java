package pers.chanus.yuntao.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.chanus.yuntao.commons.constant.ConfigConsts;
import pers.chanus.yuntao.commons.pojo.Message;
import pers.chanus.yuntao.manager.common.CacheData;
import pers.chanus.yuntao.manager.mapper.DictMapper;
import pers.chanus.yuntao.manager.model.Dict;
import pers.chanus.yuntao.manager.model.DictItem;
import pers.chanus.yuntao.manager.service.DictService;
import pers.chanus.yuntao.server.service.impl.BaseServiceImpl;
import pers.chanus.yuntao.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 数据字典集管理接口实现
 *
 * @author Chanus
 * @date 2019-06-07 12:55:23
 * @since 0.1.1
 */
@Service
public class DictServiceImpl extends BaseServiceImpl<DictMapper, Dict, Integer> implements DictService {

    @Autowired
    public void setMapper(DictMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Message reloadDict() {
        // 清空已缓存的字典数据
        CacheData.SYSTEM_DICT_MAP.clear();
        CacheData.SYSTEM_DICT_ITEM_MAP.clear();

        // 重载字典数据
        List<Dict> dicts = mapper.listWithDictItems(null);
        if (!CollectionUtils.isEmpty(dicts)) {
            dicts.stream().filter(dict -> dict.getValidStatus().equals(ConfigConsts.STATUS_YES)).forEach(dict -> CacheData.SYSTEM_DICT_MAP.put(dict.getDictCode(), dict.getDictItems().stream().filter(dictItem -> dictItem.getValidStatus().equals(ConfigConsts.STATUS_YES)).collect(Collectors.toList())));

            List<DictItem> dictItems = new ArrayList<>();
            dicts.forEach(dict -> dictItems.addAll(dict.getDictItems()));
            dictItems.forEach(dictItem -> CacheData.SYSTEM_DICT_ITEM_MAP.put(dictItem.getDictCode() + "|" + dictItem.getItemCode(), dictItem.getItemName()));
        }

        return Message.success("系统字典数据重载成功");
    }
}
