package io.nodom.cucumber;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.google.gson.JsonParser;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import io.nodom.configuration.TestBeanConfig;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;


@CucumberContextConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK, classes = TestBeanConfig.class)
@AutoConfigureMockMvc
@RequiredArgsConstructor
public class ScenarioDefs {

  private final MockMvc mockMvc;
  private final JsonParser jsonParser;
  private MvcResult actualResult;

  @Given("we have {int} user in the DB")
  public void we_have_user_in_the_DB(Integer int1) {
    // no-op
  }

  @When("GET /{int} is called")
  public void get_is_called(int userId) throws Throwable {
    actualResult = mockMvc.perform(get("/" + userId)).andReturn();
  }

  @Then("a user with httpCode {int} and response should be JSON:")
  public void a_user_with_httpCode_and_response_should_be_JSON_$(int httpCode, String docString)
      throws Throwable {
    Assertions.assertEquals(actualResult.getResponse().getStatus(), httpCode);
    Assertions
        .assertEquals(jsonParser.parse(docString),
            jsonParser.parse(actualResult.getResponse().getContentAsString()));
  }

  @Then("an httpCode {int} and response should be JSON:")
  public void an_httpCode_and_response_should_be_JSON_$(int httpCode, String docString)
      throws Throwable {
    Assertions.assertEquals(actualResult.getResponse().getStatus(), httpCode);
    Assertions
        .assertEquals(jsonParser.parse(docString),
            jsonParser.parse(actualResult.getResponse().getContentAsString()));
  }


}
