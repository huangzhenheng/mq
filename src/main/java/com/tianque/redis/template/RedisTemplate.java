package com.tianque.redis.template;

import redis.clients.jedis.BinaryClient;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * @author hzh
 */
public interface RedisTemplate {
    /**
     * 清空所有key
     *
     * @return
     */
    String flushAll();

    /**
     * 设置键的过期时间
     *
     * @param key
     * @param seconds
     */
    void expire(String key, int seconds);

    /**
     * 设置键的默认过期时间60000(一小时)
     *
     * @param key
     */
    void expire(String key);

    /**
     * 设置key的过期时间,它是距历元（即格林威治标准时间 1970 年 1 月 1 日的 00:00:00，格里高利历）的偏移量。
     *
     * @param String   key
     * @param 时间,已秒为单位
     * @return 影响的记录数
     */
    Long expireAt(String key, long timestamp);

    /**
     * 查询key的过期时间
     *
     * @param String key
     * @return 以秒为单位的时间表示
     */
    Long ttl(String key);

    /**
     * 取消对key过期时间的设置
     *
     * @param key
     * @return 影响的记录数
     */
    Long persist(String key);

    /**
     * 更改key的名字
     *
     * @param String oldkey
     * @param String newkey
     * @return 状态码
     */
    String rename(String oldkey, String newkey);

    /**
     * 添加记录,如果记录已存在将覆盖原有的value
     *
     * @param String
     *            key
     * @param String
     *            value
     * @return 状态码
     */
     String set(String key, String value);
}
