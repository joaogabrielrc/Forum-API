package com.joaogabrielrc.auth;

import com.joaogabrielrc.auth.dto.LoginForm;
import com.joaogabrielrc.config.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private TokenService tokenService;

  @PostMapping
  public ResponseEntity<?> authenticate(
    @RequestBody @Valid
    LoginForm form
  ) {
    UsernamePasswordAuthenticationToken credentials = form.toCredentialsToken();
    try {
      Authentication authentication = this.authenticationManager.authenticate(credentials);
      String token = tokenService.generate(authentication);
      Map<String, String> body = new HashMap<>();
      body.put("token", token);
      return ResponseEntity.ok(body);
    } catch (AuthenticationException exception) {
      return ResponseEntity.badRequest().build();
    }
  }

}
