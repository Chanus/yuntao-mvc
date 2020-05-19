/*
 * Copyright (c), 2020-persent, Chanus and/or its affiliates. All rights reserved.
 * FileName: MenuApiTest
 * Author:   Chanus
 * Date:     2020-05-19 09:16:22
 * Description: MenuApi 测试
 * History:
 * <author>          <time>          <version>          <desc>
 */
package pers.chanus.yuntao.weixin.mp.api;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import pers.chanus.yuntao.weixin.mp.api.bean.ApiConfig;
import pers.chanus.yuntao.weixin.mp.api.bean.Menu;

/**
 * MenuApi 测试
 *
 * @author Chanus
 * @date 2020-05-19 09:16:22
 * @since 0.1.9
 */
public class MenuApiTest {
    private String appId = "wxe1b1996ffb222290";
    private String appSecret = "3eff2d48cab4356dd096228528a1537c";

    @Test
    public void createMenuTest() {
        AccessTokenApi.apiConfig = new ApiConfig(appId, appSecret);
        // String json = "{\"button\":[{\"type\":\"click\",\"name\":\"今日歌曲\",\"key\":\"V1001_TODAY_MUSIC\"},{\"name\":\"菜单\",\"sub_button\":[{\"type\":\"view\",\"name\":\"搜索\",\"url\":\"http://www.soso.com/\"},{\"type\":\"click\",\"name\":\"赞一下我们\",\"key\":\"V1001_GOOD\"}]}]}";
        // JSONObject jsonObject = MenuApi.createMenu(json);
        String json = Menu.create().setButton("click", "今日歌曲", "V1001_TODAY_MUSIC", (String) null)
                .setButton("菜单", Menu.MenuButton.create().setSub_button("view", "搜索", "http://www.soso.com")
                        .setSub_button("click", "赞一下我们", "V1001_GOOD", (String) null).getSub_button()).toJSONString();
        System.out.println(json);

        JSONObject jsonObject = MenuApi.createMenu(json);
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void getCurrentSelfMenuInfoTest() {
        AccessTokenApi.apiConfig = new ApiConfig(appId, appSecret);
        JSONObject jsonObject = MenuApi.getCurrentSelfMenuInfo();
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void getMenuInfoTest() {
        AccessTokenApi.apiConfig = new ApiConfig(appId, appSecret);
        JSONObject jsonObject = MenuApi.getMenuInfo();
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void deleteMenuTest() {
        AccessTokenApi.apiConfig = new ApiConfig(appId, appSecret);
        JSONObject jsonObject = MenuApi.deleteMenu();
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void addConditionalTest() {
        AccessTokenApi.apiConfig = new ApiConfig(appId, appSecret);
        String json = Menu.create().setButton("click", "今日歌曲", "V1001_TODAY_MUSIC", (String) null)
                .setButton("菜单", Menu.MenuButton.create().setSub_button("view", "搜索", "http://www.soso.com")
                        .setSub_button("click", "赞一下我们", "V1001_GOOD", (String) null).getSub_button())
                .setMatchrule(Menu.MatchRule.create().setTag_id("2").setSex("1").setCountry("中国").setProvince("安徽")
                        .setCity("亳州").setClient_platform_type("2").setLanguage("zh_HK"))
                .toJSONString();
        System.out.println(json);
        JSONObject jsonObject = MenuApi.addConditional(json);
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void delConditionalTest() {
        AccessTokenApi.apiConfig = new ApiConfig(appId, appSecret);
        JSONObject jsonObject = MenuApi.delConditional("463833304");
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void tryMatchTest() {
        AccessTokenApi.apiConfig = new ApiConfig(appId, appSecret);
        JSONObject jsonObject = MenuApi.tryMatch("o-mAK55lxMjG-3Kd5wRtFtS__4rA");
        System.out.println(jsonObject.toJSONString());
    }
}
