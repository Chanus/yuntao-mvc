/*
 * Copyright (c) 2020 Chanus
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.chanus.yuntao.mvc.manager.test;

import com.chanus.yuntao.mvc.manager.common.ehcache.EhCacheUtils;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.config.PersistenceConfiguration;
import net.sf.ehcache.store.MemoryStoreEvictionPolicy;
import org.junit.Test;
import com.chanus.yuntao.utils.core.CollectionUtils;

import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Ehcache工具类测试
 *
 * @author Chanus
 * @date 2019-06-27 00:30
 * @since 0.0.1
 */
public class EhCacheUtilsTest {
    @Test
    public void initCacheManagerTest() {
        URL url = EhCacheUtils.class.getResource("/ehache.xml");
        System.out.println(url.getPath());
    }

    @Test
    public void initCacheTest() {
        Cache cache = EhCacheUtils.initCache("testCache", 50000, 1000000, PersistenceConfiguration.Strategy.LOCALTEMPSWAP, false, 86400L, 600L, 200, MemoryStoreEvictionPolicy.LFU);
        CacheConfiguration config = cache.getCacheConfiguration();
        System.out.println("name = " + config.getName());
        System.out.println("timeToLiveSeconds = " + config.getTimeToLiveSeconds());
        System.out.println("timeToIdleSeconds = " + config.getTimeToIdleSeconds());
        System.out.println("maxEntriesLocalHeap = " + config.getMaxEntriesLocalHeap());
        System.out.println("maxEntriesLocalDisk = " + config.getMaxEntriesLocalDisk());
        System.out.println("diskExpiryThreadIntervalSeconds = " + config.getDiskExpiryThreadIntervalSeconds());

        System.out.println("memoryStoreEvictionPolicy = " + config.getMemoryStoreEvictionPolicy());

        PersistenceConfiguration persistenceConfiguration = config.getPersistenceConfiguration();
        System.out.println("strategy = " + persistenceConfiguration.getStrategy());

        Cache cache2 = EhCacheUtils.initCacheEternal("testCache2");
        CacheConfiguration config2 = cache2.getCacheConfiguration();
        System.out.println("name = " + config2.getName());
        System.out.println("timeToLiveSeconds = " + config2.getTimeToLiveSeconds());
        System.out.println("timeToIdleSeconds = " + config2.getTimeToIdleSeconds());
        System.out.println("maxEntriesLocalHeap = " + config2.getMaxEntriesLocalHeap());
        System.out.println("maxEntriesLocalDisk = " + config2.getMaxEntriesLocalDisk());
        System.out.println("diskExpiryThreadIntervalSeconds = " + config2.getDiskExpiryThreadIntervalSeconds());

        System.out.println("memoryStoreEvictionPolicy = " + config2.getMemoryStoreEvictionPolicy());

        PersistenceConfiguration persistenceConfiguration2 = config2.getPersistenceConfiguration();
        System.out.println("strategy = " + persistenceConfiguration2.getStrategy());

        Cache cache3 = EhCacheUtils.initCache("testCache3");
        CacheConfiguration config3 = cache3.getCacheConfiguration();
        System.out.println("name = " + config3.getName());
        System.out.println("timeToLiveSeconds = " + config3.getTimeToLiveSeconds());
        System.out.println("timeToIdleSeconds = " + config3.getTimeToIdleSeconds());
        System.out.println("maxEntriesLocalHeap = " + config3.getMaxEntriesLocalHeap());
        System.out.println("maxEntriesLocalDisk = " + config3.getMaxEntriesLocalDisk());
        System.out.println("diskExpiryThreadIntervalSeconds = " + config3.getDiskExpiryThreadIntervalSeconds());

        System.out.println("memoryStoreEvictionPolicy = " + config3.getMemoryStoreEvictionPolicy());

        PersistenceConfiguration persistenceConfiguration3 = config3.getPersistenceConfiguration();
        System.out.println("strategy = " + persistenceConfiguration3.getStrategy());

        EhCacheUtils.shutdown();
    }

