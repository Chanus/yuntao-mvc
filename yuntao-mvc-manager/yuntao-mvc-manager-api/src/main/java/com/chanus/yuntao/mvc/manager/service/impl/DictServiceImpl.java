package com.chanus.yuntao.mvc.manager.service.impl;

import com.chanus.yuntao.mvc.manager.common.CacheData;
import com.chanus.yuntao.mvc.manager.mapper.DictMapper;
import com.chanus.yuntao.mvc.manager.model.Dict;
import com.chanus.yuntao.mvc.manager.model.DictItem;
import com.chanus.yuntao.mvc.framework.service.impl.BaseServiceImpl;
import com.chanus.yuntao.utils.core.CollectionUtils;
import com.chanus.yuntao.utils.core.lang.Message;
import org.springframework.stereotype.Service;
import com.chanus.yuntao.mvc.common.constant.Constants;
import com.chanus.yuntao.mvc.manager.service.DictService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
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
public class DictServiceImpl extends BaseServiceImpl<DictMapper, Dict> implements DictService {
    @Override
    public Message delete(Serializable pk) {
        getBaseMapper().deleteBatchIds(Collections.singletonList(pk));
        return Message.deleteSuccess();
    }

    @Override
    public Message reloadDict() {
        // 清空已缓存的字典数据
        CacheData.SYSTEM_DICT_MAP.clear();
        CacheData.SYSTEM_DICT_ITEM_MAP.clear();

        // 重载字典数据
        List<Dict> dicts = getBaseMapper().listWithDictItems(null);
        if (CollectionUtils.isNotEmpty(dicts)) {
            dicts.stream().filter(dict -> dict.getValidStatus().equals(Constants.STATUS_YES)).forEach(dict -> CacheData.SYSTEM_DICT_MAP.put(dict.getDictCode(), dict.getDictItems().stream().filter(dictItem -> dictItem.getValidStatus().equals(Constants.STATUS_YES)).collect(Collectors.toList())));

            List<DictItem> dictItems = new ArrayList<>();
            dicts.forEach(dict -> dictItems.addAll(dict.getDictItems()));
            dictItems.forEach(dictItem -> CacheData.SYSTEM_DICT_ITEM_MAP.put(dictItem.getDictCode() + "|" + dictItem.getItemCode(), dictItem.getItemName()));
        }

        return Message.success("系统字典数据重载成功");
    }
}
