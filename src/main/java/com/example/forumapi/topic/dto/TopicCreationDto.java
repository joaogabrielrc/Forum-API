package com.example.forumapi.topic.dto;

import javax.validation.constraints.NotEmpty;

public class TopicCreationDto {

  @NotEmpty
  private final String title;

  @NotEmpty
  private final String message;

  @NotEmpty
  private final String courseId;

  public TopicCreationDto(
    String title,
    String message,
    String courseId
  ) {
    this.title = title;
    this.message = message;
    this.courseId = courseId;
  }

  public String getTitle() {
    return this.title;
  }

  public String getMessage() {
    return this.message;
  }

  public String getCourseId() {
    return this.courseId;
  }

}
