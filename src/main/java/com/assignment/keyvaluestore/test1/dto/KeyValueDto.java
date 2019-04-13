package com.assignment.keyvaluestore.test1.dto;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class KeyValueDto {

  private static final Gson gson = new GsonBuilder().serializeNulls().create();

  private String key;
  private String value;

  public KeyValueDto() {
  }

  public KeyValueDto(String key, String value) {
    this.key = key;
    this.value = value;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return gson.toJson(this);
  }
}
