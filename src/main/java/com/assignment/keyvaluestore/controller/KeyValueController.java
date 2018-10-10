package com.assignment.keyvaluestore.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ops")
public class KeyValueController {

  @RequestMapping(value = "/key/{key}", method = RequestMethod.GET)
  public ResponseEntity<Map<String, String>> getValue() {
    Map<String, String> response = new HashMap<>();
    // todo - get data corresponding to key
    return new ResponseEntity(response, HttpStatus.OK);
  }

  @RequestMapping(value = "/key/{key}", method = RequestMethod.GET)
  public ResponseEntity<Map<String, String>> putValue() {
    Map<String, String> response = new HashMap<>();
    // todo - update key if already exists, else insert newly
    return new ResponseEntity(response, HttpStatus.OK);
  }
}
