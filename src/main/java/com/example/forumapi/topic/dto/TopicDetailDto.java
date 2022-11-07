package com.example.forumapi.topic.dto;

import com.example.forumapi.answer.Answer;
import com.example.forumapi.answer.dto.AnswerDto;
import com.example.forumapi.course.Course;
import com.example.forumapi.topic.TopicStatus;
import com.example.forumapi.user.User;
import com.example.forumapi.user.dto.UserDto;

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
