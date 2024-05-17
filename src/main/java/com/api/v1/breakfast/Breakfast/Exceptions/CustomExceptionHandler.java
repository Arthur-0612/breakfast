package com.api.v1.breakfast.Breakfast.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {
	
	@ExceptionHandler(GlobalException.class)
    public ResponseEntity<StandardError> handleGlobalException(GlobalException ex) {
        var errorResponse = new StandardError(System.currentTimeMillis(), ex.getErrorCode(), ex.getMessage(), ex.getCode());

        return ResponseEntity.status(ex.getErrorCode()).body(errorResponse);
    }
	
	 @ExceptionHandler(MethodArgumentNotValidException.class)
	    public ResponseEntity<StandardError> validation(MethodArgumentNotValidException ex) {

	        ValidationError err = new ValidationError(System.currentTimeMillis(), HttpStatus.UNPROCESSABLE_ENTITY, "Validation Error", 1000);
	        for (FieldError x : ex.getBindingResult().getFieldErrors()) {
	            err.addError(x.getField(), x.getDefaultMessage());
	        }
	        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(err);
	    }
}
