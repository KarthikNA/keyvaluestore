package com.assignment.keyvaluestore.test1.exception;

import com.assignment.keyvaluestore.test1.dto.ErrorDetail;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

  /**
   * Validation exception handler.
   * @param ex exception.
   * @return ErrorDetail.
   */
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(value = ValidationException.class)
  public ResponseEntity<ErrorDetail> handleValidationException (
      ValidationException ex) {
    logger.error("Validation exception occurred due to cause : " + ex.getCause()
        + "\n Message : " + ex.getMessage());
    ErrorDetail errorDetail = new ErrorDetail(ex.getCause().getMessage(),
        HttpStatus.BAD_REQUEST.getReasonPhrase(), ex.getMessage());
    return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);
  }

  /**
   * NotFoundException exception handler.
   * @param ex exception.
   * @return ErrorDetail.
   */
  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(value = NotFoundException.class)
  public ResponseEntity<ErrorDetail> handleNotFoundException(
      NotFoundException ex) {
    logger.error("Resource Not Found exception occurred due to cause : " + ex.getCause()
        + "\n Message : " + ex.getMessage());
    ErrorDetail errorDetail = new ErrorDetail(ex.getCause().getMessage(),
        HttpStatus.NOT_FOUND.getReasonPhrase(), ex.getMessage());
    return new ResponseEntity<>(errorDetail, HttpStatus.NOT_FOUND);
  }

  /**
   * comes here in case of called services throw an unknown exception.
   * default Exception error handler.
   * @param ex .
   * @return ErrorDetail.
   */
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(value = Exception.class)
  public ResponseEntity<ErrorDetail> handleException(Exception ex) {
    logger.error("default exception : " + ex.getMessage());
    ErrorDetail errorDetail = new ErrorDetail(HttpStatus.INTERNAL_SERVER_ERROR.toString(),
        "Default Exception", ex.getMessage());
    return new ResponseEntity<>(errorDetail, HttpStatus.INTERNAL_SERVER_ERROR);
  }


}
