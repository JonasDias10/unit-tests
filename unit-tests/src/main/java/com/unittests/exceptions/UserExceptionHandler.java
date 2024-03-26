package com.unittests.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserExceptionHandler {

      @ExceptionHandler(UserException.class)
      public ResponseEntity<Object> handleUserException(UserException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
      }

}