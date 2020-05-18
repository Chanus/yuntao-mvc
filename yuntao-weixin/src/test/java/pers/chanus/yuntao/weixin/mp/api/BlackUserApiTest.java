/*
 * Copyright (c), 2020-persent, Chanus and/or its affiliates. All rights reserved.
 * FileName: BlackUserApiTest
 * Author:   Chanus
 * Date:     2020-05-18 16:16:24
 * Description: BlackUserApi 测试
 * History:
 * <author>          <time>          <version>          <desc>
 */
package pers.chanus.yuntao.weixin.mp.api;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import pers.chanus.yuntao.weixin.mp.api.bean.ApiConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * BlackUserApi 测试
 *
 * @author Chanus
 * @date 2020-05-18 16:16:24
 * @since 0.1.9
 */
public class BlackUserApiTest {
    private String appId = "wxe1b1996ffb222290";
    private String appSecret = "3eff2d48cab4356dd096228528a1537c";

    @Test
    public void getBlackListTest() {
        AccessTokenApi.apiConfig = new ApiConfig(appId, appSecret);
        JSONObject jsonObject1 = BlackUserApi.getBlackList();
        System.out.println(jsonObject1.toJSONString());

        String beginOpenid = "o-mAK55lxMjG-3Kd5wRtFtS__4rA";
        JSONObject jsonObject2 = BlackUserApi.getBlackList(beginOpenid);
        System.out.println(jsonObject2.toJSONString());
    }

    @Test
    public void batchBlackUsersTest() {
        AccessTokenApi.apiConfig = new ApiConfig(appId, appSecret);
        List<String> openIdList = new ArrayList<>();
        openIdList.add("o-mAK55lxMjG-3Kd5wRtFtS__4rA");

        JSONObject jsonObject = BlackUserApi.batchBlackUsers(openIdList);
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void batchUnblackUsersTest() {
        AccessTokenApi.apiConfig = new ApiConfig(appId, appSecret);
        List<String> openIdList = new ArrayList<>();
        openIdList.add("o-mAK55lxMjG-3Kd5wRtFtS__4rA");

        JSONObject jsonObject = BlackUserApi.batchUnblackUsers(openIdList);
        System.out.println(jsonObject.toJSONString());
    }
}
