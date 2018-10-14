package com.assignment.keyvaluestore.dto;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ResponseDto {

  private static final Gson gson = new GsonBuilder().serializeNulls().create();

  private String message;

  public ResponseDto() {
  }

  public ResponseDto(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public String toString() {
    return gson.toJson(this);
  }
}
