/*
 * Copyright (c), 2020-persent, Chanus and/or its affiliates. All rights reserved.
 * FileName: UserApi
 * Author:   Chanus
 * Date:     2020-05-18 15:58:49
 * Description: 用户管理 API
 * History:
 * <author>          <time>          <version>          <desc>
 */
package pers.chanus.yuntao.weixin.mp.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chanus.yuntao.utils.core.CollectionUtils;
import com.chanus.yuntao.utils.core.HttpUtils;
import com.chanus.yuntao.utils.core.StringUtils;
import com.chanus.yuntao.utils.core.UrlUtils;

import java.util.List;

/**
 * 用户管理 API<br>
 * 详情请见：
 * <pre>
 *     设置用户备注名：https://developers.weixin.qq.com/doc/offiaccount/User_Management/Configuring_user_notes.html
 *     获取用户基本信息(UnionID机制)：https://developers.weixin.qq.com/doc/offiaccount/User_Management/Get_users_basic_information_UnionID.html#UinonId
 *     获取用户列表：https://developers.weixin.qq.com/doc/offiaccount/User_Management/Getting_a_User_List.html
 *     批量获取用户基本信息：http://caibaojian.com/wxwiki/3c3151152c9e627a42e5728e219da4763d0b2d32.html
 * </pre>
 *
 * @author Chanus
 * @date 2020-05-18 15:58:49
 * @since 0.1.9
 */
public class UserApi {
    /**
     * 设置用户备注名 url，请求方式为 POST
     */
    private static final String UPDATE_REMARK_URL = "https://api.weixin.qq.com/cgi-bin/user/info/updateremark?access_token=ACCESS_TOKEN";
    /**
     * 获取用户基本信息（包括UnionID机制） url，请求方式为 GET
     */
    private static final String USER_INFO_URL = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
    /**
     * 获取用户列表 url，请求方式为 GET
     */
    private static final String USER_LIST_URL = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN";
    /**
     * 批量获取用户基本信息 url，请求方式为 POST
     */
    private static final String BATCH_USER_INFO_URL = "https://api.weixin.qq.com/cgi-bin/user/info/batchget?access_token=ACCESS_TOKEN";

    /**
     * 设置用户备注名
     *
     * @param openId 用户标识
     * @param remark 新的备注名，长度必须小于30字符
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject updateRemark(String openId, String remark) {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = UPDATE_REMARK_URL.replace("ACCESS_TOKEN", accessToken);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("openid", openId);
        jsonObject.put("remark", remark);

        String result = HttpUtils.post(url, jsonObject.toJSONString());

        return JSON.parseObject(result);
    }

    /**
     * 获取用户基本信息（包括UnionID机制）
     *
     * @param openId 普通用户的标识，对当前公众号唯一
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject getUserInfo(String openId) {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = USER_INFO_URL.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);

        String result = HttpUtils.get(url);

        return JSON.parseObject(result);
    }

    /**
     * 获取用户列表
     *
     * @param nextOpenid 第一个拉取的 OPENID，不填默认从头开始拉取
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject getUsers(String nextOpenid) {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = USER_LIST_URL.replace("ACCESS_TOKEN", accessToken);
        if (StringUtils.isNotBlank(nextOpenid))
            url = UrlUtils.setParam(url, "next_openid", nextOpenid);

        String result = HttpUtils.get(url);

        return JSON.parseObject(result);
    }

    /**
     * 获取用户列表
     *
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject getUsers() {
        return getUsers(null);
    }

    /**
     * 批量获取用户基本信息
     *
     * @param json json 字符串
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject batchGetUserInfo(String json) {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = BATCH_USER_INFO_URL.replace("ACCESS_TOKEN", accessToken);

        String result = HttpUtils.post(url, json);

        return JSON.parseObject(result);
    }

    /**
     * 批量获取用户基本信息
     *
     * @param openIdList openid 列表
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject batchGetUserInfo(List<String> openIdList) {
        JSONObject jsonObject = new JSONObject();

        if (!CollectionUtils.isEmpty(openIdList)) {
            JSONArray jsonArray = new JSONArray();
            openIdList.forEach(openId -> {
                JSONObject json = new JSONObject();
                json.put("openid", openId);
                json.put("lang", "zh_CN");
                jsonArray.add(json);
            });
            jsonObject.put("user_list", jsonArray);
        }

        return batchGetUserInfo(jsonObject.toJSONString());
    }
}
