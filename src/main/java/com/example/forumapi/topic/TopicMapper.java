package com.example.forumapi.topic;

import com.example.forumapi.answer.AnswerMapper;
import com.example.forumapi.answer.dto.AnswerDto;
import com.example.forumapi.course.Course;
import com.example.forumapi.course.CourseRepository;
import com.example.forumapi.topic.dto.TopicCreationDto;
import com.example.forumapi.topic.dto.TopicDetailDto;
import com.example.forumapi.topic.dto.TopicDto;
import com.example.forumapi.user.User;
import com.example.forumapi.user.UserMapper;
import com.example.forumapi.user.UserRepository;
import com.example.forumapi.user.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class TopicMapper {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private CourseRepository courseRepository;

  @Autowired
  private AnswerMapper answerMapper;

  @Autowired
  private UserMapper userMapper;

  public TopicDto toDto(Topic topic) {
    return new TopicDto(
      topic.getId(),
      topic.getTitle(),
      topic.getMessage(),
      topic.getStatus(),
      topic.getCreatedAt()
    );
  }

  public Optional<Topic> creationFormToTopic(TopicCreationDto topicForm) {
    String title = topicForm.getTitle().toLowerCase().trim();
    String message = topicForm.getMessage().toLowerCase().trim();
    Optional<User> author = userRepository.findById(1L);
    Long courseId = Long.valueOf(topicForm.getCourseId().trim());
    Optional<Course> course = courseRepository.findById(courseId);
    Topic topic = null;

    if (author.isPresent() && course.isPresent()) {
       topic = new Topic(
        title,
        message,
        author.get(),
        course.get()
      );
    }
    return Optional.ofNullable(topic);
  }

  public TopicDetailDto toDetailDto(Topic topic) {
    UserDto authorDto = this.userMapper.toDto(topic.getAuthor());
    List<AnswerDto> answerDtoList = topic.getAnswers().stream()
      .map(answerMapper::toDto)
      .collect(Collectors.toList());

    return new TopicDetailDto(
      topic.getId(),
      topic.getTitle(),
      topic.getMessage(),
      topic.getStatus(),
      authorDto,
      topic.getCourse(),
      answerDtoList,
      topic.getCreatedAt()
    );
  }

}
