package io.nodom.cucumber.dto;


import javax.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


@Getter
@Builder
public class User {

  @NotEmpty
  private String firstName;

  @NotEmpty
  private String lastName;

  @NotEmpty
  private String email;
}
