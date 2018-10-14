package com.assignment.keyvaluestore.controller;

import com.assignment.keyvaluestore.dto.KeyValueDto;
import com.assignment.keyvaluestore.service.KeyValueService;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ops")
public class KeyValueController {


  @Autowired
  private KeyValueService service;

  @RequestMapping(value = "/key/{key}", method = RequestMethod.GET)
  public ResponseEntity<Map<String, String>> getValue(@PathVariable(name = "key") String key) {
    Map<String, String> response = new HashMap<>();
    // todo - get data corresponding to key
    return new ResponseEntity(response, HttpStatus.OK);
  }

  @RequestMapping(value = "/key", method = RequestMethod.PUT)
  public ResponseEntity<Map<String, String>> putValue(@RequestBody KeyValueDto dto) {
    Map<String, String> response = new HashMap<>();
    service.storeValue(dto);
    // todo - update key if already exists, else insert newly
    return new ResponseEntity(response, HttpStatus.OK);
  }
}