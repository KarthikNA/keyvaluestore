package com.assignment.keyvaluestore.service.impl;

import com.assignment.keyvaluestore.dto.KeyValueDto;
import com.assignment.keyvaluestore.dto.ResponseDto;
import com.assignment.keyvaluestore.service.KeyValueService;
import com.assignment.keyvaluestore.service.RedisService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KeyValueServiceImpl implements KeyValueService {

  private static final Gson gson = new GsonBuilder().serializeNulls().create();

  private static final Logger log = LoggerFactory.getLogger(KeyValueServiceImpl.class);

  @Autowired
  private RedisService redis;

  @Override
  public KeyValueDto getValue(String key) {
    log.info("Request received to get value for the key : {}", key);
    Object obj = redis.getValue(key);
    //Type type = new TypeToken<String>() {}.getType();
    String value = obj.toString();
    //return gson.fromJson(value, type);
    return new KeyValueDto(key, value);
  }


  @Override
  public ResponseDto storeValue(KeyValueDto dto) {
    log.info("Request received to store or update with key : {} and value : {}",
        dto.getKey(), dto.getValue());
    redis.insertValueWithoutExpiry(dto.getKey(), dto.getValue());
    return new ResponseDto("Data Successfully Stored");
  }
}
