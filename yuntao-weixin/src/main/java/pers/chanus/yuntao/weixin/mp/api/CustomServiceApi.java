/*
 * Copyright (c), 2020-persent, Chanus and/or its affiliates. All rights reserved.
 * FileName: CustomServiceApi
 * Author:   Chanus
 * Date:     2020-05-28 22:26:29
 * Description: 客服消息接口 API
 * History:
 * <author>          <time>          <version>          <desc>
 */
package pers.chanus.yuntao.weixin.mp.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chanus.yuntao.utils.core.HttpUtils;

import java.io.File;

/**
 * 客服消息接口 API<br>
 * 详情请见：
 * <pre>
 *     https://developers.weixin.qq.com/doc/offiaccount/Message_Management/Service_Center_messages.html
 *     https://developers.weixin.qq.com/doc/offiaccount/Customer_Service/Forwarding_of_messages_to_service_center.html
 *     https://developers.weixin.qq.com/doc/offiaccount/Customer_Service/Customer_Service_Management.html
 *     https://developers.weixin.qq.com/doc/offiaccount/Customer_Service/Session_control.html
 *     https://developers.weixin.qq.com/doc/offiaccount/Customer_Service/Obtain_chat_transcript.html
 * </pre>
 *
 * @author Chanus
 * @date 2020-05-28 22:26:29
 * @since 0.1.9
 */
public class CustomServiceApi {
    /**
     * 添加客服帐号 url，请求方式为 POST
     */
    private static final String ADD_KF_ACCOUNT_URL = "https://api.weixin.qq.com/customservice/kfaccount/add?access_token=ACCESS_TOKEN";
    /**
     * 邀请绑定客服帐号 url，请求方式为 POST
     */
    private static final String INVITE_WORKER_URL = "https://api.weixin.qq.com/customservice/kfaccount/inviteworker?access_token=ACCESS_TOKEN";
    /**
     * 修改客服帐号 url，请求方式为 POST
     */
    private static final String UPDATE_KF_ACCOUNT_URL = "https://api.weixin.qq.com/customservice/kfaccount/update?access_token=ACCESS_TOKEN";
    /**
     * 删除客服帐号 url，请求方式为 POST
     */
    private static final String DEL_KF_ACCOUNT_URL = "https://api.weixin.qq.com/customservice/kfaccount/del?access_token=ACCESS_TOKEN";
    /**
     * 设置客服帐号的头像 url，请求方式为 POST/FORM，使用curl命令，用FORM表单方式上传一个多媒体文件
     */
    private static final String UPLOAD_HEADIMG_URL = "https://api.weixin.qq.com/customservice/kfaccount/uploadheadimg?access_token=ACCESS_TOKEN&kf_account=KFACCOUNT";
    /**
     * 获取所有客服信息 url，请求方式为 GET
     */
    private static final String GET_KF_LIST_URL = "https://api.weixin.qq.com/cgi-bin/customservice/getkflist?access_token=ACCESS_TOKEN";
    /**
     * 获取所有在线客服信息 url，请求方式为 GET
     */
    private static final String GET_ONLINE_KF_LIST_URL = "https://api.weixin.qq.com/cgi-bin/customservice/getonlinekflist?access_token=ACCESS_TOKEN";
    /**
     * 客服接口-发消息 url，请求方式为 POST
     */
    private static final String CUSTOM_SEND_URL = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";
    /**
     * 客服输入状态接口 url，请求方式为 POST
     */
    private static final String CUSTOM_TYPING_URL = "https://api.weixin.qq.com/cgi-bin/message/custom/typing?access_token=ACCESS_TOKEN";
    /**
     * 创建会话 url，请求方式为 POST
     */
    private static final String CREATE_KF_SESSION_URL = "https://api.weixin.qq.com/customservice/kfsession/create?access_token=ACCESS_TOKEN";
    /**
     * 关闭会话 url，请求方式为 POST
     */
    private static final String CLOSE_KF_SESSION_URL = "https://api.weixin.qq.com/customservice/kfsession/close?access_token=ACCESS_TOKEN";
    /**
     * 获取客户会话状态 url，请求方式为 GET
     */
    private static final String GET_SESSION_URL = "https://api.weixin.qq.com/customservice/kfsession/getsession?access_token=ACCESS_TOKEN&openid=OPENID";
    /**
     * 获取客服会话列表 url，请求方式为 GET
     */
    private static final String GET_SESSION_LIST_URL = "https://api.weixin.qq.com/customservice/kfsession/getsessionlist?access_token=ACCESS_TOKEN&kf_account=KFACCOUNT";
    /**
     * 获取未接入会话列表 url，请求方式为 GET
     */
    private static final String GET_WAIT_CASE_URL = "https://api.weixin.qq.com/customservice/kfsession/getwaitcase?access_token=ACCESS_TOKEN";
    /**
     * 获取聊天记录 url，请求方式为 POST
     */
    private static final String GET_MSG_LIST_URL = "https://api.weixin.qq.com/customservice/msgrecord/getmsglist?access_token=ACCESS_TOKEN";
    /**
     * 获取客服聊天记录接口 url，请求方式为 POST
     */
    private static final String GET_RECORD_URL = "https://api.weixin.qq.com/customservice/msgrecord/getrecord?access_token=ACCESS_TOKEN";

