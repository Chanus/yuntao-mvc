/*
 * Copyright (c), 2019-2019, Chanus and/or its affiliates. All rights reserved.
 * FileName: RandomUtilsTest
 * Author:   Chanus
 * Date:     2019-06-11 19:07
 * Description: 工具类测试
 * History:
 * <author>          <time>          <version>          <desc>
 */
package pers.chanus.yuntao.util.test;

import org.junit.Test;
import pers.chanus.yuntao.util.RandomUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * RandomUtils工具类测试
 *
 * @author Chanus
 * @date 2019-06-11 19:07
 * @since 0.1.2
 */
public class RandomUtilsTest {
    @Test
    public void getRandomIntTest() {
        for (int i = 0; i < 10; i++) {
            System.out.println(RandomUtils.getRandomInt(10));
        }
        int i1 = RandomUtils.getRandomInt();
        int i2 = RandomUtils.getRandomInt(100);
        int i3 = RandomUtils.getRandomInt(100, 1000);

        System.out.println("随机int数值：" + i1);
        System.out.println("0-100之间的随机int数值：" + i2);
        System.out.println("100-1000之间的随机int数值：" + i3);
    }

    @Test
    public void getRandomDigitsTest() throws InterruptedException {
        int length = 10;

        ExecutorService executorService = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 10; i++) {
            executorService.execute(() -> {
                for (int j = 0; j < 10; j++) {
                    System.out.println("随机数字字符串：" + RandomUtils.getRandomDigits(length));
                }
            });
        }

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.DAYS);
    }

    @Test
    public void getRandomLongTest() {
        long l1 = RandomUtils.getRandomLong();
        long l2 = RandomUtils.getRandomLong(100L);
        long l3 = RandomUtils.getRandomLong(100L, 1000L);

        System.out.println("随机long数值：" + l1);
        System.out.println("0-100之间的随机long数值：" + l2);
        System.out.println("100-1000之间的随机long数值：" + l3);
    }

    @Test
    public void getRandomDoubleTest() {
        double d1 = RandomUtils.getRandomDouble();
        double d2 = RandomUtils.getRandomDouble(100.0D);
        double d3 = RandomUtils.getRandomDouble(100.0D, 1000.0D);

        System.out.println("随机double数值：" + d1);
        System.out.println("0-100之间的随机double数值：" + d2);
        System.out.println("100-1000之间的随机double数值：" + d3);
    }

    @Test
    public void getRandomCharTest() {
        System.out.println(RandomUtils.getRandomChar());
    }

    @Test
    public void getRandomNormalCharTest() {
        System.out.println(RandomUtils.getRandomNormalChar());
    }

    @Test
    public void getRandomLetterCharTest() {
        System.out.println(RandomUtils.getRandomLetterChar());
    }

    @Test
    public void getRandomStringTest() {
        System.out.println(RandomUtils.getRandomString(10));
    }

    @Test
    public void getRandomNormalStringTest() {
        System.out.println(RandomUtils.getRandomNormalString(10));
    }

    @Test
    public void getRandomLetterStringTest() {
        System.out.println(RandomUtils.getRandomLetterString(10));
    }

    @Test
    public void getRandomUniqueNoTest() {
        System.out.println(RandomUtils.getRandomUniqueNo());
    }

    @Test
    public void getLowercaseUUIDTest() {
        System.out.println(RandomUtils.getLowercaseUUID());
    }

    @Test
    public void getUppercaseUUIDTest() {
        System.out.println(RandomUtils.getUppercaseUUID());
    }
}
