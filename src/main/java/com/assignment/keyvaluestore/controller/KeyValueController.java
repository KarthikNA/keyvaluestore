package com.assignment.keyvaluestore.controller;

import com.assignment.keyvaluestore.dto.KeyValueDto;
import com.assignment.keyvaluestore.dto.ResponseDto;
import com.assignment.keyvaluestore.service.KeyValueService;
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

  /**
   * get a the value corresponding to the key.
   * @param key .
   * @return KeyValueDto comprising of key and value.
   */
  @RequestMapping(value = "/key/{key}", method = RequestMethod.GET)
  public ResponseEntity<KeyValueDto> getValue(@PathVariable(name = "key") String key) {
    KeyValueDto response = service.getValue(key);
    return new ResponseEntity(response, HttpStatus.OK);
  }

  /**
   * insert a key value pair into the data store.
   * @param dto KeyValueDto.
   * @return ResponseDto indicating the success or failure of the operation.
   */
  @RequestMapping(value = "/key", method = RequestMethod.PUT)
  public ResponseEntity<ResponseDto> putValue(@RequestBody KeyValueDto dto) {
    ResponseDto response = service.storeValue(dto);
    return new ResponseEntity(response, HttpStatus.OK);
  }
}
