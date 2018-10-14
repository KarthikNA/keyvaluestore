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
  public KeyValueDto getValue(String key){
    KeyValueDto dto = this.keyValueStoreMap.get(key);
    if (dto == null) {
      Object obj = redis.getValue(key);
      if (obj == null) {
        return null;
      }
      String value = obj.toString();
      log.info("Value : `{}` Found in Redis(Disk) Storage for key : '{}'",
          dto.getValue(), dto.getValue());
      return new KeyValueDto(key, value);
    }
    log.info("Value : `{}` Found in Local Storage for key : '{}'", dto.getValue(), dto.getValue());
    return dto;
  }

  //todo - need to handle case if the data is in redis and not in local storage.
  // update the redis data
  @Override
  public void insertKeyValue(KeyValueDto dto) {
    KeyValueDto localStore = this.keyValueStoreMap.get(dto.getKey());
    if (localStore != null) {
      log.info("key : '{}' is Present in Local Store and is Updated to value : '{}'",
          dto.getKey(), dto.getValue() );
      this.keyValueStoreMap.put(dto.getKey(), dto);
    }
    if (keyValueStoreMap.size() >= maxCacheSize) {
      log.info("Local Storage is Full");
      keyValueStoreMap.forEach((k,v) -> storeInRedis(v.getKey(), v.getValue()));
      log.info("Clearing Local Storage to Store New key-value pairs");
      keyValueStoreMap.clear();
    }
    log.info("key : '{}' value : '{}' Stored in Local Storage", dto.getKey(), dto.getValue());
    this.keyValueStoreMap.put(dto.getKey(), dto);
  }

  private void storeInRedis(String key, String value) {
    log.info("Storing key : '{}' value : '{}' in Redis", key, value);
    redis.insertValueWithoutExpiry(key, value);
  }

}
