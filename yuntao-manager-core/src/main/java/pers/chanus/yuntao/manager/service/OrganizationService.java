package pers.chanus.yuntao.manager.service;

import pers.chanus.yuntao.commons.pojo.Message;
import pers.chanus.yuntao.manager.model.Organization;
import pers.chanus.yuntao.server.service.BaseService;

/**
 * 组织机构管理接口
 *
 * @author Chanus
 * @date 2019-05-06 21:11:46
 * @since 0.0.8
 */
public interface OrganizationService extends BaseService<Organization, Integer> {
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
