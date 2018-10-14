package com.assignment.keyvaluestore.validation;

import com.assignment.keyvaluestore.exception.ValidationException;
import org.springframework.stereotype.Component;

import static com.assignment.keyvaluestore.enums.ErrorCodeEnum.BAD_REQ_400_01;
import static com.assignment.keyvaluestore.enums.ErrorCodeEnum.BAD_REQ_400_02;

@Component
public class Validation {

  public void validateKey(String key) {
    if (key == null || key.isEmpty() || key.length() == 0) {
      throw new ValidationException(
          BAD_REQ_400_01.getValue(), new Throwable(BAD_REQ_400_01.getId()));
    }
  }

  public void validateValue(String value) {
    if (value == null || value.isEmpty() || value.length() == 0) {
      throw new ValidationException(
          BAD_REQ_400_02.getValue(), new Throwable(BAD_REQ_400_02.getId()));
    }
  }

}
