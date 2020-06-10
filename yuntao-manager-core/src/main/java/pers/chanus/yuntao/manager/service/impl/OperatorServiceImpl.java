/*
 * Copyright (c) 2018, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.manager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pers.chanus.yuntao.commons.constant.ConfigConsts;
import pers.chanus.yuntao.commons.pojo.CustomMap;
import pers.chanus.yuntao.commons.pojo.Message;
import pers.chanus.yuntao.commons.pojo.PageBean;
import pers.chanus.yuntao.manager.common.AESKeyConsts;
import pers.chanus.yuntao.manager.mapper.LoginUserViewMapper;
import pers.chanus.yuntao.manager.mapper.OperatorMapper;
import pers.chanus.yuntao.manager.mapper.RoleMapper;
import pers.chanus.yuntao.manager.mapper.RoleModulePowerMapper;
import pers.chanus.yuntao.manager.model.LoginUserView;
import pers.chanus.yuntao.manager.model.Operator;
import pers.chanus.yuntao.manager.model.Role;
import pers.chanus.yuntao.manager.model.RoleModulePower;
import pers.chanus.yuntao.manager.service.OperatorService;
import pers.chanus.yuntao.server.service.impl.BaseServiceImpl;
import pers.chanus.yuntao.util.CollectionUtils;
import pers.chanus.yuntao.util.StringUtils;
import pers.chanus.yuntao.util.encrypt.SHAUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 操作员管理接口实现
 *
 * @author Chanus
 * @date 2018-09-06 23:00:12
 * @since 0.0.1
 */
@Service
public class OperatorServiceImpl extends BaseServiceImpl<OperatorMapper, Operator> implements OperatorService {
    @Autowired
    private LoginUserViewMapper loginUserViewMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RoleModulePowerMapper roleModulePowerMapper;

    @Override
    public Message insert(Operator t) {
        if (StringUtils.isNotBlank(loginUserViewMapper.getUserName(t.getOperatorNo())))
            return Message.fail("账号已存在");

        if (StringUtils.isNotBlank(t.getOperatorPassword()))
            t.setOperatorPassword(SHAUtils.sha256(t.getOperatorPassword() + t.getOperatorNo()));

        Role role;
        if (ConfigConsts.ROLE_SUB_1.equals(t.getOperatorRoleCode())) {// 添加子账号时
            if (StringUtils.isBlank(t.getMasterNo()))
                return Message.fail("主账号不能为空");

            LoginUserView user = loginUserViewMapper.getUser(t.getMasterNo());
            if (user == null)
                return Message.fail("主账号不存在");
            if (ConfigConsts.ROLE_SUB_1.equals(user.getRoleCode()))
                return Message.fail("子账号不能创建子账号");

            t.setMasterNo(user.getLoginNo());
            t.setMasterRoleCode(user.getRoleCode());

            role = roleMapper.selectOne(new QueryWrapper<Role>().lambda().eq(Role::getRoleCode, user.getRoleCode()));
        } else {
            role = roleMapper.selectOne(new QueryWrapper<Role>().lambda().eq(Role::getRoleCode, t.getOperatorRoleCode()));
        }

        t.setSuperior(role.getSuperior());
        t.setAesEmailKey(AESKeyConsts.KEY_EMAIL);
        t.setAesPhoneKey(AESKeyConsts.KEY_PHONE);

        return super.insert(t);
    }

    @Override
    public Message update(Operator t) {
        if (StringUtils.isNotBlank(t.getOperatorPassword()))
            t.setOperatorPassword(SHAUtils.sha256(t.getOperatorPassword() + t.getOperatorNo()));

        t.setAesEmailKey(AESKeyConsts.KEY_EMAIL);
        t.setAesPhoneKey(AESKeyConsts.KEY_PHONE);

        getBaseMapper().updateByPrimaryKeySelective(t);
        return Message.updateSuccess();
    }

