package com.joaogabrielrc.answer;

import com.joaogabrielrc.topic.Topic;
import com.joaogabrielrc.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name="answers")
public class Answer {

  @Id
  @SequenceGenerator(
    name="answer_sequence",
    sequenceName="answer_sequence",
    allocationSize=1
  )
  @GeneratedValue(
    strategy=SEQUENCE,
    generator="answer_sequence"
  )
  private Long id;

  @Column(name="message")
  private String message;

  @ManyToOne
  private Topic topic;

  @ManyToOne
  private User author;

  @Column(name="solution")
  private Boolean solution;

  @Column(name="created_at")
  private LocalDateTime createdAt = LocalDateTime.now();

  private Answer() {
  }

  public Answer(String message, Topic topic, User author) {
    this.message = message;
    this.topic = topic;
    this.author = author;
  }

  public Long getId() {
    return this.id;
  }

  public String getMessage() {
    return this.message;
  }

  public Topic getTopic() {
    return this.topic;
  }

  public User getAuthor() {
    return this.author;
  }

  public Boolean getSolution() {
    return this.solution;
  }

  public LocalDateTime getCreatedAt() {
    return this.createdAt;
  }

}
