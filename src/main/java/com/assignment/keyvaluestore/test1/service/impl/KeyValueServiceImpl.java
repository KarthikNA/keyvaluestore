package com.assignment.keyvaluestore.test1.service.impl;

import com.assignment.keyvaluestore.test1.dto.KeyValueDto;
import com.assignment.keyvaluestore.test1.dto.ResponseDto;
import com.assignment.keyvaluestore.test1.exception.ValidationException;
import com.assignment.keyvaluestore.test1.service.KeyValueService;
import com.assignment.keyvaluestore.test1.util.DataStore;
import com.assignment.keyvaluestore.test1.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import static com.assignment.keyvaluestore.test1.enums.ErrorCodeEnum.NOT_FOUND_404_01;

@Service
public class KeyValueServiceImpl implements KeyValueService {

  private static final Logger log = LoggerFactory.getLogger(KeyValueServiceImpl.class);

  @Autowired
  private Validator validator;

  @Autowired
  @Qualifier("redisDataStore")
  private DataStore dataStore;

  @Override
  public KeyValueDto getValue(String key) {
    log.info("Request received to get value for the key : '{}'", key);
    validator.validateGetValue(key);
    log.info("Passed All Validations for key : '{}'", key);
    KeyValueDto dto = dataStore.getValue(key);
    if (dto == null) {
      log.error("key : '{}' is Not Found in Data Store", key);
      throw new ValidationException(
          NOT_FOUND_404_01.getValue(), new Throwable(NOT_FOUND_404_01.getId()));
    }
    log.info("Data associated with key : '{}' is value : '{}'", key, dto.getValue());
    return dto;
  }

  @Override
  public ResponseDto storeValue(KeyValueDto dto) {
    log.info("Request received to store or update with key : '{}' and value : '{}",
        dto.getKey(), dto.getValue());
    validator.validateInsertKeyValue(dto);
    log.info("Passed All Validations for key : '{}' and value : '{}'", dto.getKey(), dto.getValue());
    dataStore.insertKeyValue(dto);
    log.info("Information Successfully Stored for key : '{}' and value : '{}'",
        dto.getKey(), dto.getValue());
    return new ResponseDto("Data Successfully Stored");
  }
}
