package io.nodom.cucumber;

import static java.util.stream.Collectors.toList;

import io.nodom.cucumber.domain.UserEntity;
import io.nodom.cucumber.repository.UserRepository;
import java.util.List;
import java.util.stream.IntStream;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CucumberDemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(CucumberDemoApplication.class, args);
  }

  @Bean
  public CommandLineRunner commandLineRunner(UserRepository userRepository) {
    return (args) -> {
      List<UserEntity> users = IntStream.range(1, 11)
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
