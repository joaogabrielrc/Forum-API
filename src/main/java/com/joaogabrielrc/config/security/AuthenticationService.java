package com.joaogabrielrc.config.security;

import com.joaogabrielrc.user.User;
import com.joaogabrielrc.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<User> user = this.userRepository.findByEmail(username);
    if (user.isPresent()) return user.get();
    throw new UsernameNotFoundException("Invalid credentials");
  }

}
