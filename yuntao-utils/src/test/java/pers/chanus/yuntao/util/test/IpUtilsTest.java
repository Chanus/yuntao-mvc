/*
 * Copyright (c), 2019-2020, Chanus and/or its affiliates. All rights reserved.
 * FileName: IpUtilsTest
 * Author:   Chanus
 * Date:     2020/3/1 14:06
 * Description: 工具类测试
 * History:
 * <author>          <time>          <version>          <desc>
 */
package pers.chanus.yuntao.util.test;

import org.junit.Test;
import pers.chanus.yuntao.util.IpUtils;

/**
 * IpUtils工具类测试
 *
 * @author Chanus
 * @date 2020/3/1 14:06
 * @since 0.1.5
 */
public class IpUtilsTest {
    @Test
    public void getIpPhysicalAddressTest() {
        String ip = "127.0.0.1";

        IpUtils.IpAddress ipAddress = IpUtils.getIpPhysicalAddress(ip);
        System.out.println(ipAddress.toString());
    }
}
