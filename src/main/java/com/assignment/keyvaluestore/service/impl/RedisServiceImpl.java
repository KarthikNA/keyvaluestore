package com.assignment.keyvaluestore.service.impl;

import com.assignment.keyvaluestore.service.RedisService;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicInteger;
import org.springframework.stereotype.Service;

@Service
public class RedisServiceImpl implements RedisService {

  @Autowired
  private RedisTemplate<String, Object> redisTemplate;

  /**
   * autoIncrement of key.
   * @param key      redis key.
   * @param incrBy   increment value by.
   * @param expiry   expiry .
   * @param timeUnit time unit of expiry.
   * @return incremented value in redis.
   */
  @Override
  public Integer autoIncrement(String key, Integer incrBy, Long expiry, TimeUnit timeUnit) {
    RedisAtomicInteger counter = new RedisAtomicInteger(key, redisTemplate.getConnectionFactory());
    Integer value = counter.addAndGet(incrBy);
    if (expiry != null && timeUnit != null) {
      counter.expire(expiry, timeUnit);
    }
    return value;
  }

  /**
   * autoDecrement of key.
   * @param key      redis key.
   * @param expiry   expiry .
   * @param timeUnit time unit of expiry.
   * @return decremented value in redis.
   */
  @Override
  public Integer autoDecrement(String key, Long expiry, TimeUnit timeUnit) {
    RedisAtomicInteger counter = new RedisAtomicInteger(key, redisTemplate.getConnectionFactory());
    Integer value = counter.decrementAndGet();
    if (expiry != null && timeUnit != null) {
      counter.expire(expiry, timeUnit);
    }
    return value;
  }

  /**
   * delete redis key.
   * @param key key.
   * @return delete or not.
   */
  @Override
  public Boolean removeKey(String key) {
    RedisAtomicInteger counter = new RedisAtomicInteger(key, redisTemplate.getConnectionFactory());
    counter.expire(0L, TimeUnit.SECONDS);
    return true;
  }

  /**
   * insert redis key .
   * @param key      .
   * @param value    .
   * @param expiry   .
   * @param timeUnit time unit of expiry.
   */
  @Override
  public void insertValue(String key, Object value, Long expiry, TimeUnit timeUnit) {
    redisTemplate.opsForValue().set(key, value);
    if (expiry != null) {
      redisTemplate.expire(key, expiry, timeUnit);
    }
  }

  /**
   * insert redis key .
   * @param key      .
   * @param value    .
   */
  @Override
  public void insertValueWithoutExpiry(String key, Object value) {
    redisTemplate.opsForValue().set(key, value);
  }

  /**
   * get redis key-value pair.
   * @param key .
   * @return key-value pair.
   */
  @Override
  public Object getValue(String key) {
    return redisTemplate.opsForValue().get(key);
  }

  /**
   * delete a particular redis key.
   * @param key .
   */
  @Override
  public void deleteValue(String key) {
    redisTemplate.delete(key);
  }

  @Override
  public void putIntoHash(
      String key, Object hashKey, Object value, Long expiry, TimeUnit timeUnit) {
    long size = redisTemplate.opsForHash().size(key);
    redisTemplate.opsForHash().put(key, hashKey, value);
    if (size == 0) {
      redisTemplate.expire(key, expiry, timeUnit);
    }
  }

  @Override
  public void putIntoHash(String key, Object hashKey, Object value, Long timeout) {
    redisTemplate.opsForHash().put(key, hashKey, value);
    if (timeout != null) {
      redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
    }
  }

  @Override
  public Object getFromHash(String key, Object hashKey) {
    return redisTemplate.opsForHash().get(key, hashKey);
  }

  @Override
  public List<Object> getValuesFromHash(String key) {
    return redisTemplate.opsForHash().values(key);
  }

  @Override
  public void deleteKeyFromHash(String key, Object hashKey) {
    redisTemplate.opsForHash().delete(key, hashKey);
  }

  @Override
  public void deleteHash(String key) {
    redisTemplate.delete(key);
  }

}
