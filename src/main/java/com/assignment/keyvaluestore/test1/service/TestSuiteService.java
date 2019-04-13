package com.assignment.keyvaluestore.test1.service;

import com.assignment.keyvaluestore.test1.dto.TestCaseExecutionResponseDto;
import java.util.List;

public interface TestSuiteService {

  List<TestCaseExecutionResponseDto> executeTestCases();
}
