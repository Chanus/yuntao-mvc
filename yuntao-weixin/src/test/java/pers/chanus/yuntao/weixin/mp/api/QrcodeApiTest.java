/*
 * Copyright (c), 2020-persent, Chanus and/or its affiliates. All rights reserved.
 * FileName: QrcodeApiTest
 * Author:   Chanus
 * Date:     2020-05-18 01:10:12
 * Description: QrcodeApi 测试
 * History:
 * <author>          <time>          <version>          <desc>
 */
package pers.chanus.yuntao.weixin.mp.api;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import pers.chanus.yuntao.weixin.mp.api.bean.ApiConfig;

/**
 * QrcodeApi 测试
 *
 * @author Chanus
 * @date 2020-05-18 01:10:12
 * @since 0.1.9
 */
public class QrcodeApiTest {
    private String appId = "wxe1b1996ffb222290";
    private String appSecret = "3eff2d48cab4356dd096228528a1537c";

    @Test
    public void createTemporary() {
        AccessTokenApi.apiConfig = new ApiConfig(appId, appSecret);

        JSONObject jsonObject1 = QrcodeApi.createTemporary(10000, 1);
        System.out.println(jsonObject1.toJSONString());

        JSONObject jsonObject2 = QrcodeApi.createTemporary(10000, "test");
        System.out.println(jsonObject2.toJSONString());
    }

    @Test
    public void createPermanentTest() {
        AccessTokenApi.apiConfig = new ApiConfig(appId, appSecret);

        JSONObject jsonObject1 = QrcodeApi.createPermanent(1);
        System.out.println(jsonObject1.toJSONString());

        JSONObject jsonObject2 = QrcodeApi.createPermanent("test");
        System.out.println(jsonObject2.toJSONString());
    }

    @Test
    public void getShowQrcodeUrlTest() {
        String ticket = "gQEo8TwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAyNjBfaXQwV3djYUQxMDAwMHcwN3UAAgSFccFeAwQAAAAA";
        String qrcodeUrl = QrcodeApi.getShowQrcodeUrl(ticket);

        System.out.println(qrcodeUrl);
    }
}
