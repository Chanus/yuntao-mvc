/*
 * Copyright (c), 2020-persent, Chanus and/or its affiliates. All rights reserved.
 * FileName: MediaApi
 * Author:   Chanus
 * Date:     2020-06-06 23:35:08
 * Description: 素材管理 API
 * History:
 * <author>          <time>          <version>          <desc>
 */
package pers.chanus.yuntao.weixin.mp.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chanus.yuntao.utils.core.HttpUtils;
import pers.chanus.yuntao.weixin.mp.api.bean.MediaArticle;
import pers.chanus.yuntao.weixin.mp.api.bean.MediaTypeEnum;

import java.io.BufferedInputStream;
import java.io.File;

/**
 * 素材管理 API<br>
 * 详情请见：
 * <pre>
 *     新增临时素材：https://developers.weixin.qq.com/doc/offiaccount/Asset_Management/New_temporary_materials.html
 *     获取临时素材：https://developers.weixin.qq.com/doc/offiaccount/Asset_Management/Get_temporary_materials.html
 *     新增永久素材：https://developers.weixin.qq.com/doc/offiaccount/Asset_Management/Adding_Permanent_Assets.html
 *     获取永久素材：https://developers.weixin.qq.com/doc/offiaccount/Asset_Management/Getting_Permanent_Assets.html
 *     删除永久素材：https://developers.weixin.qq.com/doc/offiaccount/Asset_Management/Deleting_Permanent_Assets.html
 *     修改永久图文素材：https://developers.weixin.qq.com/doc/offiaccount/Asset_Management/Editing_Permanent_Rich_Media_Assets.html
 *     获取素材总数：https://developers.weixin.qq.com/doc/offiaccount/Asset_Management/Get_the_total_of_all_materials.html
 *     获取素材列表：https://developers.weixin.qq.com/doc/offiaccount/Asset_Management/Get_materials_list.html
 * </pre>
 *
 * @author Chanus
 * @date 2020-06-06 23:35:08
 * @since 0.1.9
 */
public class MediaApi {
    /**
     * 新增临时素材 url，请求方式为 POST/FORM
     */
    private static final String UPLOAD_MEDIA_URL = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";
    /**
     * 获取临时素材 url，请求方式为 GET
     */
    private static final String GET_MEDIA_URL = "https://api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";
    /**
     * 高清语音素材获取接口 url，请求方式为 GET
     */
    private static final String GET_JSSDK_MEDIA_URL = "https://api.weixin.qq.com/cgi-bin/media/get/jssdk?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";
    /**
     * 新增永久图文素材 url，请求方式为 POST
     */
    private static final String ADD_NEWS_URL = "https://api.weixin.qq.com/cgi-bin/material/add_news?access_token=ACCESS_TOKEN";
    /**
     * 上传图文消息内的图片获取URL url，请求方式为 POST
     */
    private static final String UPLOAD_IMG_URL = "https://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token=ACCESS_TOKEN";
    /**
     * 新增其他类型永久素材 url，请求方式为 POST/FORM
     */
    private static final String ADD_MATERIAL_URL = "https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=ACCESS_TOKEN&type=TYPE";
    /**
     * 获取永久素材 url，请求方式为 POST
     */
    private static final String GET_MATERIAL_URL = "https://api.weixin.qq.com/cgi-bin/material/get_material?access_token=ACCESS_TOKEN";
    /**
     * 删除永久素材 url，请求方式为 POST
     */
    private static final String DEL_MATERIAL_URL = "https://api.weixin.qq.com/cgi-bin/material/del_material?access_token=ACCESS_TOKEN";
    /**
     * 修改永久图文素材 url，请求方式为 POST
     */
    private static final String UPDATE_NEWS_URL = "https://api.weixin.qq.com/cgi-bin/material/update_news?access_token=ACCESS_TOKEN";
    /**
     * 获取素材总数 url，请求方式为 GET
     */
    private static final String GET_MATERIAL_COUNT_URL = "https://api.weixin.qq.com/cgi-bin/material/get_materialcount?access_token=ACCESS_TOKEN";
    /**
     * 获取素材列表 url，请求方式为 POST
     */
    private static final String BATCH_GET_MATERIAL_URL = "https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token=ACCESS_TOKEN";
    /**
     * 上传图文消息素材 url，请求方式为 POST
     */
    private static final String UPLOAD_NEWS_URL = "https://api.weixin.qq.com/cgi-bin/media/uploadnews?access_token=ACCESS_TOKEN";
    /**
     * 上传视频消息素材 url，请求方式为 POST
     */
    private static final String UPLOAD_VIDEO_URL = "https://api.weixin.qq.com/cgi-bin/media/uploadvideo?access_token=ACCESS_TOKEN";

