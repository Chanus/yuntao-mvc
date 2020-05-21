/*
 * Copyright (c), 2020-persent, Chanus and/or its affiliates. All rights reserved.
 * FileName: GroupsApiTest
 * Author:   Chanus
 * Date:     2020-05-21 22:42:41
 * Description: GroupsApi 测试
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
 * GroupsApi 测试
 *
 * @author Chanus
 * @date 2020-05-21 22:42:41
 * @since 0.1.9
 */
public class GroupsApiTest {
    private String appId = "wxe1b1996ffb222290";
    private String appSecret = "3eff2d48cab4356dd096228528a1537c";

    @Test
    public void createTest() {
        AccessTokenApi.apiConfig = new ApiConfig(appId, appSecret);
        JSONObject jsonObject = GroupsApi.create("安徽");
        System.out.println(jsonObject);
    }

    @Test
    public void getTest() {
        AccessTokenApi.apiConfig = new ApiConfig(appId, appSecret);
        JSONObject jsonObject = GroupsApi.get();
        System.out.println(jsonObject);
    }

    @Test
    public void getUserGroupIdTest() {
        AccessTokenApi.apiConfig = new ApiConfig(appId, appSecret);
        String openId = "o-mAK55lxMjG-3Kd5wRtFtS__4rA";
        JSONObject jsonObject = GroupsApi.getUserGroupId(openId);
        System.out.println(jsonObject);
    }

    @Test
    public void updateTest() {
        AccessTokenApi.apiConfig = new ApiConfig(appId, appSecret);
        JSONObject jsonObject = GroupsApi.update(103, "安徽");
        System.out.println(jsonObject);
    }

    @Test
    public void updateUserGroupsTest() {
        AccessTokenApi.apiConfig = new ApiConfig(appId, appSecret);
        String openId = "o-mAK55lxMjG-3Kd5wRtFtS__4rA";
        JSONObject jsonObject = GroupsApi.updateUserGroups(openId, 103);
        System.out.println(jsonObject);
    }

    @Test
    public void batchUpdateUserGroupsTest() {
        AccessTokenApi.apiConfig = new ApiConfig(appId, appSecret);
        List<String> openIdList = new ArrayList<>();
        openIdList.add("o-mAK5_H-VujM2sT7SYs1pyJEEio");
        openIdList.add("o-mAK56WJbFNVxrptEbo80apufVc");
        openIdList.add("o-mAK55qfZGGZLh-BV4AG78sXgtQ");
        JSONObject jsonObject = GroupsApi.batchUpdateUserGroups(openIdList, 102);
        System.out.println(jsonObject);
    }

    @Test
    public void deleteTest() {
        AccessTokenApi.apiConfig = new ApiConfig(appId, appSecret);
        JSONObject jsonObject = GroupsApi.delete(103);
        System.out.println(jsonObject);
    }
}