    @Override
    public List<Operator> list(CustomMap params) {
        return super.list(params.putNext("aesEmailKey", AESKeyConsts.KEY_EMAIL).putNext("aesPhoneKey", AESKeyConsts.KEY_PHONE));
    }

    @Override
    public PageBean listPagination(CustomMap params) {
        return super.listPagination(params.putNext("aesEmailKey", AESKeyConsts.KEY_EMAIL).putNext("aesPhoneKey", AESKeyConsts.KEY_PHONE));
    }

    @Override
    public Operator get(Integer id) {
        return getBaseMapper().getById(id, AESKeyConsts.KEY_EMAIL, AESKeyConsts.KEY_PHONE);
    }

    @Override
    public Operator get(String operatorNo) {
        return getBaseMapper().getByOperatorNo(operatorNo, AESKeyConsts.KEY_EMAIL, AESKeyConsts.KEY_PHONE);
    }

    @Override
    public Message updatePassword(String operatorNo, String oldPassword, String newPassword, boolean self) {
        if (StringUtils.isBlank(newPassword))
            return Message.fail("新密码不能为空");
        if (self) {// 修改个人密码
            if (StringUtils.isBlank(oldPassword))
                return Message.fail("旧密码不能为空");

            String operatorPassword = getBaseMapper().getPassword(operatorNo);
            if (StringUtils.isBlank(operatorPassword))
                return Message.fail("用户不存在");
            if (!SHAUtils.verifySHA256(oldPassword + operatorNo, operatorPassword))
                return Message.fail("旧密码不正确");
        }

        getBaseMapper().updatePassword(operatorNo, SHAUtils.sha256(newPassword + operatorNo));

        return Message.success("密码修改成功");
    }

    @Override
    public PageBean listSubPagination(CustomMap params) {
        int count = getBaseMapper().countSub(params);
        if (count > 0) {
            params.putNext("aesEmailKey", AESKeyConsts.KEY_EMAIL).putNext("aesPhoneKey", AESKeyConsts.KEY_PHONE);
            int page = params.get("page") == null ? 1 : Integer.parseInt(String.valueOf(params.get("page")));
            int limit = params.get("limit") == null ? PageBean.PAGE_SIZE : Integer.parseInt(String.valueOf(params.get("limit")));
            params.putNext("start", (page - 1) * limit).putNext("limit", limit).putNext("pagination", true);
            return PageBean.pagination(count, getBaseMapper().listSub(params));
        }

        return new PageBean();
    }

    @Transactional
    @Override
    public Message grantSubModulePower(String subNo, String[] modulePowers) {
        if (StringUtils.isBlank(subNo))
            return Message.fail("请选择被授权的子账号");

        // 先删除子账号原有权限
        roleModulePowerMapper.deleteBySubNo(subNo);
        // 再写入新的子账号权限
        if (!CollectionUtils.isEmpty(modulePowers)) {
            List<RoleModulePower> subModulePowers = new ArrayList<>();
            RoleModulePower subModulePower;
            for (String s : modulePowers) {
                subModulePower = new RoleModulePower();
                subModulePower.setRoleCode(ConfigConsts.ROLE_SUB_1);
                subModulePower.setSubNo(subNo);
                String[] mp = s.split("\\.");
                subModulePower.setModuleCode(mp[0]);
                subModulePower.setPowerItem(mp[1]);
                subModulePowers.add(subModulePower);
            }
            roleModulePowerMapper.insertBatch(subModulePowers);
        }

        return Message.success("子账号授权成功");
    }

    @Override
    public String getHeadImage(String operatorNo) {
        return getBaseMapper().getHeadImage(operatorNo);
    }

    @Override
    public Message updateHeadImage(String operatorNo, String headImage) {
        getBaseMapper().updateHeadImage(operatorNo, headImage);
        return Message.success("头像上传成功").initMsg(headImage);
    }

}
