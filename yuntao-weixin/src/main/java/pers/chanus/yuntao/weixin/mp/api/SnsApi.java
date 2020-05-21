/*
 * Copyright (c), 2020-persent, Chanus and/or its affiliates. All rights reserved.
 * FileName: SnsApi
 * Author:   Chanus
 * Date:     2020-05-19 14:03:42
 * Description: 网页授权 API
 * History:
 * <author>          <time>          <version>          <desc>
 */
package pers.chanus.yuntao.weixin.mp.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import pers.chanus.yuntao.util.HttpUtils;
import pers.chanus.yuntao.util.RetryUtils;
import pers.chanus.yuntao.util.StringUtils;
import pers.chanus.yuntao.util.UrlUtils;
import pers.chanus.yuntao.weixin.mp.api.bean.SnsAccessToken;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.TreeMap;

/**
 * 网页授权 API<br>
 * 详情请见：https://developers.weixin.qq.com/doc/offiaccount/OA_Web_Apps/Wechat_webpage_authorization.html
 *
 * @author Chanus
 * @date 2020-05-19 14:03:42
 * @since 0.1.9
 */
public class SnsApi {
    /**
     * 用户同意授权，获取 code，引导关注者打开此页面
     */
    private static final String AUTHORIZE_URL = "https://open.weixin.qq.com/connect/oauth2/authorize";
    /**
     * 生成扫描二维码，获取 code，引导关注者打开此页面
     */
    private static final String QRCONNECT_URL = "https://open.weixin.qq.com/connect/qrconnect";
    /**
     * 通过code换取网页授权 access_token url，请求方式为 GET
     */
    private static final String GET_SNS_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
    /**
     * 刷新 access_token（如果需要） url，请求方式为 GET
     */
    private static final String REFRESH_SNS_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";
    /**
     * 拉取用户信息(需scope为 snsapi_userinfo) url，请求方式为 GET
     */
    private static final String SNS_USER_INFO_URL = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
    /**
     * 检验授权凭证（access_token）是否有效 url，请求方式为 GET
     */
    private static final String SNS_AUTH_URL = "https://api.weixin.qq.com/sns/auth?access_token=ACCESS_TOKEN&openid=OPENID";

