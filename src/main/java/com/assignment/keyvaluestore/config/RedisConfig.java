package com.assignment.keyvaluestore.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import redis.clients.jedis.JedisPoolConfig;

@Configuration
@ComponentScan
public class RedisConfig {
  @Value("${redis.hostname}")
  private String hostName;
  @Value("${redis.port}")
  private Integer port;
  @Value("${redis.database}")
  private Integer dbCompartment;
  @Value("${redis.password}")
  private String password;


  /**
   * set up jedis connection.
   * @return JedisConnectionFactory.
   */
  @Bean
  JedisConnectionFactory jedisConnectionFactory() {
    final JedisConnectionFactory connectionFactory = new JedisConnectionFactory();
    connectionFactory.setDatabase(dbCompartment);
    connectionFactory.setHostName(hostName);
    connectionFactory.setPort(port);
    connectionFactory.setPoolConfig(new JedisPoolConfig());
    connectionFactory.setUsePool(true);
    connectionFactory.setPassword(password);
    return connectionFactory;
  }

  /**
   * Redis Template Bean Config.
   * @return RedisTemplate.
   */
  @Bean
  @Primary
  public RedisTemplate<String, Object> redisTemplate() {
    RedisTemplate<String, Object> template = new RedisTemplate<>();
    template.setConnectionFactory(jedisConnectionFactory());
    template.setKeySerializer(new StringRedisSerializer());
    return template;
  }
}
