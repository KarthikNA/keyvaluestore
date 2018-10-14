package com.assignment.keyvaluestore.util;

import com.assignment.keyvaluestore.dto.KeyValueDto;
import com.assignment.keyvaluestore.service.RedisService;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("redisDataStore")
public class RedisDataStoreImpl implements DataStore {

  private static final Logger log = LoggerFactory.getLogger(RedisDataStoreImpl.class);

  public static Map<String, KeyValueDto> keyValueStoreMap;

  @Autowired
  private RedisService redis;

  @Value("${max.cache.size}")
  private Integer maxCacheSize;

  static {
    keyValueStoreMap = new HashMap<>();
  }

  @Override
  public KeyValueDto getValue(String key) {
    KeyValueDto dto = this.keyValueStoreMap.get(key);
    if (dto != null) {
      log.info("Value : `{}` Found in Local Storage for key : '{}'",
          dto.getValue(), dto.getValue());
      return dto;
    }
    return getFromRedis(key);
  }

  @Override
  public void insertKeyValue(KeyValueDto dto) {
    KeyValueDto localStore = this.keyValueStoreMap.get(dto.getKey());
    if (localStore != null) {
      log.info("key : '{}' is Present in Local Store with value : '{}' and is Updated "
              + "to value : '{}'", dto.getKey(), localStore.getValue(), dto.getValue());
      this.keyValueStoreMap.put(dto.getKey(), dto);
      return;
    }
    KeyValueDto redisStore = getFromRedis(dto.getKey());
    if (redisStore != null) {
      log.info("key : '{}' value : '{}' was Stored in Redis(Disk) Storage and value updated "
          + "to : '{}'", dto.getKey(), redisStore.getValue(), dto.getValue());
      storeInRedis(dto.getKey(), dto.getValue());
      return;
    }
    if (keyValueStoreMap.size() >= maxCacheSize) {
      log.info("Local Storage is Full");
      keyValueStoreMap.forEach((k, v) -> storeInRedis(v.getKey(), v.getValue()));
      log.info("Clearing Local Storage to Store New key-value pairs");
      keyValueStoreMap.clear();
    }
    log.info("<><><<>> ---- --- {}", keyValueStoreMap);
    log.info("key : '{}' value : '{}' Stored in Local Storage", dto.getKey(), dto.getValue());
    this.keyValueStoreMap.put(dto.getKey(), dto);
  }

  private KeyValueDto getFromRedis(String key) {
    Object obj = redis.getValue(key);
    if (obj == null) {
      return null;
    }
    String value = obj.toString();
    log.info("Value : `{}` Found in Redis(Disk) Storage for key : '{}'", value, key);
    return new KeyValueDto(key, value);
  }

  private void storeInRedis(String key, String value) {
    log.info("Storing key : '{}' value : '{}' in Redis", key, value);
    redis.insertValueWithoutExpiry(key, value);
  }

}
