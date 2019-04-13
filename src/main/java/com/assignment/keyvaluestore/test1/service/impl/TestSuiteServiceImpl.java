package com.assignment.keyvaluestore.test1.service.impl;

import com.assignment.keyvaluestore.test1.dto.KeyValueDto;
import com.assignment.keyvaluestore.test1.dto.TestCaseExecutionResponseDto;
import com.assignment.keyvaluestore.test1.enums.TestCaseExecutionResultEnum;
import com.assignment.keyvaluestore.test1.service.KeyValueService;
import com.assignment.keyvaluestore.test1.service.RedisService;
import com.assignment.keyvaluestore.test1.service.TestSuiteService;
import com.assignment.keyvaluestore.test1.util.DataStore;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestSuiteServiceImpl implements TestSuiteService {

  private static final Logger log = LoggerFactory.getLogger(TestSuiteServiceImpl.class);

  @Autowired
  private RedisService redis;

  @Autowired
  private KeyValueService service;

  @Autowired
  private DataStore dataStore;

  @Override
  public List<TestCaseExecutionResponseDto> executeTestCases() {
    List<TestCaseExecutionResponseDto> responseDtos = new ArrayList<>();
    clearStorage();
    responseDtos.add(testCase1());
    responseDtos.add(testCase2());
    responseDtos.add(testCase3());
    responseDtos.add(testCase4());
    responseDtos.add(testCase6());
    responseDtos.add(testCase7());
    responseDtos.add(testCase8());
    return responseDtos;
  }

  private TestCaseExecutionResponseDto testCase1() {
    TestCaseExecutionResultEnum resultEnum = TestCaseExecutionResultEnum.FAIL;
    try {
      KeyValueDto dto = service.getValue("random_key");
    } catch (Exception ex) {
      resultEnum = TestCaseExecutionResultEnum.PASS;
    }
    clearStorage();
    return testCaseResponse(1, "Retrieve data from empty local and disk store", resultEnum);
  }

  private TestCaseExecutionResponseDto testCase2() {
    TestCaseExecutionResultEnum resultEnum = TestCaseExecutionResultEnum.FAIL;
    try {
      service.storeValue(new KeyValueDto("1", "1"));
      service.storeValue(new KeyValueDto("2", "2"));
      service.storeValue(new KeyValueDto("3", "3"));
      KeyValueDto dto = service.getValue("1");
      resultEnum = TestCaseExecutionResultEnum.PASS;
    } catch (Exception ex) {
      resultEnum = TestCaseExecutionResultEnum.FAIL;
    }
    clearStorage();
    return testCaseResponse(2, "Retrieve data from local store and key in local store only ", resultEnum);
  }

  private TestCaseExecutionResponseDto testCase3() {
    TestCaseExecutionResultEnum resultEnum = TestCaseExecutionResultEnum.FAIL;
    try {
      service.storeValue(new KeyValueDto("1", "1"));
      service.storeValue(new KeyValueDto("2", "2"));
      service.storeValue(new KeyValueDto("3", "3"));
      service.storeValue(new KeyValueDto("4", "4"));
      KeyValueDto dto = service.getValue("1");
      resultEnum = TestCaseExecutionResultEnum.PASS;
    } catch (Exception ex) {
      resultEnum = TestCaseExecutionResultEnum.FAIL;
    }
    clearStorage();
    return testCaseResponse(3, "Retrieve data from disk store and key in disk store and not local store", resultEnum);
  }

  private TestCaseExecutionResponseDto testCase4() {
    TestCaseExecutionResultEnum resultEnum = TestCaseExecutionResultEnum.FAIL;
    try {
      service.storeValue(new KeyValueDto("1", "1"));
      service.storeValue(new KeyValueDto("2", "2"));
      service.storeValue(new KeyValueDto("3", "3"));
      service.storeValue(new KeyValueDto("4", "4"));
      KeyValueDto dto = service.getValue("x");
      resultEnum = TestCaseExecutionResultEnum.FAIL;
    } catch (Exception ex) {
      resultEnum = TestCaseExecutionResultEnum.PASS;
    }
    clearStorage();
    return testCaseResponse(4, "Retrieve data where key not present in local and disk store", resultEnum);
  }

  private TestCaseExecutionResponseDto testCase5() {
    TestCaseExecutionResultEnum resultEnum = TestCaseExecutionResultEnum.FAIL;
    try {
      service.storeValue(new KeyValueDto("1", "1"));
      service.storeValue(new KeyValueDto("2", "2"));
      service.storeValue(new KeyValueDto("3", "3"));
      service.storeValue(new KeyValueDto("4", "4"));
      service.storeValue(new KeyValueDto("5", "5"));
      KeyValueDto dto = service.getValue("5");
      resultEnum = TestCaseExecutionResultEnum.PASS;
    } catch (Exception ex) {
      resultEnum = TestCaseExecutionResultEnum.FAIL;
    }
    clearStorage();
    return testCaseResponse(5, "Key present only in local storage but data is present in both local and disk store", resultEnum);
  }

  private TestCaseExecutionResponseDto testCase6() {
    TestCaseExecutionResultEnum resultEnum = TestCaseExecutionResultEnum.FAIL;
    try {
      service.storeValue(new KeyValueDto("1", "1"));
      service.storeValue(new KeyValueDto("2", "2"));
      service.storeValue(new KeyValueDto("3", "3"));
      service.storeValue(new KeyValueDto("1", "4"));
      resultEnum = TestCaseExecutionResultEnum.PASS;
    } catch (Exception ex) {
      resultEnum = TestCaseExecutionResultEnum.FAIL;
    }
    clearStorage();
    return testCaseResponse(6, "Key present in local storage and not disk storage.Update the value corresponding to the key without inserting new record", resultEnum);
  }


  private TestCaseExecutionResponseDto testCase7() {
    TestCaseExecutionResultEnum resultEnum = TestCaseExecutionResultEnum.FAIL;
    try {
      service.storeValue(new KeyValueDto("1", "1"));
      service.storeValue(new KeyValueDto("2", "2"));
      service.storeValue(new KeyValueDto("3", "3"));
      service.storeValue(new KeyValueDto("4", "4"));
      service.storeValue(new KeyValueDto("4", "15"));
      service.storeValue(new KeyValueDto("5", "5"));
      service.storeValue(new KeyValueDto("6", "6"));
      service.storeValue(new KeyValueDto("6", "12"));
      service.storeValue(new KeyValueDto("1", "4"));
      resultEnum = TestCaseExecutionResultEnum.PASS;
    } catch (Exception ex) {
      resultEnum = TestCaseExecutionResultEnum.FAIL;
    }
    clearStorage();
    return testCaseResponse(7, "Key present in disk storage and not local storage.Update the value corresponding to the key without inserting new record", resultEnum);
  }


  private TestCaseExecutionResponseDto testCase8() {
    TestCaseExecutionResultEnum resultEnum = TestCaseExecutionResultEnum.FAIL;
    try {
      service.storeValue(new KeyValueDto("1", "1"));
      service.storeValue(new KeyValueDto("2", "2"));
      KeyValueDto dto = service.getValue("1");
      service.storeValue(new KeyValueDto("3", "3"));
      service.storeValue(new KeyValueDto("4", "4"));
      dto = service.getValue("1");
      service.storeValue(new KeyValueDto("5", "5"));
      dto = service.getValue("5");
      service.storeValue(new KeyValueDto("6", "6"));
      service.storeValue(new KeyValueDto("1", "4"));
      dto = service.getValue("5");
      service.storeValue(new KeyValueDto("2", "w"));
      dto = service.getValue("3");
      resultEnum = TestCaseExecutionResultEnum.PASS;
    } catch (Exception ex) {
      resultEnum = TestCaseExecutionResultEnum.FAIL;
    }
    clearStorage();
    return testCaseResponse(8, "Perform a valid sequence of insertions and fetches. No error should occur", resultEnum);
  }




  private TestCaseExecutionResponseDto testCaseResponse(
      Integer testCaseNumber, String description, TestCaseExecutionResultEnum resultEnum) {
    log.info("\n ==================================\n Test Case #{} \n Test Case Description : {} \n"
        + " Test Case Result : {} \n ==================================\n",
        testCaseNumber, description, resultEnum.getValue());
    return new TestCaseExecutionResponseDto(testCaseNumber, description, resultEnum.getValue());
  }

  private void clearStorage() {
    // clear local storage
    dataStore.clearLocalStorage();
    // clear disk storage
    redis.flushDb();
  }
}
