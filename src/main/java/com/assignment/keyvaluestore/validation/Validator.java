package com.assignment.keyvaluestore.validation;

import com.assignment.keyvaluestore.dto.KeyValueDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Validator {

  @Autowired
  private Validation validation;

  public void validateInsertKeyValue(KeyValueDto dto) {
    validation.validateKey(dto.getKey());
    validation.validateValue(dto.getValue());
  }

  public void validateGetValue(String key) {
    validation.validateKey(key);
  }
}
