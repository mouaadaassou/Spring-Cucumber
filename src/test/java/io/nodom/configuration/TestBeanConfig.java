package io.nodom.configuration;


import static java.util.stream.Collectors.toList;

import com.fasterxml.jackson.core.JsonFactory;
import com.google.gson.JsonParser;
import io.nodom.cucumber.domain.UserEntity;
import io.nodom.cucumber.repository.UserRepository;
import java.util.List;
import java.util.stream.IntStream;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@TestConfiguration
public class TestBeanConfig {

  @Bean
  public JsonParser jsonParser() {
    return new JsonParser();
  }

  @Bean
  @Primary
  public CommandLineRunner commandLineRunner(UserRepository userRepository) {
    return (args) -> {
      List<UserEntity> users = IntStream.range(1, 10)
          .mapToObj(index -> UserEntity.builder()
              .firstName("firstName-" + index)
              .lastName("lastName-" + index)
              .email("firstName_" + index + "gmail.com")
              .build())
          .collect(toList());

      userRepository.saveAll(users);
    };
  }
}
