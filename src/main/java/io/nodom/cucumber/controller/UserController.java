package io.nodom.cucumber.controller;


import io.nodom.cucumber.domain.UserEntity;
import io.nodom.cucumber.dto.User;
import io.nodom.cucumber.exception.UserNotFoundException;
import io.nodom.cucumber.mapper.UserMapper;
import io.nodom.cucumber.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static io.nodom.cucumber.mapper.UserMapper.mapUserEntityToUser;
import static io.nodom.cucumber.mapper.UserMapper.mapUserToUserEntity;
import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class UserController {

  private static final String ID_PATH_TEMPLATE = "/{id}";
  private final UserRepository userRepository;

  @RequestMapping(method = RequestMethod.OPTIONS)
  public ResponseEntity<?> getSupportedHTTPOperations() {
    return ResponseEntity.ok()
            .allow(HttpMethod.GET, HttpMethod.POST, HttpMethod.DELETE,
                    HttpMethod.OPTIONS).build();
  }

  @GetMapping
  public ResponseEntity<List<User>> getUsers() {
    return ResponseEntity.status(HttpStatus.OK)
        .body(this.userRepository.findAll().stream()
            .map(UserMapper::mapUserEntityToUser).collect(toList()));
  }

  @GetMapping("/{userId}")
  public ResponseEntity<User> getUserById(@PathVariable("userId") Long userId)
      throws UserNotFoundException {
    return ResponseEntity.status(HttpStatus.OK)
        .body(mapUserEntityToUser(this.userRepository.findById(userId)
            .orElseThrow(() -> new UserNotFoundException(userId))));
  }

  @PostMapping
  public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
    UserEntity newUser = this.userRepository.save(mapUserToUserEntity(user));

    URI createdUserUri = ServletUriComponentsBuilder.fromCurrentRequest()
            .path(ID_PATH_TEMPLATE).buildAndExpand(newUser.getId()).toUri();

    return ResponseEntity.created(createdUserUri).body(mapUserEntityToUser(newUser));
  }
}
