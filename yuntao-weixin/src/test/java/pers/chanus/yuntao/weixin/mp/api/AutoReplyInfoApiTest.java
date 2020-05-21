/*
 * Copyright (c), 2020-persent, Chanus and/or its affiliates. All rights reserved.
 * FileName: AutoReplyInfoApiTest
 * Author:   Chanus
 * Date:     2020-05-21 23:20:12
 * Description: AutoReplyInfoApi 测试
 * History:
 * <author>          <time>          <version>          <desc>
 */
package pers.chanus.yuntao.weixin.mp.api;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import pers.chanus.yuntao.weixin.mp.api.bean.ApiConfig;

/**
 * AutoReplyInfoApi 测试
 *
 * @author Chanus
 * @date 2020-05-21 23:20:12
 * @since 0.1.9
 */
public class AutoReplyInfoApiTest {
    private String appId = "wxe1b1996ffb222290";
    private String appSecret = "3eff2d48cab4356dd096228528a1537c";

    @Test
    public void getCurrentAutoreplyInfoTest() {
        AccessTokenApi.apiConfig = new ApiConfig(appId, appSecret);
        JSONObject jsonObject = AutoReplyInfoApi.getCurrentAutoreplyInfo();
        System.out.println(jsonObject);
    }
}
