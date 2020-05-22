/*
 * Copyright (c), 2020-persent, Chanus and/or its affiliates. All rights reserved.
 * FileName: UserTagApiTest
 * Author:   Chanus
 * Date:     2020-05-18 16:15:50
 * Description: UserTagApi 测试
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
 * UserTagApi 测试
 *
 * @author Chanus
 * @date 2020-05-18 16:15:50
 * @since 0.1.9
 */
public class UserTagApiTest {
    private String appId = "wxe1b1996ffb222290";
    private String appSecret = "3eff2d48cab4356dd096228528a1537c";

    @Test
    public void createTest() {
        AccessTokenApi.apiConfig = new ApiConfig(appId, appSecret);
        JSONObject jsonObject = UserTagApi.create("福建");
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void getTest() {
        AccessTokenApi.apiConfig = new ApiConfig(appId, appSecret);
        JSONObject jsonObject = UserTagApi.get();
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void updateTest() {
        AccessTokenApi.apiConfig = new ApiConfig(appId, appSecret);
        JSONObject jsonObject = UserTagApi.update(101, "安徽");
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void deleteTest() {
        AccessTokenApi.apiConfig = new ApiConfig(appId, appSecret);
        JSONObject jsonObject = UserTagApi.delete(100);
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void getTagUsersTest() {
        AccessTokenApi.apiConfig = new ApiConfig(appId, appSecret);
        JSONObject jsonObject = UserTagApi.getTagUsers(100, null);
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void batchTaggingTest() {
        AccessTokenApi.apiConfig = new ApiConfig(appId, appSecret);
        List<String> openIdList = new ArrayList<>();
        openIdList.add("o-mAK55lxMjG-3Kd5wRtFtS__4rA");
        openIdList.add("o-mAK5_H-VujM2sT7SYs1pyJEEio");
        openIdList.add("o-mAK56WJbFNVxrptEbo80apufVc");
        openIdList.add("o-mAK55qfZGGZLh-BV4AG78sXgtQ");
        JSONObject jsonObject = UserTagApi.batchTagging(104, openIdList);
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void batchUntaggingTest() {
        AccessTokenApi.apiConfig = new ApiConfig(appId, appSecret);
        List<String> openIdList = new ArrayList<>();
        openIdList.add("o-mAK55lxMjG-3Kd5wRtFtS__4rA");
        openIdList.add("o-mAK5_H-VujM2sT7SYs1pyJEEio");
        openIdList.add("o-mAK56WJbFNVxrptEbo80apufVc");
        openIdList.add("o-mAK55qfZGGZLh-BV4AG78sXgtQ");
        JSONObject jsonObject = UserTagApi.batchUntagging(100, openIdList);
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void getUserTagsTest() {
        AccessTokenApi.apiConfig = new ApiConfig(appId, appSecret);
        JSONObject jsonObject = UserTagApi.getUserTags("o-mAK56WJbFNVxrptEbo80apufVc");
        System.out.println(jsonObject.toJSONString());
    }
}
