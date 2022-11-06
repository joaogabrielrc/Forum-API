package com.example.forumapi.topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService {

  @Autowired
  private TopicRepository topicRepository;

  public List<Topic> findAll(String courseName) {
    if (courseName != null)
      return topicRepository.findByCourseName(courseName);
    return topicRepository.findAll();
  }

}
