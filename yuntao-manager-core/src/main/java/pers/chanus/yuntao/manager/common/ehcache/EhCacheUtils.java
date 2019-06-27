/*
 * Copyright (c), 2019-2019, Chanus and/or its affiliates. All rights reserved.
 * FileName: EhCacheUtils
 * Author:   Chanus
 * Date:     2019-06-24 18:15
 * Description: Ehcache工具类
 * History:
 * <author>          <time>          <version>          <desc>
 */
package pers.chanus.yuntao.manager.common.ehcache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.config.PersistenceConfiguration;
import net.sf.ehcache.store.MemoryStoreEvictionPolicy;

import java.util.List;

/**
 * Ehcache工具类
 *
 * @author Chanus
 * @date 2019-06-24 18:15
 * @since 0.1.3
 */
public class EhCacheUtils {
    private static CacheManager cacheManager = null;
    private static Cache defaultCache = null;

    public static final String DEFAULT_CACHE_NAME = "yuntaoCache";

    static {
        EhCacheUtils.initCacheManager();
        EhCacheUtils.initDefaultCache();
    }

    /**
     * 初始化缓存管理容器
     *
     * @since 0.1.3
     */
    public static void initCacheManager() {
        if (cacheManager == null)
            cacheManager = CacheManager.getInstance();
    }

    /**
     * 初始化缓存管理容器
     *
     * @param path ehcache.xml存放的路径
     * @return {@code CacheManager}实例
     * @since 0.1.3
     */
    public static CacheManager initCacheManager(String path) {
        if (cacheManager == null)
            cacheManager = CacheManager.create(path);
        return cacheManager;
    }

    /**
     * 初始化默认Cache容器
     *
     * @since 0.1.3
     */
    public static void initDefaultCache() {
        checkCacheManager();
        if (defaultCache == null) {
            CacheConfiguration cacheConfiguration = new CacheConfiguration(DEFAULT_CACHE_NAME, EhCacheConfig.MAX_ELEMENTS_IN_MEMORY);
            cacheConfiguration.addPersistence(new PersistenceConfiguration().strategy(PersistenceConfiguration.Strategy.LOCALTEMPSWAP));
            cacheConfiguration.setMaxEntriesLocalDisk(EhCacheConfig.MAX_ELEMENTS_ON_DISK);
            cacheConfiguration.setEternal(true);
            cacheConfiguration.setTimeToLiveSeconds(EhCacheConfig.TIME_TO_lIVE_SECONDS);
            cacheConfiguration.setTimeToIdleSeconds(EhCacheConfig.TIME_TO_IDLE_SECONDS);
            cacheConfiguration.setDiskExpiryThreadIntervalSeconds(EhCacheConfig.DISK_EXPIRY_THREAD_INTERVAL_SECONDS);
            cacheConfiguration.setMemoryStoreEvictionPolicyFromObject(EhCacheConfig.MEMORY_STORE_EVICTION_POLICY);
            defaultCache = new Cache(cacheConfiguration);
            cacheManager.addCache(defaultCache);
        }
    }

    /**
     * 初始化Cache容器
     *
     * @param cacheName                       容器名称
     * @param maxEntriesLocalHeap             在内存中缓存的element的最大数目
     * @param maxEntriesLocalDisk             在磁盘上缓存的element的最大数目
     * @param strategy                        当前Cache对应的持久化策略：
     *                                        localTempSwap：当堆内存或者非堆内存里面的元素已经满了的时候，将其中的元素临时的存放在磁盘上，一旦重启就会消失。
     *                                        localRestartable：该策略只对企业版Ehcache有用。它可以在重启的时候将堆内存或者非堆内存里面的元素持久化到硬盘上，重启之后再从硬盘上恢复元素到内存中。
     *                                        none：不持久化缓存的元素。
     *                                        distributed：该策略不适用于单机，是用于分布式的。
     * @param eternal                         缓存的elements是否永远不过期
     * @param timeToLiveSeconds               对象存活时间
     * @param timeToIdleSeconds               对象空闲时间
     * @param diskExpiryThreadIntervalSeconds 磁盘失效线程运行时间间隔，默认是120秒
     * @param memoryStoreEvictionPolicy       如果内存中数据超过内存限制，向磁盘缓存时的策略
     * @return {@code Cache}容器
     * @since 0.1.3
     */
    public static Cache initCache(String cacheName, int maxEntriesLocalHeap, long maxEntriesLocalDisk, PersistenceConfiguration.Strategy strategy, boolean eternal,
                                  long timeToLiveSeconds, long timeToIdleSeconds, long diskExpiryThreadIntervalSeconds, MemoryStoreEvictionPolicy memoryStoreEvictionPolicy) {
        CacheManager singletonManager = CacheManager.create();
        Cache cache = singletonManager.getCache(cacheName);

        if (cache == null) {
            CacheConfiguration cacheConfiguration = new CacheConfiguration(cacheName, maxEntriesLocalHeap);
            cacheConfiguration.addPersistence(new PersistenceConfiguration().strategy(strategy));
            cacheConfiguration.setMaxEntriesLocalDisk(maxEntriesLocalDisk);
            cacheConfiguration.setEternal(eternal);
            cacheConfiguration.setTimeToLiveSeconds(timeToLiveSeconds);
            cacheConfiguration.setTimeToIdleSeconds(timeToIdleSeconds);
            cacheConfiguration.setDiskExpiryThreadIntervalSeconds(diskExpiryThreadIntervalSeconds);
            cacheConfiguration.setMemoryStoreEvictionPolicyFromObject(memoryStoreEvictionPolicy);

            cache = new Cache(cacheConfiguration);
            singletonManager.addCache(cache);
        }
        return cache;
    }

