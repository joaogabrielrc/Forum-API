package com.example.forumapi.user.dto;

public class UserDto {

  private final Long id;
  private final String name;

  public UserDto(Long id, String name) {
    this.id = id;
    this.name = name;
  }

  public Long getId() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }

}
