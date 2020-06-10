package io.nodom.configuration;


import io.nodom.cucumber.domain.UserEntity;
import io.nodom.cucumber.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

@TestConfiguration
public class TestBeanConfig {

  @Bean
  @Primary
  public CommandLineRunner commandLineRunner(UserRepository userRepository) {
    return (args) -> {
      List<UserEntity> users = IntStream.range(1, 11)
          .mapToObj(index -> UserEntity.builder()
              .firstName("firstName-" + index)
              .lastName("lastName-" + index)
              .email("firstName_" + index + "@gmail.com")
              .build())
          .collect(toList());

      userRepository.saveAll(users);
    };
  }
}
