/*
 * Copyright (c), 2020-persent, Chanus and/or its affiliates. All rights reserved.
 * FileName: CallbackIpApi
 * Author:   Chanus
 * Date:     2020-05-18 15:35:52
 * Description: 获取微信服务器IP地址 API
 * History:
 * <author>          <time>          <version>          <desc>
 */
package pers.chanus.yuntao.weixin.mp.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chanus.yuntao.utils.core.HttpUtils;

/**
 * 获取微信服务器IP地址 API<br>
 * 详情请见：https://developers.weixin.qq.com/doc/offiaccount/Basic_Information/Get_the_WeChat_server_IP_address.html
 *
 * @author Chanus
 * @date 2020-05-18 15:35:52
 * @since 0.1.9
 */
public class CallbackIpApi {
    /**
     * 获取微信 callback IP 地址 url，请求方式为 GET
     */
    private static final String GET_CALLBACK_IP_URL = "https://api.weixin.qq.com/cgi-bin/getcallbackip?access_token=ACCESS_TOKEN";
    /**
     * 获取微信 API 接口 IP 地址 url，请求方式为 GET
     */
    private static final String GET_API_IP_URL = "https://api.weixin.qq.com/cgi-bin/get_api_domain_ip?access_token=ACCESS_TOKEN";

    /**
     * 获取设置的行业信息
     *
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject getCallBackIp() {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = GET_CALLBACK_IP_URL.replace("ACCESS_TOKEN", accessToken);

        String result = HttpUtils.get(url);

        return JSON.parseObject(result);
    }

    /**
     * 获取设置的行业信息
     *
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject getApiIp() {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = GET_API_IP_URL.replace("ACCESS_TOKEN", accessToken);

        String result = HttpUtils.get(url);

        return JSON.parseObject(result);
    }

}
