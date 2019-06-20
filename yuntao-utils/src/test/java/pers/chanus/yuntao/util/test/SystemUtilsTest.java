/*
 * Copyright (c), 2019-2019, Chanus and/or its affiliates. All rights reserved.
 * FileName: SystemUtilsTest
 * Author:   Chanus
 * Date:     2019-06-20 17:10
 * Description: 工具类测试
 * History:
 * <author>          <time>          <version>          <desc>
 */
package pers.chanus.yuntao.util.test;

import org.junit.Test;
import pers.chanus.yuntao.util.SystemUtils;

/**
 * SystemUtils工具类测试
 *
 * @author Chanus
 * @date 2019-06-20 17:10
 * @since 0.1.2
 */
public class SystemUtilsTest {
    @Test
    public void test() {
        System.out.println("JDK的版本: " + SystemUtils.JDK_VERSION);
        System.out.println("JVM的编码: " + SystemUtils.JVM_ENCODING);
        System.out.println("操作系统名称: " + SystemUtils.OS_NAME);
        System.out.println("操作系统版本: " + SystemUtils.OS_VERSION);
        System.out.println("主机架构: " + SystemUtils.OS_ARCH);
        System.out.println("主机名: " + SystemUtils.HOST_NAME);
        System.out.println("主机mac地址: " + SystemUtils.HOST_MAC);
        System.out.println("主机本地IP: " + SystemUtils.HOST_IP);
        System.out.println("当前用户: " + SystemUtils.CURRENT_USER);
        System.out.println("当前用户的家目录: " + SystemUtils.CURRENT_USER_HOME);
        System.out.println("系统总的物理内存: " + SystemUtils.totalPhysicalMemorySize);
        System.out.println("1KB = " + SystemUtils.KB + "B");
        System.out.println("1MB = " + SystemUtils.MB + "B");
        System.out.println("1GB = " + SystemUtils.GB + "B");
        System.out.println("1TB = " + SystemUtils.TB + "B");
        System.out.println("可用的物理内存: " + SystemUtils.getFreePhysicalMemorySize());
        System.out.println("已使用的物理内存: " + SystemUtils.getUsedPhysicalMemorySize());
        System.out.println("JVM总的内存空间: " + SystemUtils.getTotalJVMMemorySize());
        System.out.println("JVM空闲的内存空间: " + SystemUtils.getFreeJVMMemorySize());
        System.out.println("JVM已用的内存空间: " + SystemUtils.getUsedJVMMemorySize());
        System.out.println("JVM最大的内存空间: " + SystemUtils.getMaxJVMMemorySize());
    }
}
