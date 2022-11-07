package com.joaogabrielrc.answer;

import com.joaogabrielrc.answer.dto.AnswerDto;
import com.joaogabrielrc.user.UserMapper;
import com.joaogabrielrc.user.dto.UserDto;
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
