package com.soccer.demo.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class IdUsedException extends RuntimeException{
    public IdUsedException(String msg) {
        super(msg);
    }

    public IdUsedException() {
    }
}
