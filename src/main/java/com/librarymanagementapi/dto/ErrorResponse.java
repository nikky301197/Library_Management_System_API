package com.librarymanagementapi.dto;

import java.util.Date;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorResponse {

	
	String error;
	String message;
	HttpStatus httpStatus;
	Date timestamp;
	
	
	
}
