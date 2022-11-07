package com.joaogabrielrc.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.http.HttpMethod.*;

@EnableWebSecurity
public class SecurityConfiguration {

  @Autowired
  private AuthenticationService authenticationService;

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(authenticationService)
      .passwordEncoder(this.passwordEncoder());
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.authorizeRequests()
      .antMatchers(GET, "/api/**/topics").permitAll()
      .antMatchers(GET, "/api/**/topics/*").permitAll()
      .anyRequest().authenticated()
      .and().formLogin();
    return http.build();
  }

}
