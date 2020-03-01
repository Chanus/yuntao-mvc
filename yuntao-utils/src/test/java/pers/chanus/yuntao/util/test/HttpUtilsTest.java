/*
 * Copyright (c), 2019-2020, Chanus and/or its affiliates. All rights reserved.
 * FileName: HttpUtilsTest
 * Author:   Chanus
 * Date:     2020/3/1 12:54
 * Description: 工具类测试
 * History:
 * <author>          <time>          <version>          <desc>
 */
package pers.chanus.yuntao.util.test;

import org.junit.Test;
import pers.chanus.yuntao.util.HttpUtils;
import pers.chanus.yuntao.util.IpUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * HttpUtils工具类测试
 *
 * @author Chanus
 * @date 2020/3/1 12:54
 * @since 0.0.1
 */
public class HttpUtilsTest {
    @Test
    public void getTest() {
        String url = "http://api.pi.do/api/v1/queryip";
        String ip = "36.4.195.250";
        String result1 = HttpUtils.get(url + "?ip=" +  ip);
        System.out.println("result1 == " + result1);

        Map<String, Object> params = new HashMap<>();
        params.put("ip", ip);
        String result2 = HttpUtils.get(url, params);
        System.out.println("result2 == " + result2);

        HttpUtils.getAsyn(url + "?ip=" +  ip, result -> System.out.println("result3 == " + result));

        IpUtils.IpAddress ipAddress = new IpUtils.IpAddress();
    }
}