    /**
     * 新增临时素材
     *
     * @param file      媒体文件
     * @param mediaType 媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb）
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject uploadMedia(File file, MediaTypeEnum mediaType) {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = UPLOAD_MEDIA_URL.replace("ACCESS_TOKEN", accessToken).replace("TYPE", mediaType.get());

        String result = HttpUtils.upload(url, file);

        return JSON.parseObject(result);
    }

    /**
     * 获取临时素材
     *
     * @param mediaId 媒体文件ID
     * @return 临时素材输入流
     * @since 0.1.9
     */
    public static BufferedInputStream getMedia(String mediaId) {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = GET_MEDIA_URL.replace("ACCESS_TOKEN", accessToken).replace("MEDIA_ID", mediaId);

        return HttpUtils.downloadGet(url);
    }

    /**
     * 获取临时素材
     *
     * @param mediaId 媒体文件ID
     * @param path    媒体文件保存路径
     * @return 临时素材文件
     * @since 0.1.9
     */
    public static File getMedia(String mediaId, String path) {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = GET_MEDIA_URL.replace("ACCESS_TOKEN", accessToken).replace("MEDIA_ID", mediaId);

        return HttpUtils.downloadGet(url, path);
    }

    /**
     * 获取高清语音素材
     *
     * @param mediaId 媒体文件ID
     * @return 高清语音素材输入流
     * @since 0.1.9
     */
    public static BufferedInputStream getJsSdkMedia(String mediaId) {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = GET_JSSDK_MEDIA_URL.replace("ACCESS_TOKEN", accessToken).replace("MEDIA_ID", mediaId);

        return HttpUtils.downloadGet(url);
    }

    /**
     * 获取高清语音素材
     *
     * @param mediaId 媒体文件ID
     * @param path    媒体文件保存路径
     * @return 高清语音素材文件
     * @since 0.1.9
     */
    public static File getJsSdkMedia(String mediaId, String path) {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = GET_JSSDK_MEDIA_URL.replace("ACCESS_TOKEN", accessToken).replace("MEDIA_ID", mediaId);

        return HttpUtils.downloadGet(url, path);
    }

    /**
     * 新增永久图文素材
     *
     * @param articles 永久图文素材
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject addNews(String articles) {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = ADD_NEWS_URL.replace("ACCESS_TOKEN", accessToken);

        String result = HttpUtils.post(url, articles);

        return JSON.parseObject(result);
    }

    /**
     * 上传图文消息内的图片获取URL<br>
     * 本接口所上传的图片不占用公众号的素材库中图片数量的100000个的限制。图片仅支持jpg/png格式，大小必须在1MB以下
     *
     * @param file 图片文件
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject uploadImg(File file) {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = UPLOAD_IMG_URL.replace("ACCESS_TOKEN", accessToken);

        String result = HttpUtils.upload(url, file);

        return JSON.parseObject(result);
    }

    /**
     * 新增其他类型永久素材
     *
     * @param file      媒体文件
     * @param mediaType 媒体文件类型，分别有图片（image）、语音（voice）和缩略图（thumb）
     * @return 请求结果的 json 对象
     * @see MediaApi#addMaterial(File, String, String) 上传永久视频素材请使用该方法
     * @since 0.1.9
     */
    public static JSONObject addMaterial(File file, MediaTypeEnum mediaType) {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = ADD_MATERIAL_URL.replace("ACCESS_TOKEN", accessToken).replace("TYPE", mediaType.get());

        String result = HttpUtils.upload(url, file);

        return JSON.parseObject(result);
    }

