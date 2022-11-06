package com.example.forumapi.topic;

import com.example.forumapi.answer.Answer;
import com.example.forumapi.course.Course;
import com.example.forumapi.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.example.forumapi.topic.TopicStatus.OPENED;
import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name="topics")
public class Topic {

  @Id
  @SequenceGenerator(
    name="topic_sequence",
    sequenceName="topic_sequence",
    allocationSize=1
  )
  @GeneratedValue(
    strategy=SEQUENCE,
    generator="topic_sequence"
  )
  private Long id;

  @Column(name="title")
  private String title;

  @Column(name="message")
  private String message;

  @Enumerated(EnumType.STRING)
  private TopicStatus status = OPENED;

  @ManyToOne
  private User author;

  @ManyToOne
  private Course course;

  @OneToMany(mappedBy="topic")
  private List<Answer> answers = new ArrayList<>();

  @Column(name="created_at")
  private LocalDateTime createdAt = LocalDateTime.now();

  private Topic() {
  }

  public Topic(String title, String message, User author, Course course) {
    this.title = title;
    this.message = message;
    this.author = author;
    this.course = course;
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

  public User getAuthor() {
    return this.author;
  }

  public Course getCourse() {
    return this.course;
  }

  public List<Answer> getAnswers() {
    return this.answers;
  }

  public LocalDateTime getCreatedAt() {
    return this.createdAt;
  }

}
