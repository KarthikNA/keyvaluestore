package com.assignment.keyvaluestore.test1.util;

import com.assignment.keyvaluestore.test1.dto.KeyValueDto;

public interface DataStore {

  KeyValueDto getValue(String key);

  void insertKeyValue(KeyValueDto dto);

  void clearLocalStorage();
}
