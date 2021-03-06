package com.chanus.yuntao.mvc.manager.service;

import com.chanus.yuntao.mvc.manager.model.Operator;
import com.chanus.yuntao.mvc.framework.service.BaseService;
import com.chanus.yuntao.utils.core.lang.Message;
import com.chanus.yuntao.utils.core.lang.Page;
import com.chanus.yuntao.utils.core.map.CustomMap;

/**
 * 操作员管理接口
 *
 * @author Chanus
 * @date 2018-09-06 22:45:43
 * @since 0.0.1
 */
public interface OperatorService extends BaseService<Operator> {
    /**
     * 根据操作员ID查询操作员信息
     *
     * @param id 操作员ID
     * @return
     * @since 0.0.1
     */
    Operator get(Integer id);

    /**
     * 根据操作员账号查询操作员信息
     *
     * @param operatorNo 操作员账号
     * @return
     * @since 0.0.1
     */
    Operator get(String operatorNo);

    /**
     * 修改密码，操作员修改个人密码时旧密码不能为空
     *
     * @param operatorNo  操作员账号
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @param self        操作标识：{@code true}-修改个人密码，{@code false}-管理员修改当前账号的密码
     * @return
     * @since 0.0.1
     */
    Message updatePassword(String operatorNo, String oldPassword, String newPassword, boolean self);

    /**
     * 分页查询子账号
     *
     * @param params loginRoleCode    登录角色编码，必需
     *               loginNo    登录账号，必需
     *               subNo  子账号
     *               masterNo   主账号
     *               masterRoleCode   主账号角色编码
     *               validStatus    状态
     * @return
     * @since 0.0.1
     */
    Page<Operator> listSubPagination(CustomMap params);

    /**
     * 子账号授权
     *
     * @param subNo        子账号
     * @param modulePowers 模块-权限项数组
     * @return
     * @since 0.0.1
     */
    Message grantSubModulePower(String subNo, String[] modulePowers);

    /**
     * 获取操作员的头像路径
     *
     * @param operatorNo 操作员账号
     * @return
     * @since 0.0.1
     */
    String getHeadImage(String operatorNo);

    /**
     * 更新操作员头像
     *
     * @param operatorNo 操作员账号
     * @param headImage  操作员账号头像路径
     * @return
     * @since 0.0.1
     */
    Message updateHeadImage(String operatorNo, String headImage);
}
