package com.microservices.restfulwebservices.user;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {
        public UserNotFoundException(String message){
            super(message);    // Excepts a message and pass it to superclass RuntimeException
        }
}

