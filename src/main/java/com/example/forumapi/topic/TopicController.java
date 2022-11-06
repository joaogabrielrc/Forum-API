package com.example.forumapi.topic;

import com.example.forumapi.topic.dto.TopicDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/topics")
public class TopicController {

  @Autowired
  private TopicService topicService;

  @Autowired
  private TopicMapper topicMapper;

  @GetMapping
  public List<TopicDto> getList(String courseName) {
    List<Topic> topics = topicService.findAll(courseName);
    return topics.stream().map(topicMapper::toDto).collect(Collectors.toList());
  }

}
