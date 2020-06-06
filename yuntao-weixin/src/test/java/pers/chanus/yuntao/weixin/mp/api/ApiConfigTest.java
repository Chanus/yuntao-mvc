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
        String appId = "wx16232f77ef463d30";
        String appSecret = "95a8506000fd232e948608e567b32faf";
        AccessTokenApi.apiConfig = new ApiConfig(appId, appSecret);
    }
}
