package com.chanus.yuntao.mvc.manager.service.impl;

import com.chanus.yuntao.mvc.framework.enums.LogTypeEnum;
import com.chanus.yuntao.mvc.framework.log.Log;
import com.chanus.yuntao.mvc.framework.log.LogMapper;
import com.chanus.yuntao.utils.core.IpUtils;
import com.chanus.yuntao.utils.core.lang.Message;
import com.chanus.yuntao.utils.core.lang.Page;
import com.chanus.yuntao.utils.core.map.CustomMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.chanus.yuntao.mvc.common.pojo.LoginUser;
import com.chanus.yuntao.mvc.manager.service.LogService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

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
    public void insert(HttpServletRequest request, String moduleCode, String content, LogTypeEnum logType, String operateTypeDesc) {
        Log log = new Log();
        LoginUser loginUser = LoginUser.getLoginUser();
        String operateNo = loginUser == null ? "system" : loginUser.getLoginNo(),
                operateRoleCode = loginUser == null ? "0" : loginUser.getRoleCode();
        log.setOperateNo(operateNo).setOperateRoleCode(operateRoleCode).setOperateIp(IpUtils.getIpAddress(request))
                .setOperateModuleCode(moduleCode).setOperateUrl(String.valueOf(request.getRequestURL()))
                .setOperateContent(content).setOperateType(logType.name())
                .setOperateTypeDesc(operateTypeDesc).setOperateTime(LocalDateTime.now());

        logMapper.insertSelective(log);
    }

    @Override
    public void insert(String operateNo, String operateRoleCode, String moduleCode, String operateMethod, String content, LogTypeEnum logType, String operateTypeDesc, Integer operateConsumeTime) {
        Log log = new Log();
        log.setOperateNo(operateNo).setOperateRoleCode(operateRoleCode).setOperateModuleCode(moduleCode).setOperateMethod(operateMethod)
                .setOperateContent(content).setOperateType(logType.name()).setOperateTypeDesc(operateTypeDesc)
                .setOperateConsumeTime(operateConsumeTime).setOperateTime(LocalDateTime.now());

        logMapper.insertSelective(log);
    }

    @Override
    public Log get(Long id) {
        return logMapper.get(id);
    }

    @Override
    public Page<Log> listPagination(CustomMap params) {
        int count = logMapper.count(params);
        if (count > 0) {
            return Page.pagination(count, logMapper.list(Page.initPageParams(params)));
        }

        return new Page<>();
    }

    @Override
    public Log getLastLoginInfo(String operateNo) {
        return logMapper.getLastLoginInfo(operateNo);
    }

    @Override
    public Message delete(Integer id) {
        logMapper.delete(id);
        return Message.deleteSuccess();
    }

    @Override
    public Message clear() {
        logMapper.clear();
        return Message.success("清除日志成功");
    }
}
