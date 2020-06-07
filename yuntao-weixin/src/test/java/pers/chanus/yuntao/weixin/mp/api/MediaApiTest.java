/*
 * Copyright (c), 2020-persent, Chanus and/or its affiliates. All rights reserved.
 * FileName: MediaApiTest
 * Author:   Chanus
 * Date:     2020-06-06 23:59:45
 * Description: MediaApi 测试
 * History:
 * <author>          <time>          <version>          <desc>
 */
package pers.chanus.yuntao.weixin.mp.api;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import pers.chanus.yuntao.util.FileUtils;
import pers.chanus.yuntao.weixin.mp.api.bean.MediaArticle;
import pers.chanus.yuntao.weixin.mp.api.bean.MediaTypeEnum;

import java.io.BufferedInputStream;
import java.io.File;

/**
 * MediaApi 测试
 *
 * @author Chanus
 * @date 2020-06-06 23:59:45
 * @since 0.1.9
 */
public class MediaApiTest extends ApiConfigTest {
    @Test
    public void uploadMediaTest() {
        // File image = new File("/Users/Chanus/Documents/headimg.jpg");
        // JSONObject jsonObject = MediaApi.uploadMedia(image, MediaTypeEnum.IMAGE);
        // System.out.println(jsonObject.toJSONString());
        //
        // File voice = new File("/Users/Chanus/Documents/feedback.mp3");
        // JSONObject jsonObject2 = MediaApi.uploadMedia(voice, MediaTypeEnum.VOICE);
        // System.out.println(jsonObject2.toJSONString());

        File video = new File("/Users/Chanus/Documents/video.mp4");
        JSONObject jsonObject3 = MediaApi.uploadMedia(video, MediaTypeEnum.VIDEO);
        System.out.println(jsonObject3.toJSONString());
    }

    @Test
    public void getMediaTest() {
        String mediaId = "F82bqOAuMBz2Cv_tFIGA0y1UeUiP3yKEfy9B9W4uvzwzxv9Ab-g_3yca0kCpLoHf";
        BufferedInputStream bufferedInputStream = MediaApi.getMedia(mediaId);
        FileUtils.write("/Users/Chanus/Documents/img.jpg", bufferedInputStream, false);

        File file = MediaApi.getMedia(mediaId, "/Users/Chanus/Documents/mp");
        System.out.println(file.getName());
    }

    @Test
    public void getJsSdkMediaTest() {
        String mediaId = "KKjUQf5L3TbtcsLRbvFNjlmEmH_O0awvtymGHDC8sL828b13utwZpZMlGDDYj4L-";
        BufferedInputStream bufferedInputStream = MediaApi.getJsSdkMedia(mediaId);
        FileUtils.write("/Users/Chanus/Documents/voice.mp3", bufferedInputStream, false);
        File file = MediaApi.getJsSdkMedia(mediaId, "/Users/Chanus/Documents/mp");
        System.out.println(file.getName());
    }

