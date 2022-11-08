package com.joaogabrielrc.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

  @Autowired
  private UserRepository userRepository;

  @Test
  public void itShouldReturnAUserByEmail() {
    String userEmail = "employee@mail.com";
    Optional<User> user = userRepository.findByEmail(userEmail);
    assertTrue(user.isPresent());
  }

  @Test
  public void itShouldNotReturnAUserByEmail() {
    String userEmail = "fail@test.com";
    Optional<User> user = userRepository.findByEmail(userEmail);
    assertTrue(user.isEmpty());
  }

}