    /**
     * 生成网页授权的 url 链接
     *
     * @param redirectUri 授权后重定向的回调链接地址
     * @param snsapiBase  应用授权作用域，{@code true}表示静默授权并自动跳转到回调页，{@code false}表示需要用户手动同意<br>
     *                    snsapi_base （不弹出授权页面，直接跳转，只能获取用户openid），snsapi_userinfo （弹出授权页面，可通过openid拿到昵称、性别、所在地。并且，即使在未关注的情况下，只要用户授权，也能获取其信息 ）
     * @param state       重定向后会带上state参数，开发者可以填写a-zA-Z0-9的参数值，最多128字节
     * @return 网页授权的 url 链接
     * @since 0.1.9
     */
    public static String getAuthorizeURL(String redirectUri, boolean snsapiBase, String state) {
        Map<String, Object> params = new TreeMap<>();
        params.put("appid", AccessTokenApi.apiConfig.getAppId());
        try {
            params.put("redirect_uri", URLEncoder.encode(redirectUri, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        params.put("response_type", "code");
        if (snsapiBase)
            params.put("scope", "snsapi_base");
        else
            params.put("scope", "snsapi_userinfo");
        if (StringUtils.isNotBlank(state))
            params.put("state", state);

        return UrlUtils.setParam(AUTHORIZE_URL, params).concat("#wechat_redirect");
    }

    /**
     * 生成网页授权的 url 链接
     *
     * @param redirectUri 授权后重定向的回调链接地址
     * @param snsapiBase  应用授权作用域，{@code true}表示静默授权并自动跳转到回调页，{@code false}表示需要用户手动同意<br>
     *                    snsapi_base （不弹出授权页面，直接跳转，只能获取用户openid），snsapi_userinfo （弹出授权页面，可通过openid拿到昵称、性别、所在地。并且，即使在未关注的情况下，只要用户授权，也能获取其信息 ）最多128字节
     * @return 网页授权的 url 链接
     * @since 0.1.9
     */
    public static String getAuthorizeURL(String redirectUri, boolean snsapiBase) {
        return getAuthorizeURL(redirectUri, snsapiBase, null);
    }

    /**
     * 生成网页二维码授权链接
     *
     * @param redirectUri 授权后重定向的回调链接地址
     * @param state       重定向后会带上state参数，开发者可以填写a-zA-Z0-9的参数值，最多128字节
     * @return 网页二维码授权链接
     * @since 0.1.9
     */
    public static String getQrConnectURL(String redirectUri, String state) {
        Map<String, Object> params = new TreeMap<>();
        params.put("appid", AccessTokenApi.apiConfig.getAppId());
        try {
            params.put("redirect_uri", URLEncoder.encode(redirectUri, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        params.put("response_type", "code");
        params.put("scope", "snsapi_login");
        if (StringUtils.isNotBlank(state))
            params.put("state", state);

        return UrlUtils.setParam(QRCONNECT_URL, params).concat("#wechat_redirect");
    }


    /**
     * 生成网页二维码授权链接
     *
     * @param redirectUri 授权后重定向的回调链接地址
     * @return 网页二维码授权链接
     * @since 0.1.9
     */
    public static String getQrConnectURL(String redirectUri) {
        return getQrConnectURL(redirectUri, null);
    }

    /**
     * 通过 code 换取网页授权 access_token
     *
     * @param code 填写第一步获取的 code 参数
     * @return {@code SnsAccessToken}
     * @since 0.1.9
     */
    public static SnsAccessToken getSnsAccessToken(String code) {
        String url = GET_SNS_ACCESS_TOKEN_URL.replace("APPID", AccessTokenApi.apiConfig.getAppId())
                .replace("SECRET", AccessTokenApi.apiConfig.getAppSecret()).replace("CODE", code);

        return RetryUtils.retryOnException(3, () -> {
            String json = HttpUtils.get(url);
            return new SnsAccessToken(json);
        });
    }

    /**
     * 通过 code 换取网页授权 access_token，获取用户 openid
     *
     * @param code 填写第一步获取的 code 参数
     * @return 用户 openid
     * @since 0.1.9
     */
    public static String getUserOpenId(String code) {
        SnsAccessToken snsAccessToken = getSnsAccessToken(code);

        return snsAccessToken.getOpenid();
    }

    /**
     * 刷新 access_token（如果需要）
     *
     * @param refreshToken 填写通过 access_token 获取到的 refresh_token 参数
     * @return {@code SnsAccessToken}
     * @since 0.1.9
     */
    public static SnsAccessToken refreshSnsAccessToken(String refreshToken) {
        String url = REFRESH_SNS_ACCESS_TOKEN_URL.replace("APPID", AccessTokenApi.apiConfig.getAppId())
                .replace("REFRESH_TOKEN", refreshToken);

        return RetryUtils.retryOnException(3, () -> {
            String json = HttpUtils.get(url);
            return new SnsAccessToken(json);
        });
    }

    /**
     * 获取用户基本信息（需scope为 snsapi_userinfo）
     *
     * @param accessToken 网页授权接口调用凭证，注意：此 access_token 与基础支持的 access_token 不同
     * @param openId      用户的唯一标识
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject getUserInfo(String accessToken, String openId) {
        String url = SNS_USER_INFO_URL.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);

        String result = HttpUtils.get(url);

        return JSON.parseObject(result);
    }

    /**
     * 检验授权凭证（access_token）是否有效
     *
     * @param accessToken 网页授权接口调用凭证，注意：此 access_token 与基础支持的 access_token 不同
     * @param openId      用户的唯一标识
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject authSnsAccessToken(String accessToken, String openId) {
        String url = SNS_AUTH_URL.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);

        String result = HttpUtils.get(url);

        return JSON.parseObject(result);
    }
}
