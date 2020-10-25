/*
 * Copyright (c) 2019, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.commons.pojo;

import java.util.HashMap;
import java.util.Map;

/**
 * 存储全局的SessionId，实现账号只能在同一地点登录
 *
 * @author Chanus
 * @date 2019-04-26 17:23:45
 * @since 0.0.7
 */
public class SessionSave {
    private static Map<String, String> sessionIdSave = new HashMap<>();

    public static Map<String, String> getSessionIdSave() {
        return sessionIdSave;
    }

    public static void setSessionIdSave(Map<String, String> sessionIdSave) {
        SessionSave.sessionIdSave = sessionIdSave;
    }
}
