package com.example.forumapi.answer.dto;

import com.example.forumapi.user.User;
import com.example.forumapi.user.dto.UserDto;

import java.time.LocalDateTime;

public class AnswerDto {

  private final Long id;
  private final String message;
  private final UserDto author;
  private final LocalDateTime createdAt;

  public AnswerDto(
    Long id,
    String message,
    UserDto author,
    LocalDateTime createdAt
  ) {
    this.id = id;
    this.message = message;
    this.author = author;
    this.createdAt = createdAt;
  }

  public Long getId() {
    return this.id;
  }

  public String getMessage() {
    return this.message;
  }

  public UserDto getAuthor() {
    return this.author;
  }

  public LocalDateTime getCreatedAt() {
    return this.createdAt;
  }

}
