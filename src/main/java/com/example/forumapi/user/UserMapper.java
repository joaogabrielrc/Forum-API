package com.example.forumapi.user;

import com.example.forumapi.user.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

  public UserDto toDto(User author) {
    return new UserDto(
      author.getId(),
      author.getName()
    );
  }

}
