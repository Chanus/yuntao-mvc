package com.chanus.yuntao.mvc.manager.service;

import com.chanus.yuntao.mvc.manager.model.Organization;
import com.chanus.yuntao.mvc.framework.service.BaseService;
import com.chanus.yuntao.utils.core.lang.Message;

/**
 * 组织机构管理接口
 *
 * @author Chanus
 * @date 2019-05-06 21:11:46
 * @since 0.0.8
 */
public interface OrganizationService extends BaseService<Organization> {
    /**
     * 创建组织机构树
     *
     * @return
     * @since 0.0.8
     */
    String createTree();

    /**
     * 修改组织机构排序
     *
     * @param orgId    组织机构ID
     * @param priority 组织机构排序
     * @return
     * @since 0.0.8
     */
    Message priority(Integer orgId, Integer priority);
}
