package com.assignment.keyvaluestore.test1.enums;

public enum ErrorCodeEnum {

  BAD_REQ_400_01("400_01", "Key is Empty"),
  BAD_REQ_400_02("400_02", "Value is Empty"),
  NOT_FOUND_404_01("404_01", "Key Not Found");


  ErrorCodeEnum(String id, String value) {
    this.id = id;
    this.value = value;
  }

  private String id;
  private String value;

  public String getId() {
    return id;
  }

  public String getValue() {
    return value;
  }

}
