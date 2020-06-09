package io.nodom.cucumber.exception;


import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends Exception {

  @Getter
  private Long userId;
  private static final String ERROR_MESSAGE = "User with id %d NOT FOUND";

  public UserNotFoundException(Exception exception, Long userId) {
    super(String.format(ERROR_MESSAGE, userId), exception);
    this.userId = userId;
  }

  public UserNotFoundException(Long userId) {
    super(String.format(ERROR_MESSAGE, userId));
    this.userId = userId;
  }
}
