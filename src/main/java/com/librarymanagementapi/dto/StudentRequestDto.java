package com.librarymanagementapi.dto;

import org.springframework.format.annotation.NumberFormat;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentRequestDto {
	private Long studentId;
	@NotEmpty(message = "Student name cannot be empty")
	private String studentName;
	@NotEmpty(message = "Email cannot be empty")
	@Email(message = "Required valid email id ")
	private String email;
	
	@Size(min = 10, max = 10 , message = "Phone number must be 10 digits only")
	@Digits(fraction = 0, integer = 10 , message = "Please enter only numeric value")
	private String phoneNo;

}
