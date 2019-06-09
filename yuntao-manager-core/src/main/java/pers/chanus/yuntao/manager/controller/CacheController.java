/*
 * Copyright (c) 2018, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pers.chanus.yuntao.manager.common.CacheData;
import pers.chanus.yuntao.manager.service.DictItemService;
import pers.chanus.yuntao.manager.service.ParamService;

import javax.annotation.PostConstruct;

/**
 * 缓存系统基础数据
 *
 * @author Chanus
 * @date 2018-09-06 17:39:20
 * @since 0.0.1
 */
@Component
public class CacheController {
    @Autowired
    private ParamService paramService;
    @Autowired
    private DictItemService dictItemService;

    /**
     * 初始化RSA密钥RSA_KEYS_QUEUE
     *
     * @since 0.0.1
     */
    @PostConstruct
    public void initRsaKeysQueue() {
        CacheData.initRsaKeysQueue();
    }

    /**
     * 初始化系统参数SYSTEM_PARAMS_MAP
     *
     * @since 0.0.1
     */
    @PostConstruct
    public void initSysParamsMap() {
        paramService.reloadParam();
    }

    /**
     * 初始化系统字典数据SYSTEM_DICT_MAP
     *
     * @since 0.1.1
     */
    @PostConstruct
    public void initSysDictMap() {
        dictItemService.reloadDict();
    }
}
