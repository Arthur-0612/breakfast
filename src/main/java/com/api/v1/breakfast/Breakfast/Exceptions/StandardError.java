package com.api.v1.breakfast.Breakfast.Exceptions;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StandardError {
	
	private Long timestamp;
	
    private HttpStatus httpStatus;
    
    private String message;
    
    private Integer code;

}