    @Test
    public void addNewsTest() {
        String articles = MediaArticle.create()
                .addArticle(new MediaArticle.Article().setTitle("test")
                        .setAuthor("Chanus").setThumb_media_id("BoD9IkOKVe0sSTnFOvPnnqfTAFUUl5XDvUPuT_oHtgw")
                        .setDigest("这是一个摘要信息").setShow_cover_pic(1).setContent("图文素材的正文").setContent_source_url("www.baidu.com"))
                .addArticle(new MediaArticle.Article().setTitle("test2")
                        .setAuthor("Chanus").setThumb_media_id("BoD9IkOKVe0sSTnFOvPnnqfTAFUUl5XDvUPuT_oHtgw")
                        .setDigest("这是一个摘要信息2").setShow_cover_pic(1).setContent("图文素材的正文2").setContent_source_url("www.baidu.com"))
                .toJSONString();
        System.out.println(articles);

        JSONObject jsonObject = MediaApi.addNews(articles);
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void uploadImgTest() {
        File image = new File("/Users/Chanus/Documents/headimg.jpg");
        JSONObject jsonObject = MediaApi.uploadImg(image);
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void addMaterialTest() {
        File image = new File("/Users/Chanus/Documents/headimg.jpg");
        JSONObject imageJsonObject = MediaApi.addMaterial(image, MediaTypeEnum.IMAGE);
        System.out.println(imageJsonObject.toJSONString());

        // File thumb = new File("/Users/Chanus/Documents/头像.jpg");
        // JSONObject thumbJsonObject = MediaApi.addMaterial(thumb, MediaTypeEnum.THUMB);
        // System.out.println(thumbJsonObject.toJSONString());

        // File voice = new File("/Users/Chanus/Documents/feedback.mp3");
        // JSONObject jsonObject2 = MediaApi.addMaterial(voice, MediaTypeEnum.VOICE);
        // System.out.println(jsonObject2.toJSONString());
        //
        // File video = new File("/Users/Chanus/Documents/video.mp4");
        // JSONObject jsonObject3 = MediaApi.addMaterial(video, "测试视频素材", "这是一个测试用的视频素材");
        // System.out.println(jsonObject3.toJSONString());
    }

    @Test
    public void getMaterialTest() {
        String mediaId = "BoD9IkOKVe0sSTnFOvPnnqfTAFUUl5XDvUPuT_oHtgw";
        BufferedInputStream bufferedInputStream = MediaApi.getMaterial(mediaId);
        FileUtils.write("/Users/Chanus/Documents/img.jpg", bufferedInputStream, false);

        File file = MediaApi.getMaterial(mediaId, "/Users/Chanus/Documents/mp");
        System.out.println(file.getName());
    }

    @Test
    public void delMaterialTest() {
        String mediaId = "BoD9IkOKVe0sSTnFOvPnnqfTAFUUl5XDvUPuT_oHtgw";
        JSONObject jsonObject = MediaApi.delMaterial(mediaId);
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void updateNewsTest() {
        MediaArticle.Article articles = MediaArticle.Article.create().setTitle("test22")
                .setAuthor("LiuYun").setThumb_media_id("BoD9IkOKVe0sSTnFOvPnnvCXAeqKdr4QsknhVcr4JgA")
                .setDigest("这是一个摘要信息2").setShow_cover_pic(1).setContent("图文素材的正文2")
                .setContent_source_url("www.baidu.com");
        System.out.println(articles.toJSONString());

        JSONObject jsonObject = MediaApi.updateNews("BoD9IkOKVe0sSTnFOvPnnn7LzD_KeMi2HkThzIxfo_Q", 1, articles);
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void getMaterialCountTest() {
        JSONObject jsonObject = MediaApi.getMaterialCount();
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void batchGetMaterialTest() {
        JSONObject imageJsonObject = MediaApi.batchGetMaterial(MediaTypeEnum.IMAGE, 0, 20);
        System.out.println(imageJsonObject.toJSONString());

        JSONObject voiceJsonObject = MediaApi.batchGetMaterial(MediaTypeEnum.VOICE, 0, 20);
        System.out.println(voiceJsonObject.toJSONString());

        JSONObject videoJsonObject = MediaApi.batchGetMaterial(MediaTypeEnum.VIDEO, 0, 20);
        System.out.println(videoJsonObject.toJSONString());

        JSONObject newsJsonObject = MediaApi.batchGetMaterial(MediaTypeEnum.NEWS, 0, 20);
        System.out.println(newsJsonObject.toJSONString());
    }

    @Test
    public void uploadNewsTest() {
        // 注意：thumb_media_id 是临时图片素材的 media_id
        String articles = MediaArticle.create()
                .addArticle(new MediaArticle.Article().setTitle("test")
                        .setAuthor("Chanus").setThumb_media_id("s0iWZawffIjomOBPerigwDqdcMpHcjc9gBnG1NhcFPxgElkni7m_x7axK8W0QEqY")
                        .setDigest("这是一个摘要信息").setShow_cover_pic(1).setContent("图文素材的正文").setContent_source_url("www.baidu.com")
                        .setNeed_open_comment(0).setOnly_fans_can_comment(0))
                .addArticle(new MediaArticle.Article().setTitle("test2")
                        .setAuthor("Chanus").setThumb_media_id("s0iWZawffIjomOBPerigwDqdcMpHcjc9gBnG1NhcFPxgElkni7m_x7axK8W0QEqY")
                        .setDigest("这是一个摘要信息2").setShow_cover_pic(1).setContent("图文素材的正文2").setContent_source_url("www.baidu.com")
                        .setNeed_open_comment(0).setOnly_fans_can_comment(0))
                .toJSONString();
        System.out.println(articles);

        JSONObject jsonObject = MediaApi.uploadNews(articles);
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void uploadVideoTest() {
        // 注意：media_id 是临时视频素材的 media_id
        String mediaId = "5SjMRKnb03syHqy1XUUv59HbZdFkWnNmW-HJFNhOHhZSaJpOLuAsyi2esVMVTdiE";
        JSONObject jsonObject = MediaApi.uploadVideo(mediaId, "test", "test message");
        System.out.println(jsonObject.toJSONString());
    }
}
