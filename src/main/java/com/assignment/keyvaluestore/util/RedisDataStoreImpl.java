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

  /**
   * get value from the key received.
   * @param key .
   * @return KeyValueDto.
   */
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

  /**
   * store the key and value in local store or disc backed storage.
   * @param dto KeyValueDto.
   */
  @Override
  public void insertKeyValue(KeyValueDto dto) {
    // if the key is present in the local storage, update it in local storage itself
    KeyValueDto localStore = this.keyValueStoreMap.get(dto.getKey());
    if (localStore != null) {
      log.info("key : '{}' is Present in Local Store with value : '{}' and is Updated "
              + "to value : '{}'", dto.getKey(), localStore.getValue(), dto.getValue());
      this.keyValueStoreMap.put(dto.getKey(), dto);
      return;
    }
    // if the key is present in the disc backed storage, update the key in the disk backed storage.
    KeyValueDto redisStore = getFromRedis(dto.getKey());
    if (redisStore != null) {
      log.info("key : '{}' value : '{}' was Stored in Redis(Disk) Storage and value updated "
          + "to : '{}'", dto.getKey(), redisStore.getValue(), dto.getValue());
      storeInRedis(dto.getKey(), dto.getValue());
      return;
    }
    // if the local memory is full, store the key value pair in the disc backed storage.
    if (keyValueStoreMap.size() >= maxCacheSize) {
      // store all data in the local storage in the disc storage and free the local storage.
      log.info("Local Storage is Full");
      keyValueStoreMap.forEach((k, v) -> storeInRedis(v.getKey(), v.getValue()));
      log.info("Clearing Local Storage to Store New key-value pairs");
      keyValueStoreMap.clear();
    }
    // store the new data in the local storage.
    log.info("key : '{}' value : '{}' Stored in Local Storage", dto.getKey(), dto.getValue());
    this.keyValueStoreMap.put(dto.getKey(), dto);
  }

  /**
   * retrieve the data from disk backed storage.
   * @param key .
   * @return KeyValueDto.
   */
  private KeyValueDto getFromRedis(String key) {
    Object obj = redis.getValue(key);
    if (obj == null) {
      return null;
    }
    String value = obj.toString();
    log.info("Value : `{}` Found in Redis(Disk) Storage for key : '{}'", value, key);
    return new KeyValueDto(key, value);
  }

  /**
   * store the key value pairs in disk backed storage.
   * @param key .
   * @param value value to be stored with the key.
   */
  private void storeInRedis(String key, String value) {
    log.info("Storing key : '{}' value : '{}' in Redis", key, value);
    redis.insertValueWithoutExpiry(key, value);
  }

}
