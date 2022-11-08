package com.joaogabrielrc.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.*;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@EnableWebSecurity
public class SecurityConfiguration {

  @Autowired
  private AuthenticationService authenticationService;

  @Autowired
  private TokenService tokenService;

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
      .antMatchers(POST, "/api/**/auth").permitAll()
      .antMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html", "/**/api-docs").permitAll()
      .antMatchers("**/favicon.ico", "/css/**", "/images/**", "/js/**", "/webjars/**").permitAll()
      .antMatchers(DELETE, "/api/**/topics/*").hasRole("MANAGER")
      .anyRequest().authenticated()
      .and().csrf().disable()
      .sessionManagement().sessionCreationPolicy(STATELESS)
      .and().addFilterBefore(
        new TokenAuthenticationFilter(tokenService),
        UsernamePasswordAuthenticationFilter.class
      );
    return http.build();
  }

  @Bean
  public AuthenticationManager authenticationManager(
    AuthenticationConfiguration authConfiguration
  ) throws Exception {
    return authConfiguration.getAuthenticationManager();
  }

}
