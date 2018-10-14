package com.assignment.keyvaluestore.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class TestCaseExecutionResponseDto {

  private static final Gson gson = new GsonBuilder().serializeNulls().create();

  @JsonProperty("Test Case Number")
  private Integer testCaseNumber;
  @JsonProperty("Test Case Description")
  private String testCaseDescription;
  @JsonProperty("Test Case Result")
  private String testCaseResult;

  public TestCaseExecutionResponseDto() {
  }

  public TestCaseExecutionResponseDto(Integer testCaseNumber, String testCaseDescription, String
      testCaseResult) {
    this.testCaseNumber = testCaseNumber;
    this.testCaseDescription = testCaseDescription;
    this.testCaseResult = testCaseResult;
  }

  public Integer getTestCaseNumber() {
    return testCaseNumber;
  }

  public void setTestCaseNumber(Integer testCaseNumber) {
    this.testCaseNumber = testCaseNumber;
  }

  public String getTestCaseDescription() {
    return testCaseDescription;
  }

  public void setTestCaseDescription(String testCaseDescription) {
    this.testCaseDescription = testCaseDescription;
  }

  public String getTestCaseResult() {
    return testCaseResult;
  }

  public void setTestCaseResult(String testCaseResult) {
    this.testCaseResult = testCaseResult;
  }

  @Override
  public String toString() {
    return gson.toJson(this);
  }
}
