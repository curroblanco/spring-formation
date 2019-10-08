package es.urjc.code.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class MessageContainsSwearingException extends Exception {

    public MessageContainsSwearingException() {
    }

    public MessageContainsSwearingException(String message) {
        super(message);
    }
}