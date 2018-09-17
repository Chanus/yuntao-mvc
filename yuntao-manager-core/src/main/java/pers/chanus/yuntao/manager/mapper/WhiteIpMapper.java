package pers.chanus.yuntao.manager.mapper;

import pers.chanus.yuntao.manager.model.WhiteIp;
import pers.chanus.yuntao.server.mapper.BaseMapper;

public interface WhiteIpMapper extends BaseMapper<WhiteIp, Integer> {
	String getFixedWhiteIps(String loginNo);
}