    /**
     * 初始化Cache容器，缓存容器永不过期
     *
     * @param cacheName           容器名称
     * @param maxEntriesLocalHeap 在内存中缓存的element的最大数目
     * @param maxEntriesLocalDisk 在磁盘上缓存的element的最大数目
     * @param strategy            当前Cache对应的持久化策略
     * @return {@code Cache}容器
     * @see #initCache(String, int, long, PersistenceConfiguration.Strategy, boolean, long, long, long, MemoryStoreEvictionPolicy)
     * @since 0.1.3
     */
    public static Cache initCacheEternal(String cacheName, int maxEntriesLocalHeap, long maxEntriesLocalDisk, PersistenceConfiguration.Strategy strategy) {
        return initCache(cacheName, maxEntriesLocalHeap, maxEntriesLocalDisk, strategy, true, 0L, 0L, EhCacheConfig.DISK_EXPIRY_THREAD_INTERVAL_SECONDS, EhCacheConfig.MEMORY_STORE_EVICTION_POLICY);
    }

    /**
     * 初始化Cache容器，缓存容器永不过期
     *
     * @param cacheName 容器名称
     * @return {@code Cache}容器
     * @see #initCacheEternal(String, int, long, PersistenceConfiguration.Strategy)
     * @since 0.1.3
     */
    public static Cache initCacheEternal(String cacheName) {
        return initCacheEternal(cacheName, EhCacheConfig.MAX_ELEMENTS_IN_MEMORY, EhCacheConfig.MAX_ELEMENTS_ON_DISK, PersistenceConfiguration.Strategy.LOCALTEMPSWAP);
    }

    /**
     * 初始化Cache容器，缓存容器会过期
     *
     * @param cacheName           容器名称
     * @param maxEntriesLocalHeap 在内存中缓存的element的最大数目
     * @param maxEntriesLocalDisk 在磁盘上缓存的element的最大数目
     * @param strategy            当前Cache对应的持久化策略
     * @param timeToLiveSeconds   对象存活时间
     * @param timeToIdleSeconds   对象空闲时间
     * @return {@code Cache}容器
     * @see #initCache(String, int, long, PersistenceConfiguration.Strategy, boolean, long, long, long, MemoryStoreEvictionPolicy)
     * @since 0.1.3
     */
    public static Cache initCache(String cacheName, int maxEntriesLocalHeap, long maxEntriesLocalDisk, PersistenceConfiguration.Strategy strategy, long timeToLiveSeconds, long timeToIdleSeconds) {
        return initCache(cacheName, maxEntriesLocalHeap, maxEntriesLocalDisk, strategy, EhCacheConfig.ETERNAL, timeToLiveSeconds, timeToIdleSeconds, EhCacheConfig.DISK_EXPIRY_THREAD_INTERVAL_SECONDS, EhCacheConfig.MEMORY_STORE_EVICTION_POLICY);
    }

    /**
     * 初始化Cache容器，缓存容器会过期
     *
     * @param cacheName 容器名称
     * @return {@code Cache}容器
     * @see #initCache(String, int, long, PersistenceConfiguration.Strategy, long, long)
     * @since 0.1.3
     */
    public static Cache initCache(String cacheName) {
        return initCache(cacheName, EhCacheConfig.MAX_ELEMENTS_IN_MEMORY, EhCacheConfig.MAX_ELEMENTS_ON_DISK, PersistenceConfiguration.Strategy.LOCALTEMPSWAP, EhCacheConfig.TIME_TO_lIVE_SECONDS, EhCacheConfig.TIME_TO_IDLE_SECONDS);
    }

    /**
     * 向默认缓存中添加数据
     *
     * @param key   cache中的key
     * @param value cache中的key对应的value
     * @since 0.1.3
     */
    public static void put(Object key, Object value) {
        checkCache();
        // 创建Element,然后放入Cache对象中
        Element element = new Element(key, value);
        defaultCache.put(element);
    }

