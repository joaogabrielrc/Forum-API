package com.joaogabrielrc.topic.dto;

import com.joaogabrielrc.topic.TopicStatus;

public class TopicUpdateDto {

  private final String title;
  private final String message;
  private final TopicStatus status;

  public TopicUpdateDto(String title, String message, TopicStatus status) {
    this.title = title;
    this.message = message;
    this.status = status;
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

}
