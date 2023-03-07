package com.example.librarymanager.exceptions;

import jakarta.persistence.NonUniqueResultException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TooManyCoursesReturnedException extends NonUniqueResultException {
        public TooManyCoursesReturnedException(String message){
            super(message);
        }
}