    /**
     * 向指定永久容器中添加数据
     *
     * @param cacheName 容器名称
     * @param key       cache中的key
     * @param value     cache中的key对应的value
     * @since 0.1.3
     */
    public static void putEternal(String cacheName, String key, Object value) {
        CacheManager manager = CacheManager.create();
        Cache cache = manager.getCache(cacheName);
        if (cache == null) {
            cache = initCacheEternal(cacheName);
        }
        cache.put(new Element(key, value));
    }

    /**
     * 向指定非永久容器中添加数据
     *
     * @param cacheName 容器名称
     * @param key       cache中的key
     * @param value     cache中的key对应的value
     * @since 0.1.3
     */
    public static void put(String cacheName, String key, Object value) {
        CacheManager manager = CacheManager.create();
        Cache cache = manager.getCache(cacheName);
        if (cache == null) {
            cache = initCache(cacheName);
        }
        cache.put(new Element(key, value));
    }

    /**
     * 从默认缓存中获取数据
     *
     * @param key cache中的key
     * @return {@code key}对应的缓存内容
     * @since 0.1.3
     */
    public static Object get(Object key) {
        checkCache();
        Element element = defaultCache.get(key);

        return element == null ? null : element.getObjectValue();
    }

    /**
     * 从指定容器中获取数据
     *
     * @param cacheName 容器名称
     * @param key       cache中的key
     * @return 容器中{@code key}对应的值
     * @since 0.1.3
     */
    public static Object get(String cacheName, String key) {
        Cache cache = CacheManager.create().getCache(cacheName);

        return (cache == null || cache.get(key) == null) ? null : cache.get(key).getObjectValue();
    }

    /**
     * 获取所有的Cache名称
     *
     * @return 所有的Cache名称
     * @since 0.1.3
     */
    public static String[] getCacheNames() {
        checkCacheManager();
        return cacheManager.getCacheNames();
    }

    /**
     * 从指定容器中获取所有的key
     *
     * @param cacheName 容器名称
     * @return {@code Cache}所有的key
     * @since 0.1.3
     */
    public static List getKeys(String cacheName) {
        Cache cache = CacheManager.create().getCache(cacheName);

        return cache == null ? null : cache.getKeys();
    }

    /**
     * 从默认缓存中获取所有的key
     *
     * @return 默认缓存中所有的key
     * @since 0.1.3
     */
    public static List getKeys() {
        checkCache();
        return defaultCache.getKeys();
    }

    /**
     * 移除指定的缓存容器
     *
     * @param cacheName 容器名称
     * @since 0.1.3
     */
    public static void removeCache(String cacheName) {
        CacheManager manager = CacheManager.create();
        manager.removeCache(cacheName);
    }

    /**
     * 移除指定容器中的指定key元素
     *
     * @param cacheName 容器名称
     * @param key       cache中的key
     * @since 0.1.3
     */
    public static void remove(String cacheName, String key) {
        Cache cache = CacheManager.create().getCache(cacheName);
        if (cache != null)
            cache.remove(key);
    }

    /**
     * 移除默认缓存中的key元素
     *
     * @param key 默认缓存中的key
     * @since 0.1.3
     */
    public static void remove(String key) {
        checkCache();
        defaultCache.remove(key);
    }

    /**
     * 移除指定容器中的所有元素
     *
     * @param cacheName 容器名称
     * @since 0.1.3
     */
    public static void removeAll(String cacheName) {
        Cache cache = CacheManager.create().getCache(cacheName);
        if (cache != null)
            cache.removeAll();
    }

    /**
     * 移除默认缓存中的所有元素
     *
     * @since 0.1.3
     */
    public static void removeAll() {
        checkCache();
        defaultCache.removeAll();
    }

    /**
     * 移除所有的Cache
     *
     * @since 0.1.3
     */
    public static void removeAllCaches() {
        checkCacheManager();
        cacheManager.removeAllCaches();
    }

    /**
     * 释放CacheManage
     *
     * @since 0.1.3
     */
    public static void shutdown() {
        cacheManager.shutdown();
    }

    /**
     * 检测{@code cacheManager}是否存在，不存在则抛出异常
     *
     * @since 0.1.3
     */
    private static void checkCacheManager() {
        if (null == cacheManager)
            throw new IllegalArgumentException("调用前请先初始化CacheManager值：EHCacheUtil.initCacheManager()");
    }

    /**
     * 检测{@code defaultCache}是否存在，不存在则抛出异常
     *
     * @since 0.1.3
     */
    private static void checkCache() {
        if (null == defaultCache)
            throw new IllegalArgumentException("调用前请先初始化Cache值：EHCacheUtil.initCache(参数)");
    }
}