    @Test
    public void ehcacheTest() {
        System.out.println("-----------------------操作默认缓存-----------------------");
        EhCacheUtils.put("key1", "1111");
        EhCacheUtils.put("key2", 2222);
        EhCacheUtils.put("key3", true);
        EhCacheUtils.put("key4", 100.11);
        EhCacheUtils.put("key4", 200.22);

        System.out.println("key1 = " + EhCacheUtils.get("key1"));
        System.out.println("key2 = " + EhCacheUtils.get("key2"));
        System.out.println("key3 = " + EhCacheUtils.get("key3"));
        System.out.println("key4 = " + EhCacheUtils.get("key4"));

        System.out.println("-----------------------操作永久缓存-----------------------");
        EhCacheUtils.putEternal("eternalCache", "key1", "eternal test");
        EhCacheUtils.putEternal("eternalCache", "key3", "eternal test 333");

        System.out.println("key1 = " + EhCacheUtils.get("eternalCache", "key1"));
        System.out.println("key2 = " + EhCacheUtils.get("eternalCache", "key2"));
        System.out.println("key3 = " + EhCacheUtils.get("eternalCache", "key3"));

        System.out.println("-----------------------操作非永久缓存-----------------------");
        EhCacheUtils.put("notEternalCache", "key1", "not eternal test");
        EhCacheUtils.put("notEternalCache", "key4", "not eternal test 444");

        System.out.println("key1 = " + EhCacheUtils.get("notEternalCache", "key1"));
        System.out.println("key2 = " + EhCacheUtils.get("notEternalCache", "key2"));
        System.out.println("key4 = " + EhCacheUtils.get("notEternalCache", "key4"));

        System.out.println("-----------------------获取所有的Cache名称-----------------------");
        String[] cacheNames = EhCacheUtils.getCacheNames();
        Arrays.asList(cacheNames).forEach(System.out::println);

        System.out.println("-----------------------从指定容器中获取所有的key-----------------------");
        List eternalKeys = EhCacheUtils.getKeys("eternalCache");
        eternalKeys.forEach(System.out::println);

        List notEternalKeys = EhCacheUtils.getKeys("notEternalCache");
        notEternalKeys.forEach(System.out::println);

        System.out.println("-----------------------从默认缓存中获取所有的key-----------------------");
        List keys = EhCacheUtils.getKeys();
        keys.forEach(System.out::println);

        System.out.println("-----------------------移除指定的缓存容器-----------------------");
        EhCacheUtils.put("removeCacheTest", "key1", "remove cache test");
        System.out.println("key1 = " + EhCacheUtils.get("removeCacheTest", "key1"));
        EhCacheUtils.removeCache("removeCacheTest");
        Cache removeCache = CacheManager.getInstance().getCache("removeCacheTest");// getInstance()实际调用的就是create()
        if (removeCache == null)
            System.out.println("removeCacheTest 已被移除");

        System.out.println("-----------------------移除指定容器中的指定key元素-----------------------");
        System.out.println("notEternalCache 移除前 key1 = " + EhCacheUtils.get("notEternalCache", "key1"));
        EhCacheUtils.remove("notEternalCache", "key1");
        System.out.println("notEternalCache 移除后 key1 = " + EhCacheUtils.get("notEternalCache", "key1"));

        System.out.println("-----------------------移除默认缓存中的key元素-----------------------");
        System.out.println("默认缓存移除前 key1 = " + EhCacheUtils.get("key1"));
        EhCacheUtils.remove("key1");
        System.out.println("默认缓存移除后 key1 = " + EhCacheUtils.get("key1"));

        System.out.println("-----------------------移除指定容器中的所有元素-----------------------");
        EhCacheUtils.removeAll("eternalCache");
        eternalKeys = EhCacheUtils.getKeys("eternalCache");
        if (CollectionUtils.isEmpty(eternalKeys))
            System.out.println("eternalCache中的所有元素都已被移除");

        System.out.println("-----------------------移除默认缓存中的所有元素-----------------------");
        EhCacheUtils.removeAll();
        keys = EhCacheUtils.getKeys();
        if (CollectionUtils.isEmpty(eternalKeys))
            System.out.println("默认缓存中的所有元素都已被移除");

        System.out.println("-----------------------移除所有的Cache-----------------------");
        cacheNames = EhCacheUtils.getCacheNames();
        int length = cacheNames == null ? 0 : cacheNames.length;
        System.out.println("移除前缓存数量：" + length);
        EhCacheUtils.removeAllCaches();
        cacheNames = EhCacheUtils.getCacheNames();
        length = cacheNames == null ? 0 : cacheNames.length;
        System.out.println("移除后缓存数量：" + length);

        EhCacheUtils.shutdown();
    }

    /**
     * 1000条数据：平均420毫秒左右
     * 10000条数据：平均1400毫秒左右
     * 100000条数据：测试5次分别是15184，11799，10009，11241，9529
     */
    @Test
    public void ehcachePerformTest() {
        int count = 100000;
        // String cacheName = "testcache";
        long start = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            EhCacheUtils.put("key" + i, i);
        }
        EhCacheUtils.shutdown();
        long end = System.currentTimeMillis();
        System.err.println("用时" + (end - start) + "毫秒");
    }

    /**
     * 1000条数据：1毫秒
     * 10000条数据：4-5毫秒
     * 100000条数据：40毫秒左右
     * 1000000条数据：210毫秒左右
     */
    @Test
    public void hashMapPerformTest() {
        int count = 1000000;
        Map map = new HashMap<>();
        long start = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            map.put("key" + i, i);
        }
        long end = System.currentTimeMillis();
        System.err.println("用时" + (end - start) + "毫秒");
    }
}
