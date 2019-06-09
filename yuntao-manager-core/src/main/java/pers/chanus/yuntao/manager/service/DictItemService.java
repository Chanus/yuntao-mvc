package pers.chanus.yuntao.manager.service;

import pers.chanus.yuntao.commons.pojo.Message;
import pers.chanus.yuntao.manager.model.DictItem;
import pers.chanus.yuntao.server.service.BaseService;

/**
 * 数据字典项管理接口
 *
 * @author Chanus
 * @date 2019-06-07 14:01:03
 * @since 0.1.1
 */
public interface DictItemService extends BaseService<DictItem, Integer> {
    /**
     * 重载有效的字典数据
     *
     * @return
     * @since 0.1.1
     */
    Message reloadDict();
}
