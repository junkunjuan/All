package com.miaosha.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class RedisService {

	@Autowired
	JedisPool jedisPool;

	/**
	 * 获取单个对象
	 */
	public <T> T get(KeyPrefix keyPrefix, String key, Class<T> clazz) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			// 生成真正的key
			String realkey = keyPrefix.getPrefix() + key;
			String string = jedis.get(realkey);
			T t = stringToBean(string, clazz);
			return t;
		} finally {
			returnToPool(jedis);
		}
	}

	/**
	 * 设置对象
	 * 
	 * @param value
	 * @return
	 */
	public <T> boolean set(KeyPrefix prefix, String key, T value) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			String string = beanToString(value);
			if (string == null || string.length() <= 0) {
				return false;
			}
			// 生成真正的key
			String realkey = prefix.getPrefix() + key;
			int seconds = prefix.expireSeconds();
			if (seconds <= 0) {
				jedis.set(realkey, string);
			} else {
				jedis.setex(realkey, seconds, string);
			}
			return true;
		} finally {
			returnToPool(jedis);
		}
	}

	public static <T> String beanToString(T value) {
		if (value == null) {
			return null;
		}
		Class<?> clazz = value.getClass();
		if (clazz == int.class || clazz == Integer.class) {
			return "" + value;
		} else if (clazz == String.class) {
			return (String) value;
		} else if (clazz == long.class || clazz == Long.class) {
			return "" + value;
		} else {
			return JSON.toJSONString(value);
		}

	}

	private void returnToPool(Jedis jedis) {
		if (jedis != null) {
			jedis.close();
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> T stringToBean(String string, Class<T> clazz) {
		if (string == null || string.length() <= 0 || clazz == null) {
			return null;
		}
		if (clazz == int.class || clazz == Integer.class) {
			return (T) Integer.valueOf(string);
		} else if (clazz == String.class) {
			return (T) string;
		} else if (clazz == Long.class || clazz == long.class) {
			return (T) Long.valueOf(string);
		} else {
			return JSON.toJavaObject(JSON.parseObject(string), clazz);
		}

	}

	public boolean delete(KeyPrefix prefix, String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			String realkey = prefix.getPrefix() + key;
			long ret = jedis.del(realkey);
			return ret > 0;
		} finally {
			returnToPool(jedis);
		}
	}

	/**
	 * 减库存
	 * 
	 * @param prefix
	 * @param key
	 * @return
	 */
	public <T> long decr(KeyPrefix prefix, String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			// 生成真正的key
			String realKey = prefix.getPrefix() + key;
			return jedis.decr(realKey);
		} finally {
			returnToPool(jedis);
		}
	}

	/**
	 * 判断key是否存在
	 */
	public <T> boolean exists(KeyPrefix prefix, String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			// 生成真正的key
			String realKey = prefix.getPrefix() + key;
			return jedis.exists(realKey);
		} finally {
			returnToPool(jedis);
		}
	}

	public <T> Long incr(KeyPrefix prefix, String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			// 生成真正的key
			String realKey = prefix.getPrefix() + key;
			return jedis.incr(realKey);
		} finally {
			returnToPool(jedis);
		}
	}
}
