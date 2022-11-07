package com.joaogabrielrc.course;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name="courses")
public class Course {

  @Id
  @SequenceGenerator(
    name="course_sequence",
    sequenceName="course_sequence",
    allocationSize=1
  )
  @GeneratedValue(
    strategy=SEQUENCE,
    generator="course_sequence"
  )
  private Long id;

  @Column(name="name")
  private String name;

  @Column(name="category")
  private String category;

  private Course() {
  }

  public Course(String name, String category) {
    this.name = name;
    this.category = category;
  }

  public Long getId() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }

  public String getCategory() {
    return this.category;
  }

}
