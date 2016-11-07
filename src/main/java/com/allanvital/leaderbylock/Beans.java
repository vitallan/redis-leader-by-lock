package com.allanvital.leaderbylock;

import java.util.Random;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class Beans {
	
	@Bean
	public JedisPool jedisPool() {
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setTestOnBorrow(true);
		poolConfig.setTestWhileIdle(true);
		return new JedisPool(poolConfig, "192.168.1.70", 6379, 500);
	}
	
	@Bean
	public Jedis jedis(JedisPool jedisPool) {
		return jedisPool.getResource();
	}
	
	@Bean
	public String myApplicationId() {
		return "" + new Random().nextInt(Integer.MAX_VALUE);
	}
	
}
