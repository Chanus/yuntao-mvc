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

/**
 * AccessTokenApi 测试
 *
 * @author Chanus
 * @date 2020-05-17 17:30:37
 * @since 0.1.9
 */
public class AccessTokenApiTest extends ApiConfigTest {
    @Test
    public void getAccessTokenTest() {
        AccessToken accessToken = AccessTokenApi.getAccessToken();
        System.out.println(accessToken.toString());
    }

    @Test
    public void getAccessTokenStrTest() {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        System.out.println(accessToken);
    }
}
