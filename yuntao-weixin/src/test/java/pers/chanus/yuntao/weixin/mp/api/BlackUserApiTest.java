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

import java.util.ArrayList;
import java.util.List;

/**
 * BlackUserApi 测试
 *
 * @author Chanus
 * @date 2020-05-18 16:16:24
 * @since 0.1.9
 */
public class BlackUserApiTest extends ApiConfigTest {
    @Test
    public void getBlackListTest() {
        JSONObject jsonObject1 = BlackUserApi.getBlackList();
        System.out.println(jsonObject1.toJSONString());

        String beginOpenid = "o9Q3g0c-95M7vLPzPY4iUfLyCLVs";
        JSONObject jsonObject2 = BlackUserApi.getBlackList(beginOpenid);
        System.out.println(jsonObject2.toJSONString());
    }

    @Test
    public void batchBlackUsersTest() {
        List<String> openIdList = new ArrayList<>();
        openIdList.add("o9Q3g0c-95M7vLPzPY4iUfLyCLVs");

        JSONObject jsonObject = BlackUserApi.batchBlackUsers(openIdList);
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void batchUnblackUsersTest() {
        List<String> openIdList = new ArrayList<>();
        openIdList.add("o9Q3g0c-95M7vLPzPY4iUfLyCLVs");

        JSONObject jsonObject = BlackUserApi.batchUnblackUsers(openIdList);
        System.out.println(jsonObject.toJSONString());
    }
}
