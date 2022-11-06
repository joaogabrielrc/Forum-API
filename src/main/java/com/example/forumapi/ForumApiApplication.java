package com.example.forumapi;

import com.example.forumapi.course.Course;
import com.example.forumapi.course.CourseRepository;
import com.example.forumapi.topic.Topic;
import com.example.forumapi.topic.TopicRepository;
import com.example.forumapi.user.User;
import com.example.forumapi.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class ForumApiApplication {

  public static void main(String[] args) {
    SpringApplication.run(ForumApiApplication.class, args);
  }

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private TopicRepository topicRepository;

  @Bean
  CommandLineRunner commandLineRunner() {
    return args -> {
      User john_doe = new User(
        "john@mail.com",
        "1234",
        "john doe"
      );
			userRepository.save(john_doe);

			Course spring_boot = new Course(
				"spring boot framework",
				"programming"
			);

			Course html = new Course(
				"html 5",
				"front-end"
			);

			courseRepository.saveAll(List.of(spring_boot, html));

			Topic topic1 = new Topic(
				"doubt with the spring boot",
				"error to initialize the project",
				john_doe,
				spring_boot
			);

			Topic topic2 = new Topic(
				"doubt with html tags",
				"the tags doesn't work",
				john_doe,
				html
			);

			topicRepository.saveAll(List.of(topic1, topic2));
    };
  }

}
