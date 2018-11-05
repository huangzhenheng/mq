package com.tianque.redis.template;

import com.tianque.redis.core.JedisResource;
import com.tianque.redis.exception.RedisException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

@Component("redisTemplate")
public class RedisTemplateImpl implements RedisTemplate {
    /**
     * 缓存生存时间 秒
     */
    private final static int CACHESECONDS = 60000;

    @Autowired
    private JedisResource jedisResource;

    @Override
    public String flushAll() {
        Jedis jedis = jedisResource.getInstance();
        String status = null;
        try {
            status = jedis.flushAll();
        } catch (Exception e) {
            throw new RedisException("flushAll is error", e);
        } finally {
            jedisResource.returnResource(jedis);
        }
        return status;
    }

    @Override
    public void expire(String key, int seconds) {
        if (seconds <= 0) {
            return;
        }
        Jedis jedis = jedisResource.getInstance();
        try {
            jedis.expire(key, seconds);
        } catch (Exception e) {
            throw new RedisException("set(seconds) data is error", e);
        } finally {
            jedisResource.returnResource(jedis);
        }
    }

    /**
     * 设置键的默认过期时间60000(一小时)
     *
     * @param key
     */
    @Override
    public void expire(String key) {
        expire(key, CACHESECONDS);
    }

    /**
     * 设置key的过期时间,它是距历元（即格林威治标准时间 1970 年 1 月 1 日的 00:00:00，格里高利历）的偏移量。
     *
     * @param String   key
     * @param 时间,已秒为单位
     * @return 影响的记录数
     */
    @Override
    public Long expireAt(String key, long timestamp) {
        Jedis jedis = jedisResource.getInstance();
        Long count = null;
        try {
            count = jedis.expireAt(key, timestamp);
        } catch (Exception e) {
            throw new RedisException("expireAt data is error", e);
        } finally {
            jedisResource.returnResource(jedis);
        }
        return count;
    }

    /**
     * 查询key的过期时间
     *
     * @param String key
     * @return 以秒为单位的时间表示
     */
    @Override
    public Long ttl(String key) {
        Jedis jedis = jedisResource.getInstance();
        Long len = null;
        try {
            len = jedis.ttl(key);
        } catch (Exception e) {
            throw new RedisException("ttl data is error", e);
        } finally {
            jedisResource.returnResource(jedis);
        }
        return len;
    }

    /**
     * 取消对key过期时间的设置
     *
     * @param key
     * @return 影响的记录数
     */
    @Override
    public Long persist(String key) {
        Jedis jedis = jedisResource.getInstance();
        Long count = null;
        try {
            count = jedis.persist(key);
        } catch (Exception e) {
            throw new RedisException("ttl data is error", e);
        } finally {
            jedisResource.returnResource(jedis);
        }
        return count;
    }

    /**
     * 更改key的名字
     *
     * @param String oldkey
     * @param String newkey
     * @return 状态码
     */
    @Override
    public String rename(String oldkey, String newkey) {
        Jedis jedis = jedisResource.getInstance();
        String status = null;
        try {
            status = jedis.rename(oldkey, newkey);
        } catch (Exception e) {
            throw new RedisException("renamenx(oldkey, newkey)  is error", e);
        } finally {
            jedisResource.returnResource(jedis);
        }
        return status;
    }

    /**
     * 添加记录,如果记录已存在将覆盖原有的value
     *
     * @param String
     *            key
     * @param String
     *            value
     * @return 状态码
     */
    @Override
    public  String set(String key, String value) {
        Jedis jedis = jedisResource.getInstance();
        String status = null;
        try {
            status = jedis.set(key, value);
        } catch (Exception e) {

        } finally {
            jedisResource.returnResource(jedis);
        }
        return status;
    }

}
