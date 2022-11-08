package com.joaogabrielrc.auth;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@SpringBootTest
@AutoConfigureMockMvc
class AuthenticationControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void itShouldReturnBadRequest() throws Exception {
    URI uri = new URI("/api/v1/auth");
    String content = "{}";
    mockMvc.perform(
        MockMvcRequestBuilders
          .post(uri)
          .content(content)
          .contentType(APPLICATION_JSON)
      )
      .andExpect(
        MockMvcResultMatchers.status().isBadRequest()
      );
  }

}