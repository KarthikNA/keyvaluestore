package com.assignment.keyvaluestore.validation;

import com.assignment.keyvaluestore.dto.KeyValueDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Validator {

  @Autowired
  private Validation validation;

  /**
   * validate the insertion of a key value pair.
   * @param dto KeyValueDto.
   */
  public void validateInsertKeyValue(KeyValueDto dto) {
    validation.validateKey(dto.getKey());
    validation.validateValue(dto.getValue());
  }

  /**
   * validate the request to fetch the value from the key.
   * @param key key of value that needs to be fetched.
   */
  public void validateGetValue(String key) {
    validation.validateKey(key);
  }
}
