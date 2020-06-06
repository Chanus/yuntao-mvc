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
import pers.chanus.yuntao.weixin.mp.api.bean.TemplateMessage;

import java.io.UnsupportedEncodingException;

/**
 * SubscribeMsgApi 测试
 *
 * @author Chanus
 * @date 2020-05-27 13:15:38
 * @since 0.1.9
 */
public class SubscribeMsgApiTest extends ApiConfigTest {
    @Test
    public void getAuthorizeURLTest() throws UnsupportedEncodingException {
        String url = SubscribeMsgApi.getAuthorizeURL(1000, "rFpUW9knKNbu1by6EYd80OnfqNGhMGrr4JLAtjfMnPA",
                "http://t.liulianhuan.com/teacher/register?schoolCode=350524001", "test");

        System.out.println(url);
    }

    @Test
    public void subscribeTest() {
        String json = TemplateMessage.create().setTouser("o9Q3g0c-95M7vLPzPY4iUfLyCLVs")
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
