/*
 * Copyright (c), 2020-persent, Chanus and/or its affiliates. All rights reserved.
 * FileName: MessageApi
 * Author:   Chanus
 * Date:     2020-05-22 21:28:24
 * Description: MessageApi 测试
 * History:
 * <author>          <time>          <version>          <desc>
 */
package pers.chanus.yuntao.weixin.mp.api;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import pers.chanus.yuntao.weixin.mp.api.bean.ApiConfig;
import pers.chanus.yuntao.weixin.mp.api.bean.mass.MassTagMessage;

import java.util.Arrays;

/**
 * MessageApi 测试
 *
 * @author Chanus
 * @date 2020-05-22 21:28:24
 * @since 0.1.9
 */
public class MessageApiTest {
    private String appId = "wxe1b1996ffb222290";
    private String appSecret = "3eff2d48cab4356dd096228528a1537c";

    @Test
    public void massTagSendTest() {
        String mpnewsJson = MassTagMessage.create(104, "mpnews")
                .setMpnews("123dsdajkasd231jhksad")
                .setSend_ignore_reprint(0)
                .toJSONString();
        System.out.println(mpnewsJson);

        String textJson = MassTagMessage.create(true, 104, "text")
                .setText("群发一条测试文本消息").setMsgtype("text")
                .toJSONString();
        System.out.println(textJson);

        String voiceJson = MassTagMessage.create(104, "voice")
                .setVoice("123dsdajkasd231jhksad")
                .toJSONString();
        System.out.println(voiceJson);

        String imagesJson = MassTagMessage.create(104, "image")
                .setImages(Arrays.asList("aaa", "bbb", "ccc"), "xxx", 1, 0)
                .toJSONString();
        System.out.println(imagesJson);

        String mpvideoJson = MassTagMessage.create(104, "mpvideo")
                .setMpvideo("IhdaAQXuvJtGzwwc0abfXnzeezfO0NgPK6AQYShD8RQYMTtfzbLdBIQkQziv2XJc")
                .toJSONString();
        System.out.println(mpvideoJson);

        String wxcardJson = MassTagMessage.create(104, "wxcard")
                .setWxcard("123dsdajkasd231jhksad")
                .toJSONString();
        System.out.println(wxcardJson);

        AccessTokenApi.apiConfig = new ApiConfig(appId, appSecret);
        JSONObject jsonObject = MessageApi.massTagSend(textJson);
        System.out.println(jsonObject.toJSONString());
    }
}
