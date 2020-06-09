/*
 * Copyright (c) 2018, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.manager.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pers.chanus.yuntao.commons.constant.ConfigConsts;
import pers.chanus.yuntao.commons.pojo.CustomMap;
import pers.chanus.yuntao.commons.pojo.Message;
import pers.chanus.yuntao.manager.mapper.ModuleMapper;
import pers.chanus.yuntao.manager.mapper.RoleMapper;
import pers.chanus.yuntao.manager.model.Module;
import pers.chanus.yuntao.manager.model.Role;
import pers.chanus.yuntao.manager.service.ModuleService;
import pers.chanus.yuntao.server.service.impl.BaseServiceImpl;
import pers.chanus.yuntao.util.CollectionUtils;
import pers.chanus.yuntao.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * 模块管理接口实现
 *
 * @author Chanus
 * @date 2018-09-06 23:00:02
 * @since 0.0.1
 */
@Service
public class ModuleServiceImpl extends BaseServiceImpl<ModuleMapper, Module> implements ModuleService {
    @Autowired
    private RoleMapper roleMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(ModuleServiceImpl.class);

    @Override
    public Message insert(Module t) {
        // 验证模块代码是否已存在
        if (StringUtils.isNotBlank(t.getModuleCode())) {
            int count = baseMapper.checkModuleCode(t.getModuleCode());
            if (count > 0)
                return Message.fail("模块代码已存在");
        }

        Integer maxModuleId = baseMapper.getMaxModuleId(t.getModuleParentId());
        if (maxModuleId == null) {// 不存在同级模块
            if (t.getModuleParentId() == 0)// 一级模块
                t.setModuleId(10);
            else// 二级模块
                t.setModuleId(Integer.parseInt(t.getModuleParentId() + "01"));
        } else {// 存在同级模块
            t.setModuleId(maxModuleId + 1);
        }
        String maxModuleLevel = baseMapper.getMaxModuleLevel(t.getModuleParentId());
        if (StringUtils.isBlank(maxModuleLevel)) {// 不存在同级模块
            if (t.getModuleParentId() == 0)// 一级模块
                t.setModuleLevel("10");
            else// 二级模块
                t.setModuleLevel(baseMapper.selectById(t.getModuleParentId()).getModuleLevel() + "01");
        } else {// 存在同级模块
            t.setModuleLevel(String.valueOf(Integer.parseInt(maxModuleLevel) + 1));
        }

        return super.insert(t);
    }

    @Override
    public String createTree() {
        StringBuilder tree = new StringBuilder("[");
        // 构建一个模块列表根节点
        tree.append("{\"id\":0, \"pId\":0, \"name\":\"模块列表\", \"open\":true").append(", \"icon\":\"../../lib/zTree/zTreeStyle/img/diy/1_open.png\"").append(", \"iconOpen\":\"../../lib/zTree/zTreeStyle/img/diy/1_open.png\"").append(", \"iconClose\":\"../../lib/zTree/zTreeStyle/img/diy/1_close.png\"}");
        try {
            // 构建模块列表目录节点
            List<Module> modules = baseMapper.list(CustomMap.get().putNext("moduleParentId", 0));
            if (!CollectionUtils.isEmpty(modules)) {
                for (Module module : modules) {
                    tree.append(", {\"id\":").append(module.getModuleId()).append(", \"pId\":0, \"name\":\"").append(module.getModuleName()).append("\", \"icon\":\"../../lib/zTree/zTreeStyle/img/diy/9.png\"}");
                }
            }
        } catch (Exception e) {
            LOGGER.error("获取模块列表，系统异常", e);
        }
        tree.append("]");

        return tree.toString();
    }

