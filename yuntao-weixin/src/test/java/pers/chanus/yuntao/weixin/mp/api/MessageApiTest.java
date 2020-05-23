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
import pers.chanus.yuntao.weixin.mp.api.bean.MassMessage;
import pers.chanus.yuntao.weixin.mp.api.bean.MassPreviewMessage;

import java.util.Arrays;
import java.util.List;

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
        String mpnewsJson = MassMessage.create(104).setMpnews("123dsdajkasd231jhksad").setSend_ignore_reprint(0)
                .toJSONString();
        System.out.println(mpnewsJson);

        String textJson = MassMessage.create(true).setText("群发一条测试文本消息")
                .toJSONString();
        System.out.println(textJson);

        String voiceJson = MassMessage.create(104).setVoice("123dsdajkasd231jhksad")
                .toJSONString();
        System.out.println(voiceJson);

        String imagesJson = MassMessage.create(104).setImages(Arrays.asList("aaa", "bbb", "ccc"), "xxx", 1, 0)
                .toJSONString();
        System.out.println(imagesJson);

        String mpvideoJson = MassMessage.create(104).setMpvideo("IhdaAQXuvJtGzwwc0abfXnzeezfO0NgPK6AQYShD8RQYMTtfzbLdBIQkQziv2XJc")
                .toJSONString();
        System.out.println(mpvideoJson);

        String wxcardJson = MassMessage.create(104).setWxcard("123dsdajkasd231jhksad")
                .toJSONString();
        System.out.println(wxcardJson);

        AccessTokenApi.apiConfig = new ApiConfig(appId, appSecret);
        JSONObject jsonObject = MassMessageApi.massTagSend(textJson);
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void massOpenIdSendTest() {
        List<String> touser = Arrays.asList("o-mAK55lxMjG-3Kd5wRtFtS__4rA", "o-mAK5_H-VujM2sT7SYs1pyJEEio");
        String mpnewsJson = MassMessage.create(touser).setMpnews("123dsdajkasd231jhksad").setSend_ignore_reprint(0)
                .toJSONString();
        System.out.println(mpnewsJson);

        String textJson = MassMessage.create(touser).setText("群发一条测试文本消息，通过openID").setMsgtype("text").setClientmsgid("testagin")
                .toJSONString();
        System.out.println(textJson);

        String voiceJson = MassMessage.create(touser).setVoice("123dsdajkasd231jhksad")
                .toJSONString();
        System.out.println(voiceJson);

        String imagesJson = MassMessage.create(touser).setImages(Arrays.asList("aaa", "bbb", "ccc"), "xxx", 1, 0)
                .toJSONString();
        System.out.println(imagesJson);

        String mpvideoJson = MassMessage.create(touser).setMpvideo("IhdaAQXuvJtGzwwc0abfXnzeezfO0NgPK6AQYShD8RQYMTtfzbLdBIQkQziv2XJc")
                .toJSONString();
        System.out.println(mpvideoJson);

        String wxcardJson = MassMessage.create(touser).setWxcard("123dsdajkasd231jhksad")
                .toJSONString();
        System.out.println(wxcardJson);

        AccessTokenApi.apiConfig = new ApiConfig(appId, appSecret);
        JSONObject jsonObject = MassMessageApi.massOpenIdSend(textJson);
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void deleteTest() {
        AccessTokenApi.apiConfig = new ApiConfig(appId, appSecret);
        JSONObject jsonObject = MassMessageApi.delete(3147483653L, null);
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void previewTest() {
        String mpnewsJson = MassPreviewMessage.create("o-mAK55lxMjG-3Kd5wRtFtS__4rA")
                .setMpnews("123dsdajkasd231jhksad").toJSONString();
        System.out.println(mpnewsJson);

        String textJson = MassPreviewMessage.create("o-mAK55lxMjG-3Kd5wRtFtS__4rA")
                .setText("CONTENT").toJSONString();
        System.out.println(textJson);

        String voiceJson = MassPreviewMessage.create("o-mAK55lxMjG-3Kd5wRtFtS__4rA")
                .setVoice("123dsdajkasd231jhksad").toJSONString();
        System.out.println(voiceJson);

        String imageJson = MassPreviewMessage.create("o-mAK55lxMjG-3Kd5wRtFtS__4rA")
                .setImage("123dsdajkasd231jhksad").toJSONString();
        System.out.println(imageJson);

        String mpvideoJson = MassPreviewMessage.create("o-mAK55lxMjG-3Kd5wRtFtS__4rA")
                .setMpvideo("IhdaAQXuvJtGzwwc0abfXnzeezfO0NgPK6AQYShD8RQYMTtfzbLdBIQkQziv2XJc").toJSONString();
        System.out.println(mpvideoJson);

        String wxcardJson = MassPreviewMessage.create("o-mAK55lxMjG-3Kd5wRtFtS__4rA")
                .setWxcard("123dsdajkasd231jhksad", "{\"code\":\"\",\"openid\":\"\",\"timestamp\":\"1402057159\",\"signature\":\"017bb17407c8e0058a66d72dcc61632b70f511ad\"}").toJSONString();
        System.out.println(wxcardJson);

        AccessTokenApi.apiConfig = new ApiConfig(appId, appSecret);
        JSONObject jsonObject = MassMessageApi.preview(textJson);
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void getTest() {
        AccessTokenApi.apiConfig = new ApiConfig(appId, appSecret);
        JSONObject jsonObject = MassMessageApi.get(3147483654L);
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void getMassSpeedTest() {
        AccessTokenApi.apiConfig = new ApiConfig(appId, appSecret);
        JSONObject jsonObject = MassMessageApi.getMassSpeed();
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void setMassSpeedTest() {
        AccessTokenApi.apiConfig = new ApiConfig(appId, appSecret);
        JSONObject jsonObject = MassMessageApi.setMassSpeed(0);
        System.out.println(jsonObject.toJSONString());
    }
}
