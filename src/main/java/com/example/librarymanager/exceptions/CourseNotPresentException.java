package com.example.librarymanager.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CourseNotPresentException extends RuntimeException{
    public CourseNotPresentException(String message){
        super(message);
    }
}
