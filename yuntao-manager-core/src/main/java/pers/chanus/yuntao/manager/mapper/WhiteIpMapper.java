package pers.chanus.yuntao.manager.mapper;

import pers.chanus.yuntao.manager.model.WhiteIp;
import pers.chanus.yuntao.server.mapper.SuperMapper;

public interface WhiteIpMapper extends SuperMapper<WhiteIp> {
    String getFixedWhiteIps(String loginNo);
}