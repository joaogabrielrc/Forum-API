package com.example.forumapi.answer;

import com.example.forumapi.answer.dto.AnswerDto;
import com.example.forumapi.user.UserMapper;
import com.example.forumapi.user.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AnswerMapper {

  @Autowired
  private UserMapper userMapper;

  public AnswerDto toDto(Answer answer) {
    UserDto authorDto = this.userMapper.toDto(answer.getAuthor());

    return new AnswerDto(
      answer.getId(),
      answer.getMessage(),
      authorDto,
      answer.getCreatedAt()
    );
  }

}
