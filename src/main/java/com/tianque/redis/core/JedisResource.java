package com.tianque.redis.core;

import redis.clients.jedis.Jedis;

/**
 * @author hzh
 * @date 2018-10-10
 */
public interface JedisResource {
	
	/**
	 * 从连接池中获取一个jedis实例
	 * @return
	 */
	public Jedis getInstance();

	/**
	 * 释放jedis
	 * @param jedis
	 */
	public void returnResource(Jedis jedis);
}
