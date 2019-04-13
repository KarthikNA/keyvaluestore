package com.assignment.keyvaluestore.test1.controller;

import com.assignment.keyvaluestore.test1.dto.TestCaseExecutionResponseDto;
import com.assignment.keyvaluestore.test1.service.TestSuiteService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/run-tests")
public class TestSuiteController {

  @Autowired
  private TestSuiteService service;

  /**
   * run the tests written plain java.
   * @return ResponseDto.
   */
  @RequestMapping(value = "", method = RequestMethod.POST)
  public ResponseEntity<List<TestCaseExecutionResponseDto>> executeTestCases() {
    List<TestCaseExecutionResponseDto> responses =  service.executeTestCases();
    return new ResponseEntity(responses, HttpStatus.OK);
  }
}
