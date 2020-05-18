/*
 * Copyright (c), 2020-persent, Chanus and/or its affiliates. All rights reserved.
 * FileName: TemplateMsgApi
 * Author:   Chanus
 * Date:     2020-05-18 14:11:43
 * Description: 模板消息 API
 * History:
 * <author>          <time>          <version>          <desc>
 */
package pers.chanus.yuntao.weixin.mp.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import pers.chanus.yuntao.util.HttpUtils;

/**
 * 模板消息 API<br>
 * 详情请见：https://developers.weixin.qq.com/doc/offiaccount/Message_Management/Template_Message_Interface.html
 *
 * @author Chanus
 * @date 2020-05-18 14:11:43
 * @since 0.1.9
 */
public class TemplateMsgApi {
    /**
     * 设置所属行业 url，请求方式为 POST
     */
    private static final String SET_INDUSTRY_URL = "https://api.weixin.qq.com/cgi-bin/template/api_set_industry?access_token=ACCESS_TOKEN";
    /**
     * 获取设置的行业信息 url，请求方式为 GET
     */
    private static final String GET_INDUSTRY_URL = "https://api.weixin.qq.com/cgi-bin/template/get_industry?access_token=ACCESS_TOKEN";
    /**
     * 获得模板ID url，请求方式为 POST
     */
    private static final String GET_TEMPLATE_ID_URL = "https://api.weixin.qq.com/cgi-bin/template/api_add_template?access_token=ACCESS_TOKEN";
    /**
     * 获取模板列表 url，请求方式为 GET
     */
    private static final String GET_ALL_PRIVATE_TEMPLATE_URL = "https://api.weixin.qq.com/cgi-bin/template/get_all_private_template?access_token=ACCESS_TOKEN";
    /**
     * 删除模板 url，请求方式为 POST
     */
    private static final String DEL_PRIVATE_TEMPLATE_URL = "https://api.weixin.qq.com/cgi-bin/template/del_private_template?access_token=ACCESS_TOKEN";

    /**
     * 发送模板消息 url，请求方式为 POST
     */
    private static final String SEND_TEMPLATE_MSG_URL = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";

    /**
     * 设置所属行业
     *
     * @param industryId1 公众号模板消息所属行业编号
     * @param industryId2 公众号模板消息所属行业编号
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject setIndustry(String industryId1, String industryId2) {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = SET_INDUSTRY_URL.replace("ACCESS_TOKEN", accessToken);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("industry_id1", industryId1);
        jsonObject.put("industry_id2", industryId2);

        String result = HttpUtils.post(url, jsonObject.toJSONString());

        return JSON.parseObject(result);
    }

    /**
     * 获取设置的行业信息
     *
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject getIndustry() {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = GET_INDUSTRY_URL.replace("ACCESS_TOKEN", accessToken);

        String result = HttpUtils.get(url);

        return JSON.parseObject(result);
    }

    /**
     * 获得模板ID
     *
     * @param templateIdShort 模板库中模板的编号，有“TM**”和“OPENTMTM**”等形式
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject getTemplateId(String templateIdShort) {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = GET_TEMPLATE_ID_URL.replace("ACCESS_TOKEN", accessToken);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("template_id_short", templateIdShort);

        String result = HttpUtils.post(url, jsonObject.toJSONString());

        return JSON.parseObject(result);
    }

    /**
     * 获取模板列表
     *
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject getAllTemplate() {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = GET_ALL_PRIVATE_TEMPLATE_URL.replace("ACCESS_TOKEN", accessToken);

        String result = HttpUtils.get(url);

        return JSON.parseObject(result);
    }

    /**
     * 删除模板
     *
     * @param templateId 公众帐号下模板消息ID
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject delTemplate(String templateId) {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = DEL_PRIVATE_TEMPLATE_URL.replace("ACCESS_TOKEN", accessToken);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("template_id", templateId);

        String result = HttpUtils.post(url, jsonObject.toJSONString());

        return JSON.parseObject(result);
    }

    /**
     * 发送模板消息
     *
     * @param json 模板数据 json 字符串
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject send(String json) {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = SEND_TEMPLATE_MSG_URL.replace("ACCESS_TOKEN", accessToken);

        String result = HttpUtils.post(url, json);

        return JSON.parseObject(result);
    }

}
