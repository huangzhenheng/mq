package com.tianque;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 访问redis集群
 *
 */
public class RedisCluster{
    public static void main(String[] args) throws IOException{

        Set<HostAndPort> jedisClusterNode = new HashSet<HostAndPort>();

        jedisClusterNode.add(new HostAndPort("192.168.100.21", 7004));
        jedisClusterNode.add(new HostAndPort("192.168.100.21", 7005));
        jedisClusterNode.add(new HostAndPort("192.168.100.21", 7006));
        jedisClusterNode.add(new HostAndPort("192.168.100.21", 7014));
        jedisClusterNode.add(new HostAndPort("192.168.100.21", 7015));
        jedisClusterNode.add(new HostAndPort("192.168.100.21", 7016));
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(100); config.setMaxIdle(10);
        config.setTestOnBorrow(true);

        JedisCluster jedisCluster = new JedisCluster(jedisClusterNode, 6000, 10,config);

        System.out.println(jedisCluster.set("student", "aaron"));
        System.out.println(jedisCluster.set("age", "18"));
        System.out.println(jedisCluster.get("student"));
        System.out.println(jedisCluster.get("age"));

        jedisCluster.close();
    }
}