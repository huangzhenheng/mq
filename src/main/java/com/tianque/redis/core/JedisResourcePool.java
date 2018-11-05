package com.tianque.redis.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author hzh
 */
@Component("jedisResourcePool")
public class JedisResourcePool implements JedisResource {
	private static final Logger logger = LoggerFactory.getLogger(JedisResourcePool.class);

	@Autowired
	private JedisPool jedisPool;

	@Override
	public Jedis getInstance() {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis;
		} catch (Exception e) {
			logger.error("[JedisDS] getRedisClent error:" + e.getMessage());
			if (null != jedis) {
				jedis.close();
			}
		}
		return null;
	}

	@Override
	public void returnResource(Jedis jedis) {
		jedis.close();
	}

}
