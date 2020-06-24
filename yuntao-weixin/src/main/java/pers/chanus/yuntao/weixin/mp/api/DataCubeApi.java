/*
 * Copyright (c), 2020-persent, Chanus and/or its affiliates. All rights reserved.
 * FileName: DataCubeApi
 * Author:   Chanus
 * Date:     2020-06-08 20:36:54
 * Description: 数据统计 API
 * History:
 * <author>          <time>          <version>          <desc>
 */
package pers.chanus.yuntao.weixin.mp.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chanus.yuntao.utils.core.HttpUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 数据统计 API<br>
 * 详情请见：
 * <pre>
 *     用户分析：https://developers.weixin.qq.com/doc/offiaccount/Analytics/User_Analysis_Data_Interface.html
 *     图文分析：https://developers.weixin.qq.com/doc/offiaccount/Analytics/Graphic_Analysis_Data_Interface.html
 *     消息分析：https://developers.weixin.qq.com/doc/offiaccount/Analytics/Message_analysis_data_interface.html
 *     广告分析：https://developers.weixin.qq.com/doc/offiaccount/Analytics/Ad_Analysis.html
 *     接口分析：https://developers.weixin.qq.com/doc/offiaccount/Analytics/Analytics_API.html
 * </pre>
 *
 * @author Chanus
 * @date 2020-06-08 20:36:54
 * @since 0.1.9
 */
public class DataCubeApi {
    /**
     * 获取用户增减数据 url，请求方式为 POST
     */
    private static final String GET_USER_SUMMARY_URL = "https://api.weixin.qq.com/datacube/getusersummary?access_token=ACCESS_TOKEN";
    /**
     * 获取累计用户数据 url，请求方式为 POST
     */
    private static final String GET_USER_CUMULATE_URL = "https://api.weixin.qq.com/datacube/getusercumulate?access_token=ACCESS_TOKEN";
    /**
     * 获取图文群发每日数据 url，请求方式为 POST
     */
    private static final String GET_ARTICLE_SUMMARY_URL = "https://api.weixin.qq.com/datacube/getarticlesummary?access_token=ACCESS_TOKEN";
    /**
     * 获取图文群发总数据 url，请求方式为 POST
     */
    private static final String GET_ARTICLE_TOTAL_URL = "https://api.weixin.qq.com/datacube/getarticletotal?access_token=ACCESS_TOKEN";
    /**
     * 获取图文统计数据 url，请求方式为 POST
     */
    private static final String GET_USER_READ_URL = "https://api.weixin.qq.com/datacube/getuserread?access_token=ACCESS_TOKEN";
    /**
     * 获取图文统计分时数据 url，请求方式为 POST
     */
    private static final String GET_USER_READ_HOUR_URL = "https://api.weixin.qq.com/datacube/getuserreadhour?access_token=ACCESS_TOKEN";
    /**
     * 获取图文分享转发数据 url，请求方式为 POST
     */
    private static final String GET_USER_SHARE_URL = "https://api.weixin.qq.com/datacube/getusershare?access_token=ACCESS_TOKEN";
    /**
     * 获取图文分享转发分时数据 url，请求方式为 POST
     */
    private static final String GET_USER_SHARE_HOUR_URL = "https://api.weixin.qq.com/datacube/getusersharehour?access_token=ACCESS_TOKEN";
    /**
     * 获取消息发送概况数据 url，请求方式为 POST
     */
    private static final String GET_UP_STREAM_MSG_URL = "https://api.weixin.qq.com/datacube/getupstreammsg?access_token=ACCESS_TOKEN";
    /**
     * 获取消息分送分时数据 url，请求方式为 POST
     */
    private static final String GET_UP_STREAM_MSG_HOUR_URL = "https://api.weixin.qq.com/datacube/getupstreammsghour?access_token=ACCESS_TOKEN";
    /**
     * 获取消息发送周数据 url，请求方式为 POST
     */
    private static final String GET_UP_STREAM_MSG_WEEK_URL = "https://api.weixin.qq.com/datacube/getupstreammsgweek?access_token=ACCESS_TOKEN";
    /**
     * 获取消息发送月数据 url，请求方式为 POST
     */
    private static final String GET_UP_STREAM_MSG_MONTH_URL = "https://api.weixin.qq.com/datacube/getupstreammsgmonth?access_token=ACCESS_TOKEN";
    /**
     * 获取消息发送分布数据 url，请求方式为 POST
     */
    private static final String GET_UP_STREAM_MSG_DIST_URL = "https://api.weixin.qq.com/datacube/getupstreammsgdist?access_token=ACCESS_TOKEN";
    /**
     * 获取消息发送分布周数据 url，请求方式为 POST
     */
    private static final String GET_UP_STREAM_MSG_DIST_WEEK_URL = "https://api.weixin.qq.com/datacube/getupstreammsgdistweek?access_token=ACCESS_TOKEN";
    /**
     * 获取消息发送分布月数据 url，请求方式为 POST
     */
    private static final String GET_UP_STREAM_MSG_DIST_MONTH_URL = "https://api.weixin.qq.com/datacube/getupstreammsgdistmonth?access_token=ACCESS_TOKEN";
    /**
     * 获取公众号分广告位数据 url，请求方式为 GET
     */
    private static final String PUBLISHER_AD_POS_GENERAL_URL = "https://api.weixin.qq.com/publisher/stat?action=publisher_adpos_general&access_token=ACCESS_TOKEN";
    /**
     * 获取公众号返佣商品数据 url，请求方式为 GET
     */
    private static final String PUBLISHER_CPS_GENERAL_URL = "https://api.weixin.qq.com/publisher/stat?action=publisher_cps_general&access_token=ACCESS_TOKEN";
    /**
     * 获取公众号结算收入数据及结算主体信息 url，请求方式为 GET
     */
    private static final String PUBLISHER_SETTLEMENT_URL = "https://api.weixin.qq.com/publisher/stat?action=publisher_settlement&access_token=ACCESS_TOKEN";
    /**
     * 获取接口分析数据 url，请求方式为 GET
     */
    private static final String GET_INTERFACE_SUMMARY_URL = "https://api.weixin.qq.com/datacube/getinterfacesummary?access_token=ACCESS_TOKEN";
    /**
     * 获取接口分析分时数据 url，请求方式为 GET
     */
    private static final String GET_INTERFACE_SUMMARY_HOUR_URL = "https://api.weixin.qq.com/datacube/getinterfacesummaryhour?access_token=ACCESS_TOKEN";

