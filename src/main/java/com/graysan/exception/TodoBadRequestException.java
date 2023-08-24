package com.graysan.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// 400:Kötü istek
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TodoBadRequestException extends RuntimeException{
    public TodoBadRequestException(String message){
        super(message);
    }

}