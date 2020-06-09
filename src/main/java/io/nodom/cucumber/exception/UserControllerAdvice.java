package io.nodom.cucumber.exception;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import io.nodom.cucumber.controller.UserController;
import io.nodom.cucumber.dto.ErrorDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice(basePackageClasses = UserController.class)
public class UserControllerAdvice {


  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<ErrorDto> handleUserNotFoundException(UserNotFoundException ex) {
    ErrorDto errorDto = new ErrorDto(NOT_FOUND.name(), ex.getMessage(), ex.getUserId());
    return new ResponseEntity<>(errorDto, NOT_FOUND);
  }
}
