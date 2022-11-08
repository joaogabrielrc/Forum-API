package com.joaogabrielrc;

import com.joaogabrielrc.course.Course;
import com.joaogabrielrc.course.CourseRepository;
import com.joaogabrielrc.topic.Topic;
import com.joaogabrielrc.topic.TopicRepository;
import com.joaogabrielrc.user.User;
import com.joaogabrielrc.user.UserRepository;
import com.joaogabrielrc.user.profile.Profile;
import com.joaogabrielrc.user.profile.ProfileRepository;
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
  private ProfileRepository profileRepository;

  @Autowired
  private CourseRepository courseRepository;

  @Autowired
  private TopicRepository topicRepository;

  @Bean
  CommandLineRunner commandLineRunner() {
    return args -> {
      Profile roleManager = new Profile("ROLE_MANAGER");
      profileRepository.save(roleManager);

      User employee = new User(
        "employee@mail.com",
        "$2a$10$AWK0qSo1IyeoyVtNSUJ02uW.bWcz01fA.Fv7Ki5XV6yOOr5IegMAq", //123456
        "user employee"
      );
      User manager = new User(
        "manager@mail.com",
        "$2a$10$AWK0qSo1IyeoyVtNSUJ02uW.bWcz01fA.Fv7Ki5XV6yOOr5IegMAq", //123456
        "user manager"
      );
      manager.addProfile(roleManager);
      userRepository.saveAll(List.of(employee, manager));

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
        employee,
        spring_boot
      );
      Topic topic2 = new Topic(
        "doubt with html tags",
        "the tags doesn't work",
        employee,
        html
      );
      topicRepository.saveAll(List.of(topic1, topic2));
    };
  }

}