    /**
     * 添加客服帐号
     *
     * @param kfAccount 完整客服帐号，格式为：帐号前缀@公众号微信号，帐号前缀最多10个字符，必须是英文、数字字符或者下划线，后缀为公众号微信号，长度不超过30个字符
     * @param nickname  客服昵称，最长6个汉字或12个英文字符
     * @param password  客服账号登录密码，格式为密码明文的32位加密MD5值。该密码仅用于在公众平台官网的多客服功能中使用，若不使用多客服功能，则不必设置密码
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject addKfAccount(String kfAccount, String nickname, String password) {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = ADD_KF_ACCOUNT_URL.replace("ACCESS_TOKEN", accessToken);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("kf_account", kfAccount);
        jsonObject.put("nickname", nickname);
        jsonObject.put("password", password);

        String result = HttpUtils.post(url, jsonObject.toJSONString());

        return JSON.parseObject(result);
    }

    /**
     * 添加客服帐号
     *
     * @param kfAccount 完整客服帐号，格式为：帐号前缀@公众号微信号
     * @param inviteWx  接收绑定邀请的客服微信号
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject inviteWorker(String kfAccount, String inviteWx) {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = INVITE_WORKER_URL.replace("ACCESS_TOKEN", accessToken);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("kf_account", kfAccount);
        jsonObject.put("invite_wx", inviteWx);

        String result = HttpUtils.post(url, jsonObject.toJSONString());

        return JSON.parseObject(result);
    }

    /**
     * 修改客服帐号
     *
     * @param kfAccount 完整客服账号，格式为：账号前缀@公众号微信号
     * @param nickname  客服昵称，最长6个汉字或12个英文字符
     * @param password  客服账号登录密码，格式为密码明文的32位加密MD5值。该密码仅用于在公众平台官网的多客服功能中使用，若不使用多客服功能，则不必设置密码
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject updateKfAccount(String kfAccount, String nickname, String password) {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = UPDATE_KF_ACCOUNT_URL.replace("ACCESS_TOKEN", accessToken);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("kf_account", kfAccount);
        jsonObject.put("nickname", nickname);
        jsonObject.put("password", password);

        String result = HttpUtils.post(url, jsonObject.toJSONString());

        return JSON.parseObject(result);
    }

    /**
     * 删除客服帐号
     *
     * @param kfAccount 完整客服账号，格式为：账号前缀@公众号微信号
     * @param nickname  客服昵称，最长6个汉字或12个英文字符
     * @param password  客服账号登录密码，格式为密码明文的32位加密MD5值。该密码仅用于在公众平台官网的多客服功能中使用，若不使用多客服功能，则不必设置密码
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject delKfAccount(String kfAccount, String nickname, String password) {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = DEL_KF_ACCOUNT_URL.replace("ACCESS_TOKEN", accessToken);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("kf_account", kfAccount);
        jsonObject.put("nickname", nickname);
        jsonObject.put("password", password);

        String result = HttpUtils.post(url, jsonObject.toJSONString());

        return JSON.parseObject(result);
    }

    /**
     * 设置客服帐号的头像
     *
     * @param kfAccount 完整客服账号，格式为：账号前缀@公众号微信号
     * @param headImg   客服人员的头像，头像图片文件必须是jpg格式，推荐使用640*640大小的图片以达到最佳效果
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject uploadKfAccountHeadImg(String kfAccount, File headImg) {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = UPLOAD_HEADIMG_URL.replace("ACCESS_TOKEN", accessToken).replace("KFACCOUNT", kfAccount);

        String result = HttpUtils.upload(url, headImg);

        return JSON.parseObject(result);
    }

    /**
     * 获取所有客服信息
     *
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject getKfList() {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = GET_KF_LIST_URL.replace("ACCESS_TOKEN", accessToken);

        String result = HttpUtils.get(url);

        return JSON.parseObject(result);
    }

    /**
     * 获取所有在线客服信息
     *
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject getOnlineKfList() {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = GET_ONLINE_KF_LIST_URL.replace("ACCESS_TOKEN", accessToken);

        String result = HttpUtils.get(url);

        return JSON.parseObject(result);
    }

    /**
     * 客服接口-发消息
     *
     * @param json 发送的消息数据的 json 字符串
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject send(String json) {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = CUSTOM_SEND_URL.replace("ACCESS_TOKEN", accessToken);

        String result = HttpUtils.post(url, json);

        return JSON.parseObject(result);
    }

    /**
     * 客服输入状态
     *
     * @param touser 普通用户（openid）
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject typing(String touser) {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = CUSTOM_TYPING_URL.replace("ACCESS_TOKEN", accessToken);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("touser", touser);
        jsonObject.put("command", "Typing");

        String result = HttpUtils.post(url, jsonObject.toJSONString());

        return JSON.parseObject(result);
    }

    /**
     * 创建会话
     *
     * @param kfAccount 完整客服帐号，格式为：帐号前缀@公众号微信号
     * @param openId    粉丝的openid
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject createKfSession(String kfAccount, String openId) {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = CREATE_KF_SESSION_URL.replace("ACCESS_TOKEN", accessToken);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("kf_account", kfAccount);
        jsonObject.put("openid", openId);

        String result = HttpUtils.post(url, jsonObject.toJSONString());

        return JSON.parseObject(result);
    }

    /**
     * 关闭会话
     *
     * @param kfAccount 完整客服帐号，格式为：帐号前缀@公众号微信号
     * @param openId    粉丝的openid
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject closeKfSession(String kfAccount, String openId) {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = CLOSE_KF_SESSION_URL.replace("ACCESS_TOKEN", accessToken);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("kf_account", kfAccount);
        jsonObject.put("openid", openId);

        String result = HttpUtils.post(url, jsonObject.toJSONString());

        return JSON.parseObject(result);
    }

    /**
     * 获取客户会话状态
     *
     * @param openId 粉丝的openid
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject getSession(String openId) {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = GET_SESSION_URL.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);

        String result = HttpUtils.get(url);

        return JSON.parseObject(result);
    }

    /**
     * 获取客服会话列表
     *
     * @param kfAccount 完整客服帐号，格式为：帐号前缀@公众号微信号
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject getSessionList(String kfAccount) {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = GET_SESSION_LIST_URL.replace("ACCESS_TOKEN", accessToken).replace("KFACCOUNT", kfAccount);

        String result = HttpUtils.get(url);

        return JSON.parseObject(result);
    }

    /**
     * 获取未接入会话列表
     *
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject getWaitCase() {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = GET_WAIT_CASE_URL.replace("ACCESS_TOKEN", accessToken);

        String result = HttpUtils.get(url);

        return JSON.parseObject(result);
    }

    /**
     * 获取聊天记录
     *
     * @param startTime 起始时间，unix时间戳
     * @param endTime   结束时间，unix时间戳，每次查询时段不能超过24小时
     * @param msgId     消息id顺序从小到大，从1开始
     * @param number    每次获取条数，最多10000条
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject getMsgList(long startTime, long endTime, int msgId, int number) {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = GET_MSG_LIST_URL.replace("ACCESS_TOKEN", accessToken);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("starttime", startTime);
        jsonObject.put("endtime", endTime);
        jsonObject.put("msgid", msgId);
        jsonObject.put("number", number);

        String result = HttpUtils.post(url, jsonObject.toJSONString());

        return JSON.parseObject(result);
    }

    /**
     * 获取客服聊天记录接口
     *
     * @param startTime 查询开始时间，UNIX时间戳
     * @param endTime   查询结束时间，UNIX时间戳，每次查询不能跨日查询
     * @param pageIndex 查询第几页，从1开始
     * @param pageSize  每页大小，每页最多拉取50条
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject getRecord(long startTime, long endTime, int pageIndex, int pageSize) {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = GET_RECORD_URL.replace("ACCESS_TOKEN", accessToken);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("starttime", startTime);
        jsonObject.put("endtime", endTime);
        jsonObject.put("pageindex", pageIndex);
        jsonObject.put("pagesize", pageSize);

        String result = HttpUtils.post(url, jsonObject.toJSONString());

        return JSON.parseObject(result);
    }

}
