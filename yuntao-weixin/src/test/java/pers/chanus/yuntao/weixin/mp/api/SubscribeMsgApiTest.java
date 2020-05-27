/*
 * Copyright (c), 2020-persent, Chanus and/or its affiliates. All rights reserved.
 * FileName: SubscribeMsgApiTest
 * Author:   Chanus
 * Date:     2020-05-27 13:15:38
 * Description: SubscribeMsgApi 测试
 * History:
 * <author>          <time>          <version>          <desc>
 */
package pers.chanus.yuntao.weixin.mp.api;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import pers.chanus.yuntao.weixin.mp.api.bean.ApiConfig;
import pers.chanus.yuntao.weixin.mp.api.bean.TemplateMessage;

import java.io.UnsupportedEncodingException;

/**
 * SubscribeMsgApi 测试
 *
 * @author Chanus
 * @date 2020-05-27 13:15:38
 * @since 0.1.9
 */
public class SubscribeMsgApiTest {
    private String appId = "wxe1b1996ffb222290";
    private String appSecret = "3eff2d48cab4356dd096228528a1537c";

    @Test
    public void getAuthorizeURLTest() throws UnsupportedEncodingException {
        AccessTokenApi.apiConfig = new ApiConfig(appId, appSecret);
        String url = SubscribeMsgApi.getAuthorizeURL(1000, "rFpUW9knKNbu1by6EYd80OnfqNGhMGrr4JLAtjfMnPA",
                "http://t.liulianhuan.com/teacher/register?schoolCode=350524001", "test");

        System.out.println(url);
    }

    @Test
    public void subscribeTest() {
        AccessTokenApi.apiConfig = new ApiConfig(appId, appSecret);
        String json = TemplateMessage.create().setTouser("o-mAK55lxMjG-3Kd5wRtFtS__4rA")
                .setTemplate_id("rFpUW9knKNbu1by6EYd80OnfqNGhMGrr4JLAtjfMnPA")
                .setUrl("http://t.liulianhuan.com/teacher/register?schoolCode=350524001")
                .setScene("1000").setTitle("一次性订阅消息")
                .setSubscribeData("一次性订阅消息")
                .toJSONString();

        System.out.println(json);

        JSONObject jsonObject = SubscribeMsgApi.subscribe(json);
        System.out.println(jsonObject.toJSONString());
    }
}
