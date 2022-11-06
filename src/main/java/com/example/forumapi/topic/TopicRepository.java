package com.example.forumapi.topic;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicRepository extends JpaRepository<Topic, Long> {

  List<Topic> findByCourseName(String courseName);

}
