/*
 * Copyright (c), 2020-persent, Chanus and/or its affiliates. All rights reserved.
 * FileName: ApiConfig
 * Author:   Chanus
 * Date:     2020-05-17 13:55:36
 * Description: 公众号开发信息配置
 * History:
 * <author>          <time>          <version>          <desc>
 */
package pers.chanus.yuntao.weixin.mp.api.bean;

import java.io.Serializable;

/**
 * 公众号开发信息配置<br>
 * 详情请见: https://developers.weixin.qq.com/doc/offiaccount/Basic_Information/Access_Overview.html
 *
 * @author Chanus
 * @date 2020-05-17 13:55:36
 * @since 0.1.9
 */
public class ApiConfig implements Serializable {
    private static final long serialVersionUID = -7251374840133877967L;

    /**
     * 开发者ID
     */
    private String appId;
    /**
     * 开发者密码
     */
    private String appSecret;
    /**
     * 令牌
     */
    private String token;
    /**
     * 消息加解密密钥
     */
    private String encodingAESKey;
    /**
     * 消息加解密方式，{@code false}表示明文模式，{@code true}表示安全模式，默认为明文模式
     */
    private boolean messageEncrypt = false;

    public ApiConfig() {
    }

    public ApiConfig(String token) {
        setToken(token);
    }

    public ApiConfig(String appId, String appSecret) {
        setAppId(appId);
        setAppSecret(appSecret);
    }

    public ApiConfig(String appId, String appSecret, String token) {
        setAppId(appId);
        setAppSecret(appSecret);
        setToken(token);
    }

    public ApiConfig(String appId, String appSecret, String token, String encodingAESKey, boolean messageEncrypt) {
        setAppId(appId);
        setAppSecret(appSecret);
        setToken(token);
        setEncodingAESKey(encodingAESKey);
        setMessageEncrypt(messageEncrypt);
    }

    public String getAppId() {
        if (appId == null)
            throw new IllegalStateException("appId 未被赋值");
        return appId;
    }

    public void setAppId(String appId) {
        if (appId == null)
            throw new IllegalStateException("appId 值不能为 null");
        this.appId = appId;
    }

    public String getAppSecret() {
        if (appSecret == null)
            throw new IllegalStateException("appSecret 未被赋值");
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        if (appSecret == null)
            throw new IllegalStateException("appSecret 值不能为 null");
        this.appSecret = appSecret;
    }

    public String getToken() {
        if (token == null)
            throw new IllegalStateException("token 未被赋值");
        return token;
    }

    public void setToken(String token) {
        if (token == null)
            throw new IllegalStateException("token 值不能为 null");
        this.token = token;
    }

    public String getEncodingAESKey() {
        if (encodingAESKey == null)
            throw new IllegalStateException("encodingAESKey 未被赋值");
        return encodingAESKey;
    }

    public void setEncodingAESKey(String encodingAESKey) {
        if (encodingAESKey == null)
            throw new IllegalStateException("encodingAESKey 值不能为 null");
        this.encodingAESKey = encodingAESKey;
    }

    public boolean isMessageEncrypt() {
        return messageEncrypt;
    }

    public void setMessageEncrypt(boolean messageEncrypt) {
        this.messageEncrypt = messageEncrypt;
    }
}
