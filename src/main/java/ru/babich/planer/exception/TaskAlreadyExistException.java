package ru.babich.planer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TaskAlreadyExistException extends Throwable{

    public TaskAlreadyExistException(String message) {
        super(message);
    }
}
