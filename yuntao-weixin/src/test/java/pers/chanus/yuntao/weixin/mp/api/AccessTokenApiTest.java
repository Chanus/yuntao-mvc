/*
 * Copyright (c), 2020-persent, Chanus and/or its affiliates. All rights reserved.
 * FileName: AccessTokenApiTest
 * Author:   Chanus
 * Date:     2020-05-17 17:30:37
 * Description: AccessTokenApi 测试
 * History:
 * <author>          <time>          <version>          <desc>
 */
package pers.chanus.yuntao.weixin.mp.api;

import org.junit.Test;
import pers.chanus.yuntao.weixin.mp.api.bean.AccessToken;
import pers.chanus.yuntao.weixin.mp.api.bean.ApiConfig;

/**
 * AccessTokenApi 测试
 *
 * @author Chanus
 * @date 2020-05-17 17:30:37
 * @since 0.1.9
 */
public class AccessTokenApiTest {
    private String appId = "wxe1b1996ffb222290";
    private String appSecret = "3eff2d48cab4356dd096228528a1537c";

    @Test
    public void getAccessTokenTest() {
        AccessTokenApi.apiConfig = new ApiConfig(appId, appSecret);
        AccessToken accessToken = AccessTokenApi.getAccessToken();
        System.out.println(accessToken.toString());
    }

    @Test
    public void getAccessTokenStrTest() {
        AccessTokenApi.apiConfig = new ApiConfig(appId, appSecret);
        String accessToken = AccessTokenApi.getAccessTokenStr();
        System.out.println(accessToken);
    }
}