    @Transactional
    @Override
    public Message priority(Map<String, Object> params) {
        // 获取当前模块
        Module currentModule = baseMapper.selectById(Integer.parseInt((String) params.get("moduleId")));
        if (currentModule == null)
            return Message.fail("当前模块不存在");
        // 获取待交换优先级的模块
        params.put("moduleParentId", currentModule.getModuleParentId());
        params.put("moduleLevel", currentModule.getModuleLevel());
        Module module = baseMapper.getModuleForPriority(params);
        if (module == null)
            return Message.fail("待交换优先级的模块不存在");
        // 交换优先级并更新记录
        String level = currentModule.getModuleLevel();
        currentModule.setModuleLevel(module.getModuleLevel());
        module.setModuleLevel(level);
        super.update(currentModule);
        super.update(module);

        // 若调整的是一级模块的优先级，则更新其下所有二级模块的优先级
        if (currentModule.getModuleParentId() == 0) {
            params.clear();
            // 更新当前模块下的二级模块优先级
            params.put("moduleParentId", currentModule.getModuleId());
            List<Module> modules = baseMapper.list(params);
            if (!CollectionUtils.isEmpty(modules)) {
                for (Module m : modules) {
                    m.setModuleLevel(currentModule.getModuleLevel() + m.getModuleLevel().substring(2));
                    super.update(m);
                }
            }
            // 更新待交换优先级模块下的二级模块优先级
            params.put("moduleParentId", module.getModuleId());
            modules = baseMapper.list(params);
            if (!CollectionUtils.isEmpty(modules)) {
                for (Module m : modules) {
                    m.setModuleLevel(module.getModuleLevel() + m.getModuleLevel().substring(2));
                    super.update(m);
                }
            }
        }

        return Message.success("调整优先级成功");
    }

    @Override
    public List<Module> listRoleModulePower(String roleCode) {
        if (ConfigConsts.ROLE_ADMIN_0.equals(roleCode)) {// 超级管理员
            return baseMapper.listRoleModulePowerForAdmin();
        } else {
            Role role = roleMapper.getByRoleCode(roleCode);
            Role parentRole = roleMapper.get(role.getParentRoleId());
            return ConfigConsts.STATUS_YES.equals(role.getLoginFlag()) ? baseMapper.listRoleModulePowerForNotAdmin(roleCode, parentRole.getRoleCode()) : null;
        }
    }

    @Override
    public List<Module> listSubModulePower(String subNo, String masterRoleCode) {
        return baseMapper.listSubModulePower(subNo, masterRoleCode);
    }

    @Override
    public List<Module> listMenu(String roleCode, String subNo) {
        return baseMapper.listMenu(roleCode, subNo);
    }

    @Override
    public List<String> listUrl(String roleCode, String subNo) {
        return baseMapper.listUrl(roleCode, subNo);
    }

    @Transactional
    @Override
    public Message transfer(Integer moduleId, Integer moduleParentId) {
        if (moduleId == null)
            return Message.fail("待迁移的模块ID为空");
        if (moduleParentId == null)
            return Message.fail("要迁移到的模块ID为空");

        // 验证模块是否存在
        Module module = baseMapper.selectById(moduleId);
        if (module == null)
            return Message.fail("待迁移的模块不存在");
        Module parentModule = baseMapper.selectById(moduleParentId);
        if (parentModule == null)
            return Message.fail("要迁移到的模块不存在");

        // 更新待迁移的模块
        Integer maxModuleId = baseMapper.getMaxModuleId(moduleParentId);
        if (maxModuleId == null) {// 不存在同级模块
            maxModuleId = Integer.parseInt(moduleParentId + "01");
        } else {// 存在同级模块
            maxModuleId++;
        }
        String maxModuleLevel = baseMapper.getMaxModuleLevel(moduleParentId);
        if (StringUtils.isBlank(maxModuleLevel)) {// 不存在同级模块
            maxModuleLevel = parentModule.getModuleLevel() + "01";
        } else {// 存在同级模块
            maxModuleLevel = String.valueOf(Integer.parseInt(maxModuleLevel) + 1);
        }
        baseMapper.update(moduleId, maxModuleId, moduleParentId, maxModuleLevel);

        // 调整比待迁移的模块的层级低的模块层级
        baseMapper.bottom(module.getModuleParentId(), module.getModuleLevel());

        return Message.success("迁移成功");
    }
}
