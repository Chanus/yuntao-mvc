package pers.chanus.yuntao.manager.service.impl;

import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pers.chanus.yuntao.commons.pojo.Message;
import pers.chanus.yuntao.manager.mapper.OrganizationMapper;
import pers.chanus.yuntao.manager.model.Organization;
import pers.chanus.yuntao.manager.service.OrganizationService;
import pers.chanus.yuntao.server.service.impl.BaseServiceImpl;
import pers.chanus.yuntao.util.CollectionUtils;

/**
 * 组织机构管理接口实现
 * 
 * @author Chanus
 * @date 2019-05-06 21:11:46
 * @since 0.0.8
 */
@Service
public class OrganizationServiceImpl extends BaseServiceImpl<OrganizationMapper, Organization, Integer> implements OrganizationService {
	private static final Logger LOGGER = LoggerFactory.getLogger(OrganizationServiceImpl.class);

	@Autowired
	public void setMapper(OrganizationMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public Message insert(Organization t) {
		if (t.getOrgParentId() == null)
			t.setOrgParentId(0);
		
		Integer priority = mapper.getMaxPriority(t.getOrgParentId());
		t.setPriority(priority == null ? 1 : (priority + 1));
		
		return super.insert(t);
	}

	@Override
	public Message delete(Integer pk) {
		int count = mapper.hasChildren(pk);
		if (count > 0)
			return Message.fail("请先删除下级组织机构");
		
		return super.delete(pk);
	}

	@Override
	public Message delete(Collection<Integer> pks) {
		int count = 0;
		for (Integer i : pks) {
			count = mapper.hasChildren(i);
			if (count > 0)
				return Message.fail("请先删除下级组织机构");
		}
		
		return super.delete(pks);
	}

	@Override
	public String createTree() {
		StringBuffer tree = new StringBuffer("[");
		// 构建一个组织机构列表根节点
		tree.append("{\"id\":0, \"pId\":0, \"name\":\"组织机构\", \"open\":true").append(", \"icon\":\"../../lib/zTree/zTreeStyle/img/diy/1_open.png\"").append(", \"iconOpen\":\"../../lib/zTree/zTreeStyle/img/diy/1_open.png\"").append(", \"iconClose\":\"../../lib/zTree/zTreeStyle/img/diy/1_close.png\"}");
		try {
			// 构建组织机构列表目录节点
			List<Organization> organizations = mapper.list(null);
			if (!CollectionUtils.isEmpty(organizations)) {
				for (Organization organization : organizations) {
					tree.append(", {\"id\":\"").append(organization.getOrgId()).append("\", \"pId\":\"").append(organization.getOrgParentId()).append("\", \"name\":\"").append(organization.getOrgName()).append("\", \"icon\":\"../../lib/zTree/zTreeStyle/img/diy/9.png\"}");
				}
			}
		} catch (Exception e) {
			LOGGER.error("获取组织机构列表，系统异常", e);
		}
		tree.append("]");
		
		return tree.toString();
	}

	@Override
	public Message priority(Integer orgId, Integer priority) {
		mapper.priority(orgId, priority);
		return Message.success("排序修改成功");
	}

}
