package io.nodom.cucumber.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorDto {

  private String httpStatus;
  private String errorMessage;
  private Long logRef;
}
