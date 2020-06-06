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
import pers.chanus.yuntao.weixin.mp.api.bean.Menu;

/**
 * MenuApi 测试
 *
 * @author Chanus
 * @date 2020-05-19 09:16:22
 * @since 0.1.9
 */
public class MenuApiTest extends ApiConfigTest {
    @Test
    public void createMenuTest() {
        // String json = "{\"button\":[{\"type\":\"click\",\"name\":\"今日歌曲\",\"key\":\"V1001_TODAY_MUSIC\"},{\"name\":\"菜单\",\"sub_button\":[{\"type\":\"view\",\"name\":\"搜索\",\"url\":\"http://www.soso.com/\"},{\"type\":\"click\",\"name\":\"赞一下我们\",\"key\":\"V1001_GOOD\"}]}]}";
        // JSONObject jsonObject = MenuApi.createMenu(json);
        String json = Menu.create().setClickButton("今日歌曲", "V1001_TODAY_MUSIC")
                .setButton("菜单", Menu.MenuButton.create().setViewSubButton("搜索", "http://www.soso.com")
                        .setClickSubButton("赞一下我们", "V1001_GOOD").getSub_button()).toJSONString();
        System.out.println(json);

        JSONObject jsonObject = MenuApi.createMenu(json);
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void getCurrentSelfMenuInfoTest() {
        JSONObject jsonObject = MenuApi.getCurrentSelfMenuInfo();
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void getMenuInfoTest() {
        JSONObject jsonObject = MenuApi.getMenuInfo();
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void deleteMenuTest() {
        JSONObject jsonObject = MenuApi.deleteMenu();
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void addConditionalTest() {
        String json = Menu.create().setClickButton("今日歌曲", "V1001_TODAY_MUSIC")
                .setButton("菜单", Menu.MenuButton.create().setViewSubButton("搜索", "http://www.soso.com")
                        .setClickSubButton("赞一下我们", "V1001_GOOD").getSub_button())
                .setMatchrule(Menu.MatchRule.create().setTag_id("2").setSex("1").setCountry("中国").setProvince("安徽")
                        .setCity("亳州").setClient_platform_type("2").setLanguage("zh_HK"))
                .toJSONString();
        System.out.println(json);
        JSONObject jsonObject = MenuApi.addConditional(json);
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void delConditionalTest() {
        JSONObject jsonObject = MenuApi.delConditional("463833304");
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void tryMatchTest() {
        JSONObject jsonObject = MenuApi.tryMatch("o9Q3g0c-95M7vLPzPY4iUfLyCLVs");
        System.out.println(jsonObject.toJSONString());
    }
}
