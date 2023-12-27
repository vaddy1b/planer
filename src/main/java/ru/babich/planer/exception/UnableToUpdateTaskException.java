package ru.babich.planer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UnableToUpdateTaskException extends Throwable {

    public UnableToUpdateTaskException(String message) {
        super(message);
    }
}