/*
 * Copyright (c), 2020-persent, Chanus and/or its affiliates. All rights reserved.
 * FileName: CallbackIpApiTest
 * Author:   Chanus
 * Date:     2020-05-18 15:43:08
 * Description: CallbackIpApi 测试
 * History:
 * <author>          <time>          <version>          <desc>
 */
package pers.chanus.yuntao.weixin.mp.api;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import pers.chanus.yuntao.weixin.mp.api.bean.ApiConfig;

/**
 * CallbackIpApi 测试
 *
 * @author Chanus
 * @date 2020-05-18 15:43:08
 * @since 0.1.9
 */
public class CallbackIpApiTest {
    private String appId = "wxe1b1996ffb222290";
    private String appSecret = "3eff2d48cab4356dd096228528a1537c";

    @Test
    public void getCallBackIpTest() {
        AccessTokenApi.apiConfig = new ApiConfig(appId, appSecret);
        JSONObject jsonObject = CallbackIpApi.getCallBackIp();
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void getApiIpTest() {
        AccessTokenApi.apiConfig = new ApiConfig(appId, appSecret);
        JSONObject jsonObject = CallbackIpApi.getApiIp();
        System.out.println(jsonObject.toJSONString());
    }
}
