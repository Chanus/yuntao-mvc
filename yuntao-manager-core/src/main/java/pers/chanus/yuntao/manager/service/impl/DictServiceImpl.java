package pers.chanus.yuntao.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pers.chanus.yuntao.manager.mapper.DictMapper;
import pers.chanus.yuntao.manager.model.Dict;
import pers.chanus.yuntao.manager.service.DictService;
import pers.chanus.yuntao.server.service.impl.BaseServiceImpl;

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

}
