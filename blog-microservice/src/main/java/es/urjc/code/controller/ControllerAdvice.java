package es.urjc.code.controller;

import es.urjc.code.dto.ErrorDto;
import es.urjc.code.exception.MessageContainsSwearingException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto handleEntityNotFoundException(EntityNotFoundException ex) {

        return ErrorDto.builder()
                .code(404)
                .description("Entity not found")
                .name("ENTITY_NOT_FOUND_ERROR")
                .build();
    }

    @ExceptionHandler(MessageContainsSwearingException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto handleMessageContainsSwearingException(MessageContainsSwearingException ex) {

        return ErrorDto.builder()
                .code(400)
                .description(ex.getMessage())
                .name("MESSAGE_CONTAINS_SWEARING_EXCEPTION")
                .build();
    }
}
