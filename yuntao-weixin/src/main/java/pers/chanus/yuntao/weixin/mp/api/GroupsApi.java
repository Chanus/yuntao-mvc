/*
 * Copyright (c), 2020-persent, Chanus and/or its affiliates. All rights reserved.
 * FileName: GroupsApi
 * Author:   Chanus
 * Date:     2020-05-21 22:09:43
 * Description: 用户分组管理 API
 * History:
 * <author>          <time>          <version>          <desc>
 */
package pers.chanus.yuntao.weixin.mp.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import pers.chanus.yuntao.util.HttpUtils;

import java.util.List;

/**
 * 用户分组管理 API<br>
 * 详情请见：http://caibaojian.com/wxwiki/17277e71008e8e728c73e47414008b9dff5ae94b.html
 *
 * @author Chanus
 * @date 2020-05-21 22:09:43
 * @since 0.1.9
 */
public class GroupsApi {
    /**
     * 创建分组 url，请求方式为 POST
     */
    private static final String CREATE_GROUPS_URL = "https://api.weixin.qq.com/cgi-bin/groups/create?access_token=ACCESS_TOKEN";
    /**
     * 查询所有分组 url，请求方式为 GET
     */
    private static final String GET_GROUPS_URL = "https://api.weixin.qq.com/cgi-bin/groups/get?access_token=ACCESS_TOKEN";
    /**
     * 查询用户所在分组 url，请求方式为 POST
     */
    private static final String GET_USER_GROUPS_URL = "https://api.weixin.qq.com/cgi-bin/groups/getid?access_token=ACCESS_TOKEN";
    /**
     * 修改分组名 url，请求方式为 POST
     */
    private static final String UPDATE_GROUPS_URL = "https://api.weixin.qq.com/cgi-bin/groups/update?access_token=ACCESS_TOKEN";
    /**
     * 移动用户分组 url，请求方式为 POST
     */
    private static final String UPDATE_USER_GROUPS_URL = "https://api.weixin.qq.com/cgi-bin/groups/members/update?access_token=ACCESS_TOKEN";
    /**
     * 批量移动用户分组 url，请求方式为 POST
     */
    private static final String BATCH_UPDATE_USER_GROUPS_URL = "https://api.weixin.qq.com/cgi-bin/groups/members/batchupdate?access_token=ACCESS_TOKEN";
    /**
     * 删除分组 url，请求方式为 POST
     */
    private static final String DELETE_GROUPS_URL = "https://api.weixin.qq.com/cgi-bin/groups/delete?access_token=ACCESS_TOKEN";

    /**
     * 创建分组，一个公众账号，最多支持创建100个分组
     *
     * @param name 分组名字（30个字符以内）
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject create(String name) {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = CREATE_GROUPS_URL.replace("ACCESS_TOKEN", accessToken);

        JSONObject groupJson = new JSONObject();
        JSONObject groupNameJson = new JSONObject();
        groupNameJson.put("name", name);
        groupJson.put("group", groupNameJson);

        String result = HttpUtils.post(url, groupJson.toJSONString());

        return JSON.parseObject(result);
    }

    /**
     * 查询所有分组
     *
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject get() {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = GET_GROUPS_URL.replace("ACCESS_TOKEN", accessToken);

        String result = HttpUtils.get(url);

        return JSON.parseObject(result);
    }

    /**
     * 查询用户所在分组，通过用户的 OpenID 查询其所在的 GroupID
     *
     * @param openId 用户的OpenID
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject getUserGroupId(String openId) {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = GET_USER_GROUPS_URL.replace("ACCESS_TOKEN", accessToken);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("openid", openId);

        String result = HttpUtils.post(url, jsonObject.toJSONString());

        return JSON.parseObject(result);
    }

    /**
     * 修改分组名
     *
     * @param id   分组id，由微信分配
     * @param name 分组名字（30个字符以内）
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject update(int id, String name) {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = UPDATE_GROUPS_URL.replace("ACCESS_TOKEN", accessToken);

        JSONObject groupJson = new JSONObject();
        JSONObject groupNameJson = new JSONObject();
        groupNameJson.put("id", id);
        groupNameJson.put("name", name);
        groupJson.put("group", groupNameJson);

        String result = HttpUtils.post(url, groupJson.toJSONString());

        return JSON.parseObject(result);
    }

    /**
     * 移动用户分组
     *
     * @param openId    用户唯一标识符
     * @param toGroupId 分组id
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject updateUserGroups(String openId, int toGroupId) {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = UPDATE_USER_GROUPS_URL.replace("ACCESS_TOKEN", accessToken);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("openid", openId);
        jsonObject.put("to_groupid", toGroupId);

        String result = HttpUtils.post(url, jsonObject.toJSONString());

        return JSON.parseObject(result);
    }

    /**
     * 批量移动用户分组
     *
     * @param openIdList 用户唯一标识符openid的列表（size不能超过50）
     * @param toGroupId  分组id
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject batchUpdateUserGroups(List<String> openIdList, int toGroupId) {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = BATCH_UPDATE_USER_GROUPS_URL.replace("ACCESS_TOKEN", accessToken);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("openid_list", openIdList);
        jsonObject.put("to_groupid", toGroupId);

        String result = HttpUtils.post(url, jsonObject.toJSONString());

        return JSON.parseObject(result);
    }

    /**
     * 删除分组，注意本接口是删除一个用户分组，删除分组后，所有该分组内的用户自动进入默认分组
     *
     * @param id 分组的id
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject delete(int id) {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = DELETE_GROUPS_URL.replace("ACCESS_TOKEN", accessToken);

        JSONObject groupJson = new JSONObject();
        JSONObject groupIdJson = new JSONObject();
        groupIdJson.put("id", id);
        groupJson.put("group", groupIdJson);

        String result = HttpUtils.post(url, groupJson.toJSONString());

        return JSON.parseObject(result);
    }
}
