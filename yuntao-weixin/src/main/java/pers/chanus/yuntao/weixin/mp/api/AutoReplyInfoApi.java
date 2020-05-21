/*
 * Copyright (c), 2020-persent, Chanus and/or its affiliates. All rights reserved.
 * FileName: AutoReplyInfoApi
 * Author:   Chanus
 * Date:     2020-05-21 23:15:27
 * Description: 获取自动回复规则 API
 * History:
 * <author>          <time>          <version>          <desc>
 */
package pers.chanus.yuntao.weixin.mp.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import pers.chanus.yuntao.util.HttpUtils;

/**
 * 获取自动回复规则 API<br>
 * 详情请见：https://developers.weixin.qq.com/doc/offiaccount/Message_Management/Getting_Rules_for_Auto_Replies.html
 *
 * @author Chanus
 * @date 2020-05-21 23:15:27
 * @since 0.1.9
 */
public class AutoReplyInfoApi {
    /**
     * 获取自动回复规则 url，请求方式为 GET
     */
    private static final String GET_CURRENT_AUTOREPLY_INFO_URL = "https://api.weixin.qq.com/cgi-bin/get_current_autoreply_info?access_token=ACCESS_TOKEN";

    /**
     * 获取自动回复规则
     *
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject getCurrentAutoreplyInfo() {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = GET_CURRENT_AUTOREPLY_INFO_URL.replace("ACCESS_TOKEN", accessToken);

        String result = HttpUtils.get(url);

        return JSON.parseObject(result);
    }
}
