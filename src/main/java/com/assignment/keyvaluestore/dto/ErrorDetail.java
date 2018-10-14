package com.assignment.keyvaluestore.dto;

public class ErrorDetail {

  private String errorSubCode;
  private String errorDescription;
  private String message;

  /**
   * @param errorSubCode     .
   * @param errorDescription .
   */
  public ErrorDetail(String errorSubCode, String errorDescription) {
    super();
    this.errorSubCode = errorSubCode;
    this.errorDescription = errorDescription;
  }

  /**
   * error detail with ui message.
   * @param errorSubCode     .
   * @param errorDescription .
   * @param message          .
   */
  public ErrorDetail(String errorSubCode, String errorDescription, String message) {
    this.errorSubCode = errorSubCode;
    this.errorDescription = errorDescription;
    this.message = message;
  }

  public String getErrorSubCode() {
    return errorSubCode;
  }

  public void setErrorSubCode(String errorSubCode) {
    this.errorSubCode = errorSubCode;
  }

  public String getErrorDescription() {
    return errorDescription;
  }

  public void setErrorDescription(String errorDescription) {
    this.errorDescription = errorDescription;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

}
