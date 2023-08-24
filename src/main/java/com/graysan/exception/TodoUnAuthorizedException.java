package com.graysan.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// 401:Yetkisiz Giriş
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class TodoUnAuthorizedException extends RuntimeException {
    public TodoUnAuthorizedException(String message){
        super(message);
    }
}
