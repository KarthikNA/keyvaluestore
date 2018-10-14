package com.assignment.keyvaluestore.controller;

import com.assignment.keyvaluestore.dto.KeyValueDto;
import com.assignment.keyvaluestore.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/run-tests")
public class TestSuiteController {

  /**
   * run the tests written plain java.
   * @return ResponseDto.
   */
  @RequestMapping(value = "", method = RequestMethod.POST)
  public ResponseEntity<ResponseDto> putValue() {
    ResponseDto response = new ResponseDto("Test Suite has Completed Execution");
    return new ResponseEntity(response, HttpStatus.OK);
  }
}
