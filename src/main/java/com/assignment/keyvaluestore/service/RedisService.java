package com.assignment.keyvaluestore.service;

import java.util.List;
import java.util.concurrent.TimeUnit;

public interface RedisService {
  Integer autoIncrement(String key, Integer incrBy, Long expiry, TimeUnit timeUnit);

  Integer autoDecrement(String key, Long expiry, TimeUnit timeUnit);

  Boolean removeKey(String key);

  void insertValue(String key, Object value, Long expiry, TimeUnit timeUnit);

  Object getValue(String key);

  void deleteValue(String key);

  void putIntoHash(String key, Object hashKey, Object value, Long expiry, TimeUnit timeUnit);

  void putIntoHash(String key, Object hashKey, Object value, Long expireAt);

  Object getFromHash(String key, Object hashKey);

  void deleteKeyFromHash(String key, Object hashKey);

  List<Object> getValuesFromHash(String key);

  void deleteHash(String key);
}
