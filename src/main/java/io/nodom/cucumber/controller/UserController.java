package io.nodom.cucumber.controller;


import static java.util.stream.Collectors.toList;

import io.nodom.cucumber.domain.UserEntity;
import io.nodom.cucumber.dto.User;
import io.nodom.cucumber.exception.UserNotFoundException;
import io.nodom.cucumber.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class UserController {

  private final UserRepository userRepository;

  @GetMapping
  public ResponseEntity<List<User>> getUsers() {
    return ResponseEntity.status(HttpStatus.OK)
        .body(this.userRepository.findAll().stream()
            .map(this::mapUserEntityToUser).collect(toList()));
  }

  @GetMapping("/{userId}")
  public ResponseEntity<User> getUserById(@PathVariable("userId") Long userId)
      throws UserNotFoundException {
    return ResponseEntity.status(HttpStatus.OK)
        .body(this.mapUserEntityToUser(this.userRepository.findById(userId)
            .orElseThrow(() -> new UserNotFoundException(userId))));
  }

  private User mapUserEntityToUser(UserEntity userEntity) {
    return User.builder()
        .firstName(userEntity.getFirstName())
        .lastName(userEntity.getLastName())
        .build();
  }
}
