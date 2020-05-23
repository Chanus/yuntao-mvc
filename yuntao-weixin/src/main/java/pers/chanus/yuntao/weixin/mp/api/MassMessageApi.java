/*
 * Copyright (c), 2020-persent, Chanus and/or its affiliates. All rights reserved.
 * FileName: MessageApi
 * Author:   Chanus
 * Date:     2020-05-22 20:30:20
 * Description: 高级群发接口 API
 * History:
 * <author>          <time>          <version>          <desc>
 */
package pers.chanus.yuntao.weixin.mp.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import pers.chanus.yuntao.util.HttpUtils;

/**
 * 高级群发接口 API<br>
 * 详情请见：https://developers.weixin.qq.com/doc/offiaccount/Message_Management/Batch_Sends_and_Originality_Checks.html
 *
 * @author Chanus
 * @date 2020-05-22 20:30:20
 * @since 0.1.9
 */
public class MassMessageApi {
    /**
     * 根据标签（分组）进行群发 url，请求方式为 POST
     */
    private static final String TAG_SEND_ALL_URL = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=ACCESS_TOKEN";
    /**
     * 根据 OpenID 列表群发 url，请求方式为 POST
     */
    private static final String OPENID_SEND_URL = "https://api.weixin.qq.com/cgi-bin/message/mass/send?access_token=ACCESS_TOKEN";
    /**
     * 删除群发 url，请求方式为 POST
     */
    private static final String DELETE_MASS_URL = "https://api.weixin.qq.com/cgi-bin/message/mass/delete?access_token=ACCESS_TOKEN";
    /**
     * 预览接口 url，请求方式为 POST
     */
    private static final String PREVIEW_MASS_URL = "https://api.weixin.qq.com/cgi-bin/message/mass/preview?access_token=ACCESS_TOKEN";
    /**
     * 查询群发消息发送状态 url，请求方式为 POST
     */
    private static final String GET_MASS_STATUS_URL = "https://api.weixin.qq.com/cgi-bin/message/mass/get?access_token=ACCESS_TOKEN";
    /**
     * 控制群发速度 url，请求方式为 POST
     */
    private static final String GET_MASS_SPEED_URL = "https://api.weixin.qq.com/cgi-bin/message/mass/speed/get?access_token=ACCESS_TOKEN";
    /**
     * 设置群发速度 url，请求方式为 POST
     */
    private static final String SET_MASS_SPEED_URL = "https://api.weixin.qq.com/cgi-bin/message/mass/speed/set?access_token=ACCESS_TOKEN";

    /**
     * 根据标签进行群发【订阅号与服务号认证后均可用】
     *
     * @param json 群发消息数据 json 字符串
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject massTagSend(String json) {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = TAG_SEND_ALL_URL.replace("ACCESS_TOKEN", accessToken);

        String result = HttpUtils.post(url, json);

        return JSON.parseObject(result);
    }

    /**
     * 根据OpenID列表群发【订阅号不可用，服务号认证后可用】
     *
     * @param json 群发消息数据 json 字符串
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject massOpenIdSend(String json) {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = OPENID_SEND_URL.replace("ACCESS_TOKEN", accessToken);

        String result = HttpUtils.post(url, json);

        return JSON.parseObject(result);
    }

    /**
     * 删除群发【订阅号与服务号认证后均可用】
     *
     * @param msgId      发送出去的消息ID
     * @param articleIdx 要删除的文章在图文消息中的位置，第一篇编号为1，该字段不填或填0会删除全部文章
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject delete(Long msgId, Integer articleIdx) {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = DELETE_MASS_URL.replace("ACCESS_TOKEN", accessToken);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg_id", msgId);
        jsonObject.put("article_idx", articleIdx);

        String result = HttpUtils.post(url, jsonObject.toJSONString());

        return JSON.parseObject(result);
    }

    /**
     * 预览接口【订阅号与服务号认证后均可用】
     *
     * @param json 群发消息数据 json 字符串
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject preview(String json) {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = PREVIEW_MASS_URL.replace("ACCESS_TOKEN", accessToken);

        String result = HttpUtils.post(url, json);

        return JSON.parseObject(result);
    }

    /**
     * 预览接口【订阅号与服务号认证后均可用】
     *
     * @param msgId 群发消息后返回的消息id
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject get(Long msgId) {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = GET_MASS_STATUS_URL.replace("ACCESS_TOKEN", accessToken);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg_id", msgId);

        String result = HttpUtils.post(url, jsonObject.toJSONString());

        return JSON.parseObject(result);
    }

    /**
     * 获取群发速度
     *
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject getMassSpeed() {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = GET_MASS_SPEED_URL.replace("ACCESS_TOKEN", accessToken);

        String result = HttpUtils.post(url);

        return JSON.parseObject(result);
    }

    /**
     * 设置群发速度
     *
     * @param speed 群发速度的级别，是一个0到4的整数，数字越大表示群发速度越慢
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject setMassSpeed(Integer speed) {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = SET_MASS_SPEED_URL.replace("ACCESS_TOKEN", accessToken);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("speed", speed);

        String result = HttpUtils.post(url, jsonObject.toJSONString());

        return JSON.parseObject(result);
    }
}
