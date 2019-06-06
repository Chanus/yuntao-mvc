package pers.chanus.yuntao.server.syslog;

import java.util.List;
import java.util.Map;

public interface LogMapper {
    Log getById(Long id);

    int insertSelective(Log record);

    int count(Map<String, Object> params);

    List<Log> list(Map<String, Object> params);

    Log getLastLoginInfo(String operateNo);
}