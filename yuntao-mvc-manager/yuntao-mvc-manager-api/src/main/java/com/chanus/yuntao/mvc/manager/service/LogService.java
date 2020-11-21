package com.chanus.yuntao.mvc.manager.service;

import com.chanus.yuntao.mvc.framework.enums.LogTypeEnum;
import com.chanus.yuntao.mvc.framework.log.Log;
import com.chanus.yuntao.utils.core.lang.Message;
import com.chanus.yuntao.utils.core.lang.Page;
import com.chanus.yuntao.utils.core.map.CustomMap;

import javax.servlet.http.HttpServletRequest;

/**
 * 系统日志接口
 *
 * @author Chanus
 * @date 2018-09-09 15:51:06
 * @since 0.0.1
 */
public interface LogService {
    /**
     * 添加系统日志
     *
     * @param request         必需
     * @param moduleCode      模块代码，可选
     * @param content         日志内容，可选
     * @param logType         日志类型，必需
     * @param operateTypeDesc 操作类型描述，可选
     * @since 0.0.1
     */
    void insert(HttpServletRequest request, String moduleCode, String content, LogTypeEnum logType, String operateTypeDesc);

    /**
     * 添加退出系统的系统日志
     *
     * @param request         必需
     * @param operateTypeDesc 操作类型描述，可选
     * @since 0.0.1
     */
    default void logout(HttpServletRequest request, String operateTypeDesc) {
        insert(request, null, null, LogTypeEnum.LOGOUT, operateTypeDesc);
    }

    /**
     * 添加系统日志
     *
     * @param operateNo          操作账号
     * @param operateRoleCode    操作账号角色
     * @param moduleCode         模块代码，可选
     * @param operateMethod      方法描述
     * @param content            日志内容，可选
     * @param logType            日志类型，必需
     * @param operateTypeDesc    操作类型描述，可选
     * @param operateConsumeTime 操作耗时
     * @since 0.1.7
     */
    void insert(String operateNo, String operateRoleCode, String moduleCode, String operateMethod, String content, LogTypeEnum logType, String operateTypeDesc, Integer operateConsumeTime);

    /**
     * 获取系统日志内容
     *
     * @param id
     * @return
     * @since 0.0.1
     */
    Log get(Long id);

    /**
     * 分页查询系统日志
     *
     * @param params
     * @return
     * @since 0.0.1
     */
    Page<Log> listPagination(CustomMap params);

    /**
     * 获取用户最后登录信息
     *
     * @param operateNo 操作账号
     * @return
     * @since 0.0.1
     */
    Log getLastLoginInfo(String operateNo);

    /**
     * 删除日期记录
     *
     * @param id 日志ID
     * @return
     * @since 0.1.8
     */
    Message delete(Integer id);

    /**
     * 清除日期记录
     *
     * @return
     * @since 0.1.8
     */
    Message clear();
}
