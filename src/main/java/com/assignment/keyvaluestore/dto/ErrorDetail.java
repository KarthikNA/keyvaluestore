package com.assignment.keyvaluestore.dto;

public class ErrorDetail {

  private String errorSubCode;
  private String errorDescription;
  private String errorMessage;

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
   * error detail with ui errorMessage.
   * @param errorSubCode     .
   * @param errorDescription .
   * @param errorMessage          .
   */
  public ErrorDetail(String errorSubCode, String errorDescription, String errorMessage) {
    this.errorSubCode = errorSubCode;
    this.errorDescription = errorDescription;
    this.errorMessage = errorMessage;
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

  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }

}
