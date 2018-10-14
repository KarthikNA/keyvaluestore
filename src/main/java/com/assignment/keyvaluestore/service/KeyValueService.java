package com.assignment.keyvaluestore.service;

import com.assignment.keyvaluestore.dto.KeyValueDto;
import com.assignment.keyvaluestore.dto.ResponseDto;

public interface KeyValueService {

  ResponseDto storeValue(KeyValueDto dto);
}
