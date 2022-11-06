package com.example.forumapi.topic;

import com.example.forumapi.topic.dto.TopicDto;
import org.springframework.stereotype.Component;

@Component
public class TopicMapper {

  public TopicDto toDto(Topic topic) {
    return new TopicDto(
      topic.getId(),
      topic.getTitle(),
      topic.getMessage(),
      topic.getStatus(),
      topic.getCreatedAt()
    );
  }

}
