package com.example.forumapi.topic;

import com.example.forumapi.topic.dto.TopicCreationDto;
import com.example.forumapi.topic.dto.TopicDetailDto;
import com.example.forumapi.topic.dto.TopicDto;
import com.example.forumapi.topic.dto.TopicUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;
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
    List<Topic> topics = topicService.getList(courseName);
    return topics.stream().map(topicMapper::toDto).collect(Collectors.toList());
  }

  @PostMapping
  public ResponseEntity<TopicDto> create(
    @RequestBody @Valid
    TopicCreationDto topicForm,
    UriComponentsBuilder uriBuilder
  ) {
    Optional<Topic> topic = topicMapper.creationFormToTopic(topicForm);
    if (topic.isEmpty()) return ResponseEntity.badRequest().build();
    topicService.create(topic.get());
    URI uri = uriBuilder.path("/api/v1/topics/{id}")
      .buildAndExpand(topic.get().getId())
      .toUri();
    TopicDto topicDto = topicMapper.toDto(topic.get());
    return ResponseEntity.created(uri).body(topicDto);
  }

  @GetMapping("/{id}")
  public ResponseEntity<TopicDetailDto> getDetail(@PathVariable("id") Long topicId) {
    Optional<Topic> topic = this.topicService.findById(topicId);
    if (topic.isEmpty()) return ResponseEntity.notFound().build();
    TopicDetailDto topicDto = this.topicMapper.toDetailDto(topic.get());
    return ResponseEntity.ok(topicDto);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<TopicDto> update(
    @PathVariable("id")
    String topicId,
    @RequestBody
    TopicUpdateDto topicForm
  ) {
    Optional<Topic> topic = this.topicService.update(topicId, topicForm);
    if (topic.isEmpty()) return ResponseEntity.notFound().build();
    TopicDto topicDto = this.topicMapper.toDto(topic.get());
    return ResponseEntity.ok(topicDto);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> delete(
    @PathVariable("id")
    String topicId
  ) {
    boolean topicExists = this.topicService.delete(topicId);
    if (!topicExists) return ResponseEntity.notFound().build();
    return ResponseEntity.noContent().build();
  }

}