    /**
     * 获取用户增减数据，最大时间跨度为7天
     *
     * @param beginDate 获取数据的起始日期，begin_date和end_date的差值需小于“最大时间跨度”（比如最大时间跨度为1时，begin_date和end_date的差值只能为0，才能小于1），否则会报错
     * @param endDate   获取数据的结束日期，end_date允许设置的最大值为昨日
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject getUserSummary(String beginDate, String endDate) {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = GET_USER_SUMMARY_URL.replace("ACCESS_TOKEN", accessToken);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("begin_date", beginDate);
        jsonObject.put("end_date", endDate);
        String result = HttpUtils.post(url, jsonObject.toJSONString());

        return JSON.parseObject(result);
    }

    /**
     * 获取累计用户数据，最大时间跨度为7天
     *
     * @param beginDate 获取数据的起始日期，begin_date和end_date的差值需小于“最大时间跨度”（比如最大时间跨度为1时，begin_date和end_date的差值只能为0，才能小于1），否则会报错
     * @param endDate   获取数据的结束日期，end_date允许设置的最大值为昨日
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject getUserCumulate(String beginDate, String endDate) {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = GET_USER_CUMULATE_URL.replace("ACCESS_TOKEN", accessToken);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("begin_date", beginDate);
        jsonObject.put("end_date", endDate);
        String result = HttpUtils.post(url, jsonObject.toJSONString());

        return JSON.parseObject(result);
    }

    /**
     * 获取图文群发每日数据，最大时间跨度为1天
     *
     * @param beginDate 获取数据的起始日期，begin_date和end_date的差值需小于“最大时间跨度”（比如最大时间跨度为1时，begin_date和end_date的差值只能为0，才能小于1），否则会报错
     * @param endDate   获取数据的结束日期，end_date允许设置的最大值为昨日
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject getArticleSummary(String beginDate, String endDate) {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = GET_ARTICLE_SUMMARY_URL.replace("ACCESS_TOKEN", accessToken);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("begin_date", beginDate);
        jsonObject.put("end_date", endDate);
        String result = HttpUtils.post(url, jsonObject.toJSONString());

        return JSON.parseObject(result);
    }

    /**
     * 获取图文群发总数据，最大时间跨度为1天
     *
     * @param beginDate 获取数据的起始日期，begin_date和end_date的差值需小于“最大时间跨度”（比如最大时间跨度为1时，begin_date和end_date的差值只能为0，才能小于1），否则会报错
     * @param endDate   获取数据的结束日期，end_date允许设置的最大值为昨日
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject getArticleTotal(String beginDate, String endDate) {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = GET_ARTICLE_TOTAL_URL.replace("ACCESS_TOKEN", accessToken);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("begin_date", beginDate);
        jsonObject.put("end_date", endDate);
        String result = HttpUtils.post(url, jsonObject.toJSONString());

        return JSON.parseObject(result);
    }

    /**
     * 获取图文统计数据，最大时间跨度为1天
     *
     * @param beginDate 获取数据的起始日期，begin_date和end_date的差值需小于“最大时间跨度”（比如最大时间跨度为1时，begin_date和end_date的差值只能为0，才能小于1），否则会报错
     * @param endDate   获取数据的结束日期，end_date允许设置的最大值为昨日
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject getUserRead(String beginDate, String endDate) {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = GET_USER_READ_URL.replace("ACCESS_TOKEN", accessToken);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("begin_date", beginDate);
        jsonObject.put("end_date", endDate);
        String result = HttpUtils.post(url, jsonObject.toJSONString());

        return JSON.parseObject(result);
    }

    /**
     * 获取图文统计分时数据，最大时间跨度为1天
     *
     * @param beginDate 获取数据的起始日期，begin_date和end_date的差值需小于“最大时间跨度”（比如最大时间跨度为1时，begin_date和end_date的差值只能为0，才能小于1），否则会报错
     * @param endDate   获取数据的结束日期，end_date允许设置的最大值为昨日
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject getUserReadHour(String beginDate, String endDate) {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = GET_USER_READ_HOUR_URL.replace("ACCESS_TOKEN", accessToken);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("begin_date", beginDate);
        jsonObject.put("end_date", endDate);
        String result = HttpUtils.post(url, jsonObject.toJSONString());

        return JSON.parseObject(result);
    }

    /**
     * 获取图文分享转发数据，最大时间跨度为7天
     *
     * @param beginDate 获取数据的起始日期，begin_date和end_date的差值需小于“最大时间跨度”（比如最大时间跨度为1时，begin_date和end_date的差值只能为0，才能小于1），否则会报错
     * @param endDate   获取数据的结束日期，end_date允许设置的最大值为昨日
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject getUserShare(String beginDate, String endDate) {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = GET_USER_SHARE_URL.replace("ACCESS_TOKEN", accessToken);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("begin_date", beginDate);
        jsonObject.put("end_date", endDate);
        String result = HttpUtils.post(url, jsonObject.toJSONString());

        return JSON.parseObject(result);
    }

    /**
     * 获取图文分享转发分时数据，最大时间跨度为1天
     *
     * @param beginDate 获取数据的起始日期，begin_date和end_date的差值需小于“最大时间跨度”（比如最大时间跨度为1时，begin_date和end_date的差值只能为0，才能小于1），否则会报错
     * @param endDate   获取数据的结束日期，end_date允许设置的最大值为昨日
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject getUserShareHour(String beginDate, String endDate) {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = GET_USER_SHARE_HOUR_URL.replace("ACCESS_TOKEN", accessToken);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("begin_date", beginDate);
        jsonObject.put("end_date", endDate);
        String result = HttpUtils.post(url, jsonObject.toJSONString());

        return JSON.parseObject(result);
    }

    /**
     * 获取消息发送概况数据，最大时间跨度为7天
     *
     * @param beginDate 获取数据的起始日期，begin_date和end_date的差值需小于“最大时间跨度”（比如最大时间跨度为1时，begin_date和end_date的差值只能为0，才能小于1），否则会报错
     * @param endDate   获取数据的结束日期，end_date允许设置的最大值为昨日
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject getUpStreamMsg(String beginDate, String endDate) {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = GET_UP_STREAM_MSG_URL.replace("ACCESS_TOKEN", accessToken);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("begin_date", beginDate);
        jsonObject.put("end_date", endDate);
        String result = HttpUtils.post(url, jsonObject.toJSONString());

        return JSON.parseObject(result);
    }

    /**
     * 获取消息分送分时数据，最大时间跨度为1天
     *
     * @param beginDate 获取数据的起始日期，begin_date和end_date的差值需小于“最大时间跨度”（比如最大时间跨度为1时，begin_date和end_date的差值只能为0，才能小于1），否则会报错
     * @param endDate   获取数据的结束日期，end_date允许设置的最大值为昨日
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject getUpStreamMsgHour(String beginDate, String endDate) {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = GET_UP_STREAM_MSG_HOUR_URL.replace("ACCESS_TOKEN", accessToken);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("begin_date", beginDate);
        jsonObject.put("end_date", endDate);
        String result = HttpUtils.post(url, jsonObject.toJSONString());

        return JSON.parseObject(result);
    }

    /**
     * 获取消息发送周数据，最大时间跨度为30天
     *
     * @param beginDate 获取数据的起始日期，begin_date和end_date的差值需小于“最大时间跨度”（比如最大时间跨度为1时，begin_date和end_date的差值只能为0，才能小于1），否则会报错
     * @param endDate   获取数据的结束日期，end_date允许设置的最大值为昨日
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject getUpStreamMsgWeek(String beginDate, String endDate) {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = GET_UP_STREAM_MSG_WEEK_URL.replace("ACCESS_TOKEN", accessToken);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("begin_date", beginDate);
        jsonObject.put("end_date", endDate);
        String result = HttpUtils.post(url, jsonObject.toJSONString());

        return JSON.parseObject(result);
    }

    /**
     * 获取消息发送月数据，最大时间跨度为30天
     *
     * @param beginDate 获取数据的起始日期，begin_date和end_date的差值需小于“最大时间跨度”（比如最大时间跨度为1时，begin_date和end_date的差值只能为0，才能小于1），否则会报错
     * @param endDate   获取数据的结束日期，end_date允许设置的最大值为昨日
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject getUpStreamMsgMonth(String beginDate, String endDate) {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = GET_UP_STREAM_MSG_MONTH_URL.replace("ACCESS_TOKEN", accessToken);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("begin_date", beginDate);
        jsonObject.put("end_date", endDate);
        String result = HttpUtils.post(url, jsonObject.toJSONString());

        return JSON.parseObject(result);
    }

    /**
     * 获取消息发送分布数据，最大时间跨度为15天
     *
     * @param beginDate 获取数据的起始日期，begin_date和end_date的差值需小于“最大时间跨度”（比如最大时间跨度为1时，begin_date和end_date的差值只能为0，才能小于1），否则会报错
     * @param endDate   获取数据的结束日期，end_date允许设置的最大值为昨日
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject getUpStreamMsgDist(String beginDate, String endDate) {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = GET_UP_STREAM_MSG_DIST_URL.replace("ACCESS_TOKEN", accessToken);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("begin_date", beginDate);
        jsonObject.put("end_date", endDate);
        String result = HttpUtils.post(url, jsonObject.toJSONString());

        return JSON.parseObject(result);
    }

    /**
     * 获取消息发送分布周数据，最大时间跨度为30天
     *
     * @param beginDate 获取数据的起始日期，begin_date和end_date的差值需小于“最大时间跨度”（比如最大时间跨度为1时，begin_date和end_date的差值只能为0，才能小于1），否则会报错
     * @param endDate   获取数据的结束日期，end_date允许设置的最大值为昨日
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject getUpStreamMsgDistWeek(String beginDate, String endDate) {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = GET_UP_STREAM_MSG_DIST_WEEK_URL.replace("ACCESS_TOKEN", accessToken);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("begin_date", beginDate);
        jsonObject.put("end_date", endDate);
        String result = HttpUtils.post(url, jsonObject.toJSONString());

        return JSON.parseObject(result);
    }

    /**
     * 获取消息发送分布月数据，最大时间跨度为30天
     *
     * @param beginDate 获取数据的起始日期，begin_date和end_date的差值需小于“最大时间跨度”（比如最大时间跨度为1时，begin_date和end_date的差值只能为0，才能小于1），否则会报错
     * @param endDate   获取数据的结束日期，end_date允许设置的最大值为昨日
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject getUpStreamMsgDistMonth(String beginDate, String endDate) {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = GET_UP_STREAM_MSG_DIST_MONTH_URL.replace("ACCESS_TOKEN", accessToken);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("begin_date", beginDate);
        jsonObject.put("end_date", endDate);
        String result = HttpUtils.post(url, jsonObject.toJSONString());

        return JSON.parseObject(result);
    }

    /**
     * 获取公众号分广告位数据，最大时间跨度为90天
     *
     * @param page      返回第几页数据
     * @param pageSize  当页返回数据条数
     * @param startDate 获取数据的开始时间 yyyy-mm-dd
     * @param endDate   获取数据的结束时间 yyyy-mm-dd
     * @param adSlot    广告位类型名称
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject publisherAdPosGeneral(int page, int pageSize, String startDate, String endDate, String adSlot) {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = PUBLISHER_AD_POS_GENERAL_URL.replace("ACCESS_TOKEN", accessToken);

        Map<String, Object> params = new HashMap<>();
        params.put("page", page);
        params.put("page_size", pageSize);
        params.put("start_date", startDate);
        params.put("end_date", endDate);
        params.put("ad_slot", adSlot);
        String result = HttpUtils.get(url, params);

        return JSON.parseObject(result);
    }

    /**
     * 获取公众号返佣商品数据，最大时间跨度为60天
     *
     * @param page      返回第几页数据
     * @param pageSize  当页返回数据条数
     * @param startDate 获取数据的开始时间 yyyy-mm-dd
     * @param endDate   获取数据的结束时间 yyyy-mm-dd
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject publisherCpsGeneral(int page, int pageSize, String startDate, String endDate) {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = PUBLISHER_CPS_GENERAL_URL.replace("ACCESS_TOKEN", accessToken);

        Map<String, Object> params = new HashMap<>();
        params.put("page", page);
        params.put("page_size", pageSize);
        params.put("start_date", startDate);
        params.put("end_date", endDate);
        String result = HttpUtils.get(url, params);

        return JSON.parseObject(result);
    }

    /**
     * 获取公众号结算收入数据及结算主体信息
     *
     * @param page      返回第几页数据
     * @param pageSize  当页返回数据条数
     * @param startDate 获取数据的开始时间 yyyy-mm-dd
     * @param endDate   获取数据的结束时间 yyyy-mm-dd
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject publisherSettlement(int page, int pageSize, String startDate, String endDate) {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = PUBLISHER_SETTLEMENT_URL.replace("ACCESS_TOKEN", accessToken);

        Map<String, Object> params = new HashMap<>();
        params.put("page", page);
        params.put("page_size", pageSize);
        params.put("start_date", startDate);
        params.put("end_date", endDate);
        String result = HttpUtils.get(url, params);

        return JSON.parseObject(result);
    }

    /**
     * 获取接口分析数据，最大时间跨度为30天
     *
     * @param beginDate 获取数据的起始日期，begin_date和end_date的差值需小于“最大时间跨度”（比如最大时间跨度为1时，begin_date和end_date的差值只能为0，才能小于1），否则会报错
     * @param endDate   获取数据的结束日期，end_date允许设置的最大值为昨日
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject getInterfaceSummary(String beginDate, String endDate) {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = GET_INTERFACE_SUMMARY_URL.replace("ACCESS_TOKEN", accessToken);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("begin_date", beginDate);
        jsonObject.put("end_date", endDate);
        String result = HttpUtils.post(url, jsonObject.toJSONString());

        return JSON.parseObject(result);
    }

    /**
     * 获取接口分析分时数据，最大时间跨度为1天
     *
     * @param beginDate 获取数据的起始日期，begin_date和end_date的差值需小于“最大时间跨度”（比如最大时间跨度为1时，begin_date和end_date的差值只能为0，才能小于1），否则会报错
     * @param endDate   获取数据的结束日期，end_date允许设置的最大值为昨日
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject getInterfaceSummaryHour(String beginDate, String endDate) {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = GET_INTERFACE_SUMMARY_HOUR_URL.replace("ACCESS_TOKEN", accessToken);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("begin_date", beginDate);
        jsonObject.put("end_date", endDate);
        String result = HttpUtils.post(url, jsonObject.toJSONString());

        return JSON.parseObject(result);
    }
}
