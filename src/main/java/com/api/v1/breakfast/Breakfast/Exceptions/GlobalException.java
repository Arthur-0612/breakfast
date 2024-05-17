package com.api.v1.breakfast.Breakfast.Exceptions;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GlobalException extends RuntimeException{
	
    private HttpStatus errorCode;
    private Integer code;

    public GlobalException(String message, HttpStatus errorCode, Integer code) {
    	super(message);
        this.errorCode = errorCode;
        this.code = code;
    }
}
