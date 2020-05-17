/*
 * Copyright (c), 2020-persent, Chanus and/or its affiliates. All rights reserved.
 * FileName: AccessTokenApi
 * Author:   Chanus
 * Date:     2020-05-15 21:04:02
 * Description: 认证并获取 access_token API
 * History:
 * <author>          <time>          <version>          <desc>
 */
package pers.chanus.yuntao.weixin.mp.api;

import pers.chanus.yuntao.util.HttpUtils;
import pers.chanus.yuntao.util.RetryUtils;
import pers.chanus.yuntao.util.StringUtils;
import pers.chanus.yuntao.weixin.mp.api.bean.AccessToken;
import pers.chanus.yuntao.weixin.mp.api.bean.ApiConfig;

/**
 * 认证并获取 access_token API<br>
 * 详情请见: https://developers.weixin.qq.com/doc/offiaccount/Basic_Information/Get_access_token.html
 *
 * @author Chanus
 * @date 2020-05-15 21:04:02
 * @since 0.1.9
 */
public class AccessTokenApi {
    /**
     * 公众号开发信息
     */
    public static ApiConfig apiConfig = null;

    /**
     * 获取 access_token url，请求方式为 GET
     */
    private static final String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

    /**
     * 用于手动设置的 accessToken
     */
    private static AccessToken accessToken = null;

    /**
     * 移除 access_token
     *
     * @since 0.1.9
     */
    public static void removeAccessToken() {
        accessToken = null;
    }

    /**
     * 从缓存中获取 access_token，如果未取到或者 access_token 不可用则先更新再获取
     *
     * @return {@code AccessToken}
     * @since 0.1.9
     */
    public static AccessToken getAccessToken() {
        if (accessToken != null && accessToken.isAvailable())
            return accessToken;

        String requestUrl = url.replace("APPID", apiConfig.getAppId()).replace("APPSECRET", apiConfig.getAppSecret());
        // 发起GET请求获取凭证
        String result = HttpUtils.get(requestUrl);
        AccessToken token = null;
        if (StringUtils.isNotBlank(result)) {
            token = new AccessToken(result);
        }
        if (null == token || !token.isAvailable())
            token = refreshAccessToken();

        return token;
    }

    /**
     * 获取 access_token 字符串
     *
     * @return 获取到的凭证
     * @since 0.1.9
     */
    public static String getAccessTokenStr() {
        return getAccessToken().getAccessToken();
    }

    /**
     * 无条件强制更新 access_token 值，不再对 cache 中的 token 进行判断
     *
     * @return {@code AccessToken}
     * @since 0.1.9
     */
    public static AccessToken refreshAccessToken() {
        String requestUrl = url.replace("APPID", apiConfig.getAppId()).replace("APPSECRET", apiConfig.getAppSecret());
        // 最多三次请求
        return RetryUtils.retryOnException(3, () -> {
            String json = HttpUtils.get(requestUrl);
            return new AccessToken(json);
        });
    }
}
