package com.chanus.yuntao.mvc.manager.mapper;

import com.chanus.yuntao.mvc.manager.model.WhiteIp;
import com.chanus.yuntao.mvc.framework.mapper.SuperMapper;

/**
 * 系统IP白名单
 *
 * @author Chanus
 * @date 2018-09-09 15:42:09
 * @since 0.0.1
 */
public interface WhiteIpMapper extends SuperMapper<WhiteIp> {
    String getFixedWhiteIps(String loginNo);
}