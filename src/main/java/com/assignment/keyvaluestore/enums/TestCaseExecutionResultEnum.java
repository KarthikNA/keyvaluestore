package com.assignment.keyvaluestore.enums;

public enum TestCaseExecutionResultEnum {

  PASS(1, "PASS"),
  FAIL(0, "FAIL");

  TestCaseExecutionResultEnum(Integer id, String value) {
    this.id = id;
    this.value = value;
  }

  private Integer id;
  private String value;

  public Integer getId() {
    return id;
  }

  public String getValue() {
    return value;
  }
}
