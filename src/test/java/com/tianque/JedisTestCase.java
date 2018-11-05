package com.tianque;

import com.tianque.redis.template.RedisTemplate;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-redis.xml")
public class JedisTestCase {
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testCase1() {
        redisTemplate.set("hzh","king");
    }
}
