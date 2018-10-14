package com.assignment.keyvaluestore.service;

import com.assignment.keyvaluestore.dto.TestCaseExecutionResponseDto;
import java.util.List;

public interface TestSuiteService {

  List<TestCaseExecutionResponseDto> executeTestCases();
}
