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
package com.chanus.yuntao.mvc.redis;

import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.SerializationUtils;

import java.util.concurrent.Callable;

/**
 * 自定义Redis缓存处理
 *
 * @author Chanus
 * @date 2019-03-13 15:58:38
 * @since 0.0.7
 */
public class RedisCache implements Cache {

    private RedisTemplate<String, Object> redisTemplate;

    private String name;

    public RedisTemplate<String, Object> getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Object getNativeCache() {
        return this.redisTemplate;
    }

    @Override
    public ValueWrapper get(Object key) {
        final String keyString = key.toString();
        Object object = redisTemplate.execute((RedisCallback<Object>) connection -> {
            byte[] key1 = keyString.getBytes();
            byte[] value = connection.get(key1);

            return value == null ? null : SerializationUtils.deserialize(value);
        });

        return object == null ? null : new SimpleValueWrapper(object);
    }

    @Override
    public <T> T get(Object key, Class<T> type) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T> T get(Object key, Callable<T> valueLoader) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void put(Object key, Object value) {
        final String keyString = key.toString();
        final Object valuef = value;
        final long liveTime = 86400;
        redisTemplate.execute((RedisCallback<Long>) connection -> {
            byte[] keyb = keyString.getBytes();
            byte[] valueb = SerializationUtils.serialize(valuef);
            connection.set(keyb, valueb);
            if (liveTime > 0)
                connection.expire(keyb, liveTime);

            return 1L;
        });
    }

    @Override
    public ValueWrapper putIfAbsent(Object key, Object value) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void evict(Object key) {
        final String keyf = key.toString();
        redisTemplate.execute((RedisCallback<Long>) connection -> connection.del(keyf.getBytes()));
    }

    @Override
    public void clear() {
        redisTemplate.execute((RedisCallback<String>) connection -> {
            connection.flushDb();
            return "ok";
        });
    }

}
