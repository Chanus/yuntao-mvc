/*
 * Copyright (c), 2019-2019, Chanus and/or its affiliates. All rights reserved.
 * FileName: EhCacheConfig
 * Author:   Chanus
 * Date:     2019-06-24 18:09
 * Description: Ehcache配置
 * History:
 * <author>          <time>          <version>          <desc>
 */
package pers.chanus.yuntao.manager.common.ehcache;

import net.sf.ehcache.store.MemoryStoreEvictionPolicy;

/**
 * Ehcache配置
 *
 * @author Chanus
 * @date 2019-06-24 18:09
 * @since 0.1.3
 */
public class EhCacheConfig {
    /**
     * 在内存中缓存的element的最大数目。
     */
    public static int MAX_ELEMENTS_IN_MEMORY = 10000;

    /**
     * 在磁盘上缓存的element的最大数目，默认值为0，表示不限制。
     */
    public static long MAX_ELEMENTS_ON_DISK = 0L;

    /**
     * 设定缓存的elements是否永远不过期。如果为true，则缓存的数据始终有效，如果为false那么还要根据timeToIdleSeconds，timeToLiveSeconds判断。
     */
    public static boolean ETERNAL = false;

    /**
     * 对象空闲时间，指对象在多长时间没有被访问就会失效。只对eternal为false的有效。默认值0，表示一直可以访问。
     */
    public static int TIME_TO_IDLE_SECONDS = 600;

    /**
     * 对象存活时间，指对象从创建到失效所需要的时间。只对eternal为false的有效。默认值0，表示一直可以访问。
     */
    public static int TIME_TO_lIVE_SECONDS = 86400;

    /**
     * 是否在磁盘上持久化。指重启jvm后，数据是否有效。默认为false。
     */
    public static boolean DISK_PERSISTENT = false;

    /**
     * 磁盘失效线程运行时间间隔，默认是120秒。
     */
    public static long DISK_EXPIRY_THREAD_INTERVAL_SECONDS = 120L;

    /**
     * DiskStore使用的磁盘大小，默认值30MB。每个cache使用各自的DiskStore。
     */
    public static int DISK_SPOOL_BUFFER_SIZE_MB = 30;

    /**
     * 如果内存中数据超过内存限制，向磁盘缓存时的策略。默认值LRU，可选FIFO、LFU。
     * FIFO：First in first out (先进先出).
     * LFU：Less Frequently Used (最少使用). 意思是一直以来最少被使用的。缓存的元素有一个hit属性，hit值最小的将会被清出缓存。
     * LRU：Least Recently Used(最近最少使用). (ehcache 默认值).缓存的元素有一个时间戳，当缓存容量满了，而又需要腾出地方来缓存新的元素的时候，那么现有缓存元素中时间戳离当前时间最远的元素将被清出缓存。
     */
    public static MemoryStoreEvictionPolicy MEMORY_STORE_EVICTION_POLICY = MemoryStoreEvictionPolicy.LFU;
}
