package com.example.forumapi.topic;

import com.example.forumapi.topic.dto.TopicUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TopicService {

  private final TopicRepository topicRepository;

  @Autowired
  public TopicService(TopicRepository topicRepository) {
    this.topicRepository = topicRepository;
  }

  public List<Topic> getList(String courseName) {
    if (courseName != null)
      return this.topicRepository.findByCourseName(courseName);
    return this.topicRepository.findAll();
  }

  public void create(Topic topic) {
    this.topicRepository.save(topic);
  }

  public Optional<Topic> findById(Long topicId) {
    return this.topicRepository.findById(topicId);
  }

  public Optional<Topic> update(String topicId, TopicUpdateDto topicForm) {
    Long id = Long.valueOf(topicId);
    Topic topic = this.topicRepository.findById(id).orElse(null);
    if (topic != null) {
      if (topicForm.getTitle() != null)
        topic.setTitle(topicForm.getTitle().toLowerCase().trim());
      if (topicForm.getMessage() != null)
        topic.setMessage(topicForm.getMessage().toLowerCase().trim());
      if (topicForm.getStatus() != null)
        topic.setStatus(topicForm.getStatus());
      this.topicRepository.save(topic);
    }
    return Optional.ofNullable(topic);
  }

  public boolean delete(String topicId) {
    Long id = Long.valueOf(topicId);
    Optional<Topic> topic = this.topicRepository.findById(id);
    if (topic.isEmpty()) return false;
    this.topicRepository.deleteById(id);
    return true;
  }

}
