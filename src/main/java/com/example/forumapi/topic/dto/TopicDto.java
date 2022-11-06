package com.example.forumapi.topic.dto;

import com.example.forumapi.topic.TopicStatus;

import java.time.LocalDateTime;

public class TopicDto {

  private final Long id;
  private final String title;
  private final String message;
  private final TopicStatus status;
  private final LocalDateTime createdAt;

  public TopicDto(Long id, String title, String message, TopicStatus status, LocalDateTime createdAt) {
    this.id = id;
    this.title = title;
    this.message = message;
    this.status = status;
    this.createdAt = createdAt;
  }

  public Long getId() {
    return this.id;
  }

  public String getTitle() {
    return this.title;
  }

  public String getMessage() {
    return this.message;
  }

  public TopicStatus getStatus() {
    return this.status;
  }

  public LocalDateTime getCreatedAt() {
    return this.createdAt;
  }

}
