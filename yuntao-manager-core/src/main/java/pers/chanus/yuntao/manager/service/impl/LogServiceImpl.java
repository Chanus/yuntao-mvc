/*
 * Copyright (c) 2018, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.chanus.yuntao.commons.constant.LogTypeEnum;
import pers.chanus.yuntao.commons.pojo.CustomMap;
import pers.chanus.yuntao.commons.pojo.LoginUser;
import pers.chanus.yuntao.commons.pojo.PageBean;
import pers.chanus.yuntao.manager.service.LogService;
import pers.chanus.yuntao.server.syslog.Log;
import pers.chanus.yuntao.server.syslog.LogMapper;
import pers.chanus.yuntao.util.IpUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 系统日志接口实现
 *
 * @author Chanus
 * @date 2018-09-09 15:51:14
 * @since 0.0.1
 */
@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private LogMapper logMapper;

    @Override
    public void insert(HttpServletRequest request, Integer moduleId, String content, LogTypeEnum logType, String operateTypeDesc) {
        Log sysLog = new Log();
        LoginUser loginUser = LoginUser.getLoginUser();
        String operateNo = loginUser == null ? "system" : loginUser.getLoginNo(),
                operateRoleId = loginUser == null ? "0" : loginUser.getRoleId();
        sysLog.setOperateNo(operateNo);
        sysLog.setOperateRoleId(operateRoleId);
        sysLog.setOperateIp(IpUtils.getIpAddress(request));
        sysLog.setOperateModuleId(moduleId);
        sysLog.setOperateUrl(String.valueOf(request.getRequestURL()));// 请求URL
        sysLog.setOperateContent(content);// 操作内容
        sysLog.setOperateType(logType.name());
        sysLog.setOperateTypeDesc(operateTypeDesc);
        sysLog.setOperateTime(new Date());

        logMapper.insertSelective(sysLog);
    }

    @Override
    public Log get(Long id) {
        return logMapper.getById(id);
    }

    @Override
    public PageBean listPagination(CustomMap params) {
        int count = logMapper.count(params);
        if (count > 0) {
            int page = params.get("page") == null ? 1 : Integer.parseInt(String.valueOf(params.get("page")));
            int limit = params.get("limit") == null ? PageBean.PAGE_SIZE : Integer.parseInt(String.valueOf(params.get("limit")));
            params.putNext("start", (page - 1) * limit).putNext("limit", limit).putNext("pagination", true);
            return PageBean.pagination(count, logMapper.list(params));
        }

        return new PageBean();
    }

    @Override
    public Log getLastLoginInfo(String operateNo) {
        return logMapper.getLastLoginInfo(operateNo);
    }

}
