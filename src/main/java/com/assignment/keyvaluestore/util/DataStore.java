package com.assignment.keyvaluestore.util;

import com.assignment.keyvaluestore.dto.KeyValueDto;
import java.util.Collection;

public interface DataStore {

  KeyValueDto getValue(String key);

  void insertKeyValue(KeyValueDto dto);
}
