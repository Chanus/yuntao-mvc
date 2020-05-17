/*
 * Copyright (c), 2020-persent, Chanus and/or its affiliates. All rights reserved.
 * FileName: AccessToken
 * Author:   Chanus
 * Date:     2020-05-17 12:30:18
 * Description: 封装 access_token
 * History:
 * <author>          <time>          <version>          <desc>
 */
package pers.chanus.yuntao.weixin.mp.api.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import pers.chanus.yuntao.util.RetryUtils;

import java.io.Serializable;

/**
 * 封装 access_token
 *
 * @author Chanus
 * @date 2020-05-17 12:30:18
 * @since 0.1.9
 */
public class AccessToken implements RetryUtils.ResultCheck, Serializable {
    private static final long serialVersionUID = 6899239391686443684L;

    /**
     * 获取到的凭证，正确获取到 access_token 时有值
     */
    private String access_token;
    /**
     * 凭证有效时间，单位：秒,正确获取到 access_token 时有值
     */
    private Integer expires_in;
    /**
     * 错误代码，出错时有值
     */
    private Integer errcode;
    /**
     * 错误信息，出错时有值
     */
    private String errmsg;
    /**
     * 正确获取到 access_token 时有值，存放过期时间
     */
    private Long expiredTime;

    private String json;

    public AccessToken() {
    }

    /**
     * 构造方法
     *
     * @param json 请求微信接口获取 access_token 时返回的数据
     * @since 0.1.9
     */
    public AccessToken(String json) {
        this.json = json;
        try {
            JSONObject jsonObject = JSON.parseObject(json);
            this.access_token = jsonObject.getString("access_token");
            this.expires_in = jsonObject.getInteger("expires_in");
            this.errcode = jsonObject.getInteger("errcode");
            this.errmsg = jsonObject.getString("errmsg");

            if (expires_in != null) {
                // expires_in - 9  用于控制在 access_token 过期之前 9 秒就 "主动" 再次获取 access_token
                // 避免大并发场景下多线程同时获取 access_token，造成公众平台 api 调用额度的浪费
                expiredTime = System.currentTimeMillis() + ((expires_in - 9) * 1000);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 验证 access_token 是否有效
     *
     * @return 返回{@code true} access_token 有效，返回{@code false} access_token 无效
     * @since 0.1.9
     */
    public boolean isAvailable() {
        if (errcode != null || expiredTime == null || expiredTime < System.currentTimeMillis())
            return false;
        return access_token != null;
    }

    public String getAccessToken() {
        return access_token;
    }

    public Integer getExpiresIn() {
        return expires_in;
    }

    public Long getExpiredTime() {
        return expiredTime;
    }

    public Integer getErrCode() {
        return errcode;
    }

    public String getErrMsg() {
        if (errcode != null) {
            String result = ErrorMsg.get(errcode);
            if (result != null)
                return result;
        }
        return errmsg;
    }

    @Override
    public String getJson() {
        return json;
    }

    @Override
    public boolean matching() {
        return isAvailable();
    }

    @Override
    public String toString() {
        return "AccessToken{" +
                "access_token=" + access_token +
                ", expires_in=" + expires_in +
                ", errcode=" + errcode +
                ", errmsg=" + errmsg +
                ", expiredTime=" + expiredTime +
                ", json=" + json +
                '}';
    }
}
