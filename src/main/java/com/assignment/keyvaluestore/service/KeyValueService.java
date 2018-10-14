package com.assignment.keyvaluestore.service;

import com.assignment.keyvaluestore.dto.KeyValueDto;
import com.assignment.keyvaluestore.dto.ResponseDto;

public interface KeyValueService {

  KeyValueDto getValue(String key);

  ResponseDto storeValue(KeyValueDto dto);
}
