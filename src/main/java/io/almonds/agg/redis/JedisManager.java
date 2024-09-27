package io.almonds.agg.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
@Slf4j
public class JedisManager {

  @Value("${redis.server}")
  private String redisServer;

  @Value("${redis.port:6379}")
  private int redisPort;

  private JedisPool jedisPool;

  @PostConstruct
  public void init() {
    jedisPool = new JedisPool(buildPoolConfig(), redisServer, redisPort);
  }

  @Bean
  JedisPool getJedisPool() {
    return jedisPool;
  }

  private JedisPoolConfig buildPoolConfig() {
    final JedisPoolConfig poolConfig = new JedisPoolConfig();
    poolConfig.setMaxTotal(128);
    poolConfig.setMaxIdle(128);
    poolConfig.setMinIdle(16);
    poolConfig.setTestOnBorrow(true);
    poolConfig.setTestOnReturn(true);
    poolConfig.setTestWhileIdle(true);
    // poolConfig.setMinEvictableIdleTimeMillis(Duration.ofSeconds(60).toMillis());
    // poolConfig.setTimeBetweenEvictionRunsMillis(Duration.ofSeconds(30).toMillis());
    poolConfig.setNumTestsPerEvictionRun(3);
    poolConfig.setBlockWhenExhausted(true);
    return poolConfig;
  }

}
