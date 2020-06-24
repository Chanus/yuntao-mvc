/*
 * Copyright (c), 2020-persent, Chanus and/or its affiliates. All rights reserved.
 * FileName: NewsCommentApi
 * Author:   Chanus
 * Date:     2020-06-07 17:00:00
 * Description: 图文消息留言管理 API
 * History:
 * <author>          <time>          <version>          <desc>
 */
package pers.chanus.yuntao.weixin.mp.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chanus.yuntao.utils.core.HttpUtils;

/**
 * 图文消息留言管理 API<br>
 * 详情请见：https://developers.weixin.qq.com/doc/offiaccount/Comments_management/Image_Comments_Management_Interface.html
 *
 * @author Chanus
 * @date 2020-06-07 17:00:00
 * @since 0.1.9
 */
public class NewsCommentApi {
    /**
     * 打开已群发文章评论 url，请求方式为 POST
     */
    private static final String OPEN_COMMENT_URL = "https://api.weixin.qq.com/cgi-bin/comment/open?access_token=ACCESS_TOKEN";
    /**
     * 关闭已群发文章评论 url，请求方式为 POST
     */
    private static final String CLOSE_COMMENT_URL = "https://api.weixin.qq.com/cgi-bin/comment/close?access_token=ACCESS_TOKEN";
    /**
     * 查看指定文章的评论数据 url，请求方式为 POST
     */
    private static final String LIST_COMMENT_URL = "https://api.weixin.qq.com/cgi-bin/comment/list?access_token=ACCESS_TOKEN";
    /**
     * 将评论标记精选 url，请求方式为 POST
     */
    private static final String MARK_ELECT_COMMENT_URL = "https://api.weixin.qq.com/cgi-bin/comment/markelect?access_token=ACCESS_TOKEN";
    /**
     * 将评论取消精选 url，请求方式为 POST
     */
    private static final String UNMARK_ELECT_COMMENT_URL = "https://api.weixin.qq.com/cgi-bin/comment/unmarkelect?access_token=ACCESS_TOKEN";
    /**
     * 删除评论 url，请求方式为 POST
     */
    private static final String DELETE_COMMENT_URL = "https://api.weixin.qq.com/cgi-bin/comment/delete?access_token=ACCESS_TOKEN";
    /**
     * 回复评论 url，请求方式为 POST
     */
    private static final String REPLY_ADD_COMMENT_URL = "https://api.weixin.qq.com/cgi-bin/comment/reply/add?access_token=ACCESS_TOKEN";
    /**
     * 删除回复 url，请求方式为 POST
     */
    private static final String REPLY_DELETE_COMMENT_URL = "https://api.weixin.qq.com/cgi-bin/comment/reply/delete?access_token=ACCESS_TOKEN";