    /**
     * 新增永久视频素材
     *
     * @param file         视频素材文件
     * @param title        视频素材的标题
     * @param introduction 视频素材的描述
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject addMaterial(File file, String title, String introduction) {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = ADD_MATERIAL_URL.replace("ACCESS_TOKEN", accessToken).replace("TYPE", MediaTypeEnum.VIDEO.get());

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title", title);
        jsonObject.put("introduction", introduction);

        String result = HttpUtils.upload(url, jsonObject.toJSONString(), file);

        return JSON.parseObject(result);
    }

    /**
     * 获取永久素材
     *
     * @param mediaId 要获取的素材的 media_id
     * @return 永久素材输入流
     * @since 0.1.9
     */
    public static BufferedInputStream getMaterial(String mediaId) {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = GET_MATERIAL_URL.replace("ACCESS_TOKEN", accessToken);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("media_id", mediaId);

        return HttpUtils.downloadPost(url, jsonObject.toJSONString());
    }

    /**
     * 获取永久素材
     *
     * @param mediaId 要获取的素材的 media_id
     * @param path    获取的素材的保存路径
     * @return 永久素材文件
     * @since 0.1.9
     */
    public static File getMaterial(String mediaId, String path) {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = GET_MATERIAL_URL.replace("ACCESS_TOKEN", accessToken);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("media_id", mediaId);

        return HttpUtils.downloadPost(url, jsonObject.toJSONString(), path);
    }

    /**
     * 删除永久素材
     *
     * @param mediaId 要删除的素材的 media_id
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject delMaterial(String mediaId) {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = DEL_MATERIAL_URL.replace("ACCESS_TOKEN", accessToken);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("media_id", mediaId);

        String result = HttpUtils.post(url, jsonObject.toJSONString());

        return JSON.parseObject(result);
    }

    /**
     * 修改永久图文素材
     *
     * @param mediaId  要修改的图文消息的 id
     * @param index    要更新的文章在图文消息中的位置（多图文消息时，此字段才有意义），第一篇为0
     * @param articles 永久图文素材数据
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject updateNews(String mediaId, int index, MediaArticle.Article articles) {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = UPDATE_NEWS_URL.replace("ACCESS_TOKEN", accessToken);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("media_id", mediaId);
        jsonObject.put("index", index);
        jsonObject.put("articles", articles);

        String result = HttpUtils.post(url, jsonObject.toJSONString());

        return JSON.parseObject(result);
    }

    /**
     * 获取素材总数
     *
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject getMaterialCount() {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = GET_MATERIAL_COUNT_URL.replace("ACCESS_TOKEN", accessToken);

        String result = HttpUtils.get(url);

        return JSON.parseObject(result);
    }

    /**
     * 获取素材列表
     *
     * @param mediaType 素材的类型，图片（image）、视频（video）、语音 （voice）、图文（news）
     * @param offset    从全部素材的该偏移位置开始返回，0表示从第一个素材 返回
     * @param count     返回素材的数量，取值在1到20之间
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject batchGetMaterial(MediaTypeEnum mediaType, int offset, int count) {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = BATCH_GET_MATERIAL_URL.replace("ACCESS_TOKEN", accessToken);

        if (offset < 0) offset = 0;
        if (count > 20) count = 20;
        if (count < 1) count = 1;

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", mediaType.get());
        jsonObject.put("offset", offset);
        jsonObject.put("count", count);

        String result = HttpUtils.post(url, jsonObject.toJSONString());

        return JSON.parseObject(result);
    }

    /**
     * 上传图文消息素材
     *
     * @param articles 永久图文素材，其中 thumb_media_id 是临时图片素材的 media_id
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject uploadNews(String articles) {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = UPLOAD_NEWS_URL.replace("ACCESS_TOKEN", accessToken);

        String result = HttpUtils.post(url, articles);

        return JSON.parseObject(result);
    }

    /**
     * 上传视频消息素材
     *
     * @param mediaId     用于群发的消息的视频素材的 media_id，该 media_id 是临时视频素材的 media_id
     * @param title       消息的标题
     * @param description 消息的描述
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject uploadVideo(String mediaId, String title, String description) {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = UPLOAD_VIDEO_URL.replace("ACCESS_TOKEN", accessToken);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("media_id", mediaId);
        jsonObject.put("title", title);
        jsonObject.put("description", description);

        String result = HttpUtils.post(url, jsonObject.toJSONString());

        return JSON.parseObject(result);
    }
}
