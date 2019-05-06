/*
 * Copyright (c) 2019, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.redis;

import java.io.Serializable;
import java.util.concurrent.Callable;

import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.SerializationUtils;

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
		final String keyf = key.toString();
		Object object = null;
		object = redisTemplate.execute(new RedisCallback<Object>() {
			@Override
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] key = keyf.getBytes();
				byte[] value = connection.get(key);
				
				return value == null ? null : SerializationUtils.deserialize(value);
			}
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
		redisTemplate.execute(new RedisCallback<Long>() {
			@Override
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] keyb = keyString.getBytes();
				byte[] valueb = SerializationUtils.serialize((Serializable) valuef);
				connection.set(keyb, valueb);
				if (liveTime > 0)
					connection.expire(keyb, liveTime);
				
				return 1L;
			}
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
		redisTemplate.execute(new RedisCallback<Long>() {
			@Override
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.del(keyf.getBytes());
			}
		});
	}

	@Override
	public void clear() {
		redisTemplate.execute(new RedisCallback<String>() {
			@Override
			public String doInRedis(RedisConnection connection) throws DataAccessException {
				connection.flushDb();
				return "ok";
			}
		});
	}

}