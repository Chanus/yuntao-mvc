/*
 * Copyright (c), 2020-persent, Chanus and/or its affiliates. All rights reserved.
 * FileName: RetryUtils
 * Author:   Chanus
 * Date:     2020-05-17 12:04:41
 * Description: 云道异常重试工具类
 * History:
 * <author>          <time>          <version>          <desc>
 */
package pers.chanus.yuntao.util;

import java.util.concurrent.Callable;

/**
 * 异常重试工具类
 *
 * @author Chanus
 * @date 2020-05-17 12:04:41
 * @since 0.1.9
 */
public class RetryUtils {
    /**
     * 回调结果检查
     *
     * @since 0.1.9
     */
    public interface ResultCheck {
        boolean matching();

        String getJson();
    }

    /**
     * 在遇到异常时尝试重试
     *
     * @param retryLimit    重试次数
     * @param retryCallable 重试回调
     * @param <V>           泛型
     * @return V 结果
     * @since 0.1.9
     */
    public static <V extends ResultCheck> V retryOnException(int retryLimit, Callable<V> retryCallable) {
        V v = null;
        for (int i = 0; i < retryLimit; i++) {
            System.out.println("retry " + i);
            try {
                v = retryCallable.call();
            } catch (Exception ignored) {

            }
            if (null != v && v.matching()) break;
        }
        return v;
    }

    /**
     * 在遇到异常时尝试重试
     *
     * @param retryLimit    重试次数
     * @param sleepMillis   每次重试之后休眠的时间
     * @param retryCallable 重试回调
     * @param <V>           泛型
     * @return V 结果
     * @throws InterruptedException 线程异常
     * @since 0.1.9
     */
    public static <V extends ResultCheck> V retryOnException(int retryLimit, long sleepMillis, Callable<V> retryCallable) throws InterruptedException {
        V v = null;
        for (int i = 0; i < retryLimit; i++) {
            try {
                v = retryCallable.call();
            } catch (Exception ignored) {

            }
            if (null != v && v.matching()) break;
            Thread.sleep(sleepMillis);
        }
        return v;
    }
}
