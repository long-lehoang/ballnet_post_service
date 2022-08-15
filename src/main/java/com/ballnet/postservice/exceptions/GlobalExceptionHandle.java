package com.ballnet.postservice.exceptions;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;


@AllArgsConstructor
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandle extends ResponseEntityExceptionHandler {

  private final MessageSource messageSource;

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    return ResponseEntity.badRequest().body(ex.getAllErrors().stream()
        .map(ObjectError::getDefaultMessage).filter(Objects::nonNull).toList());
  }


  @ExceptionHandler(EmptyResultDataAccessException.class)
  public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex) {
    log.debug(ex.getMessage());
    return ResponseEntity.notFound().build();
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Object> handleCommonException(Exception ex,
                                                      HttpServletRequest request, HttpServletResponse response) {
    ex.printStackTrace();
    log.error(ex.getMessage());
    if (ex instanceof NullPointerException) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
  }
}
