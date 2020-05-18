/*
 * Copyright (c), 2020-persent, Chanus and/or its affiliates. All rights reserved.
 * FileName: TemplateMsgApiTest
 * Author:   Chanus
 * Date:     2020-05-18 14:47:15
 * Description: TemplateMsgApi 测试
 * History:
 * <author>          <time>          <version>          <desc>
 */
package pers.chanus.yuntao.weixin.mp.api;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import pers.chanus.yuntao.util.DateUtils;
import pers.chanus.yuntao.weixin.mp.api.bean.ApiConfig;
import pers.chanus.yuntao.weixin.mp.api.bean.TemplateData;

/**
 * TemplateMsgApi 测试
 *
 * @author Chanus
 * @date 2020-05-18 14:47:15
 * @since 0.1.9
 */
public class TemplateMsgApiTest {
    private String appId = "wxe1b1996ffb222290";
    private String appSecret = "3eff2d48cab4356dd096228528a1537c";

    @Test
    public void setIndustryTest() {
        AccessTokenApi.apiConfig = new ApiConfig(appId, appSecret);
        JSONObject jsonObject = TemplateMsgApi.setIndustry("1", "17");
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void getIndustryTest() {
        AccessTokenApi.apiConfig = new ApiConfig(appId, appSecret);
        JSONObject jsonObject = TemplateMsgApi.getIndustry();
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void getTemplateIdTest() {
        AccessTokenApi.apiConfig = new ApiConfig(appId, appSecret);
        JSONObject jsonObject = TemplateMsgApi.getTemplateId("TM00303");
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void getAllTemplateTest() {
        AccessTokenApi.apiConfig = new ApiConfig(appId, appSecret);
        JSONObject jsonObject = TemplateMsgApi.getAllTemplate();
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void delTemplateTest() {
        AccessTokenApi.apiConfig = new ApiConfig(appId, appSecret);
        JSONObject jsonObject = TemplateMsgApi.delTemplate("uYne7qJpCvEbyQCJkZ9wz9axFo8C9bsXs1HgY0bjROU");
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void sendTest() {
        AccessTokenApi.apiConfig = new ApiConfig(appId, appSecret);
        String json = TemplateData.create().setTouser("o-mAK55lxMjG-3Kd5wRtFtS__4rA")
                .setTemplate_id("6SihCiqY8XBgK-OUto2MHnA4c8ump7kT55rd-PHyEzo")
                .setUrl("http://t.liulianhuan.com/teacher/register?schoolCode=350524001")
                .setData("first", "老师注册")
                .setData("keyword1", "安溪一中")
                .setData("keyword2", DateUtils.getDateTimeToday())
                .setData("remark", "请点击注册，感谢您的支持。", "#FF0000")
                .toJSONString();

        JSONObject jsonObject = TemplateMsgApi.send(json);
        System.out.println(jsonObject.toJSONString());
    }
}
