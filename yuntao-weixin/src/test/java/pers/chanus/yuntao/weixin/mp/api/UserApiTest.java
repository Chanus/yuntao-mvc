/*
 * Copyright (c), 2020-persent, Chanus and/or its affiliates. All rights reserved.
 * FileName: UserApiTest
 * Author:   Chanus
 * Date:     2020-05-18 16:05:54
 * Description: UserApi 测试
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
 * UserApi 测试
 *
 * @author Chanus
 * @date 2020-05-18 16:05:54
 * @since 0.1.9
 */
public class UserApiTest {
    private String appId = "wxe1b1996ffb222290";
    private String appSecret = "3eff2d48cab4356dd096228528a1537c";

    @Test
    public void updateRemarkTest() {
        AccessTokenApi.apiConfig = new ApiConfig(appId, appSecret);
        JSONObject jsonObject = UserApi.updateRemark("o-mAK55lxMjG-3Kd5wRtFtS__4rA", "小胖");
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void getUserInfoTest() {
        AccessTokenApi.apiConfig = new ApiConfig(appId, appSecret);
        JSONObject jsonObject = UserApi.getUserInfo("o-mAK55lxMjG-3Kd5wRtFtS__4rA");
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void getUsersTest() {
        AccessTokenApi.apiConfig = new ApiConfig(appId, appSecret);
        JSONObject jsonObject1 = UserApi.getUsers("o-mAK55lxMjG-3Kd5wRtFtS__4rA");
        System.out.println(jsonObject1.toJSONString());
        JSONObject jsonObject2 = UserApi.getUsers();
        System.out.println(jsonObject2.toJSONString());
    }

    @Test
    public void batchGetUserInfoTest() {
        AccessTokenApi.apiConfig = new ApiConfig(appId, appSecret);
        List<String> openIdList = new ArrayList<>();
        openIdList.add("o-mAK55lxMjG-3Kd5wRtFtS__4rA");
        openIdList.add("o-mAK5_H-VujM2sT7SYs1pyJEEio");
        openIdList.add("o-mAK56WJbFNVxrptEbo80apufVc");
        openIdList.add("o-mAK55qfZGGZLh-BV4AG78sXgtQ");
        JSONObject jsonObject = UserApi.batchGetUserInfo(openIdList);
        System.out.println(jsonObject.toJSONString());
    }
}
