/*
 * Copyright (c), 2020-persent, Chanus and/or its affiliates. All rights reserved.
 * FileName: ApiConfigTest
 * Author:   Chanus
 * Date:     2020-06-06 13:08:38
 * Description: ApiConfig
 * History:
 * <author>          <time>          <version>          <desc>
 */
package pers.chanus.yuntao.weixin.mp.api;

import org.junit.Before;
import pers.chanus.yuntao.weixin.mp.api.bean.ApiConfig;

/**
 * ApiConfig
 *
 * @author Chanus
 * @date 2020-06-06 13:08:38
 * @since 0.1.9
 */
public class ApiConfigTest {
    @Before
    public void init() {
        System.out.println("init apiConfig");
        String appId = "wx4c0b9bf56ed6df6a";
        String appSecret = "657ddf71ed42456142380010e2be68a5";
        AccessTokenApi.apiConfig = new ApiConfig(appId, appSecret);
    }
}
