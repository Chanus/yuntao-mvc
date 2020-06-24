/*
 * Copyright (c), 2020-persent, Chanus and/or its affiliates. All rights reserved.
 * FileName: UserTagApi
 * Author:   Chanus
 * Date:     2020-05-18 16:11:42
 * Description: 用户标签管理 API
 * History:
 * <author>          <time>          <version>          <desc>
 */
package pers.chanus.yuntao.weixin.mp.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chanus.yuntao.utils.core.HttpUtils;
import com.chanus.yuntao.utils.core.StringUtils;

import java.util.List;

/**
 * 用户标签管理 API<br>
 * 详情请见：https://developers.weixin.qq.com/doc/offiaccount/User_Management/User_Tag_Management.html
 *
 * @author Chanus
 * @date 2020-05-18 16:11:42
 * @since 0.1.9
 */
public class UserTagApi {
    /**
     * 创建标签 url，请求方式为 POST
     */
    private static final String CREATE_TAG_URL = "https://api.weixin.qq.com/cgi-bin/tags/create?access_token=ACCESS_TOKEN";
    /**
     * 获取公众号已创建的标签 url，请求方式为 GET
     */
    private static final String GET_TAG_URL = "https://api.weixin.qq.com/cgi-bin/tags/get?access_token=ACCESS_TOKEN";
    /**
     * 编辑标签 url，请求方式为 POST
     */
    private static final String UPDATE_TAG_URL = "https://api.weixin.qq.com/cgi-bin/tags/update?access_token=ACCESS_TOKEN";
    /**
     * 删除标签 url，请求方式为 POST
     */
    private static final String DELETE_TAG_URL = "https://api.weixin.qq.com/cgi-bin/tags/delete?access_token=ACCESS_TOKEN";
    /**
     * 获取标签下粉丝列表 url，请求方式为 POST
     */
    private static final String GET_TAG_USER_URL = "https://api.weixin.qq.com/cgi-bin/user/tag/get?access_token=ACCESS_TOKEN";
    /**
     * 批量为用户打标签 url，请求方式为 POST
     */
    private static final String BATCH_TAGGING_URL = "https://api.weixin.qq.com/cgi-bin/tags/members/batchtagging?access_token=ACCESS_TOKEN";
    /**
     * 批量为用户取消标签 url，请求方式为 POST
     */
    private static final String BATCH_UNTAGGING_URL = "https://api.weixin.qq.com/cgi-bin/tags/members/batchuntagging?access_token=ACCESS_TOKEN";
    /**
     * 获取用户身上的标签列表 url，请求方式为 POST
     */
    private static final String GET_USER_TAGS_URL = "https://api.weixin.qq.com/cgi-bin/tags/getidlist?access_token=ACCESS_TOKEN";

    /**
     * 创建标签
     *
     * @param name 标签名（30个字符以内）
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject create(String name) {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = CREATE_TAG_URL.replace("ACCESS_TOKEN", accessToken);

        JSONObject tagJson = new JSONObject();
        JSONObject tagNameJson = new JSONObject();
        tagNameJson.put("name", name);
        tagJson.put("tag", tagNameJson);

        String result = HttpUtils.post(url, tagJson.toJSONString());

        return JSON.parseObject(result);
    }

    /**
     * 获取公众号已创建的标签
     *
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject get() {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = GET_TAG_URL.replace("ACCESS_TOKEN", accessToken);

        String result = HttpUtils.get(url);

        return JSON.parseObject(result);
    }

    /**
     * 编辑标签
     *
     * @param id   标签id
     * @param name 标签名（30个字符以内）
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject update(int id, String name) {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = UPDATE_TAG_URL.replace("ACCESS_TOKEN", accessToken);

        JSONObject tagJson = new JSONObject();
        JSONObject tagNameJson = new JSONObject();
        tagNameJson.put("id", id);
        tagNameJson.put("name", name);
        tagJson.put("tag", tagNameJson);

        String result = HttpUtils.post(url, tagJson.toJSONString());

        return JSON.parseObject(result);
    }

    /**
     * 删除标签
     *
     * @param id 标签id
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject delete(int id) {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = DELETE_TAG_URL.replace("ACCESS_TOKEN", accessToken);

        JSONObject tagJson = new JSONObject();
        JSONObject tagIdJson = new JSONObject();
        tagIdJson.put("id", id);
        tagJson.put("tag", tagIdJson);

        String result = HttpUtils.post(url, tagJson.toJSONString());

        return JSON.parseObject(result);
    }

    /**
     * 获取标签下粉丝列表
     *
     * @param tagId      标签id
     * @param nextOpenId 第一个拉取的OPENID，不填默认从头开始拉取
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject getTagUsers(int tagId, String nextOpenId) {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = GET_TAG_USER_URL.replace("ACCESS_TOKEN", accessToken);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("tagid", tagId);
        if (StringUtils.isNotBlank(nextOpenId))
            jsonObject.put("next_openid", nextOpenId);

        String result = HttpUtils.post(url, jsonObject.toJSONString());

        return JSON.parseObject(result);
    }

    /**
     * 批量为用户打标签
     *
     * @param tagId      标签id
     * @param openIdList 粉丝列表
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject batchTagging(int tagId, List<String> openIdList) {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = BATCH_TAGGING_URL.replace("ACCESS_TOKEN", accessToken);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("tagid", tagId);
        jsonObject.put("openid_list", openIdList);

        String result = HttpUtils.post(url, jsonObject.toJSONString());

        return JSON.parseObject(result);
    }

    /**
     * 批量为用户取消标签
     *
     * @param tagId      标签id
     * @param openIdList 粉丝列表
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject batchUntagging(int tagId, List<String> openIdList) {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = BATCH_UNTAGGING_URL.replace("ACCESS_TOKEN", accessToken);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("tagid", tagId);
        jsonObject.put("openid_list", openIdList);

        String result = HttpUtils.post(url, jsonObject.toJSONString());

        return JSON.parseObject(result);
    }

    /**
     * 获取用户身上的标签列表
     *
     * @param openId 用户 openid
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject getUserTags(String openId) {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = GET_USER_TAGS_URL.replace("ACCESS_TOKEN", accessToken);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("openid", openId);

        String result = HttpUtils.post(url, jsonObject.toJSONString());

        return JSON.parseObject(result);
    }

}
