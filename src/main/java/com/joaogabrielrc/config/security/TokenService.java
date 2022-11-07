package com.joaogabrielrc.config.security;

import com.joaogabrielrc.user.User;
import com.joaogabrielrc.user.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

  @Value("${forum.jwt.expiration}")
  private String expiration;

  @Value("${forum.jwt.secret}")
  private String secret;

  @Autowired
  private UserRepository userRepository;

  private Date getExpirationDate(Date date) {
    long timeExpiration = Long.parseLong(this.expiration);
    long timeToday = date.getTime();
    return new Date(timeToday + timeExpiration);
  }

  public String generate(Authentication authentication) {
    User user = (User) authentication.getPrincipal();
    String userId = user.getId().toString();
    Date today = new Date();
    Date expirationDate = this.getExpirationDate(today);

    return Jwts.builder()
      .setIssuer("Forum API")
      .setSubject(userId)
      .setIssuedAt(today)
      .setExpiration(expirationDate)
      .signWith(SignatureAlgorithm.HS256, this.secret)
      .compact();
  }

  public boolean isValid(String token) {
    try {
      Jwts.parser()
        .setSigningKey(this.secret)
        .parseClaimsJws(token);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  public User getUser(String token) {
    Claims body = Jwts.parser()
      .setSigningKey(this.secret)
      .parseClaimsJws(token)
      .getBody();
    long userId = Long.parseLong(body.getSubject());
    return this.userRepository.findById(userId).get();
  }

}
