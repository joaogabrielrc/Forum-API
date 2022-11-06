package com.example.forumapi.user;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name="users", uniqueConstraints={
  @UniqueConstraint(name="users_email_unique", columnNames="email")
})
public class User {

  @Id
  @SequenceGenerator(
    name="user_sequence",
    sequenceName="user_sequence",
    allocationSize=1
  )
  @GeneratedValue(
    strategy=SEQUENCE,
    generator="user_sequence"
  )
  private Long id;

  @Column(name="email")
  private String email;

  @Column(name="password")
  private String password;

  @Column(name="name")
  private String name;

  private User() {
  }

  public User(String email, String password, String name) {
    this.email = email;
    this.password = password;
    this.name = name;
  }

  public Long getId() {
    return this.id;
  }

  public String getEmail() {
    return this.email;
  }

  public String getPassword() {
    return this.password;
  }

  public String getName() {
    return this.name;
  }

}
