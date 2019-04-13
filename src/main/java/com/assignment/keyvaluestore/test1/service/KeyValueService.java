package com.assignment.keyvaluestore.test1.service;

import com.assignment.keyvaluestore.test1.dto.KeyValueDto;
import com.assignment.keyvaluestore.test1.dto.ResponseDto;

public interface KeyValueService {

  KeyValueDto getValue(String key);

  ResponseDto storeValue(KeyValueDto dto);
}
