package com.librarymanagementapi.exceptionHandling;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.librarymanagementapi.dto.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {

		ErrorResponse err = new ErrorResponse();
		err.setMessage(ex.getMessage());
		err.setError(ex.getClass().getSimpleName());
		err.setHttpStatus(HttpStatus.NOT_FOUND);
		err.setTimestamp(new Date());

		return new ResponseEntity(err, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(BindException.class)
	ResponseEntity<List<ErrorResponse>> handleBindException(BindException ex) {
		// Build custom error response with validation errors
		List<ErrorResponse> list = ex.getFieldErrors().stream()
				.map(err -> new ErrorResponse(ex.getClass().getSimpleName(), err.getDefaultMessage(),
						HttpStatus.BAD_REQUEST, new Date()))
				.collect(Collectors.toList());
		return new ResponseEntity<>(list, HttpStatus.BAD_REQUEST);
	}

}
