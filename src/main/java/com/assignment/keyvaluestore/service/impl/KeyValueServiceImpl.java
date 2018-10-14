package com.assignment.keyvaluestore.service.impl;

import com.assignment.keyvaluestore.dto.KeyValueDto;
import com.assignment.keyvaluestore.dto.ResponseDto;
import com.assignment.keyvaluestore.exception.NotFoundException;
import com.assignment.keyvaluestore.exception.ValidationException;
import com.assignment.keyvaluestore.service.KeyValueService;
import com.assignment.keyvaluestore.service.RedisService;
import com.assignment.keyvaluestore.validation.Validator;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.assignment.keyvaluestore.enums.ErrorCodeEnum.NOT_FOUND_404_01;

@Service
public class KeyValueServiceImpl implements KeyValueService {

  private static final Gson gson = new GsonBuilder().serializeNulls().create();

  private static final Logger log = LoggerFactory.getLogger(KeyValueServiceImpl.class);

  @Autowired
  private RedisService redis;

  @Autowired
  private Validator validator;

  @Override
  public KeyValueDto getValue(String key) {
    log.info("Request received to get value for the key : {}", key);
    validator.validateGetValue(key);
    Object obj = redis.getValue(key);
    if (obj == null) {
      throw new NotFoundException(
          NOT_FOUND_404_01.getValue(), new Throwable(NOT_FOUND_404_01.getId()));
    }
    //Type type = new TypeToken<String>() {}.getType();
    String value = obj.toString();
    //return gson.fromJson(value, type);
    if (value == null) {
      throw new ValidationException(
          NOT_FOUND_404_01.getValue(), new Throwable(NOT_FOUND_404_01.getId()));
    }
    return new KeyValueDto(key, value);
  }


  @Override
  public ResponseDto storeValue(KeyValueDto dto) {
    log.info("Request received to store or update with key : {} and value : {}",
        dto.getKey(), dto.getValue());
    validator.validateInsertKeyValue(dto);
    redis.insertValueWithoutExpiry(dto.getKey(), dto.getValue());
    return new ResponseDto("Data Successfully Stored");
  }
}
