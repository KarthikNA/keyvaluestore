package com.assignment.keyvaluestore.test1.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/status")
public class StatusController {

  /**
   * used to the status check of the application.
   * @return map of a string and appropriate state of the application.
   */
  @RequestMapping(value = "", method = RequestMethod.GET)
  public ResponseEntity<Map<String, String>> statusController() {
    Map<String, String> response = new HashMap<>();
    response.put("message", "Key-Value Store Project UP & RUNNING");
    return new ResponseEntity(response, HttpStatus.OK);
  }
}
