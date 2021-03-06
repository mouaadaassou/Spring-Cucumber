package io.nodom.cucumber;


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

import java.io.UnsupportedEncodingException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.options;


@CucumberContextConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK, classes = TestBeanConfig.class)
@AutoConfigureMockMvc
@RequiredArgsConstructor
public class ScenarioDefs {

  private final MockMvc mockMvc;
  private MvcResult actualResult;

  @Given("we have {int} user in the DB")
  public void we_have_user_in_the_DB(Integer int1) {
    // no-op
  }

  @Given("we have {int} users in the DB")
  public void we_have_users_in_the_DB(int numberOfUsersInDB) {
    // no-ops
  }

  @When("GET /{int} is called")
  public void get_is_called(int userId) throws Throwable {
    actualResult = mockMvc.perform(get("/" + userId)).andReturn();
  }

  @When("GET / is called")
  public void get_all_is_Called() throws Exception {
    actualResult = mockMvc.perform(get("/")).andReturn();
  }

  @Then("a user with httpCode {int} and response should be JSON:")
  public void a_user_with_httpCode_and_response_should_be_JSON_$(int httpCode, String docString)
      throws Throwable {
    Assertions.assertEquals(actualResult.getResponse().getStatus(), httpCode);
    Assertions
        .assertEquals(JsonParser.parseString(docString),
            JsonParser.parseString(actualResult.getResponse().getContentAsString()));
  }

  @Then("an httpCode {int} and response should be JSON:")
  public void an_httpCode_and_response_should_be_JSON_$(int httpCode, String docString)
      throws Throwable {
    Assertions.assertEquals(actualResult.getResponse().getStatus(), httpCode);
    Assertions
        .assertEquals(JsonParser.parseString(docString),
            JsonParser.parseString(actualResult.getResponse().getContentAsString()));
  }

  @Then("an httpCode {int} with response should be JSON:")
  public void an_httpCode_and_response_should_be_JSON$(int httpCode, String docString)
          throws UnsupportedEncodingException {
    Assertions.assertEquals(actualResult.getResponse().getStatus(), httpCode);
    Assertions
            .assertEquals(JsonParser.parseString(docString),
                    JsonParser.parseString(actualResult.getResponse().getContentAsString()));
  }

  @When("OPTIONS / is called")
  public void options_is_called() throws Exception {
    actualResult = mockMvc.perform(options("/")).andReturn();
  }

  @Then("an http status of {int} is returned along with allow header:")
  public void an_http_status_of_200_is_returned_along_with_allow_header$(int httpCode, String allowHeader) {
    Assertions.assertEquals(httpCode, actualResult.getResponse().getStatus());
    Assertions.assertEquals(allowHeader, actualResult.getResponse().getHeader("allow"));
  }


  }
