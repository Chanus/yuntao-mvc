/*
 * Copyright (c) 2018, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.manager.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pers.chanus.yuntao.commons.pojo.Message;
import pers.chanus.yuntao.manager.mapper.RoleMapper;
import pers.chanus.yuntao.manager.mapper.RoleModulePowerMapper;
import pers.chanus.yuntao.manager.model.Role;
import pers.chanus.yuntao.manager.model.RoleModulePower;
import pers.chanus.yuntao.manager.service.RoleService;
import pers.chanus.yuntao.server.service.impl.BaseServiceImpl;
import pers.chanus.yuntao.util.CollectionUtils;
import pers.chanus.yuntao.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 系统角色管理接口实现
 *
 * @author Chanus
 * @date 2018-09-08 21:55:31
 * @since 0.0.1
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<RoleMapper, Role, Integer> implements RoleService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Autowired
    private RoleModulePowerMapper roleModulePowerMapper;

    @Autowired
    public void setMapper(RoleMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Message insert(Role t) {
        // 获取最大角色，用来生成新的角色ID
        String roleId = mapper.getMaxRoleId(t.getParentRoleId());
        t.setRoleId(StringUtils.isBlank(roleId) ? (t.getParentRoleId() + "10") : String.valueOf(Integer.parseInt(roleId) + 1));
        // 如果没有填写角色代码，则默认为角色ID
        if (StringUtils.isBlank(t.getRoleCode()))
            t.setRoleCode(t.getRoleId());
        // 获取最大排序值，用来设置角色的排序
        Integer priority = mapper.getMaxPriority(t.getParentRoleId());
        t.setPriority(priority == null ? 1 : (priority + 1));
        // 获取上级角色信息，设置上级角色代码
        Role parent = mapper.get(t.getParentRoleId());
        if (parent == null)
            t.setSuperior(t.getRoleCode());
        else
            t.setSuperior(parent.getSuperior() + "," + t.getRoleCode());

        return super.insert(t);
    }

    @Override
    public String createTree(Map<String, Object> params) {
        StringBuilder tree = new StringBuilder("[");
        // 当前角色
        Role r = mapper.getByRoleCode((String) params.get("roleCode"));
        // 上级角色
        Role p = mapper.get(r.getParentRoleId());
        // 构建一个角色列表根节点
        tree.append("{\"id\":\"").append(r.getParentRoleId())
                .append("\", \"roleCode\":\"").append(p == null ? "-1" : p.getRoleCode())// 根节点设置上级角色的角色代码
                .append("\", \"pId\":\"-1\", \"name\":\"角色列表\", \"open\":true")
                .append(", \"icon\":\"../../lib/zTree/zTreeStyle/img/diy/1_open.png\"")
                .append(", \"iconOpen\":\"../../lib/zTree/zTreeStyle/img/diy/1_open.png\"")
                .append(", \"iconClose\":\"../../lib/zTree/zTreeStyle/img/diy/1_close.png\"}");
        try {
            // 构建角色列表目录节点
            List<Role> roles = mapper.list(params);
            if (!CollectionUtils.isEmpty(roles)) {
                for (Role role : roles) {
                    tree.append(", {\"id\":\"").append(role.getRoleId())
                            .append("\", \"roleCode\":\"").append(role.getRoleCode())
                            .append("\", \"pId\":\"").append(role.getParentRoleId())
                            .append("\", \"name\":\"").append(role.getRoleName())
                            .append("\", \"icon\":\"../../lib/zTree/zTreeStyle/img/diy/9.png\"}");
                }
            }
        } catch (Exception e) {
            LOGGER.error("获取角色列表，系统异常", e);
        }
        tree.append("]");

        return tree.toString();
    }

    @Transactional
    @Override
    public Message grantRoleModulePower(String roleId, String[] modulePowers) {
        if (StringUtils.isBlank(roleId))
            return Message.fail("请选择被授权的角色");

        // 先删除角色原有权限
        roleModulePowerMapper.deleteByRoleId(roleId);
        // 再写入新的角色权限
        if (!CollectionUtils.isEmpty(modulePowers)) {
            List<RoleModulePower> roleModulePowers = new ArrayList<>();
            RoleModulePower roleModulePower;
            for (String s : modulePowers) {
                roleModulePower = new RoleModulePower();
                roleModulePower.setRoleId(roleId);
                String[] mp = s.split("\\.");
                roleModulePower.setModuleCode(mp[0]);
                roleModulePower.setPowerItem(mp[1]);
                roleModulePower.setSubNo(StringUtils.EMPTY);
                roleModulePowers.add(roleModulePower);
            }
            roleModulePowerMapper.insertBatch(roleModulePowers);
        }

        return Message.success("角色授权成功");
    }

}
