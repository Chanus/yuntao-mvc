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

import java.util.ArrayList;
import java.util.List;

/**
 * UserApi 测试
 *
 * @author Chanus
 * @date 2020-05-18 16:05:54
 * @since 0.1.9
 */
public class UserApiTest extends ApiConfigTest {
    @Test
    public void updateRemarkTest() {
        JSONObject jsonObject = UserApi.updateRemark("o9Q3g0c-95M7vLPzPY4iUfLyCLVs", "小胖");
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void getUserInfoTest() {
        JSONObject jsonObject = UserApi.getUserInfo("o9Q3g0c-95M7vLPzPY4iUfLyCLVs");
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void getUsersTest() {
        JSONObject jsonObject1 = UserApi.getUsers("o9Q3g0c-95M7vLPzPY4iUfLyCLVs");
        System.out.println(jsonObject1.toJSONString());
        JSONObject jsonObject2 = UserApi.getUsers();
        System.out.println(jsonObject2.toJSONString());
    }

    @Test
    public void batchGetUserInfoTest() {
        List<String> openIdList = new ArrayList<>();
        openIdList.add("o9Q3g0c-95M7vLPzPY4iUfLyCLVs");
        openIdList.add("o-mAK5_H-VujM2sT7SYs1pyJEEio");
        openIdList.add("o-mAK56WJbFNVxrptEbo80apufVc");
        openIdList.add("o-mAK55qfZGGZLh-BV4AG78sXgtQ");
        JSONObject jsonObject = UserApi.batchGetUserInfo(openIdList);
        System.out.println(jsonObject.toJSONString());
    }
}
