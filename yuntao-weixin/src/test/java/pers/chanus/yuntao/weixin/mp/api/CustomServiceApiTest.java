/*
 * Copyright (c), 2020-persent, Chanus and/or its affiliates. All rights reserved.
 * FileName: CustomServiceApiTest
 * Author:   Chanus
 * Date:     2020-05-28 22:27:34
 * Description: CustomServiceApi 测试
 * History:
 * <author>          <time>          <version>          <desc>
 */
package pers.chanus.yuntao.weixin.mp.api;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import pers.chanus.yuntao.util.DateUtils;
import pers.chanus.yuntao.util.encrypt.MD5Utils;
import pers.chanus.yuntao.weixin.mp.api.bean.CustomMessage;

import java.io.File;

/**
 * CustomServiceApi 测试
 *
 * @author Chanus
 * @date 2020-05-28 22:27:34
 * @since 0.1.9
 */
public class CustomServiceApiTest extends ApiConfigTest {
    @Test
    public void addKfAccountTest() {
        JSONObject jsonObject = CustomServiceApi.addKfAccount("Chanus@gh_f19e07ef435d", "Chanus", MD5Utils.md5("123456"));
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void inviteWorkerTest() {
        JSONObject jsonObject = CustomServiceApi.inviteWorker("Chanus@gh_f19e07ef435d", "13580553960");
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void updateKfAccountTest() {
        JSONObject jsonObject = CustomServiceApi.updateKfAccount("Chanus@gh_f19e07ef435d", "Chanus", MD5Utils.md5("123456"));
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void delKfAccountTest() {
        JSONObject jsonObject = CustomServiceApi.delKfAccount("Chanus@gh_f19e07ef435d", "Chanus", MD5Utils.md5("123456"));
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void uploadKfAccountHeadImgTest() {
        File headImg = new File("/Users/Chanus/Documents/headimg.jpg");
        JSONObject jsonObject = CustomServiceApi.uploadKfAccountHeadImg("Chanus@gh_f19e07ef435d", headImg);
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void getKfListTest() {
        JSONObject jsonObject = CustomServiceApi.getKfList();
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void getOnlineKfListTest() {
        JSONObject jsonObject = CustomServiceApi.getOnlineKfList();
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void sendTest() {
        String touser = "o9Q3g0c-95M7vLPzPY4iUfLyCLVs";
        String kfAccount = "Chanus@gh_f19e07ef435d";
        String textMsg = CustomMessage.create(touser, kfAccount).setText("Hello World").toJSONString();
        System.out.println(textMsg);
        String imageMsg = CustomMessage.create(touser, kfAccount).setImage("MEDIA_ID").toJSONString();
        System.out.println(imageMsg);
        String voiceMsg = CustomMessage.create(touser, kfAccount).setVoice("MEDIA_ID").toJSONString();
        System.out.println(voiceMsg);
        String videoMsg = CustomMessage.create(touser, kfAccount).setVideo("MEDIA_ID", "THUMB_MEDIA_ID", "TITLE", "DESCRIPTION")
                .toJSONString();
        System.out.println(videoMsg);
        String musicMsg = CustomMessage.create(touser, kfAccount)
                .setMusic("MUSIC_TITLE", "MUSIC_DESCRIPTION", "MUSIC_URL", "HQ_MUSIC_URL", "THUMB_MEDIA_ID")
                .toJSONString();
        System.out.println(musicMsg);
        String newsMsg = CustomMessage.create(touser, kfAccount).setNews(CustomMessage.News.create()
                .addArticles("Happy Day", "Is Really A Happy Day", "URL", "PIC_URL")
                .addArticles("Happy Day", "Is Really A Happy Day", "URL", "PIC_URL"))
                .toJSONString();
        System.out.println(newsMsg);
        String mpnewsMsg = CustomMessage.create(touser, kfAccount).setMpnews("MEDIA_ID").toJSONString();
        System.out.println(mpnewsMsg);
        String wxcardMsg = CustomMessage.create(touser, kfAccount)
                .setWxcard("123dsdajkasd231jhksad", "{\"code\":\"\",\"openid\":\"\",\"timestamp\":\"1402057159\",\"signature\":\"017bb17407c8e0058a66d72dcc61632b70f511ad\"}")
                .toJSONString();
        System.out.println(wxcardMsg);
        String msgmenuMsg = CustomMessage.create(touser, kfAccount).setMsgmenu(CustomMessage.MsgMenu.create("您对本次服务是否满意呢? ", "欢迎再次光临")
                .addList("101", "满意").addList("102", "不满意")).toJSONString();
        System.out.println(msgmenuMsg);
        String miniprogrampageMsg = CustomMessage.create(touser, kfAccount)
                .setMiniprogrampage("title", "appid", "pagepath", "thumb_media_id")
                .toJSONString();
        System.out.println(miniprogrampageMsg);

        JSONObject jsonObject = CustomServiceApi.send(msgmenuMsg);
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void typingTest() {
        String touser = "o9Q3g0c-95M7vLPzPY4iUfLyCLVs";
        JSONObject jsonObject = CustomServiceApi.typing(touser);
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void createKfSessionTest() {
        JSONObject jsonObject = CustomServiceApi.createKfSession("Chanus@gh_f19e07ef435d", "o9Q3g0c-95M7vLPzPY4iUfLyCLVs");
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void closeKfSessionTest() {
        JSONObject jsonObject = CustomServiceApi.closeKfSession("Chanus@gh_f19e07ef435d", "o9Q3g0c-95M7vLPzPY4iUfLyCLVs");
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void getSessionTest() {
        JSONObject jsonObject = CustomServiceApi.getSession("o9Q3g0c-95M7vLPzPY4iUfLyCLVs");
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void getSessionListTest() {
        JSONObject jsonObject = CustomServiceApi.getSessionList("Chanus@gh_f19e07ef435d");
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void getWaitCaseTest() {
        JSONObject jsonObject = CustomServiceApi.getWaitCase();
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void getMsgListTest() {
        long startTime = DateUtils.parseDateTime("2020-05-30 00:00:00").getTime() / 1000L;
        long endTime = DateUtils.parseDateTime("2020-05-30 23:59:59").getTime() / 1000L;

        JSONObject jsonObject = CustomServiceApi.getMsgList(startTime, endTime, 1, 10000);
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void getRecordTest() {
        long startTime = DateUtils.parseDateTime("2020-05-30 00:00:00").getTime() / 1000L;
        long endTime = DateUtils.parseDateTime("2020-05-30 23:59:59").getTime() / 1000L;
        JSONObject jsonObject = CustomServiceApi.getRecord(startTime, endTime, 1, 10);
        System.out.println(jsonObject.toJSONString());
    }
}
