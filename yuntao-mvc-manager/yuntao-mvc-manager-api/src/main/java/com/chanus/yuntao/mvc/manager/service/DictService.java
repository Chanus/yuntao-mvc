package com.chanus.yuntao.mvc.manager.service;

import com.chanus.yuntao.mvc.manager.model.Dict;
import com.chanus.yuntao.mvc.framework.service.BaseService;
import com.chanus.yuntao.utils.core.lang.Message;

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
