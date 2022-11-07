package com.joaogabrielrc.config.security;

import com.joaogabrielrc.user.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TokenAuthenticationFilter extends OncePerRequestFilter {

  private final TokenService tokenService;

  public TokenAuthenticationFilter(TokenService tokenService) {
    this.tokenService = tokenService;
  }

  @Override
  protected void doFilterInternal(
    HttpServletRequest request,
    HttpServletResponse response,
    FilterChain filterChain
  ) throws ServletException, IOException {
    String token = this.getToken(request);
    if (tokenService.isValid(token)) {
      this.authenticate(token);
    }
    filterChain.doFilter(request, response);
  }

  private void authenticate(String token) {
    User user = tokenService.getUser(token);
    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
      user,
      null,
      user.getAuthorities()
    );
    SecurityContextHolder.getContext().setAuthentication(authentication);
  }

  private String getToken(HttpServletRequest request) {
    String authorization = request.getHeader("Authorization");
    if (authorization == null || !authorization.startsWith("Bearer ")) {
      return null;
    }
    return authorization.split("Bearer ")[1];
  }

}
