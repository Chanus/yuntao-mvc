/*
 * Copyright (c), 2020-persent, Chanus and/or its affiliates. All rights reserved.
 * FileName: PoiApi
 * Author:   Chanus
 * Date:     2020-06-08 17:09:53
 * Description: 微信门店 API
 * History:
 * <author>          <time>          <version>          <desc>
 */
package pers.chanus.yuntao.weixin.mp.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import pers.chanus.yuntao.util.HttpUtils;

/**
 * 微信门店 API<br>
 * 详情请见：https://developers.weixin.qq.com/doc/offiaccount/WeChat_Stores/WeChat_Store_Interface.html
 *
 * @author Chanus
 * @date 2020-06-08 17:09:53
 * @since 0.1.9
 */
public class PoiApi {
    /**
     * 创建门店 url，请求方式为 POST
     */
    private static final String ADD_POI_URL = "http://api.weixin.qq.com/cgi-bin/poi/addpoi?access_token=TOKEN";
    /**
     * 查询门店信息 url，请求方式为 POST
     */
    private static final String GET_POI_URL = "http://api.weixin.qq.com/cgi-bin/poi/getpoi?access_token=TOKEN";
    /**
     * 查询门店列表 url，请求方式为 POST
     */
    private static final String GET_POI_LIST_URL = "https://api.weixin.qq.com/cgi-bin/poi/getpoilist?access_token=TOKEN";
    /**
     * 修改门店服务信息 url，请求方式为 POST
     */
    private static final String UPDATE_POI_URL = "https://api.weixin.qq.com/cgi-bin/poi/updatepoi?access_token=TOKEN";
    /**
     * 删除门店 url，请求方式为 POST
     */
    private static final String DEL_POI_URL = "https://api.weixin.qq.com/cgi-bin/poi/delpoi?access_token=TOKEN";
    /**
     * 获取门店类目表 url，请求方式为 GET
     */
    private static final String GET_WXCATEGORY_URL = "http://api.weixin.qq.com/cgi-bin/poi/getwxcategory?access_token=TOKEN";

    /**
     * 创建门店
     *
     * @param json 门店信息数据
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject addPoi(String json) {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = ADD_POI_URL.replace("TOKEN", accessToken);
        String result = HttpUtils.post(url, json);

        return JSON.parseObject(result);
    }

    /**
     * 查询门店信息
     *
     * @param poiId 门店的poi_id
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject getPoi(String poiId) {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = GET_POI_URL.replace("TOKEN", accessToken);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("poi_id", poiId);
        String result = HttpUtils.post(url, jsonObject.toJSONString());

        return JSON.parseObject(result);
    }

    /**
     * 查询门店列表
     *
     * @param begin 开始位置，0 即为从第一条开始查询
     * @param limit 返回数据条数，最大允许50，默认为20
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject getPoiList(int begin, int limit) {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = GET_POI_LIST_URL.replace("TOKEN", accessToken);

        if (begin < 0) begin = 0;
        if (limit < 0) limit = 20;
        else if (limit > 50) limit = 50;

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("begin", begin);
        jsonObject.put("limit", limit);
        String result = HttpUtils.post(url, jsonObject.toJSONString());

        return JSON.parseObject(result);
    }

    /**
     * 修改门店服务信息
     *
     * @param json 门店信息数据
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject updatePoi(String json) {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = UPDATE_POI_URL.replace("TOKEN", accessToken);
        String result = HttpUtils.post(url, json);

        return JSON.parseObject(result);
    }

    /**
     * 删除门店
     *
     * @param poiId 门店的poi_id
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject delPoi(String poiId) {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = DEL_POI_URL.replace("TOKEN", accessToken);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("poi_id", poiId);
        String result = HttpUtils.post(url, jsonObject.toJSONString());

        return JSON.parseObject(result);
    }

    /**
     * 获取门店类目表
     *
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject getWxCategory() {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = GET_WXCATEGORY_URL.replace("TOKEN", accessToken);

        String result = HttpUtils.get(url);

        return JSON.parseObject(result);
    }
}
