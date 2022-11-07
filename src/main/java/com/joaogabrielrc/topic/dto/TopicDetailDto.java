package com.joaogabrielrc.topic.dto;

import com.joaogabrielrc.answer.dto.AnswerDto;
import com.joaogabrielrc.course.Course;
import com.joaogabrielrc.topic.TopicStatus;
import com.joaogabrielrc.user.dto.UserDto;

import java.time.LocalDateTime;
import java.util.List;

public class TopicDetailDto {

  private final Long id;
  private final String title;
  private final String message;
  private final TopicStatus status;
  private final UserDto author;
  private final Course course;
  private final List<AnswerDto> answers;
  private final LocalDateTime createdAt;

  public TopicDetailDto(
    Long id,
    String title,
    String message,
    TopicStatus status,
    UserDto author,
    Course course,
    List<AnswerDto> answers, LocalDateTime createdAt
  ) {
    this.id = id;
    this.title = title;
    this.message = message;
    this.status = status;
    this.author = author;
    this.course = course;
    this.answers = answers;
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

  public UserDto getAuthor() {
    return this.author;
  }

  public Course getCourse() {
    return this.course;
  }

  public LocalDateTime getCreatedAt() {
    return this.createdAt;
  }

  public List<AnswerDto> getAnswers() {
    return this.answers;
  }

}
