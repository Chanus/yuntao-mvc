/*
 * Copyright (c), 2020-persent, Chanus and/or its affiliates. All rights reserved.
 * FileName: BlackUserApi
 * Author:   Chanus
 * Date:     2020-05-18 16:12:36
 * Description: 黑名单管理 API
 * History:
 * <author>          <time>          <version>          <desc>
 */
package pers.chanus.yuntao.weixin.mp.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chanus.yuntao.utils.core.CollectionUtils;
import com.chanus.yuntao.utils.core.HttpUtils;
import com.chanus.yuntao.utils.core.StringUtils;

import java.util.List;

/**
 * 黑名单管理 API<br>
 * 详情请见：https://developers.weixin.qq.com/doc/offiaccount/User_Management/Manage_blacklist.html
 *
 * @author Chanus
 * @date 2020-05-18 16:12:36
 * @since 0.1.9
 */
public class BlackUserApi {
    /**
     * 获取公众号的黑名单列表 url，请求方式为 POST
     */
    private static final String GET_BLACK_LIST_URL = "https://api.weixin.qq.com/cgi-bin/tags/members/getblacklist?access_token=ACCESS_TOKEN";
    /**
     * 拉黑用户 url，请求方式为 POST
     */
    private static final String BATCH_BLACK_LIST_URL = "https://api.weixin.qq.com/cgi-bin/tags/members/batchblacklist?access_token=ACCESS_TOKEN";
    /**
     * 取消拉黑用户 url，请求方式为 POST
     */
    private static final String BATCH_UNBLACK_LIST_URL = "https://api.weixin.qq.com/cgi-bin/tags/members/batchunblacklist?access_token=ACCESS_TOKEN";

    /**
     * 获取公众号的黑名单列表
     *
     * @param beginOpenid 当 begin_openid 为空时，默认从开头拉取
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject getBlackList(String beginOpenid) {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = GET_BLACK_LIST_URL.replace("ACCESS_TOKEN", accessToken);

        JSONObject jsonObject = new JSONObject();
        if (StringUtils.isNotBlank(beginOpenid))
            jsonObject.put("begin_openid", beginOpenid);

        String result = HttpUtils.post(url, jsonObject.toJSONString());

        return JSON.parseObject(result);
    }

    /**
     * 获取公众号的黑名单列表
     *
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject getBlackList() {
        return getBlackList(null);
    }

    /**
     * 拉黑用户
     *
     * @param json json 字符串
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject batchBlackUsers(String json) {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = BATCH_BLACK_LIST_URL.replace("ACCESS_TOKEN", accessToken);

        String result = HttpUtils.post(url, json);

        return JSON.parseObject(result);
    }

    /**
     * 拉黑用户
     *
     * @param openIdList 需要拉入黑名单的用户的 openid，一次拉黑最多允许20个
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject batchBlackUsers(List<String> openIdList) throws IllegalArgumentException {
        if (CollectionUtils.isEmpty(openIdList)) {
            throw new IllegalArgumentException("需要拉入黑名单的用户的 openid 为空");
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("openid_list", openIdList);

        return batchBlackUsers(jsonObject.toJSONString());
    }

    /**
     * 取消拉黑用户
     *
     * @param json json 字符串
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject batchUnblackUsers(String json) {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = BATCH_UNBLACK_LIST_URL.replace("ACCESS_TOKEN", accessToken);

        String result = HttpUtils.post(url, json);

        return JSON.parseObject(result);
    }

    /**
     * 取消拉黑用户
     *
     * @param openIdList 需要取消拉黑的用户 openid，一次取消拉黑最多允许20个
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject batchUnblackUsers(List<String> openIdList) throws IllegalArgumentException {
        if (CollectionUtils.isEmpty(openIdList)) {
            throw new IllegalArgumentException("需要取消拉黑的用户 openid 为空");
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("openid_list", openIdList);

        return batchUnblackUsers(jsonObject.toJSONString());
    }
}