    /**
     * 打开已群发文章评论
     *
     * @param msgDataId 群发返回的 msg_data_id
     * @param index     多图文时，用来指定第几篇图文，从0开始，不带默认操作该 msg_data_id 的第一篇图文
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject open(long msgDataId, Integer index) {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = OPEN_COMMENT_URL.replace("ACCESS_TOKEN", accessToken);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg_data_id", msgDataId);
        jsonObject.put("index", index);

        String result = HttpUtils.post(url, jsonObject.toJSONString());

        return JSON.parseObject(result);
    }

    /**
     * 闭已群发文章评论
     *
     * @param msgDataId 群发返回的 msg_data_id
     * @param index     多图文时，用来指定第几篇图文，从0开始，不带默认操作该 msg_data_id 的第一篇图文
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject close(long msgDataId, Integer index) {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = CLOSE_COMMENT_URL.replace("ACCESS_TOKEN", accessToken);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg_data_id", msgDataId);
        jsonObject.put("index", index);

        String result = HttpUtils.post(url, jsonObject.toJSONString());

        return JSON.parseObject(result);
    }

    /**
     * 查看指定文章的评论数据
     *
     * @param msgDataId 群发返回的 msg_data_id
     * @param index     多图文时，用来指定第几篇图文，从0开始，不带默认返回该 msg_data_id 的第一篇图文
     * @param begin     起始位置
     * @param count     获取数目（>=50会被拒绝）
     * @param type      type=0 普通评论&精选评论 type=1 普通评论 type=2 精选评论
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject list(long msgDataId, Integer index, int begin, int count, int type) {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = LIST_COMMENT_URL.replace("ACCESS_TOKEN", accessToken);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg_data_id", msgDataId);
        jsonObject.put("index", index);
        jsonObject.put("begin", begin);
        jsonObject.put("count", count);
        jsonObject.put("type", type);

        String result = HttpUtils.post(url, jsonObject.toJSONString());

        return JSON.parseObject(result);
    }

    /**
     * 将评论标记精选
     *
     * @param msgDataId     群发返回的 msg_data_id
     * @param index         多图文时，用来指定第几篇图文，从0开始，不带默认操作该 msg_data_id 的第一篇图文
     * @param userCommentId 用户评论id
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject markElect(long msgDataId, Integer index, int userCommentId) {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = MARK_ELECT_COMMENT_URL.replace("ACCESS_TOKEN", accessToken);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg_data_id", msgDataId);
        jsonObject.put("index", index);
        jsonObject.put("user_comment_id", userCommentId);

        String result = HttpUtils.post(url, jsonObject.toJSONString());

        return JSON.parseObject(result);
    }

    /**
     * 将评论取消精选
     *
     * @param msgDataId     群发返回的 msg_data_id
     * @param index         多图文时，用来指定第几篇图文，从0开始，不带默认操作该 msg_data_id 的第一篇图文
     * @param userCommentId 用户评论id
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject unmarkElect(long msgDataId, Integer index, int userCommentId) {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = UNMARK_ELECT_COMMENT_URL.replace("ACCESS_TOKEN", accessToken);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg_data_id", msgDataId);
        jsonObject.put("index", index);
        jsonObject.put("user_comment_id", userCommentId);

        String result = HttpUtils.post(url, jsonObject.toJSONString());

        return JSON.parseObject(result);
    }

    /**
     * 删除评论
     *
     * @param msgDataId     群发返回的 msg_data_id
     * @param index         多图文时，用来指定第几篇图文，从0开始，不带默认操作该 msg_data_id 的第一篇图文
     * @param userCommentId 用户评论id
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject delete(long msgDataId, Integer index, int userCommentId) {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = DELETE_COMMENT_URL.replace("ACCESS_TOKEN", accessToken);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg_data_id", msgDataId);
        jsonObject.put("index", index);
        jsonObject.put("user_comment_id", userCommentId);

        String result = HttpUtils.post(url, jsonObject.toJSONString());

        return JSON.parseObject(result);
    }

    /**
     * 回复评论
     *
     * @param msgDataId     群发返回的 msg_data_id
     * @param index         多图文时，用来指定第几篇图文，从0开始，不带默认操作该 msg_data_id 的第一篇图文
     * @param userCommentId 用户评论id
     * @param content       回复内容
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject reply(long msgDataId, Integer index, int userCommentId, String content) {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = REPLY_ADD_COMMENT_URL.replace("ACCESS_TOKEN", accessToken);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg_data_id", msgDataId);
        jsonObject.put("index", index);
        jsonObject.put("user_comment_id", userCommentId);
        jsonObject.put("content", content);

        String result = HttpUtils.post(url, jsonObject.toJSONString());

        return JSON.parseObject(result);
    }

    /**
     * 删除回复
     *
     * @param msgDataId     群发返回的 msg_data_id
     * @param index         多图文时，用来指定第几篇图文，从0开始，不带默认操作该 msg_data_id 的第一篇图文
     * @param userCommentId 用户评论id
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject deleteReply(long msgDataId, Integer index, int userCommentId) {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = REPLY_DELETE_COMMENT_URL.replace("ACCESS_TOKEN", accessToken);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg_data_id", msgDataId);
        jsonObject.put("index", index);
        jsonObject.put("user_comment_id", userCommentId);

        String result = HttpUtils.post(url, jsonObject.toJSONString());

        return JSON.parseObject(result);
    }
}
