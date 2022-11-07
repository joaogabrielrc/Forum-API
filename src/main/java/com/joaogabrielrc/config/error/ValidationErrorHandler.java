package com.joaogabrielrc.config.error;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class ValidationErrorHandler {

  @Autowired
  private MessageSource messageSource;

  @ResponseStatus(code=BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public Map<String, String> handle(MethodArgumentNotValidException exception) {
    Map<String, String> result = new HashMap<>();
    List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
    fieldErrors.forEach(error -> {
      String message = messageSource.getMessage(error, LocaleContextHolder.getLocale());
      result.put(error.getField(), message);
    });
    return result;
  }

}
