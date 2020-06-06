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

import java.util.ArrayList;
import java.util.List;

/**
 * GroupsApi 测试
 *
 * @author Chanus
 * @date 2020-05-21 22:42:41
 * @since 0.1.9
 */
public class GroupsApiTest extends ApiConfigTest {
    @Test
    public void createTest() {
        JSONObject jsonObject = GroupsApi.create("安徽");
        System.out.println(jsonObject);
    }

    @Test
    public void getTest() {
        JSONObject jsonObject = GroupsApi.get();
        System.out.println(jsonObject);
    }

    @Test
    public void getUserGroupIdTest() {
        String openId = "o9Q3g0c-95M7vLPzPY4iUfLyCLVs";
        JSONObject jsonObject = GroupsApi.getUserGroupId(openId);
        System.out.println(jsonObject);
    }

    @Test
    public void updateTest() {
        JSONObject jsonObject = GroupsApi.update(103, "安徽");
        System.out.println(jsonObject);
    }

    @Test
    public void updateUserGroupsTest() {
        String openId = "o9Q3g0c-95M7vLPzPY4iUfLyCLVs";
        JSONObject jsonObject = GroupsApi.updateUserGroups(openId, 103);
        System.out.println(jsonObject);
    }

    @Test
    public void batchUpdateUserGroupsTest() {
        List<String> openIdList = new ArrayList<>();
        openIdList.add("o-mAK5_H-VujM2sT7SYs1pyJEEio");
        openIdList.add("o-mAK56WJbFNVxrptEbo80apufVc");
        openIdList.add("o-mAK55qfZGGZLh-BV4AG78sXgtQ");
        JSONObject jsonObject = GroupsApi.batchUpdateUserGroups(openIdList, 102);
        System.out.println(jsonObject);
    }

    @Test
    public void deleteTest() {
        JSONObject jsonObject = GroupsApi.delete(103);
        System.out.println(jsonObject);
    }
}
