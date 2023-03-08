package com.example.librarymanager.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BookNotPresentException extends RuntimeException{
    public BookNotPresentException(String message){
        super(message);
    }
}
