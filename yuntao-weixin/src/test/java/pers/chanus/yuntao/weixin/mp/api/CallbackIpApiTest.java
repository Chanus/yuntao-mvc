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

/**
 * CallbackIpApi 测试
 *
 * @author Chanus
 * @date 2020-05-18 15:43:08
 * @since 0.1.9
 */
public class CallbackIpApiTest extends ApiConfigTest {
    @Test
    public void getCallBackIpTest() {
        JSONObject jsonObject = CallbackIpApi.getCallBackIp();
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void getApiIpTest() {
        JSONObject jsonObject = CallbackIpApi.getApiIp();
        System.out.println(jsonObject.toJSONString());
    }
}
