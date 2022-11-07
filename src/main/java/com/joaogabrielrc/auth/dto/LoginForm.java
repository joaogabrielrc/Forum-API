package com.joaogabrielrc.auth.dto;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.NotEmpty;

public class LoginForm {

  @NotEmpty
  private final String email;

  @NotEmpty
  private final String password;

  public LoginForm(String email, String password) {
    this.email = email;
    this.password = password;
  }

  public String getEmail() {
    return this.email;
  }

  public String getPassword() {
    return this.password;
  }

  public UsernamePasswordAuthenticationToken toCredentialsToken() {
    return new UsernamePasswordAuthenticationToken(
      this.email,
      this.password
    );
  }

}
