package pers.chanus.yuntao.manager.service;

import pers.chanus.yuntao.commons.pojo.Message;
import pers.chanus.yuntao.manager.model.Dict;
import pers.chanus.yuntao.springmvc.service.BaseService;

/**
 * 数据字典集管理接口
 *
 * @author Chanus
 * @date 2019-06-07 12:55:23
 * @since 0.1.1
 */
public interface DictService extends BaseService<Dict> {
    /**
     * 重载字典数据
     *
     * @return
     * @since 0.1.7
     */
    Message reloadDict();
}
