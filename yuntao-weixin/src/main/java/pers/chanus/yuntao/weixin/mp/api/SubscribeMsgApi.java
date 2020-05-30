/*
 * Copyright (c), 2020-persent, Chanus and/or its affiliates. All rights reserved.
 * FileName: SubscribeMessageApi
 * Author:   Chanus
 * Date:     2020-05-27 12:56:18
 * Description: 一次性订阅消息 API
 * History:
 * <author>          <time>          <version>          <desc>
 */
package pers.chanus.yuntao.weixin.mp.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import pers.chanus.yuntao.util.HttpUtils;
import pers.chanus.yuntao.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 一次性订阅消息 API<br>
 * 详情请见：https://developers.weixin.qq.com/doc/offiaccount/Message_Management/One-time_subscription_info.html
 *
 * @author Chanus
 * @date 2020-05-27 12:56:18
 * @since 0.1.9
 */
public class SubscribeMsgApi {
    /**
     * 用户同意授权链接 url，获取一次给用户推送一条订阅模板消息的机会，引导用户在微信客户端打开此链接
     */
    private static final String AUTHORIZE_URL = "https://mp.weixin.qq.com/mp/subscribemsg?action=get_confirm";
    /**
     * 推送订阅模板消息给到授权微信用户 url，请求方式为 POST
     */
    private static final String SUBSCRIBE_URL = "https://api.weixin.qq.com/cgi-bin/message/template/subscribe?access_token=ACCESS_TOKEN";

    /**
     * 生成用户授权的 url 链接
     *
     * @param scene       重定向后会带上scene参数，开发者可以填0-10000的整形值，用来标识订阅场景值
     * @param templateId  订阅消息模板ID，登录公众平台后台，在接口权限列表处可查看订阅模板ID
     * @param redirectUrl 授权后重定向的回调地址，请使用UrlEncode对链接进行处理。注：要求 redirect_url 的域名要跟登记的业务域名一致，且业务域名不能带路径。业务域名需登录公众号，在设置-公众号设置-功能设置里面对业务域名设置
     * @param reserved    用于保持请求和回调的状态，授权请后原样带回给第三方。该参数可用于防止 csrf 攻击（跨站请求伪造攻击），建议第三方带上该参数，可设置为简单的随机数加 session 进行校验，开发者可以填写 a-zA-Z0-9 的参数值，最多128字节，要求做 urlencode
     * @return 用户授权的 url 链接
     * @throws UnsupportedEncodingException {@code UnsupportedEncodingException}
     * @since 0.1.9
     */
    public static String getAuthorizeURL(int scene, String templateId, String redirectUrl, String reserved) throws UnsupportedEncodingException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(AUTHORIZE_URL).append("&appid=").append(AccessTokenApi.apiConfig.getAppId())
                .append("&scene=").append(scene).append("&template_id=").append(templateId)
                .append("&redirect_url=").append(URLEncoder.encode(redirectUrl, "utf-8"));

        if (StringUtils.isNotBlank(reserved)) {
            stringBuilder.append("&reserved=").append(reserved);
        }
        stringBuilder.append("#wechat_redirect");

        return stringBuilder.toString();
    }

    /**
     * 推送订阅模板消息给到授权微信用户
     *
     * @param json 订阅模板消息数据 json 字符串
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject subscribe(String json) {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = SUBSCRIBE_URL.replace("ACCESS_TOKEN", accessToken);

        String result = HttpUtils.post(url, json);

        return JSON.parseObject(result);
    }

}
