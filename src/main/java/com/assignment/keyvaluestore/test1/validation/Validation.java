package com.assignment.keyvaluestore.test1.validation;

import com.assignment.keyvaluestore.test1.exception.ValidationException;
import org.springframework.stereotype.Component;

import static com.assignment.keyvaluestore.test1.enums.ErrorCodeEnum.BAD_REQ_400_01;
import static com.assignment.keyvaluestore.test1.enums.ErrorCodeEnum.BAD_REQ_400_02;

@Component
public class Validation {

  /**
   * checking if the key is empty or not.
   * @param key .
   */
  public void validateKey(String key) {
    if (key == null || key.isEmpty() || key.length() == 0) {
      throw new ValidationException(
          BAD_REQ_400_01.getValue(), new Throwable(BAD_REQ_400_01.getId()));
    }
  }

  /**
   * checking if the value associated with the key is empty of not.
   * @param value .
   */
  public void validateValue(String value) {
    if (value == null || value.isEmpty() || value.length() == 0) {
      throw new ValidationException(
          BAD_REQ_400_02.getValue(), new Throwable(BAD_REQ_400_02.getId()));
    }
  }

}
